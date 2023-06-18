package Service;

import datastorage.DAOFactory;
import model.LoginModel;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

public class LoginService {
PasswordService pwService = new PasswordService();
    public boolean login(String username,String password){
        try {
           List<LoginModel> list = DAOFactory.getDAOFactory().createLoginDAO().readAll();
           for(LoginModel user : list){
               if(user.getUsername().equals(username)){
                  if(user.getPasswordHash().equals(pwService.hashPW(password))){
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
