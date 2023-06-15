package datastorage;

import model.Archieve;
import model.Treatment;
import utils.DateConverter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class ArchieveDAO extends DAOimp<Archieve>{
    public ArchieveDAO(Connection conn) {
        super(conn);
    }



    @Override
    protected String getCreateStatementString(Archieve archieve) {
       return String.format("INSERT INTO BLOCKEDDATA (PID,TREATMENT_DATE,BEGIN,END,DESCRIPTION,REMARKS,ARCHIVED_AT) VALUES ('%s', '%s','%s','%s','%s','%s','%s')",
                archieve.getPid(),archieve.getDate(),archieve.getBegin(),archieve.getEnd(),archieve.getDescription(),archieve.getRemarks(),archieve.getArchived_at());
    }

    @Override
    protected String getReadByIDStatementString(long key) {
        return String.format("SELECT * FROM BLOCKEDDATA WHERE bid = %d", key);
    }

    @Override
    protected Archieve getInstanceFromResultSet(ResultSet set) throws SQLException {
        LocalDate date = DateConverter.convertStringToLocalDate(set.getString(3));
        LocalTime begin = DateConverter.convertStringToLocalTime(set.getString(4));
        LocalTime end = DateConverter.convertStringToLocalTime(set.getString(5));
        LocalDate archived_at =  DateConverter.convertStringToLocalDate(set.getString(8));
        Archieve m = new Archieve(set.getLong(1), set.getLong(2),
                date, begin, end, set.getString(6), set.getString(7),archived_at);
        return m;
    }

    @Override
    protected String getReadAllStatementString() {
        return "SELECT * FROM BLOCKEDDATA";
    }

    @Override
    protected ArrayList<Archieve> getListFromResultSet(ResultSet set) throws SQLException {
        ArrayList<Archieve> list = new ArrayList<Archieve>();
        Archieve t = null;
        while (set.next()) {
            LocalDate date = DateConverter.convertStringToLocalDate(set.getString(3));
            LocalTime begin = DateConverter.convertStringToLocalTime(set.getString(4));
            LocalTime end = DateConverter.convertStringToLocalTime(set.getString(5));
            LocalDate archived_at =  DateConverter.convertStringToLocalDate(set.getString(8));
            t = new Archieve(set.getLong(1), set.getLong(2),
                    date, begin, end, set.getString(6), set.getString(7),archived_at);
            list.add(t);
        }
        return list;
    }

    @Override
    protected String getUpdateStatementString(Archieve archieve) {
        return String.format("UPDATE BLOCKEDDATA SET pid = %d, treatment_date ='%s', begin = '%s', end = '%s'," +
                        "description = '%s', remarks = '%s',archived_at = '%s' WHERE bid = %d", archieve.getPid(), archieve.getDate(),
                archieve.getBegin(), archieve.getEnd(), archieve.getDescription(), archieve.getRemarks(),archieve.getArchived_at(),
                archieve.getBid());
    }

    @Override
    protected String getDeleteStatementString(long key) {
        return String.format("Delete FROM BLOCKEDDATA WHERE bid= %d", key);
    }
}
