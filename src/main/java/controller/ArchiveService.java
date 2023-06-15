package controller;

import model.Archieve;
import model.Treatment;
import utils.DateConverter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

public class ArchiveService {

    public Archieve convertTreatmentIntoArchive(Treatment t){
        LocalDate treatDate =  DateConverter.convertStringToLocalDate(t.getDate());
        LocalTime begin = DateConverter.convertStringToLocalTime(t.getBegin());
        LocalTime end = DateConverter.convertStringToLocalTime(t.getEnd());
        Archieve a = new Archieve(t.getTid(),t.getPid(),treatDate,begin,end,t.getDescription(),t.getRemarks(), LocalDate.now());
        return a;
    }
}
