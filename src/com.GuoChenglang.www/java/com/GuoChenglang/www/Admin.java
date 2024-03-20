package com.GuoChenglang.www;

import dao.Jdbc;
import util.Decryption;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Admin {
    public static void main(String[] args) throws SQLException {
        //prewatch();
    }

    //获取审核表
    public void prewatch() throws SQLException {
        ResultSet result = Jdbc.executeQuery("SELECT * FROM user WHERE istrue = 2");
        while (result.next()) {
            System.out.println(result.getString(1) + Decryption.decryption(result.getString(2)) + result.getString(4) + result.getString(5));
        }
    }
}
