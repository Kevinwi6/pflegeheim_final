package Service;

import datastorage.DAOFactory;
import model.LoginModel;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

/**
 * The LoginService class provides methods for user login authentication.
 */
public class LoginService {
    PasswordService pwService = new PasswordService();

    /**
     * Authenticates a user login based on the provided username and password.
     *
     * @param username
     * @param password
     * @return true if the login is successful, false otherwise
     */
    public boolean login(String username, String password) {
        try {
            List<LoginModel> list = DAOFactory.getDAOFactory().createLoginDAO().readAll();
            for (LoginModel user : list) {
                if (user.getUsername().equals(username)) {
                    if (user.getPasswordHash().equals(pwService.hashPW(password))) {
                        return true;
                    }
                }
            }
        } catch (SQLException | NoSuchAlgorithmException e) {
            return false;
        }
        return false;
    }
}
