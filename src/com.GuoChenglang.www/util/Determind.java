package util;

import dao.Jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Determind {
    public static boolean isId(String id) {
        Pattern pattern = Pattern.compile("[3][1][12]\\d[0][0]\\d{4}");
        Matcher matcher = pattern.matcher(id);
        return matcher.matches();
    }

    public static boolean isPhone(String phone) {
        Pattern pattern = Pattern.compile("^1[34578]\\d{9}$");
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }

    public static boolean isRepeat(String account) throws SQLException {
        ResultSet resultSet = Jdbc.executeQuery("SELECT * FROM user WHERE username = '" + account + "'");
        return resultSet.next();
    }
}
