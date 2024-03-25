
import controller.CreateMap;
import controller.SignIn;
import dao.Jdbcutil;
import dao.SqlBuilder;
import view.AdminView;
import view.UserView;


import java.sql.SQLException;
import java.text.ParseException;
import java.util.LinkedHashMap;
import java.util.Timer;

public class test {
    public static void main(String[] args) throws SQLException, ParseException {
        //AdminView.majioMenu();
        Timer timer = new Timer();
        CreateMap map = new CreateMap();
        timer.schedule(map, 0L,10000L);

    }
}
