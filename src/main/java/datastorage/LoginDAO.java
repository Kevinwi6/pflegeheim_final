package datastorage;

import model.LoginModel;
import model.Patient;
import utils.DateConverter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LoginDAO extends DAOimp<LoginModel> {
    public LoginDAO(Connection conn) {
        super(conn);
    }

    @Override
    protected String getCreateStatementString(LoginModel loginModel) {
        return  String.format("INSERT INTO USERS (USERNAME,PASSWORDHASH) VALUES ('%s', '%s')",
                loginModel.getUsername(),loginModel.getPasswordHash());
    }

    @Override
    protected String getReadByIDStatementString(long key) {
        return  String.format("SELECT * FROM USERS WHERE pid = %d", key);
    }

    @Override
    protected LoginModel getInstanceFromResultSet(ResultSet set) throws SQLException {
        LoginModel p = null;
        LocalDate date = DateConverter.convertStringToLocalDate(set.getString(4));
        p = new LoginModel(set.getLong(1), set.getString(2),(set.getString(3)));
        return p;
    }

    @Override
    protected String getReadAllStatementString() {
        return "SELECT * FROM USERS";
    }

    @Override
    protected ArrayList<LoginModel> getListFromResultSet(ResultSet result) throws SQLException {
        ArrayList<LoginModel> list = new ArrayList<LoginModel>();
        LoginModel p = null;
        while (result.next()) {
            LocalDate date = DateConverter.convertStringToLocalDate(result.getString(4));
            p = new LoginModel(result.getLong(1), result.getString(2),
                    result.getString(3));
            list.add(p);
        }
        return list;
    }

    @Override
    protected String getUpdateStatementString(LoginModel loginModel) {
        return String.format("UPDATE USERS SET USERNAME = '%s', PASSWORDHASH = '%s'" +
                         "WHERE uid = %d", loginModel.getUsername(),loginModel.getPasswordHash(), loginModel.getiD());
    }

    @Override
    protected String getDeleteStatementString(long key) {
        return String.format("Delete FROM USERS WHERE uid=%d", key);
    }
}
