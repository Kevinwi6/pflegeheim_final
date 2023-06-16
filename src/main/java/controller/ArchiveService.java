package controller;

import datastorage.PArchiveDAO;
import datastorage.TArchieveDAO;
import datastorage.ConnectionBuilder;
import model.PArchive;
import model.Patient;
import model.TArchieve;
import model.Treatment;
import utils.DateConverter;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

//METHODDEN FÜR PARCHIVE MACHEN
public class ArchiveService {
    private TArchieveDAO TArchieveDAO = new TArchieveDAO(ConnectionBuilder.getConnection());
    private PArchiveDAO pArchiveDAO = new PArchiveDAO(ConnectionBuilder.getConnection());
    public TArchieve convertTreatmentIntoArchive(Treatment t){
        LocalDate treatDate =  DateConverter.convertStringToLocalDate(t.getDate());
        LocalTime begin = DateConverter.convertStringToLocalTime(t.getBegin());
        LocalTime end = DateConverter.convertStringToLocalTime(t.getEnd());
        TArchieve a = new TArchieve(t.getTid(),t.getPid(),treatDate,begin,end,t.getDescription(),t.getRemarks(), LocalDate.now());
        return a;
    }
    public PArchive convertPatientIntoArchive(Patient p){
        LocalDate dateofbirth = DateConverter.convertStringToLocalDate(p.getDateOfBirth());
        PArchive a = new PArchive(p.getPid(),p.getFirstName(),p.getSurname(),dateofbirth,p.getCareLevel(),p.getRoomnumber(),LocalDate.now());
        return a;
    }

    public void checkDateForDelete() throws SQLException {
       List<TArchieve> checkForDelete = TArchieveDAO.readAll();
       List<PArchive> checkPForDelte = pArchiveDAO.readAll();
       LocalDate actualDate = LocalDate.now();
       for(TArchieve a : checkForDelete){
           LocalDate toCheckDate = getCorrectDateToCalc(DateConverter.convertStringToLocalDate(a.getArchived_at()));
           if(checkForTenYears(actualDate,toCheckDate)){
               TArchieveDAO.deleteById(a.getBid());
           }
       }
       for(PArchive p : checkPForDelte){
           LocalDate toCheckDate = getCorrectDateToCalc(DateConverter.convertStringToLocalDate(p.getArchived_at()));
           if(checkForTenYears(actualDate,toCheckDate)){
               pArchiveDAO.deleteById(p.getPid());
           }
       }
    }
    public LocalDate getCorrectDateToCalc(LocalDate oldDate){
      return   oldDate.plusYears(1).withMonth(1).withDayOfMonth(1);
    }
    public boolean checkForTenYears(LocalDate actualDate,LocalDate toCheck){
        if(actualDate.getYear() - toCheck.getYear() >=10)return true;
        return false;
    }

}
