package dao;

import java.sql.*;

//JDBC方法的简单封装
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

    public static int executeUpdate(String sql) throws SQLException {
        ConnectManager pool = new ConnectManager();
        Connection conn = pool.getConnection();
        ResultSet resultSet = null;
        PreparedStatement pre = conn.prepareStatement(sql);
        int line = pre.executeUpdate();
        pool.returnConnection(conn);
        pool.releaseAll(null, pre, resultSet);
        return line;
    }

    public static ResultSet executeQuery(String sql) throws SQLException {
        ConnectManager pool = new ConnectManager();
        Connection conn = pool.getConnection();
        PreparedStatement pre = conn.prepareStatement(sql);
        ResultSet resultSet = pre.executeQuery();
        pool.returnConnection(conn);
        return resultSet;
    }


}