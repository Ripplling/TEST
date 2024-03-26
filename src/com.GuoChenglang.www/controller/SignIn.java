package controller;

import dao.*;
import util.Determind;
import util.Encryption;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Scanner;
//注册操作的方法
public class SignIn {
    public static void signIn() throws SQLException {
        Jdbcutil jdbc = new Jdbcutil();
        Scanner sc = new Scanner(System.in);
        String account = null;
        //对输入的账号进行非重复操作
        while (true) {
            System.out.println("请输入注册的账号");
            account = sc.nextLine();
            if (!Determind.isRepeat(account)) {
                break;
            }
            System.out.println("该账号已经存在，请重新输入");
        }
        String password = null;
        //对输入的密码进行非空操作
        while (true) {
            System.out.println("请输入注册的密码");
            password = sc.nextLine();
            if (password != null) {
                break;
            } else {
                System.out.println("非法密码");
            }
        }
        //对密码进行加密操作
        String secondPassword = Encryption.encryption(password);
        System.out.println("请输入你的姓名");
        String name = sc.nextLine();
        String id = null;
        //对学号进行正则判断
        while (true) {
            System.out.println("请输入你的学号");
            id = sc.nextLine();
            if (Determind.isId(id)) {
                break;
            }
            System.out.println("输入学号的格式错误");
        }
        //Jdbc.executeUpdate("INSERT INTO user(username,password,name,id,istrue) VALUES('" + account + "','" + secondPassword + "','" + name + "','" + id + "',2) ");
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        //插入进user表当中
        map.put("username", account);
        map.put("password", secondPassword);
        map.put("name", name);
        map.put("id", id);
        map.put("istrue", 2);
        jdbc.insert("user", map, true);
        System.out.println("注册已提交，请等待管理员审核");
    }
}
