package dao;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public interface Connectutil {
    Connection getConnection();
    void returnConnection(Connection conn) throws SQLException;
    void releaseConnection();
    void releaseAll(Connection conn, Statement state, ResultSet resultSet);
}