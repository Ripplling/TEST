package com.GuoChenglang.www.pool;

import com.GuoChenglang.www.jdbc.Jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;

//连接池类
public class Connectpool implements Connectutil {
    //构造方法,创建对象时初始化连接池
    public Connectpool() {
        init(config);
    }


    //当前链接个数
    public static int connection = 0;
    //配置文件对象生成
    static Config config = new Config();

    //数据库链接的初始化的方法
    static LinkedList<Connection> init(Config config) {
        //获取初始连接个数
        int init = config.getInit();
        //创建集合
        LinkedList<Connection> arr = new LinkedList<>();
        while (init > 0) {
            //将集连接对象添加到集合中
            Connection conn = Jdbc.getConnection();
            arr.add(conn);
            init--;
        }
        return arr;
    }

    //创建集合对象,初始化连接池（使用public是为了在test中打印集合）
    public static LinkedList<Connection> arr = init(config);

    //获取连接池链接的方法
    @Override
    public Connection getConnection() {
        synchronized (Connectpool.class) {
            //连接数小于初始的情况
            if (connection < config.getInit()) {
                connection++;
                System.out.println("调用的是连接池里面的链接");
                return arr.removeFirst();
            }
            //连接数大于初始的情况
            else if (connection >= config.getInit() && connection < config.getMax()) {
                connection++;
                System.out.println("新建了一个新的连接");
                return Jdbc.getConnection();
            }
            //连接数大于最大值的情况
            else {
                System.out.println("连接过多");
                return null;
            }
        }
    }

    @Override
    //归还连接到连接池
    public void returnConnection(Connection conn) throws SQLException {
        synchronized (Connectpool.class) {
            if (conn != null && !conn.isClosed()) {
                arr.add(conn);
                System.out.println("归还成功");
                connection--;
                releaseConnection();
            }

        }

    }

    //释放多余的链接
    @Override
    public void releaseConnection() {
        synchronized (Connectpool.class) {
            while (arr.size() > config.getInit()) {
                Connection conn = arr.remove(config.getInit());
                Jdbc.releaseAll(conn, null, null);
                System.out.println("成功释放资源");
            }
        }
    }

    //释放资源
    @Override
    public void releaseAll(Connection conn, Statement state, ResultSet resultSet) {
        Jdbc.releaseAll(conn, state, resultSet);
    }




}
