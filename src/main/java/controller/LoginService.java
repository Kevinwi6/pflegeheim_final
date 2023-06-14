package controller;

import datastorage.DAOFactory;
import model.LoginModel;

import java.sql.SQLException;
import java.util.List;

public class LoginService {
    public void login(){
    String password = "PasswordBox.Value";
    String username = "Username.Value";

        try {
           List<LoginModel> list = DAOFactory.getDAOFactory().createLoginDAO().readAll();
           for(LoginModel user : list){
               if(user.getUsername().equals(username)){
                   //Überpüfen ob der Password Hash passt
               }
           }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
