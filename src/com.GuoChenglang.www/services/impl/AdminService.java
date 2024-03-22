package services.impl;

import dao.Affair;
import dao.ConnectManager;
import dao.Jdbc;
import dao.Jdbcutil;
import services.AdminControl;

import java.sql.Connection;
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
        ConnectManager pool = new ConnectManager();
        Connection conn = pool.getConnection();
        conn.setAutoCommit(false);
        Jdbcutil.update(conn, "user", set, condition);
        Affair.startAffair(conn);
        //Affair.startAffair(conn);
        pool.returnConnection(conn);
    }

    //不同意非法的学生
    @Override
    public void rejectStudent(String id) throws SQLException {
        LinkedHashMap<String, Object> condition = new LinkedHashMap<>();
        condition.put("istrue", 2);
        condition.put("id", id);
        ConnectManager pool = new ConnectManager();
        Connection conn = pool.getConnection();
        conn.setAutoCommit(false);
        Jdbcutil.delect(conn, "user", condition);
        Affair.startAffair(conn);
        pool.returnConnection(conn);
    }

    //插入医生信息
    @Override
    public void insertDoc(String name, String room) throws SQLException {
        LinkedHashMap<String, Object> comfort = new LinkedHashMap<>();
        comfort.put("name", name);
        comfort.put("room", room);
        comfort.put("isfree", 1);
        ConnectManager pool = new ConnectManager();
        Connection conn = pool.getConnection();
        conn.setAutoCommit(false);
        Jdbcutil.insert(conn, "doctor", comfort);
        Affair.startAffair(conn);
        pool.returnConnection(conn);
    }

    //插入时间段
    @Override
    public void inserDocDate(String date, String room) throws SQLException {
        LinkedHashMap<String, Object> comfort = new LinkedHashMap<>();
        comfort.put("date", date);
        comfort.put("room", room);
        ConnectManager pool = new ConnectManager();
        Connection conn = new ConnectManager().getConnection();
        conn.setAutoCommit(false);
        Jdbcutil.insert(conn, "date", comfort);
        Affair.startAffair(conn);
        pool.returnConnection(conn);
    }

}
