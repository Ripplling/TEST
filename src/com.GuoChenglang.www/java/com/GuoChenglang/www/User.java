package com.GuoChenglang.www;

import dao.Jdbc;
import util.Encryption;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class User {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //signIn();
    }

    public static void logIn() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入账号");
        String name = sc.nextLine();
        System.out.println("请输入密码");
        String password = sc.nextLine();
        ResultSet result = Jdbc.executeQuery("SELECT * FROM user WHERE username = '" + name + "'&& password = '" + password + "'&& istrue = 1");
        if (result.next()) {
            System.out.println("登录成功");

        } else {
            System.out.println("登录失败，请检查账号或密码");
        }
        Jdbc.releaseAll(null, result.getStatement(), result);

    }


}