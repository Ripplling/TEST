package util;

import dao.Jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
//判断工具类，判断传入的数据是否合法
public class Determind {
    //学号是否合法
    public static boolean isId(String id) {
        Pattern pattern = Pattern.compile("[3][1][12]\\d[0][0]\\d{4}");
        Matcher matcher = pattern.matcher(id);
        return matcher.matches();
    }
    //电话号码是否合法
    public static boolean isPhone(String phone) {
        Pattern pattern = Pattern.compile("^1[34578]\\d{9}$");
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }
    //当前账号是否重复了（用早期封装写的，以后应该会用回jdbc工具类）
    public static boolean isRepeat(String account) throws SQLException {
        ResultSet resultSet = Jdbc.executeQuery("SELECT * FROM user WHERE username = '" + account + "'");
        return resultSet.next();
    }
}
