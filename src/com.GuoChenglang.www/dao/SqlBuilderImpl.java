package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

//sql构建器的接口
public interface SqlBuilderImpl {
    String insertSql(String table, LinkedHashMap<String, Object> comfort);

    String deleteSql(String table, LinkedHashMap<String, Object> condition);

    String updateSql(String table, LinkedHashMap<String, Object> set, LinkedHashMap<String, Object> condition);

    String selectSql(String table, ArrayList<String> select, LinkedHashMap<String, Object> condition);
}
