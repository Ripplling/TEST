import controller.LogIn;
import services.impl.AdminService;

import java.sql.SQLException;
import java.text.ParseException;

public class test {
    public static void main(String[] args) throws SQLException, ParseException {
        //AccountControl.signIn();
       /* HashMap <String,Object> map = new HashMap<>();
        map.put("name","小明");
        map.put("room","110");
        map.put("istrue","1");
        Jdbcutil.insert(map);*/
        //String sql = "INSERT INTO doctor(name,room,istrue) VALUES('小明','001',1)                                          ";
        //AdminController.temporarily();
        AdminService controller = new AdminService();
        //controller.acceptStudent("3214");
        //SignIn.signIn();
        controller.inserDocDate("1月22日 13：23","110");

    }
}
