package services.impl;

import dao.Jdbc;
import dao.Jdbcutil;
import services.AdminControl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class AdminService implements AdminControl {
    public AdminService() {
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
        LinkedHashMap<String, Object> condition = new LinkedHashMap<>();
        condition.put("istrue", 2);
        condition.put("id", id);
        Jdbcutil.delect("user", condition);
    }

    //插入医生信息
    @Override
    public void insertDoc(String name, String room) throws SQLException {
        LinkedHashMap<String, Object> comfort = new LinkedHashMap<>();
        comfort.put("name", name);
        comfort.put("room", room);
        comfort.put("istrue",1);
        Jdbcutil.insert("doctor", comfort);
    }

}
