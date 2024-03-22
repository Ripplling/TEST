package dao;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.regex.Pattern;

public class SqlBuilder {
    public static String insertSql(String table, LinkedHashMap<String, Object> comfort) {
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
        return String.valueOf(sql);
    }

    public static String deleteSql(String table, LinkedHashMap<String, Object> condition) {
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
        return String.valueOf(sql);
    }

    public static String updateSql(String table, LinkedHashMap<String, Object> set, LinkedHashMap<String, Object> condition) {
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
        return String.valueOf(sql);
    }

    public static String selectSql(String table, ArrayList<String> select, LinkedHashMap<String, Object> condition) {
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
        return String.valueOf(sql);
    }
}
