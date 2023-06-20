package model;

/**
 * The Caregiver class represents a caregiver in a program.
 */
public class Caregiver {
    private long cid;
    private String firstname;
    private String surename;
    private String phoneNumber;

    /**
     * Constructs a Caregiver object with the given first name, surname, and phone number.
     *
     * @param firstname
     * @param surename
     * @param phoneNumber
     */
    public Caregiver(String firstname, String surename, String phoneNumber){
        this.firstname = firstname;
        this.surename = surename;
        this.phoneNumber = phoneNumber;
    }

    /**
     * Constructs a Caregiver object with the given caregiver ID, first name, surname, and phone number.
     *
     * @param cid
     * @param firstname
     * @param surename
     * @param phoneNumber
     */
    public Caregiver(long cid, String firstname, String surename, String phoneNumber){
        this.cid = cid;
        this.firstname = firstname;
        this.surename = surename;
        this.phoneNumber = phoneNumber;
    }

    /**
     * Constructs an empty Caregiver object.
     */
    //TODO Methode
    public Caregiver() {

    }

    /**
     *
     * @return the caregiver ID
     */
    public long getCid(){return this.cid;}

    /**
     *
     * @return the first name of the caregiver
     */
    public String getFirstname(){return this.firstname;}

    /**
     *
     * @return the surname of the caregiver
     */
    public String getSurename(){return this.surename;}

    /**
     *
     * @return the phone number of the caregiver
     */
    public String getPhoneNumber(){return this.phoneNumber;}

    /**
     *
     * @param firstname sets the first name
     */
    public void setFirstname(String firstname){this.firstname = firstname;}

    /**
     *
     * @param surename sets the surename
     */
    public void setSurename(String surename){this.surename = surename;}

    /**
     *
     * @param phoneNumber sets the phone number´´
     */
    public void setPhoneNumber(String phoneNumber){this.phoneNumber = phoneNumber;}

}