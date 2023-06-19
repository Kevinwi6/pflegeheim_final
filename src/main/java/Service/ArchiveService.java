package Service;

import datastorage.*;
import model.*;
import utils.DateConverter;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

//METHODDEN FÜR PARCHIVE MACHEN
/**
 * The ArchiveService class provides methods for converting treatments and patients into the P- and Tarchive objects,
 * checking if patient data or treatment data is expired and deleting these data in the archives,
 * blocking treatments by patient ID, and retrieving treatments with a given patient ID.
 */
public class ArchiveService {
    private TArchiveDAO TArchiveDAO = new TArchiveDAO(ConnectionBuilder.getConnection());
    private PArchiveDAO pArchiveDAO = new PArchiveDAO(ConnectionBuilder.getConnection());

    /**
     * Converts a Treatment object into a TArchive object.
     *
     * @param t Treatment
     * @return the converted TArchive object
     */
    public TArchive convertTreatmentIntoArchive(Treatment t) {
        LocalDate treatDate = DateConverter.convertStringToLocalDate(t.getDate());
        LocalTime begin = DateConverter.convertStringToLocalTime(t.getBegin());
        LocalTime end = DateConverter.convertStringToLocalTime(t.getEnd());
        TArchive a = new TArchive(t.getTid(), t.getPid(), treatDate, begin, end,
                t.getDescription(), t.getRemarks(), LocalDate.now());
        return a;
    }

    /**
     * Converts a Patient object into a PArchive object.
     *
     * @param p Patient
     * @return the converted PArchive object
     */
    public PArchive convertPatientIntoArchive(Patient p) {
        LocalDate dateofbirth = DateConverter.convertStringToLocalDate(p.getDateOfBirth());
        PArchive a = new PArchive(p.getPid(), p.getFirstName(), p.getSurname(), dateofbirth,
                p.getCareLevel(), p.getRoomnumber(), LocalDate.now());
        return a;
    }

    public CArchive convertCaregiverIntoArchive(Caregiver c){
        CArchive a = new CArchive(c.getCid(),c.getFirstname(),c.getSurename(),c.getPhoneNumber(),LocalDate.now());
        return a;
    }

    /**
     * Checks and deletes old data in the archives that are older than 10 years.
     */
    public void checkDateForDelete() {
        List<TArchive> checkForDelete = null;
        try {
            checkForDelete = TArchiveDAO.readAll();
            List<PArchive> checkPForDelte = pArchiveDAO.readAll();
            LocalDate actualDate = LocalDate.now();
            for (TArchive a : checkForDelete) {
                LocalDate toCheckDate = getCorrectDateToCalc(DateConverter.convertStringToLocalDate(a.getArchived_at()));
                if (checkForTenYears(actualDate, toCheckDate)) {
                    TArchiveDAO.deleteById(a.getBid());
                }
            }
            for (PArchive p : checkPForDelte) {
                LocalDate toCheckDate = getCorrectDateToCalc(DateConverter.convertStringToLocalDate(p.getArchived_at()));
                if (checkForTenYears(actualDate, toCheckDate)) {
                    pArchiveDAO.deleteById(p.getPid());
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns the new date. The old date get round up to the next year to the 1. January
     *
     * @param oldDate
     * @return the correct date to calculate
     */
    public LocalDate getCorrectDateToCalc(LocalDate oldDate) {
        return oldDate.plusYears(1).withMonth(1).withDayOfMonth(1);
    }

    /**
     * Checks if the difference between the actual date and the date to check is equal to or greater than 10 years.
     *
     * @param actualDate the actual date
     * @param toCheck    the date to check
     * @return true if the difference is equal to or greater than 10 years, false otherwise
     */
    public boolean checkForTenYears(LocalDate actualDate, LocalDate toCheck) {
        return actualDate.getYear() - toCheck.getYear() >= 10;
    }

    /**
     * Adds all treatments to the archive by patient ID.
     *
     * @param pID patient ID
     */
    public void blockAllTreatmentsByPatientID(long pID) {
        List<Treatment> archivedTreatmentsList = getListOfTreatmentsWithGivenID(pID);
        TArchiveDAO tADAO = DAOFactory.getDAOFactory().createTArchiveDAO();

        for (Treatment treatment : archivedTreatmentsList) {
            try {
                tADAO.create(convertTreatmentIntoArchive(treatment));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Returns a list of treatments with a given patient ID.
     *
     * @param pID patient ID
     * @return a list of treatments with the given patient ID
     */
    public List<Treatment> getListOfTreatmentsWithGivenID(long pID) {
        List<Treatment> treatmentsList = new ArrayList<>();
        TreatmentDAO tDAO = DAOFactory.getDAOFactory().createTreatmentDAO();

        try {
            treatmentsList.add(tDAO.read(pID));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return treatmentsList;
    }
}

