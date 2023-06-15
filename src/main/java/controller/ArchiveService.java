package controller;

import datastorage.ArchieveDAO;
import datastorage.ConnectionBuilder;
import model.Archieve;
import model.Treatment;
import utils.DateConverter;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Arrays;
import java.util.Date;

public class ArchiveService {
    private ArchieveDAO archieveDAO = new ArchieveDAO(ConnectionBuilder.getConnection());
    public Archieve convertTreatmentIntoArchive(Treatment t){
        LocalDate treatDate =  DateConverter.convertStringToLocalDate(t.getDate());
        LocalTime begin = DateConverter.convertStringToLocalTime(t.getBegin());
        LocalTime end = DateConverter.convertStringToLocalTime(t.getEnd());
        Archieve a = new Archieve(t.getTid(),t.getPid(),treatDate,begin,end,t.getDescription(),t.getRemarks(), LocalDate.now());
        return a;
    }

    public void checkDateForDelete() throws SQLException {
       List<Archieve> checkForDelete = archieveDAO.readAll();
       LocalDate actualDate = LocalDate.now();
       for(Archieve a : checkForDelete){
           LocalDate toCheckDate = getCorrectDateToCalc(DateConverter.convertStringToLocalDate(a.getArchived_at()));
           if(checkForTenYears(actualDate,toCheckDate)){
               archieveDAO.deleteById(a.getBid());
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
