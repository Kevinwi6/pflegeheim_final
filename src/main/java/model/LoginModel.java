package model;

/**
 * Represents a User that can log in to the program
 */
public class LoginModel {
    private long iD;
    private String username;
    private String passwordHash;

    /**
     * Constructs a login model with the given username and password hash.
     *
     * @param username
     * @param passwordHash
     */
    //TODO Methode
    public LoginModel(String username, String passwordHash) {
        this.username = username;
        this.passwordHash = passwordHash;
    }

    /**
     * Constructs a login model with the given ID, username, and password hash.
     *
     * @param uid
     * @param username
     * @param passwordHash
     */
    public LoginModel(long uid, String username, String passwordHash) {
        this.iD = uid;
        this.username = username;
        this.passwordHash = passwordHash;
    }

    /**
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @return the password hash
     */
    public String getPasswordHash() {
        return passwordHash;
    }

    /**
     *
     * @return the user ID
     */
    public long getiD() {
        return iD;
    }

    /**
     *
     * @param username sets the username
     */
    //TODO Methode
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     *
     * @param iD sets the user ID
     */
    //TODO Methode
    public void setiD(long iD) {
        this.iD = iD;
    }

    /**
     *
     * @param passwordHash sets the password hash
     */
    //TODO Methode
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
}
