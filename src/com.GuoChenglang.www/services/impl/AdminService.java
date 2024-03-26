package services.impl;

import dao.Jdbcutil;
import services.AdminControl;

import java.sql.SQLException;
import java.util.LinkedHashMap;

public class AdminService implements AdminControl {
    public AdminService() {
    }


    //通过传入ID同意学生进行注册
    @Override
    public int acceptStudent(String id) throws SQLException {
        Jdbcutil jdbc = new Jdbcutil();
        LinkedHashMap<String, Object> set = new LinkedHashMap<>();
        //同意注册，将2变为1
        set.put("istrue", 1);
        LinkedHashMap<String, Object> condition = new LinkedHashMap<>();
        condition.put("istrue", "2");
        condition.put("id", id);
        return jdbc.update("user", set, condition, true);
    }

    //不同意非法的学生
    @Override
    public int rejectStudent(String id) throws SQLException {
        Jdbcutil jdbc = new Jdbcutil();
        LinkedHashMap<String, Object> condition = new LinkedHashMap<>();
        condition.put("istrue", 2);
        condition.put("id", id);
        //删除当前学号的注册信息
        jdbc.delect("user", condition, true);
        return jdbc.delect("user", condition, true);
    }

    //插入医生信息
    @Override
    public int insertDoc(String name, String room) throws SQLException {
        Jdbcutil jdbc = new Jdbcutil();
        LinkedHashMap<String, Object> comfort = new LinkedHashMap<>();
        comfort.put("name", name);
        comfort.put("room", room);
        comfort.put("isfree", 1);
        return jdbc.insert("doctor", comfort, true);
    }

    //插入时间段
    @Override
    public int inserDocDate(String date, String room) throws SQLException {
        Jdbcutil jdbc = new Jdbcutil();
        LinkedHashMap<String, Object> comfort = new LinkedHashMap<>();
        comfort.put("date", date);
        comfort.put("room", room);
        return jdbc.insert("date", comfort, true);
    }

}
