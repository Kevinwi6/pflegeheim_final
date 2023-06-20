package datastorage;

import model.TArchive;
import utils.DateConverter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class TArchiveDAO extends DAOimp<TArchive>{
    public TArchiveDAO(Connection conn) {
        super(conn);
    }


    /**
     * generates a <code>INSERT INTO</code>-Statement for a given TArchive
     * @param TArchive for which a specific INSERT INTO is to be created
     * @return <code>String</code> with the generated SQL.
     */
    @Override
    protected String getCreateStatementString(TArchive TArchive) throws SQLException {
       return String.format("INSERT INTO BLOCKEDDATA (PATIENT_NAME,TREATMENT_DATE,BEGIN,END,DESCRIPTION,REMARKS,ARCHIVED_AT) VALUES ('%s', '%s','%s','%s','%s','%s','%s')",
                TArchive.getPatientName(TArchive.getPid()), TArchive.getDate(), TArchive.getBegin(), TArchive.getEnd(), TArchive.getDescription(), TArchive.getRemarks(), TArchive.getArchived_at());
    }

    /**
     * generates a <code>select</code>-Statement for a given key
     * @param key for which a specific SELECT is to be created
     * @return <code>String</code> with the generated SQL.
     */
    @Override
    protected String getReadByIDStatementString(long key) {
        return String.format("SELECT * FROM BLOCKEDDATA WHERE bid = %d", key);
    }
    /**
     * maps a <code>ResultSet</code> to a <code>Patient</code>
     * @param result ResultSet with a single row. Columns will be mapped to a patient-object.
     * @return patient with the data from the resultSet.
     */
    @Override
    protected TArchive getInstanceFromResultSet(ResultSet result) throws SQLException {
        LocalDate date = DateConverter.convertStringToLocalDate(result.getString(3));
        LocalTime begin = DateConverter.convertStringToLocalTime(result.getString(4));
        LocalTime end = DateConverter.convertStringToLocalTime(result.getString(5));
        LocalDate archived_at =  DateConverter.convertStringToLocalDate(result.getString(8));
        TArchive m = new TArchive(result.getLong(1), result.getString(2),
                date, begin, end, result.getString(6), result.getString(7),archived_at);
        return m;
    }

    /**
     * generates a <code>SELECT</code>-Statement for all blocked data.
     * @return <code>String</code> with the generated SQL.
     */
    @Override
    protected String getReadAllStatementString() {
        return "SELECT * FROM BLOCKEDDATA";
    }

    /**
     * maps a <code>ResultSet</code> to a <code>Archived-Treatment-List</code>
     * @param result ResultSet with a multiple rows. Data will be mapped to TArchive-object.
     * @return ArrayList with Archived Treatments from the resultSet.
     */
    @Override
    protected ArrayList<TArchive> getListFromResultSet(ResultSet result) throws SQLException {
        ArrayList<TArchive> list = new ArrayList<TArchive>();
        TArchive t = null;
        while (result.next()) {
            LocalDate date = DateConverter.convertStringToLocalDate(result.getString(3));
            LocalTime begin = DateConverter.convertStringToLocalTime(result.getString(4));
            LocalTime end = DateConverter.convertStringToLocalTime(result.getString(5));
            LocalDate archived_at =  DateConverter.convertStringToLocalDate(result.getString(8));
            t = new TArchive(result.getLong(1), result.getString(2),
                    date, begin, end, result.getString(6), result.getString(7),archived_at);
            list.add(t);
        }
        return list;
    }

    /**
     * generates a <code>UPDATE</code>-Statement for a given archived treatment
     * @param TArchive for which a specific update is to be created
     * @return <code>String</code> with the generated SQL.
     */
    @Override
    protected String getUpdateStatementString(TArchive TArchive) throws SQLException {
        return String.format("UPDATE BLOCKEDDATA SET pid = %d, treatment_date ='%s', begin = '%s', end = '%s'," +
                        "description = '%s', remarks = '%s',archived_at = '%s' WHERE bid = %d", TArchive.getPid(), TArchive.getDate(),
                TArchive.getBegin(), TArchive.getEnd(), TArchive.getDescription(), TArchive.getRemarks(), TArchive.getArchived_at(),
                TArchive.getBid());
    }

    /**
     * generates a <code>delete</code>-Statement for a given key
     * @param key for which a specific DELETE is to be created
     * @return <code>String</code> with the generated SQL.
     */
    @Override
    protected String getDeleteStatementString(long key) {
        return String.format("Delete FROM BLOCKEDDATA WHERE bid= %d", key);
    }
}
