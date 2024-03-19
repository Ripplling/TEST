package com.GuoChenglang.www.jdbc;

import com.GuoChenglang.www.pool.Config;
import com.GuoChenglang.www.pool.ConnectManager;

import java.sql.*;

//JDBC方法的封装
public class Jdbc {
    //私有化构造方法
    private Jdbc() {
    }

    //注册驱动
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    //获取连接方法
    public static Connection getConnection() {
        Config config = new Config();
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(config.getUrl(), config.getUsername(), config.getPassword());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }

    //释放资源方法
    public static void releaseAll(Connection conn, Statement state, ResultSet result) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (state != null) {
            try {
                state.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (result != null) {
            try {
                result.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public static int insert(String table,String[] keys,Object[] values) throws SQLException {
        String sql = "INSERT INTO ?(?) VALUES (?)";
        ConnectManager manager = new ConnectManager();
        Connection conn = manager.getConnection();
        PreparedStatement pre = conn.prepareStatement(sql);
        pre.setString(1,table);
        return 0;

    }


}