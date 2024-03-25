package util;

import java.util.LinkedHashMap;
import java.util.Set;

public class WriteUtil {
    public static String writeUtil(LinkedHashMap<String, Object> patient){
        String name = null;
        String id = null;
        String room = null;
        String date = null;
        Set<String> keys = patient.keySet();
        for (String key : keys) {
            if (key.equals("name")) {
                name = (String) patient.get(key);
            }
            if (key.equals("ID")) {
                id = (String) patient.get(key);
            }
            if (key.equals("date")) {
                date = (String) patient.get(key);
            }
            if (key.equals("room")) {
                room = (String) patient.get(key);
            }
        }
        String prepareWrite = new StringBuilder().append("姓名：").append(name).append("  学号：").append(id).append("  时间").append(date).append("  科室").append(room).append("\r\n").toString();
        return prepareWrite;
    }
}
