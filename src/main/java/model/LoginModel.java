package model;

public class LoginModel {
    private long iD;
    private String username;
   private String passwordHash;
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
    public void setUsername(String username){
        this.username = username;
    }
    public void setiD(long iD){
        this.iD = iD;
    }
    public void setPasswordHash(String passwordHash){
        this.passwordHash = passwordHash;
    }

}
