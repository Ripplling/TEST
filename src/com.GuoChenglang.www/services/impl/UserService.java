package services.impl;

import controller.UserController;
import dao.Affair;
import dao.ConnectManager;
import dao.Jdbcutil;
import pojo.User;
import services.UserControl;
import util.DocIsFree;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class UserService implements UserControl {
    public UserService() {
    }

    //选择就诊医生
    public void selectDoc(User user, String room, String date) throws SQLException {
        Jdbcutil jdbc = new Jdbcutil();
        String name = user.getName();
        String id = user.getId();
        LinkedHashMap<String, Object> comfort = new LinkedHashMap<>();
        LinkedHashMap<String, Object> comfort2 = new LinkedHashMap<>();
        comfort.put("name", name);
        comfort.put("ID", id);
        comfort.put("room", room);
        comfort.put("complete", 0);
        comfort.put("date", date);
        jdbc.insert("patient", comfort,false);
        comfort2.put("room", room);
        jdbc.delect("date", comfort2,false);
        if (!DocIsFree.docIsFree(room)) {
            LinkedHashMap<String, Object> set = new LinkedHashMap<>();
            LinkedHashMap<String, Object> docRoom = new LinkedHashMap<>();
            set.put("isfree", 0);
            docRoom.put("room", room);
            jdbc.update( "doctor", set, docRoom,false);
        }
        System.out.println("选择成功");
    }
}
