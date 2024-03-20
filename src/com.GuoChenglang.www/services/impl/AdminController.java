package services.impl;

import dao.Jdbc;
import services.AdminControl;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminController implements AdminControl {
    //打印暂存的学生
    @Override
    public void temporarily() throws SQLException {
        ResultSet result = Jdbc.executeQuery("SELECT * FROM user WHERE istrue = 2");
        while (result.next()) {
            System.out.println("账号为" + result.getString(1) + ",姓名是" + result.getString(4) + ",学号是" + result.getString(5));
        }
    }
    @Override
    public void submit(){

    }
}
