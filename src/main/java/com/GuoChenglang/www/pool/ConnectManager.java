package com.GuoChenglang.www.pool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//Connectpool的实现类，避免暴露底层
public class ConnectManager implements Connectutil {
    private final Connectpool pool = new Connectpool();

    public ConnectManager() {
    }

    @Override
    public Connection getConnection() {
        return pool.getConnection();
    }

    @Override
    public void returnConnection(Connection conn) throws SQLException {
        pool.returnConnection(conn);
    }

    @Override
    public void releaseConnection() {
        pool.releaseConnection();
    }

    @Override
    public void releaseAll(Connection conn, Statement state, ResultSet resultSet) {
        pool.releaseAll(conn, state, resultSet);
    }

}
