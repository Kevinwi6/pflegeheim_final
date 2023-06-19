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

    /**
     * generates a <code>INSERT INTO</code>-Statement for a given loginModel
     * @param loginModel for which a specific INSERT INTO is to be created
     * @return <code>String</code> with the generated SQL.
     */
    @Override
    protected String getCreateStatementString(LoginModel loginModel) {
        return  String.format("INSERT INTO USERS (USERNAME,PASSWORDHASH) VALUES ('%s', '%s')",
                loginModel.getUsername(),loginModel.getPasswordHash());
    }

    /**
     * generates a <code>select</code>-Statement for a given key
     * @param key for which a specific SELECTis to be created
     * @return <code>String</code> with the generated SQL.
     */
    @Override
    protected String getReadByIDStatementString(long key) {
        return  String.format("SELECT * FROM USERS WHERE pid = %d", key);
    }

    /**
     * maps a <code>ResultSet</code> to a <code>Patient</code>
     * @param result ResultSet with a single row. Columns will be mapped to a patient-object.
     * @return patient with the data from the resultSet.
     */
    @Override
    protected LoginModel getInstanceFromResultSet(ResultSet result) throws SQLException {
        LoginModel p = null;
        p = new LoginModel(result.getLong(1), result.getString(2),(result.getString(3)));
        return p;
    }

    /**
     * generates a <code>SELECT</code>-Statement for all users.
     * @return <code>String</code> with the generated SQL.
     */
    @Override
    protected String getReadAllStatementString() {
        return "SELECT * FROM USERS";
    }

    /**
     * maps a <code>ResultSet</code> to a <code>LoginModel-List</code>
     * @param result ResultSet with a multiple rows. Data will be mapped to LoginModel-object.
     * @return ArrayList with login models from the resultSet.
     */
    @Override
    protected ArrayList<LoginModel> getListFromResultSet(ResultSet result) throws SQLException {
        ArrayList<LoginModel> list = new ArrayList<LoginModel>();
        LoginModel p = null;
        while (result.next()) {

            p = new LoginModel(result.getLong(1), result.getString(2),
                    result.getString(3));
            list.add(p);
        }
        return list;
    }

    /**
     * generates a <code>UPDATE</code>-Statement for a given login model
     * @param loginModel for which a specific update is to be created
     * @return <code>String</code> with the generated SQL.
     */
    @Override
    protected String getUpdateStatementString(LoginModel loginModel) {
        return String.format("UPDATE USERS SET USERNAME = '%s', PASSWORDHASH = '%s'" +
                         "WHERE uid = %d", loginModel.getUsername(),loginModel.getPasswordHash(), loginModel.getiD());
    }

    /**
     * generates a <code>delete</code>-Statement for a given key
     * @param key for which a specific DELETE is to be created
     * @return <code>String</code> with the generated SQL.
     */
    @Override
    protected String getDeleteStatementString(long key) {
        return String.format("Delete FROM USERS WHERE uid=%d", key);
    }
}
