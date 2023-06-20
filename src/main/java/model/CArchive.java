package model;

import utils.DateConverter;

import java.time.LocalDate;

/**
 * The CArchive class represents an archived Caregiver.
 * It stores information such as caregiver ID, first name, last name, telephone number, and the date of archiving.
 */
public class CArchive {
    private long cid;
    private String firstname;
    private String lastname;
    private String telephonenumber;
    private LocalDate archived_at;

    /**
     * Constructs a CArchive object with the specified customer ID, first name, last name,
     * telephone number, and the date of archiving.
     *
     * @param cid
     * @param firstname
     * @param lastname
     * @param telephonenumber
     * @param archived_at
     */
    public CArchive(long cid, String firstname, String lastname, String telephonenumber, LocalDate archived_at) {
        this.cid = cid;
        this.firstname = firstname;
        this.lastname = lastname;
        this.telephonenumber = telephonenumber;
        this.archived_at = archived_at;
    }

    /**
     * Constructs a CArchive object with the specified first name, last name,
     * telephone number, and the date of archiving.
     *
     * @param firstname
     * @param lastname
     * @param telephonenumber
     * @param archived_at
     */
    public CArchive(String firstname, String lastname, String telephonenumber, LocalDate archived_at) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.telephonenumber = telephonenumber;
        this.archived_at = archived_at;
    }

    /**
     *
     * @return the caregiver ID
     */
    public long getCid() {
        return cid;
    }

    /**
     *
     * @return the first name of the customer
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     *
     * @return the last name of the customer
     */
    public String getLastname() {
        return lastname;
    }

    /**
     *
     * @return the telephone number of the customer
     */
    public String getTelephonenumber() {
        return telephonenumber;
    }

    /**
     *
     * @return the date of archiving as a formatted string
     */
    public String getArchived_at() {
        return archived_at.toString();
    }

    /**
     *
     * @param cid sets the customer ID
     */
    public void setCid(long cid) {
        this.cid = cid;
    }

    /**
     *
     * @param firstname sets the first name
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     *
     * @param lastname sets the last name
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     *
     * @param telephonenumber sets the telephone number
     */
    public void setTelephonenumber(String telephonenumber) {
        this.telephonenumber = telephonenumber;
    }

    /**
     * Sets the date of archiving by converting the provided string representation to a LocalDate object.
     *
     * @param archived_at the date of archiving as a string in the format "yyyy-MM-dd"
     */
    public void setArchived_at(String archived_at) {
        LocalDate archive = DateConverter.convertStringToLocalDate(archived_at);
        this.archived_at = archive;
    }
}

