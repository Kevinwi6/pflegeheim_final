package model;

public class Caregiver {
    private long cid;
    private String firstname;
    private String surename;
    private String phoneNumber;

    public Caregiver(String firstname, String surename, String phoneNumber){
        this.firstname = firstname;
        this.surename = surename;
        this.phoneNumber = phoneNumber;
    }

    public Caregiver(long cid, String firstname, String surename, String phoneNumber){
        this.cid = cid;
        this.firstname = firstname;
        this.surename = surename;
        this.phoneNumber = phoneNumber;
    }

    public Caregiver() {

    }

    public long getCid(){return this.cid;}
    public String getFirstname(){return this.firstname;}
    public String getSurename(){return this.surename;}
    public String getPhoneNumber(){return this.phoneNumber;}

    public void setFirstname(String firstname){this.firstname = firstname;}
    public void setSurename(String surename){this.surename = surename;}
    public void setPhoneNumber(String phoneNumber){this.phoneNumber = phoneNumber;}

}
