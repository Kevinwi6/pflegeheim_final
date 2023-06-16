package datastorage;

import model.PArchive;
import model.TArchieve;
import utils.DateConverter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class PArchiveDAO extends DAOimp<PArchive> {

    public PArchiveDAO(Connection conn) {
        super(conn);
    }

    @Override
    protected String getCreateStatementString(PArchive pArchive) {
        return String.format("INSERT INTO BLOCKEDDATA_PATIENT (PID,FIRSTNAME,SURNAME,DATEOFBIRTH,CARELEVEL,ROOMNUMBER,ARCHIVED_AT) VALUES ('%s', '%s','%s','%s','%s','%s','%s')",
                pArchive.getPid(), pArchive.getFirstName(), pArchive.getSurname(), pArchive.getDateOfBirth(), pArchive.getCareLevel(), pArchive.getRoomnumber(), pArchive.getArchived_at());
    }

    @Override
    protected String getReadByIDStatementString(long key) {
        return String.format("SELECT * FROM BLOCKEDDATA_PATIENT WHERE pid = %d", key);
    }

    @Override
    protected PArchive getInstanceFromResultSet(ResultSet set) throws SQLException {
        LocalDate dateofbirth = DateConverter.convertStringToLocalDate(set.getString(4));
        LocalDate archived_at =  DateConverter.convertStringToLocalDate(set.getString(7));
        PArchive m = new PArchive(set.getLong(1), set.getString(2),
                set.getString(3),dateofbirth,set.getString(5),set.getString(6),archived_at);
        return m;
    }


    @Override
    protected String getReadAllStatementString() {
        return "SELECT * FROM BLOCKEDDATA_PATIENT";
    }

    @Override
    protected ArrayList<PArchive> getListFromResultSet(ResultSet set) throws SQLException {
        ArrayList<PArchive> list = new ArrayList<PArchive>();
        while (set.next()) {
            LocalDate dateofbirth = DateConverter.convertStringToLocalDate(set.getString(4));
            LocalDate archived_at =  DateConverter.convertStringToLocalDate(set.getString(7));
            PArchive m = new PArchive(set.getLong(1), set.getString(2),
                    set.getString(3),dateofbirth,set.getString(5),set.getString(6),archived_at);
            list.add(m);
        }
        return list;
    }

    @Override
    protected String getUpdateStatementString(PArchive pArchive) {
        return String.format("UPDATE BLOCKEDDATA_PATIENT SET pid = %d, FIRSTNAME ='%s', SURNAME = '%s', DATEOFBIRTH = '%s',CARELEVEL = '%s',ROOMNUMBER = '%s',ARCHIVED_AT = '%s'" +
                        " WHERE pid = %d", pArchive.getPid(), pArchive.getFirstName(),
                pArchive.getSurname(), pArchive.getDateOfBirth(), pArchive.getCareLevel(), pArchive.getRoomnumber(), pArchive.getArchived_at());
    }

    @Override
    protected String getDeleteStatementString(long key) {
        return String.format("Delete FROM BLOCKEDDATA_PATIENT WHERE pid= %d", key);
    }
}
