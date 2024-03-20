import dao.ConnectManager;
import dao.Jdbc;
import dao.Jdbcutil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Objects;

public class test {
    public static void main(String[] args) throws SQLException, ParseException {
        //AccountControl.signIn();
       /* HashMap <String,Object> map = new HashMap<>();
        map.put("name","小明");
        map.put("room","110");
        map.put("istrue","1");
        Jdbcutil.insert(map);*/
        //String sql = "INSERT INTO doctor(name,room,istrue) VALUES('小明','001',1)";

        //Jdbcutil.insert("小明","001");
        LinkedHashMap<String, Object> map =new LinkedHashMap<>();
        map.size();
        map.put("username","12345");
        map.put("istrue",4);
        Jdbcutil.update("user",map);

    }
}
