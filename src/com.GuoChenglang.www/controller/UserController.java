package controller;

import dao.Jdbcutil;
import pojo.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class UserController {
    public UserController() {
    }

    //打印个人信息
    public void printUser(User user) {
        System.out.println(user);
    }

    //打印就诊记录
    public void printPatient(User user) throws SQLException {
        String name = user.getName();
        String id = user.getId();
        ArrayList<String> select = new ArrayList<>();
        select.add("name");
        select.add("ID");
        //select.add("yourdoc");
        select.add("room");
        select.add("date");
        LinkedHashMap<String, Object> condition = new LinkedHashMap<>();
        condition.put("complete", 1);
        condition.put("name", name);
        condition.put("ID", id);
        ArrayList<LinkedHashMap<String, Object>> patient = Jdbcutil.select("patient", select, condition);
        if (patient.isEmpty()) {
            System.out.println("没有挂号信息");
        } else {
            System.out.println(patient);
        }
    }

    //打印空闲可挂号医生的信息
    public boolean printFree() throws SQLException {
        ArrayList<String> select = new ArrayList<>();
        select.add("name");
        select.add("room");
        LinkedHashMap<String, Object> condition = new LinkedHashMap<>();
        condition.put("isfree", 1);
        ArrayList<LinkedHashMap<String, Object>> doctor = Jdbcutil.select("doctor", select, condition);
        if (doctor.isEmpty()) {
            System.out.println("当前无空闲医生");
            return false;
        } else {
            System.out.println(doctor);
            return true;
        }

    }

    //打印该房号空闲的时间段
    public boolean printDate(String room) throws SQLException {
        ArrayList<String> select = new ArrayList<>();
        select.add("date");
        LinkedHashMap<String, Object> condition = new LinkedHashMap<>();
        condition.put("room", room);
        ArrayList<LinkedHashMap<String, Object>> date = Jdbcutil.select("date", select, condition);
        if (date.isEmpty()) {
            System.out.println("没有该房间号");
            return false;
        } else {
            System.out.println(date);
            return true;
        }
    }
}
