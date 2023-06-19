package model;

import utils.DateConverter;

import java.time.LocalDate;

public class CArchive {
    private long cid;
    private String firstname;
    private String lastname;
    private String telephonenumber;
    private LocalDate archived_at;

    public CArchive(long cid,String firstname,String lastname,String telephonenumber,LocalDate archived_at){
        this.cid = cid;
        this.firstname =firstname;
        this.lastname = lastname;
        this.telephonenumber = telephonenumber;
        this.archived_at = archived_at;
    }
    public CArchive(String firstname,String lastname,String telephonenumber,LocalDate archived_at){
        this.firstname =firstname;
        this.lastname = lastname;
        this.telephonenumber = telephonenumber;
        this.archived_at = archived_at;
    }

    public long getCid() {
        return cid;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getTelephonenumber() {
        return telephonenumber;
    }

    public String getArchived_at() {
        return archived_at.toString();
    }

    public void setCid(long cid) {
        this.cid = cid;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setTelephonenumber(String telephonenumber) {
        this.telephonenumber = telephonenumber;
    }

    public void setArchived_at(String archived_at) {
        LocalDate archive = DateConverter.convertStringToLocalDate(archived_at);
        this.archived_at = archive;
    }
}
