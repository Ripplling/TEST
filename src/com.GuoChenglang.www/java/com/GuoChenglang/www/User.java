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

    public void signIn() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入注册的账号");
        String account = sc.nextLine();
        System.out.println("请输入注册的密码");
        String password = sc.nextLine();
        String secondPassword = Encryption.encryption(password);
        System.out.println("请输入你的姓名");
        String name = sc.nextLine();
        System.out.println("请输入你的学号");
        String id = sc.nextLine();
        Jdbc.executeUpdate("INSERT INTO user(username,password,name,id,istrue) VALUES('"+account+"','"+secondPassword+"','"+name+"','"+id+"',2) ");
        System.out.println("注册已提交，请等待管理员审核");
    }
}