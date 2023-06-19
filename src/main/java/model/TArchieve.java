package model;

import datastorage.ConnectionBuilder;
import datastorage.PatientDAO;
import utils.DateConverter;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public class TArchieve {
    private PatientDAO patientDAO = new PatientDAO(ConnectionBuilder.getConnection());
    private long bid;
    private long pid;
    private String patientName;
    private LocalDate date;
    private LocalTime begin;
    private LocalTime end;
    private String description;
    private String remarks;
    private LocalDate archived_at;

    public TArchieve(String Patientname,LocalDate date, LocalTime begin, LocalTime end, String description, String remarks, LocalDate archived_at){
        this.patientName = Patientname;
        this.archived_at = archived_at;
        this.date = date;
        this.end = end;
        this.begin = begin;
        this.description = description;
        this.remarks = remarks;
    }

    public TArchieve(long bid, String patientName, LocalDate date, LocalTime begin, LocalTime end, String description, String remarks, LocalDate archived_at){
    this.bid = bid;
    this.patientName = patientName;
    this.archived_at = archived_at;
    this.date = date;
    this.end = end;
    this.begin = begin;
    this.description = description;
    this.remarks = remarks;
    }

    public String getPatientName(long pid) throws SQLException {
        Patient p = patientDAO.read(pid);
        if(p!=null)
        patientName = p.getFirstName() + " " + p.getSurname();
        return patientName;
    }

    public void setPatientName(String patientName){
       this.patientName = patientName;
    }
    public long getBid() {
        return bid;
    }

    public long getPid() {
        return this.pid;
    }

    public String getDate() {
        return date.toString();
    }

    public String getBegin() {
        return begin.toString();
    }

    public String getEnd() {
        return end.toString();
    }
    public String getArchived_at(){return archived_at.toString();}

    public void setArchived_at(String archived_at){
        LocalDate archive = DateConverter.convertStringToLocalDate(archived_at);
        this.archived_at = archive;
    }
    public void setDate(String s_date) {
        LocalDate date = DateConverter.convertStringToLocalDate(s_date);
        this.date = date;
    }

    public void setBegin(String begin) {
        LocalTime time = DateConverter.convertStringToLocalTime(begin);
        this.begin = time;
    }

    public void setEnd(String end) {
        LocalTime time = DateConverter.convertStringToLocalTime(end);
        this.end = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

}
