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
    private TArchiveDAO tArchieveDAO = new TArchiveDAO(ConnectionBuilder.getConnection());
    private PArchiveDAO pArchiveDAO = new PArchiveDAO(ConnectionBuilder.getConnection());
    private CArchiveDAO cArchiveDAO = new CArchiveDAO(ConnectionBuilder.getConnection());
    private TreatmentDAO treatmentDAO = new TreatmentDAO(ConnectionBuilder.getConnection());


    /**
     * Converts a Treatment object into a TArchive object (Archived Treatment object).
     *
     * @param t Treatment
     * @return the converted TArchive object
     */
    public TArchive convertTreatmentIntoArchive(Treatment t) throws SQLException {
        LocalDate treatDate = DateConverter.convertStringToLocalDate(t.getDate());
        LocalTime begin = DateConverter.convertStringToLocalTime(t.getBegin());
        LocalTime end = DateConverter.convertStringToLocalTime(t.getEnd());
        TArchive a = new TArchive(t.getTid(),t.getPatientName(t.getPid()),treatDate,begin,end,t.getDescription(),t.getRemarks(), LocalDate.now());
        return a;
    }
    public CArchive convertCaregiverIntoArchive(Caregiver c){
        CArchive a = new CArchive(c.getCid(),c.getFirstname(),c.getSurename(),c.getPhoneNumber(),LocalDate.now());
        return a;
    }

        /**
         * Converts a Patient object into a PArchive object.
         *
         * @param p Patient
         * @return the converted PArchive object
         */
    public PArchive convertPatientIntoArchive(Patient p){
        LocalDate dateofbirth = DateConverter.convertStringToLocalDate(p.getDateOfBirth());
        PArchive a = new PArchive(p.getPid(),p.getFirstName(),p.getSurname(),dateofbirth,p.getCareLevel(),p.getRoomnumber(),LocalDate.now());
        return a;
    }


    /**
     * Checks and deletes old data in the archives that are older than 10 years.
     */
    public void checkDateForDelete() {

        try {
            List<TArchive>checkTForDelete = tArchieveDAO.readAll();
            List<PArchive> checkPForDelte = pArchiveDAO.readAll();
            List<CArchive> checkCForDelete = cArchiveDAO.readAll();
            List<Treatment> checkTNForDelete = treatmentDAO.readAll();
            LocalDate actualDate = LocalDate.now();
            for(TArchive a : checkTForDelete){
                LocalDate toCheckDate = getCorrectDateToCalc(DateConverter.convertStringToLocalDate(a.getArchived_at()));
                if(checkForTenYears(actualDate,toCheckDate)){
                    tArchieveDAO.deleteById(a.getBid());
                }
            }
            for(PArchive p : checkPForDelte){
                LocalDate toCheckDate = getCorrectDateToCalc(DateConverter.convertStringToLocalDate(p.getArchived_at()));
                if(checkForTenYears(actualDate,toCheckDate)){
                    pArchiveDAO.deleteById(p.getPid());
                }
            }
            for(CArchive c : checkCForDelete){
                LocalDate toCheckDate = getCorrectDateToCalc(DateConverter.convertStringToLocalDate(c.getArchived_at()));
                if(checkForTenYears(actualDate,toCheckDate)){
                    cArchiveDAO.deleteById(c.getCid());
                }
            }
            for(Treatment t : checkTNForDelete){
                if(checkForTenYears(actualDate,DateConverter.convertStringToLocalDate(t.getDate()))){
                    treatmentDAO.deleteByPid(t.getPid());
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public LocalDate getCorrectDateToCalc(LocalDate oldDate){
      return   oldDate.plusYears(1).withMonth(1).withDayOfMonth(1);
    }
    public boolean checkForTenYears(LocalDate actualDate,LocalDate toCheck){
        if(actualDate.getYear() - toCheck.getYear() >=11)return true;
        return false;
    }


}
