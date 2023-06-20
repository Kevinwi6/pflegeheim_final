package model;

import utils.DateConverter;
import java.time.LocalDate;

/**
 * Represents an archived patient.
 */
public class PArchive extends Person {
    private long pid;
    private LocalDate dateOfBirth;
    private String careLevel;
    private String roomnumber;
    private LocalDate archived_at;

    /**
     * Constructs a PArchive (Archived Person) object with the given first name and surname.
     *
     * @param firstName
     * @param surname
     */
    //TODO brauchen wir diesen Konstruktor?
    public PArchive(String firstName, String surname) {
        super(firstName, surname);
    }

    /**
     * Constructs a PArchived (Archived Person) object with the given parameters.
     *
     * @param pid
     * @param firstName
     * @param surname
     * @param dateOfBirth
     * @param careLevel
     * @param roomnumber
     * @param archived_at
     */
    public PArchive(long pid, String firstName, String surname, LocalDate dateOfBirth, String careLevel, String roomnumber, LocalDate archived_at) {
        super(firstName, surname);
        this.pid = pid;
        this.dateOfBirth = dateOfBirth;
        this.careLevel = careLevel;
        this.roomnumber = roomnumber;
        this.archived_at = archived_at;
    }

    /**
     * Constructs a PArchive object with the given parameters.
     *
     * @param firstName
     * @param surname
     * @param dateOfBirth
     * @param careLevel
     * @param roomnumber
     * @param archived_at
     */
    //TODO brauchen wir diesen Konstruktor?
    public PArchive(String firstName, String surname, LocalDate dateOfBirth, String careLevel, String roomnumber, LocalDate archived_at) {
        super(firstName, surname);
        this.dateOfBirth = dateOfBirth;
        this.careLevel = careLevel;
        this.roomnumber = roomnumber;
        this.archived_at = archived_at;
    }

    /**
     *
     * @return the person ID
     */
    public long getPid() {
        return pid;
    }

    /**
     *
     * @return the date of birth as a string
     */
    public String getDateOfBirth() {
        return dateOfBirth.toString();
    }

    /**
     *
     * @return the care level
     */
    public String getCareLevel() {
        return careLevel;
    }

    /**
     *
     *  @return the room number
     */
    public String getRoomnumber() {
        return roomnumber;
    }

    /**
     * Sets the care level of the person.
     *
     * @param careLevel the new care level
     */
    //TODO brauchen wir die Methode?
    public void setCareLevel(String careLevel) {
        this.careLevel = careLevel;
    }

    /**
     *
     * @return the archived date
     */
    public String getArchived_at() {
        return archived_at.toString();
    }

    /**
     *
     * @param archived_at the archived date as a string in the format "YYYY-MM-DD"
     */
    //TODO brauchen wir diese Methode?
    public void setArchived_at(String archived_at) {
        LocalDate archive = DateConverter.convertStringToLocalDate(archived_at);
        this.archived_at = archive;
    }

    /**
     *
     * @param roomnumber sets the new room number
     */
    //TODO brauchen wir diese Methode?
    public void setRoomnumber(String roomnumber) {
        this.roomnumber = roomnumber;
    }
}