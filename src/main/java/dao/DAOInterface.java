package main.java.dao;

import java.sql.SQLException;
import java.util.List;

public interface DAOInterface<T,S> {
    void add(T t) throws SQLException;
    int update(S s ,T t) throws SQLException;
    int delete(S s) throws SQLException;
    T findById(S s) throws SQLException;
    List <T> findAll() throws SQLException;
}
