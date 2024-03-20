import controller.SignIn;
import dao.ConnectManager;
import dao.Jdbc;
import dao.Jdbcutil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class test {
    public static void main(String[] args) throws SQLException, ParseException {
        //AccountControl.signIn();
       /* HashMap <String,Object> map = new HashMap<>();
        map.put("name","小明");
        map.put("room","110");
        map.put("istrue","1");
        Jdbcutil.insert(map);*/
        //String sql = "INSERT INTO doctor(name,room,istrue) VALUES('小明','001',1)";

        ArrayList<String> yw = new ArrayList<>();
        yw.add("name");
        yw.add("room");
        yw.add("istrue");
        LinkedHashMap<String,Object> map = new LinkedHashMap<>();
        map.put("room","001");
        //Jdbcutil.select("doctor",yw,map);
        System.out.println(Jdbcutil.select("doctor",yw,map));

    }
}
