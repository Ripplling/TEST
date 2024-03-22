package util;

import dao.Jdbcutil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class DocIsFree {
    //设定选择完当前医生后是否空闲
    public static boolean docIsFree(String room) throws SQLException {
        ArrayList<String> date = new ArrayList<>();
        date.add("date");
        LinkedHashMap<String, Object> condition = new LinkedHashMap<>();
        condition.put("room", room);
        ArrayList<LinkedHashMap<String, Object>> date1 = Jdbcutil.select("date", date, condition);
        if (date1.size() == 1) {
            return false;
        } else {
            return true;
        }

    }
}
