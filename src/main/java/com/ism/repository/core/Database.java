package com.ism.repository.core;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface Database<T> {
    void openConnection();
    void closeConnexion();
    ResultSet executeSelect(String sql);
    int executeUpdate(String sql);
    PreparedStatement getPs();
    String generateSql(String sql);
     void setFieldData(PreparedStatement stmt, T entity) throws SQLException, IllegalAccessException;
    T convertToObject(ResultSet rs) throws SQLException;


}
