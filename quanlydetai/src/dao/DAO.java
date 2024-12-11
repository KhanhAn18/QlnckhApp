package dao;

import java.sql.SQLException;
import javafx.collections.ObservableList;


public interface DAO<T>
{
    ObservableList<T> getAll() throws SQLException;

    T get(String... id) throws SQLException;

    boolean isContain(String... id) throws SQLException;

    void insert(T t) throws SQLException;

    void update(T t) throws SQLException;

    void delete(String... id) throws SQLException;
}
