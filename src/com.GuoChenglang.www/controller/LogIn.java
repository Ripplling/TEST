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

//登录操作的方法
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
        //查找是否有相同的账号&&密码
        conditon.put("username", name);
        conditon.put("password", password);
        //istrue代表是否被管理员审核
        conditon.put("istrue", 1);
        //ResultSet result = Jdbc.executeQuery("SELECT * FROM user WHERE username = '" + name + "'&& password = '" + password + "'&& istrue = 1");
        ArrayList<LinkedHashMap<String, Object>> result = jdbc.select("user", select, conditon);
        //不为空即找到了账号以及密码
        if (!result.isEmpty()) {
            System.out.println("登录成功");
        } else {
            System.out.println("登录失败，请检查账号或密码");
            return null;
        }
        //Jdbc.releaseAll(null, result.getStatement(), result);
        //创建user对象
        User user = new User();
        LinkedHashMap<String, Object> values = result.getFirst();
        Set<String> keys = values.keySet();
        for (String key : keys) {
            Object value = values.get(key);
            switch (key) {
                //通过键的名字对对应user进行赋值
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
