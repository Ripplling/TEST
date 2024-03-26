package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
//jdbc的接口
public interface JdbcutilImpl {
    int insert(String table, LinkedHashMap<String, Object> comfort, boolean affair) throws SQLException;

    int delect(String table, LinkedHashMap<String, Object> condition, boolean affair) throws SQLException;

    int update(String table, LinkedHashMap<String, Object> set, LinkedHashMap<String, Object> condition, boolean affair) throws SQLException;

    ArrayList<LinkedHashMap<String, Object>> select(String table, ArrayList<String> select, LinkedHashMap<String, Object> condition) throws SQLException;
}
