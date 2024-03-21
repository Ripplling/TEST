package services.impl;

import dao.ConnectManager;
import dao.Jdbc;
import dao.Jdbcutil;
import services.AdminControl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class AdminController implements AdminControl {
    public AdminController() {
    }

    //打印暂存的学生
    @Override
    public void temporarily() throws SQLException {
        ArrayList<String> select = new ArrayList<>();
        select.add("name");
        select.add("id");
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("istrue", "2");
        ArrayList<LinkedHashMap<String, Object>> result = Jdbcutil.select("user", select, map);
        System.out.println(result);
    }

    //通过传入ID同意学生进行注册
    @Override
    public void acceptStudent(String id) throws SQLException {
        LinkedHashMap<String, Object> set = new LinkedHashMap<>();
        //同意注册
        set.put("istrue", 1);
        LinkedHashMap<String, Object> condition = new LinkedHashMap<>();
        condition.put("istrue", "2");
        condition.put("id", id);
        Jdbcutil.update("user", set, condition);

    }

    //不同意非法的学生
    @Override
    public void rejectStudent(String id) throws SQLException {
        LinkedHashMap<String,Object> condition = new LinkedHashMap<>();
        condition.put("istrue",2);
        condition.put("id",id);
        Jdbcutil.delect("user",condition);
    }

}
