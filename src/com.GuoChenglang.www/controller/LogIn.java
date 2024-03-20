package controller;

import dao.Jdbc;
import util.Encryption;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class LogIn {
    public static boolean logIn() throws SQLException {
        Scanner sc = new Scanner(System.in);
        boolean isTrue = true;
        System.out.println("请输入账号");
        String name = sc.nextLine();
        System.out.println("请输入密码");
        String password = Encryption.encryption(sc.nextLine());
        ResultSet result = Jdbc.executeQuery("SELECT * FROM user WHERE username = '" + name + "'&& password = '" + password + "'&& istrue = 1");
        if (result.next()) {
            System.out.println("登录成功");
        } else {
            System.out.println("登录失败，请检查账号或密码");
            isTrue = false;
        }
        Jdbc.releaseAll(null, result.getStatement(), result);
        return isTrue;

    }
}
