package datastorage;

import model.Caregiver;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CaregiverDAO extends DAOimp<Caregiver>{

    public CaregiverDAO(Connection conn) {
        super(conn);
    }
    /**
     * generates a <code>INSERT INTO</code>-Statement for a given caregiver
     * @param caregiver for which a specific INSERT INTO is to be created
     * @return <code>String</code> with the generated SQL.
     */
    @Override
    protected String getCreateStatementString(Caregiver caregiver) {
        return  String.format("INSERT INTO CAREGIVER (FIRSTNAME,LASTNAME,TELEPHONENUMBER) VALUES ('%s', '%s','%s')",
                caregiver.getFirstname(),caregiver.getSurename(),caregiver.getPhoneNumber());
    }

    /**
     * generates a <code>select</code>-Statement for a given key
     * @param key for which a specific SELECT is to be created
     * @return <code>String</code> with the generated SQL.
     */
    @Override
    protected String getReadByIDStatementString(long key) {
        return  String.format("SELECT * FROM CAREGIVER WHERE cid = %d", key);
    }

    /**
     * maps a <code>ResultSet</code> to a <code>Patient</code>
     * @param result ResultSet with a single row. Columns will be mapped to a patient-object.
     * @return patient with the data from the resultSet.
     */
    @Override
    protected Caregiver getInstanceFromResultSet(ResultSet result) throws SQLException {
        Caregiver p = null;
        p = new Caregiver(result.getLong(1),result.getString(2), result.getString(3),(result.getString(4)));
        return p;
    }

    /**
     * generates a <code>SELECT</code>-Statement for all caregiver.
     * @return <code>String</code> with the generated SQL.
     */
    @Override
    protected String getReadAllStatementString() {
        return "SELECT * FROM CAREGIVER";
    }

    /**
     * maps a <code>ResultSet</code> to a <code>caregiver-List</code>
     * @param result ResultSet with a multiple rows. Data will be mapped to caregiver-object.
     * @return ArrayList with caregiver from the resultSet.
     */
    @Override
    protected ArrayList<Caregiver> getListFromResultSet(ResultSet result) throws SQLException {
        ArrayList<Caregiver> list = new ArrayList<Caregiver>();
        Caregiver p = null;
        while (result.next()) {

            p = new Caregiver(result.getLong(1), result.getString(2),
                    result.getString(3),result.getString(4));
            list.add(p);
        }
        return list;
    }

    /**
     * generates a <code>UPDATE</code>-Statement for a given caregiver
     * @param caregiver for which a specific update is to be created
     * @return <code>String</code> with the generated SQL.
     */
    @Override
    protected String getUpdateStatementString(Caregiver caregiver) {
        return String.format("UPDATE CAREGIVER SET FIRSTNAME = '%s', LASTNAME = '%s',TELEPHONENUMBER = '%s'" +
                "WHERE cid = %d", caregiver.getFirstname(),caregiver.getSurename(), caregiver.getPhoneNumber());
    }

    /**
     * generates a <code>delete</code>-Statement for a given key
     * @param key for which a specific DELETE is to be created
     * @return <code>String</code> with the generated SQL.
     */
    @Override
    protected String getDeleteStatementString(long key) {
        return String.format("Delete FROM CAREGIVER WHERE cid=%d", key);
    }
}
