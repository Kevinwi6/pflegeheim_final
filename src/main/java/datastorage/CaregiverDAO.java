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
    @Override
    protected String getCreateStatementString(Caregiver caregiver) {
        return  String.format("INSERT INTO CAREGIVER (FIRSTNAME,LASTNAME,TELEPHONENUMBER) VALUES ('%s', '%s','%s')",
                caregiver.getFirstname(),caregiver.getSurename(),caregiver.getPhoneNumber());
    }

    @Override
    protected String getReadByIDStatementString(long key) {
        return  String.format("SELECT * FROM CAREGIVER WHERE cid = %d", key);
    }

    @Override
    protected Caregiver getInstanceFromResultSet(ResultSet set) throws SQLException {
        Caregiver p = null;
        p = new Caregiver(set.getLong(1),set.getString(2), set.getString(3),(set.getString(4)));
        return p;
    }

    @Override
    protected String getReadAllStatementString() {
        return "SELECT * FROM CAREGIVER";
    }

    @Override
    protected ArrayList<Caregiver> getListFromResultSet(ResultSet set) throws SQLException {
        ArrayList<Caregiver> list = new ArrayList<Caregiver>();
        Caregiver p = null;
        while (set.next()) {

            p = new Caregiver(set.getLong(1), set.getString(2),
                    set.getString(3),set.getString(4));
            list.add(p);
        }
        return list;
    }

    @Override
    protected String getUpdateStatementString(Caregiver caregiver) {
        return String.format("UPDATE CAREGIVER SET FIRSTNAME = '%s', LASTNAME = '%s',TELEPHONENUMBER = '%s'" +
                "WHERE cid = %d", caregiver.getFirstname(),caregiver.getSurename(), caregiver.getPhoneNumber());
    }

    @Override
    protected String getDeleteStatementString(long key) {
        return String.format("Delete FROM CAREGIVER WHERE cid=%d", key);
    }
}
