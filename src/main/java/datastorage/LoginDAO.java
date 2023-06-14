package datastorage;

import model.LoginModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoginDAO extends DAOimp<LoginModel> {
    public LoginDAO(Connection conn) {
        super(conn);
    }

    @Override
    protected String getCreateStatementString(LoginModel loginModel) {
        return null
    }

    @Override
    protected String getReadByIDStatementString(long key) {
        return null;
    }

    @Override
    protected LoginModel getInstanceFromResultSet(ResultSet set) throws SQLException {
        return null;
    }

    @Override
    protected String getReadAllStatementString() {
        return null;
    }

    @Override
    protected ArrayList<LoginModel> getListFromResultSet(ResultSet set) throws SQLException {
        return null;
    }

    @Override
    protected String getUpdateStatementString(LoginModel loginModel) {
        return null;
    }

    @Override
    protected String getDeleteStatementString(long key) {
        return null;
    }
}
