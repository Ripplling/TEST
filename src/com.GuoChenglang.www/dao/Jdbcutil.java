package dao;

import java.net.CookieManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

//Jdbc的详细封装(insert,update,delete,select)（不是很会a）
public class Jdbcutil {
    //插入医生的信息
    public static int insert(String table, LinkedHashMap<String, Object> comfort) throws SQLException {
        //构建sql语句
        StringBuilder sql = new StringBuilder("INSERT INTO ");
        //插入目标表
        sql.append(table).append("(");
        Set<String> keys = comfort.keySet();
        int length = comfort.size();
        int count = 1;
        /*
         * 在计数器没有到达集合长度时，添加字段“'key' ,”
         * 在计数器到达集合长度后,添加字段“'key' ) VALUES (”
         *
         *
         * */
        for (String key : keys) {
            if (count == length) {
                sql.append(key).append(") VALUES(");
                break;
            }
            sql.append(key).append(",");
            count++;
        }
        //通过集合长度判断设定多少个占位符'?'
        for (int i = 1; i <= count; i++) {
            if (i == count) {
                sql.append("?)");
                break;
            }
            sql.append("?,");
        }
        //System.out.println(sql);
        ConnectManager pool = new ConnectManager();
        Connection conn = pool.getConnection();
        conn.setAutoCommit(false);
        PreparedStatement pre = conn.prepareStatement(String.valueOf(sql));
        count = 1;
        //通过键值对对占位符进行赋值
        for (String key : keys) {
            Object value = comfort.get(key);
            pre.setObject(count, value);
            count++;
        }
        int num = pre.executeUpdate();
        //开启事务
        Affair.startAffair(conn);
        //回收资源
        pool.returnConnection(conn);
        pool.releaseAll(null, pre, null);
        return num;
    }


    public static int delect(String table, LinkedHashMap<String, Object> condition) throws SQLException {
        //构建sql语句
        StringBuilder sql = new StringBuilder("DELETE FROM ");
        sql.append(table).append(" WHERE (");
        Set<String> keys = condition.keySet();
        int length = condition.size();
        int count = 1;
        /*
         * 在计数器没有到达长度时，添加字符“'key'=? &&”
         * 在计数器到达长度后，添加字符“'key' = ? )”
         *
         * */
        for (String key : keys) {
            if (count == length) {
                sql.append(key).append("= ?)");
                break;
            }
            sql.append(key).append("= ? && ");
            count++;
        }
        System.out.println(sql);
        count = 1;
        ConnectManager pool = new ConnectManager();
        Connection conn = pool.getConnection();
        conn.setAutoCommit(false);
        PreparedStatement pre = conn.prepareStatement(String.valueOf(sql));
        //通过键值对对占位符进行赋值
        for (String key : keys) {
            Object value = condition.get(key);
            pre.setObject(count, value);
            count++;
        }
        //回收资源
        int num = pre.executeUpdate();
        //开启事务
        Affair.startAffair(conn);
        //回收资源
        pool.returnConnection(conn);
        pool.releaseAll(null, pre, null);
        return num;
    }

    //自定义sql语句，通过键值对应来赋值
    public static int update(String table, LinkedHashMap<String, Object> set, LinkedHashMap<String, Object> condition) throws SQLException {
        //构建sql语句
        StringBuilder sql = new StringBuilder("UPDATE ");
        sql.append(table).append(" SET ");
        Set<String> keys = set.keySet();
        int length = set.size();
        int count = 1;
        /*
            在到达集合长度前，添加字符“'key '= ?,”
            到达集合长度后，添加"'key '=? WHERE ("
         * */
        for (String key : keys) {

            if (count == length) {
                sql.append(key).append(" =? ").append("WHERE (");
                break;
            }

            sql.append(key).append(" = ?, ");
            count++;
        }
        //System.out.println(sql);
        /*
         * 到达集合长度前，添加字符“'key' = ? && ”
         * 到达集合长度后，添加字符“'key '=?)”
         * */
        Set<String> condit = condition.keySet();
        length = condition.size();
        count = 1;
        for (String s : condit) {
            if (count == length) {
                sql.append(s).append(" = ?)");
                break;
            }
            sql.append(s).append(" = ? && ");
            count++;
        }
        //System.out.println(sql);
        //从连接池获取连接
        ConnectManager pool = new ConnectManager();
        Connection conn = pool.getConnection();
        conn.setAutoCommit(false);
        PreparedStatement pre = conn.prepareStatement(sql.toString());
        count = 1;
        //对占位符进行赋值
        for (String key : keys) {
            Object value = set.get(key);
            pre.setObject(count, value);
            count++;
        }
        for (String s : condit) {
            Object value = condition.get(s);
            pre.setObject(count, value);
            count++;
        }
        //System.out.println(sql);
        int num = pre.executeUpdate();
        //开启事务
        Affair.startAffair(conn);
        //回收资源
        pool.returnConnection(conn);
        pool.releaseAll(null, pre, null);
        return num;
    }


    public static ArrayList<LinkedHashMap<String, Object>> select(String table, ArrayList<String> select, LinkedHashMap<String, Object> condition) throws SQLException {
        StringBuilder sql = new StringBuilder("SELECT ");
        //sql语句的构建
        int index = 0;
        while (index < select.size() - 1) {
            sql.append(select.get(index)).append(",");
            index++;
        }
        sql.append(select.get(index)).append(" FROM ");
        //sql语句占位符的构建
        sql.append(table).append(" WHERE (");
        Set<String> keys = condition.keySet();
        int length = condition.size();
        int count = 1;
        for (String key : keys) {
            if (count == length) {
                sql.append(key).append("= ?)");
                break;
            }
            sql.append(key).append("= ? && ");
            count++;
        }
        //打印sql语句查验
        //System.out.println(sql);
        //从连接池获取连接
        ConnectManager pool = new ConnectManager();
        Connection conn = pool.getConnection();
        PreparedStatement pre = conn.prepareStatement(String.valueOf(sql));
        count = 1;
        //对占位符进行赋值
        for (String key : keys) {
            Object value = condition.get(key);
            pre.setObject(count, value);
            count++;
        }
        ResultSet resultSet = pre.executeQuery();
        //新建储存结果的集合
        ArrayList<LinkedHashMap<String, Object>> result = new ArrayList<>();
        while (resultSet.next()) {
            //新建集合储存每一列的元素
            LinkedHashMap<String, Object> newMap = new LinkedHashMap<>();
            for (index = 0, count = 1; index < select.size(); index++, count++) {
                //对Map集合进行赋值
                newMap.put(select.get(index), resultSet.getObject(count));
            }
            //将map集合储存进结果中
            result.add(newMap);
        }
        //归还连接
        pool.returnConnection(conn);
        //释放资源
        pool.releaseAll(null, pre, resultSet);
        return result;

    }
}
