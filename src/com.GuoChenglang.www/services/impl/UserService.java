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
        String name = user.getName();
        String id = user.getId();
        ConnectManager pool = new ConnectManager();
        Connection conn = pool.getConnection();
        conn.setAutoCommit(false);
        LinkedHashMap<String, Object> comfort = new LinkedHashMap<>();
        LinkedHashMap<String, Object> comfort2 = new LinkedHashMap<>();
        comfort.put("name", name);
        comfort.put("ID", id);
        comfort.put("room", room);
        comfort.put("complete", 0);
        comfort.put("date", date);
        Jdbcutil.insert(conn, "patient", comfort);
        comfort2.put("room", room);
        Jdbcutil.delect(conn, "date", comfort2);
        if (!DocIsFree.docIsFree(room)) {
            LinkedHashMap<String, Object> set = new LinkedHashMap<>();
            LinkedHashMap<String, Object> docRoom = new LinkedHashMap<>();
            set.put("isfree", 0);
            docRoom.put("room", room);
            Jdbcutil.update(conn, "doctor", set, docRoom);
        }
        Affair.startAffair(conn);
        pool.returnConnection(conn);
        System.out.println("选择成功");
    }
}
