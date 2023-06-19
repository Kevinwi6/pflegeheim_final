package model;

import datastorage.ConnectionBuilder;
import datastorage.PatientDAO;
import utils.DateConverter;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * The Treatment class represents a treatment in the system.
 * It stores information such as treatment ID, project ID, caregiver ID, date, begin time,
 * end time, description, and remarks.
 */
public class Treatment {
    private long tid;
    PatientDAO patientDAO = new PatientDAO(ConnectionBuilder.getConnection());
    private long pid;
    private String patientName;
    private long cid;
    private LocalDate date;
    private LocalTime begin;
    private LocalTime end;
    private String description;
    private String remarks;
    private Caregiver caregiver;

    /**
     * Constructs a Treatment object with the given patient ID, caregiver ID, date, begin time,
     * end time, description and remarks.
     *
     * @param pid
     * @param date
     * @param begin
     * @param end
     * @param description
     * @param remarks
     */
    public Treatment(long pid, long cid, LocalDate date, LocalTime begin, LocalTime end,
                     String description, String remarks, Caregiver caregiver) {
        this.cid = cid;
        this.pid = pid;
        this.date = date;
        this.begin = begin;
        this.end = end;
        this.description = description;
        this.remarks = remarks;
        this.caregiver = caregiver;
    }

    /**
     * Constructs a Treatment object with the given treatment ID, patient ID, caregiver ID,
     * date, begin time, end time, description, and remarks.
     *
     * @param tid
     * @param pid
     * @param cid
     * @param date
     * @param begin
     * @param end
     * @param description
     * @param remarks
     */
    public Treatment(long tid, long pid, long cid, LocalDate date, LocalTime begin, LocalTime end,
                     String description, String remarks) {
        this.tid = tid;
        this.pid = pid;
        this.cid = cid;
        this.date = date;
        this.begin = begin;
        this.end = end;
        this.description = description;
        this.remarks = remarks;
    }
    public String getPatientName(long pid) throws SQLException {
        Patient p = patientDAO.read(pid);
        return patientName = p.getFirstName() +" "+ p.getSurname();
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    /**
     *
     * @return the caregiver ID of the treatment
     */
    public long getCid() {
        return cid;
    }

    /**
     *
     * @param cid sets the caregiver ID of the treatment
     */
    public void setCid(long cid) {
        this.cid = cid;
    }

    /**
     *
     * @return the treatment ID
     */
    public long getTid() {
        return tid;
    }

    /**
     *
     * @return sets the patient ID of the treatment
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
     * @param s_date sets the date of the treatment in string format (yyyy-MM-dd)
     */
    public void setDate(String s_date) {
        LocalDate date = DateConverter.convertStringToLocalDate(s_date);
        this.date = date;
    }

    /**
     *
     * @param begin sets the begin time of the treatment in string format (HH:mm)
     */
    public void setBegin(String begin) {
        LocalTime time = DateConverter.convertStringToLocalTime(begin);
        this.begin = time;
    }

    /**
     * Sets the end time of the treatment.
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

    /**
     *
     * @return a string representation of the Treatment object
     */
    public String toString() {
        return "\nBehandlung" + "\nTID: " + this.tid +
                "\nPID: " + this.pid +
                "\nDate: " + this.date +
                "\nBegin: " + this.begin +
                "\nEnd: " + this.end +
                "\nDescription: " + this.description +
                "\nRemarks: " + this.remarks + "\n";
    }
}