package com.GuoChenglang.www;

import com.GuoChenglang.www.pool.ConnectManager;
import com.GuoChenglang.www.pool.Connectpool;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //Connectpool pool = new Connectpool();
        ConnectManager pool = new ConnectManager();
        //打印连接数，添加连接
        System.out.println(Connectpool.connection);
        Connection conn = pool.getConnection();
        Connection conn2 = pool.getConnection();
        Connection conn3 = pool.getConnection();
        Connection conn4 = pool.getConnection();
        Connection conn5 = pool.getConnection();
        Connection conn6 = pool.getConnection();
        Connection conn7 = pool.getConnection();
        Connection conn8 = pool.getConnection();
        //打印连接数
      System.out.println(Connectpool.connection);
      //归还连接
        pool.returnConnection(conn2);
        pool.returnConnection(conn);
        pool.returnConnection(conn3);
        //pool.returnConnection(conn4);
        //conn4.close();
        pool.returnConnection(conn4);
        //打印连接数和集合
        System.out.println(Connectpool.connection);
        System.out.println(Connectpool.arr);
        //打印连接归还后释放多余连接后的集合
       //pool.releaseConnection();
        System.out.println(Connectpool.arr);
        System.out.println("hello git");
    }
}