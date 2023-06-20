package datastorage;

import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {
    /**
     * creates a
     * @param t
     * @throws SQLException
     */
    void create(T t) throws SQLException;

    T read(long key) throws SQLException;

    List<T> readAll() throws SQLException;

    void update(T t) throws SQLException;

    void deleteById(long key) throws SQLException;
}
