package datastorage;

import model.PArchive;
import utils.DateConverter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class PArchiveDAO extends DAOimp<PArchive> {

    public PArchiveDAO(Connection conn) {
        super(conn);
    }

    /**
     * generates a <code>INSERT INTO</code>-Statement for a given pArchive
     * @param pArchive for which a specific INSERT INTO is to be created
     * @return <code>String</code> with the generated SQL.
     */
    @Override
    protected String getCreateStatementString(PArchive pArchive) {
        return String.format("INSERT INTO BLOCKEDDATA_PATIENT (PID,FIRSTNAME,SURNAME,DATEOFBIRTH,CARELEVEL,ROOMNUMBER,ARCHIVED_AT) VALUES ('%s', '%s','%s','%s','%s','%s','%s')",
                pArchive.getPid(), pArchive.getFirstName(), pArchive.getSurname(), pArchive.getDateOfBirth(), pArchive.getCareLevel(), pArchive.getRoomnumber(), pArchive.getArchived_at());
    }

    /**
     * generates a <code>select</code>-Statement for a given key
     * @param key for which a specific SELECTis to be created
     * @return <code>String</code> with the generated SQL.
     */
    @Override
    protected String getReadByIDStatementString(long key) {
        return String.format("SELECT * FROM BLOCKEDDATA_PATIENT WHERE pid = %d", key);
    }

    /**
     * maps a <code>ResultSet</code> to a <code>Patient</code>
     * @param result ResultSet with a single row. Columns will be mapped to a patient-object.
     * @return patient with the data from the resultSet.
     */
    @Override
    protected PArchive getInstanceFromResultSet(ResultSet result) throws SQLException {
        LocalDate dateofbirth = DateConverter.convertStringToLocalDate(result.getString(4));
        LocalDate archived_at =  DateConverter.convertStringToLocalDate(result.getString(7));
        PArchive m = new PArchive(result.getLong(1), result.getString(2),
                result.getString(3),dateofbirth,result.getString(5),result.getString(6),archived_at);
        return m;
    }

    /**
     * generates a <code>SELECT</code>-Statement for all blocked patient data.
     * @return <code>String</code> with the generated SQL.
     */
    @Override
    protected String getReadAllStatementString() {
        return "SELECT * FROM BLOCKEDDATA_PATIENT";
    }

    /**
     * maps a <code>ResultSet</code> to a <code>archived-patient-List</code>
     * @param result ResultSet with a multiple rows. Data will be mapped to PArchive-object.
     * @return ArrayList with patients from the resultSet.
     */
    @Override
    protected ArrayList<PArchive> getListFromResultSet(ResultSet result) throws SQLException {
        ArrayList<PArchive> list = new ArrayList<PArchive>();
        while (result.next()) {
            LocalDate dateofbirth = DateConverter.convertStringToLocalDate(result.getString(4));
            LocalDate archived_at =  DateConverter.convertStringToLocalDate(result.getString(7));
            PArchive m = new PArchive(result.getLong(1), result.getString(2),
                    result.getString(3),dateofbirth,result.getString(5),result.getString(6),archived_at);
            list.add(m);
        }
        return list;
    }

    /**
     * generates a <code>UPDATE</code>-Statement for a given archived patient
     * @param pArchive  for which a specific update is to be created
     * @return <code>String</code> with the generated SQL.
     */
    @Override
    protected String getUpdateStatementString(PArchive pArchive) {
        return String.format("UPDATE BLOCKEDDATA_PATIENT SET pid = %d, FIRSTNAME ='%s', SURNAME = '%s', DATEOFBIRTH = '%s',CARELEVEL = '%s',ROOMNUMBER = '%s',ARCHIVED_AT = '%s'" +
                        " WHERE pid = %d", pArchive.getPid(), pArchive.getFirstName(),
                pArchive.getSurname(), pArchive.getDateOfBirth(), pArchive.getCareLevel(), pArchive.getRoomnumber(), pArchive.getArchived_at());
    }

    /**
     * generates a <code>delete</code>-Statement for a given key
     * @param key for which a specific DELETE is to be created
     * @return <code>String</code> with the generated SQL.
     */
    @Override
    protected String getDeleteStatementString(long key) {
        return String.format("Delete FROM BLOCKEDDATA_PATIENT WHERE pid= %d", key);
    }
}
