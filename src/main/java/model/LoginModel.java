package model;

public class LoginModel {
    long iD;
    String username;
    String passwordHash;
    public LoginModel(String username,String passwordHash){
        this.username = username;
        this.passwordHash = passwordHash;
    }
    public LoginModel(long uid,String username,String passwordHash){
        this.iD = uid;
        this.username = username;
        this.passwordHash = passwordHash;
    }

    public String getUsername() {
        return username;
    }
    public String getPasswordHash(){return passwordHash;}
    public long getiD(){return iD;}

}
