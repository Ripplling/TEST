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
    public static void insert(String table, LinkedHashMap<String, Object> map) throws SQLException {
        //构建sql语句
        StringBuilder sql = new StringBuilder("INSERT INTO ");
        //插入目标表
        sql.append(table).append("(");
        Set<String> keys = map.keySet();
        int length = map.size();
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
        PreparedStatement pre = conn.prepareStatement(String.valueOf(sql));
        count = 1;
        //通过键值对对占位符进行赋值
        for (String key : keys) {
            Object value = map.get(key);
            pre.setObject(count, value);
            count++;
        }
        pre.executeUpdate();
        pool.returnConnection(conn);
        pool.releaseAll(null, pre, null);
    }


    public static void delect(String table, LinkedHashMap<String, Object> map) throws SQLException {
        //构建sql语句
        StringBuilder sql = new StringBuilder("DELETE FROM ");
        sql.append(table).append(" WHERE (");
        Set<String> keys = map.keySet();
        int length = map.size();
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
        PreparedStatement pre = conn.prepareStatement(String.valueOf(sql));
        //通过键值对对占位符进行赋值
        for (String key : keys) {
            Object value = map.get(key);
            pre.setObject(count, value);
            count++;
        }
        //回收资源
        pre.executeUpdate();
        pool.returnConnection(conn);
        pool.releaseAll(null, pre, null);
    }

    //自定义sql语句，通过键值对应来赋值
    public static void update(String table, LinkedHashMap<String, Object> map) throws SQLException {
        //构建sql语句
        StringBuilder sql = new StringBuilder("UPDATE ");
        sql.append(table).append(" SET ");
        Set<String> keys = map.keySet();
        int length = map.size() - 1;
        int count = 1;
        /*
         * 只做了单一条件搜寻的修改sql
         * 如果count小于集合长度-1，正常添加字符“'key'=? ”
         * 如果count等于集合长度-1，修改的结尾，不加，且后面加上WHERE
         * 如果count长度等于集合长度，表示WHERE的条件，添加字符“'key'=?”
         *
         * */
        for (String key : keys) {

            if (count == length) {
                sql.append(key).append(" =? ").append("WHERE ");
                count++;
                continue;

            } else if (count == length + 1) {
                sql.append(key).append("= ?");
                break;
            }
            sql.append(key).append(" = ?, ");
            count++;
        }
        count = 1;
        //从连接池获取连接
        ConnectManager pool = new ConnectManager();
        Connection conn = pool.getConnection();
        PreparedStatement pre = conn.prepareStatement(sql.toString());
        //对占位符进行赋值
        for (String key : keys) {
            Object value = map.get(key);
            pre.setObject(count, value);
            count++;

        }
        //System.out.println(sql);
        pre.executeUpdate();
        //回收资源
        pool.returnConnection(conn);
        pool.releaseAll(null, pre, null);
        //return 0;
    }


    public static ArrayList<LinkedHashMap<String, Object>> select(String table, ArrayList<String> select, LinkedHashMap<String, Object> map) throws SQLException {
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
        Set<String> keys = map.keySet();
        int length = map.size();
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
            Object value = map.get(key);
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
