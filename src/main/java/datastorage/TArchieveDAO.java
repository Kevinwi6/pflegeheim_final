package datastorage;

import model.TArchieve;
import utils.DateConverter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class TArchieveDAO extends DAOimp<TArchieve>{
    public TArchieveDAO(Connection conn) {
        super(conn);
    }



    @Override
    protected String getCreateStatementString(TArchieve TArchieve) {
       return String.format("INSERT INTO BLOCKEDDATA (PID,TREATMENT_DATE,BEGIN,END,DESCRIPTION,REMARKS,ARCHIVED_AT) VALUES ('%s', '%s','%s','%s','%s','%s','%s')",
                TArchieve.getPid(), TArchieve.getDate(), TArchieve.getBegin(), TArchieve.getEnd(), TArchieve.getDescription(), TArchieve.getRemarks(), TArchieve.getArchived_at());
    }

    @Override
    protected String getReadByIDStatementString(long key) {
        return String.format("SELECT * FROM BLOCKEDDATA WHERE bid = %d", key);
    }

    @Override
    protected TArchieve getInstanceFromResultSet(ResultSet set) throws SQLException {
        LocalDate date = DateConverter.convertStringToLocalDate(set.getString(3));
        LocalTime begin = DateConverter.convertStringToLocalTime(set.getString(4));
        LocalTime end = DateConverter.convertStringToLocalTime(set.getString(5));
        LocalDate archived_at =  DateConverter.convertStringToLocalDate(set.getString(8));
        TArchieve m = new TArchieve(set.getLong(1), set.getLong(2),
                date, begin, end, set.getString(6), set.getString(7),archived_at);
        return m;
    }

    @Override
    protected String getReadAllStatementString() {
        return "SELECT * FROM BLOCKEDDATA";
    }

    @Override
    protected ArrayList<TArchieve> getListFromResultSet(ResultSet set) throws SQLException {
        ArrayList<TArchieve> list = new ArrayList<TArchieve>();
        TArchieve t = null;
        while (set.next()) {
            LocalDate date = DateConverter.convertStringToLocalDate(set.getString(3));
            LocalTime begin = DateConverter.convertStringToLocalTime(set.getString(4));
            LocalTime end = DateConverter.convertStringToLocalTime(set.getString(5));
            LocalDate archived_at =  DateConverter.convertStringToLocalDate(set.getString(8));
            t = new TArchieve(set.getLong(1), set.getLong(2),
                    date, begin, end, set.getString(6), set.getString(7),archived_at);
            list.add(t);
        }
        return list;
    }

    @Override
    protected String getUpdateStatementString(TArchieve TArchieve) {
        return String.format("UPDATE BLOCKEDDATA SET pid = %d, treatment_date ='%s', begin = '%s', end = '%s'," +
                        "description = '%s', remarks = '%s',archived_at = '%s' WHERE bid = %d", TArchieve.getPid(), TArchieve.getDate(),
                TArchieve.getBegin(), TArchieve.getEnd(), TArchieve.getDescription(), TArchieve.getRemarks(), TArchieve.getArchived_at(),
                TArchieve.getBid());
    }

    @Override
    protected String getDeleteStatementString(long key) {
        return String.format("Delete FROM BLOCKEDDATA WHERE bid= %d", key);
    }
}
