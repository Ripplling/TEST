package services;

import java.sql.SQLException;

public interface AdminControl {
    void acceptStudent(String id) throws SQLException;
    void rejectStudent(String id) throws SQLException;
    void insertDoc(String name,String room) throws SQLException;
}
