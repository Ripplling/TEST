package controller;

import dao.Jdbc;
import dao.Jdbcutil;
import pojo.User;
import util.Encryption;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.Set;

public class LogIn {
    public static User logIn() throws SQLException {
        Jdbcutil jdbc = new Jdbcutil();
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入账号");
        String name = sc.nextLine();
        System.out.println("请输入密码");
        String password = Encryption.encryption(sc.nextLine());
        ArrayList<String> select = new ArrayList<>();
        select.add("username");
        select.add("password");
        select.add("phone");
        select.add("name");
        select.add("id");
        select.add("istrue");
        LinkedHashMap<String, Object> conditon = new LinkedHashMap<>();
        conditon.put("username", name);
        conditon.put("password", password);
        conditon.put("istrue", 1);
        //ResultSet result = Jdbc.executeQuery("SELECT * FROM user WHERE username = '" + name + "'&& password = '" + password + "'&& istrue = 1");
        ArrayList<LinkedHashMap<String, Object>> result = jdbc.select("user", select, conditon);
        if (!result.isEmpty()) {
            System.out.println("登录成功");
        } else {
            System.out.println("登录失败，请检查账号或密码");
            return null;
        }
        //Jdbc.releaseAll(null, result.getStatement(), result);
        User user = new User();
        LinkedHashMap<String, Object> values = result.getFirst();
        Set<String> keys = values.keySet();
        for (String key : keys) {
            Object value = values.get(key);
            switch (key) {
                case "username" -> user.setUsername((String) value);
                case "password" -> user.setPassword((String) value);
                case "phone" -> user.setPhone((String) value);
                case "name" -> user.setName((String) value);
                case "id" -> user.setId((String) value);
                case "istrue" -> user.setIstrue((Integer) value);
            }
        }

        return user;

    }
}
