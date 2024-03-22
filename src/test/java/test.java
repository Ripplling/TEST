
import dao.Jdbcutil;
import dao.SqlBuilder;


import java.sql.SQLException;
import java.text.ParseException;
import java.util.LinkedHashMap;

public class test {
    public static void main(String[] args) throws SQLException, ParseException {
        LinkedHashMap<String,Object> map = new LinkedHashMap<>();
        //map.put("username","guochenglang");
        LinkedHashMap<String,Object> con = new LinkedHashMap<>();
        con.put("id","3214");
        //Jdbcutil.insert("doctor",map);
        //Jdbcutil.update("user",map,con);
        Jdbcutil.delect("user",con);
    }
}
