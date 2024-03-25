package services;

import java.sql.SQLException;

public interface AdminControl {
    int acceptStudent(String id) throws SQLException;

    int rejectStudent(String id) throws SQLException;

    int insertDoc(String name, String room) throws SQLException;

    int inserDocDate(String date, String room) throws SQLException;
}
