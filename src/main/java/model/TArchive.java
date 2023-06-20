package model;

import datastorage.ConnectionBuilder;
import datastorage.PatientDAO;
import utils.DateConverter;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * The TArchive class represents the Archived Treatment object.
 */
public class TArchive {
    private long bid;
    private long pid;
    private LocalDate date;
    private LocalTime begin;
    private LocalTime end;
    private String description;
    private String remarks;
    private LocalDate archived_at;
    private String patientName;
    private PatientDAO patientDAO = new PatientDAO(ConnectionBuilder.getConnection());

    /**
     * Constructs a TArchive object (treatment archive object) with the given patient ID, date, begin time, end time,
     * description, remarks, and the date it was archived.
     *
     * @param pid
     * @param date
     * @param begin
     * @param end
     * @param description
     * @param remarks
     * @param archived_at
     */
    public TArchive(String patientName, LocalDate date, LocalTime begin, LocalTime end, String description, String remarks, LocalDate archived_at){
        this.patientName = patientName;
        this.archived_at = archived_at;
        this.date = date;
        this.end = end;
        this.begin = begin;
        this.description = description;
        this.remarks = remarks;
    }

    /**
     * Constructs a TArchive object with the given treatment ID, block ID, date, begin time, end time,
     * description, remarks, and the date it was archived.
     *
     * @param bid
     * @param pid
     * @param date
     * @param begin
     * @param end
     * @param description
     * @param remarks
     * @param archived_at
     */
    public TArchive(long bid, String patientName, LocalDate date, LocalTime begin, LocalTime end, String description, String remarks, LocalDate archived_at){
        this.bid = bid;
        this.patientName = patientName;
        this.archived_at = archived_at;
        this.date = date;
        this.end = end;
        this.begin = begin;
        this.description = description;
        this.remarks = remarks;
    }

    /**
     *
     * @return the blocked ID (primary key for the database)
     */
    public long getBid() {
        return bid;
    }

    public String getPatientName(long pid) throws SQLException {
        Patient p = patientDAO.read(pid);
        if(p!=null)
        patientName = p.getFirstName() +" "+p.getSurname();
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    /**
     *
     * @return the patient ID
     */

    public long getPid() {
        return this.pid;
    }

    /**
     *
     * @return the date of the treatment in string format (yyyy-MM-dd)
     */
    public String getDate() {
        return date.toString();
    }

    /**
     *
     * @return the begin time of the treatment in string format (HH:mm)
     */
    public String getBegin() {
        return begin.toString();
    }

    /**
     *
     * @return the end time of the treatment in string format (HH:mm)
     */
    public String getEnd() {
        return end.toString();
    }

    /**
     *
     * @return the date the treatment was archived in string format (yyyy-MM-dd)
     */
    public String getArchived_at(){
        return archived_at.toString();
    }

    /**
     *
     * @param archived_at the date the treatment was archived in string format (yyyy-MM-dd)
     */
    public void setArchived_at(String archived_at){
        LocalDate archive = DateConverter.convertStringToLocalDate(archived_at);
        this.archived_at = archive;
    }

    /**
     *
     * @param s_date the date of the treatment in string format (yyyy-MM-dd)
     */
    public void setDate(String s_date) {
        LocalDate date = DateConverter.convertStringToLocalDate(s_date);
        this.date = date;
    }

    /**
     *
     * @param begin the begin time of the treatment in string format (HH:mm)
     */
    //TODO Methode
    public void setBegin(String begin) {
        LocalTime time = DateConverter.convertStringToLocalTime(begin);
        this.begin = time;
    }

    /**
     *
     * @param end the end time of the treatment in string format (HH:mm)
     */
    public void setEnd(String end) {
        LocalTime time = DateConverter.convertStringToLocalTime(end);
        this.end = time;
    }

    /**
     *
     * @return the description of the treatment
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description sets the description of the treatment
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return the remarks of the treatment
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     *
     * @param remarks sets the remarks of the treatment
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
