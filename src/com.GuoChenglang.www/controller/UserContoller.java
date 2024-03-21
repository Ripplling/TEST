package controller;

import dao.Jdbcutil;
import pojo.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class UserContoller {
    public UserContoller() {
    }
    //打印个人信息
    public void printUser(User user){
        System.out.println(user);
    }
    //打印就诊记录
    public void printPatient(User user) throws SQLException {
        String name = user.getName();
        String id = user.getId();
        ArrayList<String> select = new ArrayList<>();
        select.add("name");
        select.add("ID");
        select.add("yourdoc");
        select.add("room");
        select.add("date");
        LinkedHashMap<String,Object> condition = new LinkedHashMap<>();
        condition.put("complete",1);
        condition.put("name",name);
        condition.put("ID",id);
        ArrayList<LinkedHashMap<String, Object>> patient = Jdbcutil.select("patient", select, condition);
        System.out.println(patient);
    }
}
