package model;

public class Caregiver {
    private long cid;
    private String firstname;
    private String lastname;
    private String phoneNumber;

    public Caregiver(String firstname,String lastname,String phoneNumber){
        this.firstname = firstname;
        this.lastname = lastname;
        this.phoneNumber = phoneNumber;
    }

    public Caregiver(long cid,String firstname,String lastname,String phoneNumber){
        this.cid = cid;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phoneNumber = phoneNumber;
    }

    public long getCid(){return this.cid;}
    public String getFirstname(){return this.firstname;}
    public String getLastname(){return this.lastname;}
    public String getPhoneNumber(){return this.phoneNumber;}

    public void setFirstname(String firstname){this.firstname = firstname;}
    public void setLastname(String lastname){this.lastname = lastname;}
    public void setPhoneNumber(String phoneNumber){this.phoneNumber = phoneNumber;}

}
