package dao;

import java.net.CookieManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

//Jdbc的详细封装(insert,update,delete,select)（不是很会a）
public class Jdbcutil implements JdbcutilImpl {
    public Jdbcutil() {
    }

    @Override
    //插入医生的信息
    public int insert(String table, LinkedHashMap<String, Object> comfort, boolean affair) throws SQLException {
        SqlBuilder sqlBuilder = new SqlBuilder();
        String sql = sqlBuilder.insertSql(table, comfort);
        //System.out.println(sql);
        ConnectManager pool = new ConnectManager();
        Connection conn = pool.getConnection();
        PreparedStatement pre = conn.prepareStatement(String.valueOf(sql));
        int count = 1;
        Set<String> keys = comfort.keySet();
        //通过键值对对占位符进行赋值
        for (String key : keys) {
            Object value = comfort.get(key);
            pre.setObject(count, value);
            count++;
        }
        if (affair) {
            conn.setAutoCommit(false);
        }
        int num = pre.executeUpdate();
        Affair.startAffair(conn, affair);
        pool.returnConnection(conn);
        pool.releaseAll(null, pre, null);
        return num;
    }

    @Override
    public int delect(String table, LinkedHashMap<String, Object> condition, boolean affair) throws SQLException {
        SqlBuilder sqlBuilder = new SqlBuilder();
        String sql = sqlBuilder.deleteSql(table, condition);
        System.out.println(sql);
        int count = 1;
        ConnectManager pool = new ConnectManager();
        Connection conn = pool.getConnection();
        PreparedStatement pre = conn.prepareStatement(String.valueOf(sql));
        Set<String> keys = condition.keySet();
        //通过键值对对占位符进行赋值
        for (String key : keys) {
            Object value = condition.get(key);
            pre.setObject(count, value);
            count++;
        }
        if (affair) {
            conn.setAutoCommit(false);
        }
        //回收资源
        int num = pre.executeUpdate();
        Affair.startAffair(conn, affair);
        pool.returnConnection(conn);
        pool.releaseAll(null, pre, null);
        return num;
    }

    @Override
    //自定义sql语句，通过键值对应来赋值
    public int update(String table, LinkedHashMap<String, Object> set, LinkedHashMap<String, Object> condition, boolean affair) throws SQLException {
        SqlBuilder sqlBuilder = new SqlBuilder();
        String sql = sqlBuilder.updateSql(table, set, condition);
        ConnectManager pool = new ConnectManager();
        Connection conn = pool.getConnection();
        PreparedStatement pre = conn.prepareStatement(sql.toString());
        int count = 1;
        Set<String> keys = set.keySet();
        Set<String> condit = condition.keySet();
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
        if (affair) {
            conn.setAutoCommit(false);
        }
        int num = pre.executeUpdate();
        Affair.startAffair(conn, affair);
        pool.returnConnection(conn);
        pool.releaseAll(null, pre, null);
        return num;
    }

    @Override
    public ArrayList<LinkedHashMap<String, Object>> select(String table, ArrayList<String> select, LinkedHashMap<String, Object> condition) throws SQLException {
        SqlBuilder sqlBuilder = new SqlBuilder();
        String sql = sqlBuilder.selectSql(table, select, condition);
        //打印sql语句查验
        //System.out.println(sql);
        //从连接池获取连接
        ConnectManager pool = new ConnectManager();
        Connection conn = pool.getConnection();
        PreparedStatement pre = conn.prepareStatement(String.valueOf(sql));
        int count = 1;
        int index = 0;
        Set<String> keys = condition.keySet();
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
        pool.returnConnection(conn);
        pool.releaseAll(null, pre, resultSet);
        return result;

    }
}
