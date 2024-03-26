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
        Jdbcutil jdbc = new Jdbcutil();
        String name = user.getName();
        String id = user.getId();
        ArrayList<String> select = new ArrayList<>();
        //搜查的词条
        select.add("name");
        select.add("ID");
        //select.add("yourdoc");
        select.add("room");
        select.add("date");
        LinkedHashMap<String, Object> condition = new LinkedHashMap<>();
        //搜查的条件，1为已经就诊完成
        condition.put("complete", 1);
        condition.put("name", name);
        condition.put("ID", id);
        ArrayList<LinkedHashMap<String, Object>> patient = jdbc.select("patient", select, condition);
        if (patient.isEmpty()) {
            System.out.println("没有挂号信息");
        } else {
            System.out.println(patient);
        }
    }

    //打印预约课室信息
    public void printOder(User user) throws SQLException {
        Jdbcutil jdbc = new Jdbcutil();
        String name = user.getName();
        String id = user.getId();
        ArrayList<String> select = new ArrayList<>();
        //搜查的词条
        select.add("name");
        select.add("ID");
        //select.add("yourdoc");
        select.add("room");
        select.add("date");
        LinkedHashMap<String, Object> condition = new LinkedHashMap<>();
        //搜查的条件，0为已经进行预约暂为就诊
        condition.put("complete", 0);
        condition.put("name", name);
        condition.put("ID", id);
        ArrayList<LinkedHashMap<String, Object>> patient = jdbc.select("patient", select, condition);
        //进行非空判断
        if (patient.isEmpty()) {
            System.out.println("没有预约信息");
        } else {
            System.out.println(patient);
        }
    }

    //打印空闲可挂号医生的信息
    public boolean printFree() throws SQLException {
        Jdbcutil jdbc = new Jdbcutil();
        ArrayList<String> select = new ArrayList<>();
        //搜查的词条
        select.add("name");
        select.add("room");
        LinkedHashMap<String, Object> condition = new LinkedHashMap<>();
        //搜查的信息，1为医生是否有空
        condition.put("isfree", 1);
        ArrayList<LinkedHashMap<String, Object>> doctor = jdbc.select("doctor", select, condition);
        //进行非空判断
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
        Jdbcutil jdbc = new Jdbcutil();
        ArrayList<String> select = new ArrayList<>();
        //搜查的词条
        select.add("date");
        LinkedHashMap<String, Object> condition = new LinkedHashMap<>();
        //搜查的条件，按房间号进行搜查，原因是一个医生可以预约多个时间段
        condition.put("room", room);
        ArrayList<LinkedHashMap<String, Object>> date = jdbc.select("date", select, condition);
        //进行非空判断
        if (date.isEmpty()) {
            System.out.println("没有该房间号");
            return false;
        } else {
            System.out.println(date);
            return true;
        }
    }
}
