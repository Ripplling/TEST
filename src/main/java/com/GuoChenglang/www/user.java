package com.GuoChenglang.www;

import com.GuoChenglang.www.jdbc.Jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class user {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        System.out.println("请输入账号");
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        System.out.println("请输入密码");
        String pass = sc.nextLine();
        //登录测试
        ResultSet result = Jdbc.executeQuery("SELECT * FROM user WHERE username = '" + name + "'&& password = '" + pass+"'&& istrue = 1" );
        if (result.next()) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }
    }
}