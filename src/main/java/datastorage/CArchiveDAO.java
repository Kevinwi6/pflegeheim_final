package datastorage;

import model.CArchive;
import model.Caregiver;
import model.PArchive;
import utils.DateConverter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class CArchiveDAO extends DAOimp<CArchive>{


    public CArchiveDAO(Connection conn) {
        super(conn);
    }

    @Override
    protected String getCreateStatementString(CArchive cArchive) {
            return String.format("INSERT INTO BLOCKEDDATA_CAREGIVER (CID,FIRSTNAME,LASTNAME,TELEPHONENUMBER,ARCHIVED_AT) VALUES ('%s', '%s','%s','%s','%s')",
                    cArchive.getCid(), cArchive.getFirstname(), cArchive.getLastname(), cArchive.getTelephonenumber(), cArchive.getArchived_at());
    }

    @Override
    protected String getReadByIDStatementString(long key) {
        return String.format("SELECT * FROM BLOCKEDDATA_CAREGIVER WHERE cid = %d", key);
    }

    @Override
    protected CArchive getInstanceFromResultSet(ResultSet set) throws SQLException {
        LocalDate archived_at = DateConverter.convertStringToLocalDate(set.getString(5));
        CArchive c = new CArchive(set.getLong(1),set.getString(2),set.getString(3),set.getString(4),archived_at);
        return c;
    }

    @Override
    protected String getReadAllStatementString() {
            return "SELECT * FROM BLOCKEDDATA_CAREGIVER";
    }

    @Override
    protected ArrayList<CArchive> getListFromResultSet(ResultSet set) throws SQLException {
        ArrayList<CArchive> list = new ArrayList<CArchive>();
        while (set.next()) {
            LocalDate archived_at =  DateConverter.convertStringToLocalDate(set.getString(5));
            CArchive c = new CArchive(set.getLong(1), set.getString(2),
                    set.getString(3),set.getString(4),archived_at);
            list.add(c);
        }
        return list;
    }

    @Override
    protected String getUpdateStatementString(CArchive cArchive) {
        return String.format("UPDATE BLOCKEDDATA_CAREGIVER SET cid = %d, FIRSTNAME ='%s', LASTNAME = '%s', TELEPHONENUMBER = '%s',ARCHIVED_AT = '%s'" +
                        " WHERE Cid = %d", cArchive.getCid(), cArchive.getFirstname(),
                cArchive.getLastname(), cArchive.getTelephonenumber(), cArchive.getArchived_at());
    }

    @Override
    protected String getDeleteStatementString(long key) {
        return String.format("Delete FROM BLOCKEDDATA_CAREGIVER WHERE cid= %d", key);
    }
}
