package sql;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface GenericDAO<E> {
    List<E> getRows() throws SQLException;

    void createTable() throws SQLException;

    void deleteRow(int id) throws SQLException;

    void createRow(E value) throws SQLException;

    void dropTable() throws SQLException;

    void editRow(int id, E value) throws SQLException;
}
