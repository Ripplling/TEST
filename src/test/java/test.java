import controller.SignIn;
import dao.ConnectManager;
import dao.Jdbc;
import dao.Jdbcutil;
import services.impl.AdminController;

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
        //AdminController.temporarily();
        AdminController controller = new AdminController();
        //controller.acceptStudent("3214");
        Connection conn = new ConnectManager().getConnection();
        conn.setAutoCommit(false);
        controller.acceptStudent("3214");

    }
}
