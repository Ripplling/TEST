import controller.UserController;
import pojo.User;
import services.impl.UserService;

import java.sql.SQLException;
import java.text.ParseException;

public class test {
    public static void main(String[] args) throws SQLException, ParseException {
       // UserService u = new UserService();
        User user = new User("guo","000",null,"guochenglang","3123",1);
        //u.selectDoc(user,"001","11-11-11");
        UserController use = new UserController();
        use.printPatient(user);
        use.printFree();
        use.printDate("110");
    }
}
