package util;

import java.util.LinkedHashMap;
import java.util.Set;

public class WriteUtil {
    //构建写入信息
    public static String writeUtil(LinkedHashMap<String, Object> patient) {
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
        return "姓名：" + name + "  学号：" + id + "  时间" + date + "  科室" + room + "\r\n";
    }
}
