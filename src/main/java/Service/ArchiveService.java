package Service;

import datastorage.CArchiveDAO;
import datastorage.PArchiveDAO;
import datastorage.TArchieveDAO;
import datastorage.ConnectionBuilder;
import model.*;
import utils.DateConverter;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

//METHODDEN FÜR PARCHIVE MACHEN
public class ArchiveService {
    private TArchieveDAO tArchieveDAO = new TArchieveDAO(ConnectionBuilder.getConnection());
    private PArchiveDAO pArchiveDAO = new PArchiveDAO(ConnectionBuilder.getConnection());
    private CArchiveDAO cArchiveDAO = new CArchiveDAO(ConnectionBuilder.getConnection());
    public TArchieve convertTreatmentIntoArchive(Treatment t) throws SQLException {
        LocalDate treatDate =  DateConverter.convertStringToLocalDate(t.getDate());
        LocalTime begin = DateConverter.convertStringToLocalTime(t.getBegin());
        LocalTime end = DateConverter.convertStringToLocalTime(t.getEnd());
        TArchieve a = new TArchieve(t.getTid(),t.getPatientName(t.getPid()),treatDate,begin,end,t.getDescription(),t.getRemarks(), LocalDate.now());
        return a;
    }
    public CArchive convertCaregiverIntoArchive(Caregiver c){
        CArchive a = new CArchive(c.getCid(),c.getFirstname(),c.getSurename(),c.getPhoneNumber(),LocalDate.now());
        return a;
    }
    public PArchive convertPatientIntoArchive(Patient p){
        LocalDate dateofbirth = DateConverter.convertStringToLocalDate(p.getDateOfBirth());
        PArchive a = new PArchive(p.getPid(),p.getFirstName(),p.getSurname(),dateofbirth,p.getCareLevel(),p.getRoomnumber(),LocalDate.now());
        return a;
    }

    public void checkDateForDelete() {

        try {
            List<TArchieve>checkTForDelete = tArchieveDAO.readAll();
            List<PArchive> checkPForDelte = pArchiveDAO.readAll();
            List<CArchive> checkCForDelete = cArchiveDAO.readAll();
            LocalDate actualDate = LocalDate.now();
            for(TArchieve a : checkTForDelete){
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
