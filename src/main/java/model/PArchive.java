package model;

import utils.DateConverter;

import java.time.LocalDate;

public class PArchive extends Person {
    private long pid;
    private LocalDate dateOfBirth;
    private int careLevel;
    private String roomnumber;
    private LocalDate archived_at;

    public PArchive(String firstName, String surname) {
        super(firstName, surname);
    }
    public PArchive(long pid, String firstName, String surname, LocalDate dateOfBirth, int careLevel, String roomnumber,LocalDate archived_at) {
        super(firstName, surname);
        this.pid = pid;
        this.dateOfBirth = dateOfBirth;
        this.careLevel = careLevel;
        this.roomnumber = roomnumber;
        this.archived_at = archived_at;
    }
    public PArchive(String firstName, String surname, LocalDate dateOfBirth, int careLevel, String roomnumber,LocalDate archived_at) {
        super(firstName, surname);
        this.dateOfBirth = dateOfBirth;
        this.careLevel = careLevel;
        this.roomnumber = roomnumber;
        this.archived_at = archived_at;
    }

    public long getPid() {
        return pid;
    }

    public String getDateOfBirth() {
        return dateOfBirth.toString();
    }

    public int getCareLevel() {
        return careLevel;
    }

    public String getRoomnumber() {
        return roomnumber;
    }

    public void setCareLevel(int careLevel) {
        this.careLevel = careLevel;
    }

    public void setDateOfBirth(String dateOfBirth) {
        LocalDate birthday = DateConverter.convertStringToLocalDate(dateOfBirth);
        this.dateOfBirth = birthday;
    }
    public String getArchived_at(){return archived_at.toString();}

    public void setArchived_at(String archived_at){
        LocalDate archive = DateConverter.convertStringToLocalDate(archived_at);
        this.archived_at = archive;
    }

    public void setRoomnumber(String roomnumber) {
        this.roomnumber = roomnumber;
    }
}
