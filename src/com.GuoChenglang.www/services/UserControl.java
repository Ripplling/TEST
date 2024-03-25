package services;

import pojo.User;

import java.sql.SQLException;

public interface UserControl {
    public void selectDoc(User user, String room, String date) throws SQLException;

    public boolean isOder(User user) throws SQLException;

    boolean isDate(String room, String date) throws SQLException;
}
