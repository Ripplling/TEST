package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Objects;
import java.util.Set;

//Jdbc的详细封装（不是很会a）
public class Jdbcutil {
    //插入医生的信息
    public static void insert(String name, String room) throws SQLException {
        ConnectManager pool = new ConnectManager();
        String sql = "INSERT INTO doctor(name,room,istrue) VALUES(?,?,?)";
        Connection conn = pool.getConnection();
        PreparedStatement pre = conn.prepareStatement(sql);
        pre.setString(1, name);
        pre.setString(2, room);
        pre.setString(3, String.valueOf(1));
        pre.executeUpdate();
        pool.returnConnection(conn);
        pool.releaseAll(null, pre, null);
    }

    //插入时间段
    public static void insert(String room, String[] date) throws SQLException {
        if (date.length != 0) {
            ConnectManager pool = new ConnectManager();
            Connection conn = pool.getConnection();
            PreparedStatement pre = null;
            String sql2 = "INSERT INTO date(date,room) VALUES(?,?)";
            pre = conn.prepareStatement(sql2);
            for (int i = 0; i < date.length; i++) {
                pre.setString(1, date[i]);
                pre.setString(2, room);
                pre.executeUpdate();
            }
            pool.returnConnection(conn);
            pool.releaseAll(null, pre, null);
        }
    }


    public static void delect(String table, String name) throws SQLException {

        String sql = "DELETE FROM " + table + " WHERE name = '" + name + "'";
        ConnectManager pool = new ConnectManager();
        Connection conn = pool.getConnection();
        PreparedStatement pre = conn.prepareStatement(sql);
        pre.executeUpdate();
        pool.returnConnection(conn);
        pool.releaseAll(null, pre, null);

    }

    //自定义sql语句，通过键值对应来赋值
    public static void update(String table, LinkedHashMap<String, Object> map) throws SQLException {
        StringBuilder sql = new StringBuilder("UPDATE ");
        Connection conn = new ConnectPool().getConnection();
        sql.append(table).append(" SET ");
        Set<String> keys = map.keySet();
        int index = map.size() - 1;
        int count = 1;
        for (String key : keys) {

            if (count == index) {
                sql.append(key).append(" =? ").append("WHERE ");
                count++;
                continue;

            } else if (count == index + 1) {
                sql.append(key).append("= ?");
                break;
            }
            sql.append(key).append(" = ?, ");
            count++;
        }
        count = 1;
        //sql.append("WHERE ");
        //打印sql语句
        System.out.println(sql);
        PreparedStatement pre = conn.prepareStatement(sql.toString());
        for (String key : keys) {
            Object value = map.get(key);
            pre.setObject(count, value);
            count++;

        }
        //System.out.println(sql);
        pre.executeUpdate();
        //return 0;
    }


    public static HashMap<String, Object> select() {
        return null;
    }
}
