package controller;

import dao.Jdbcutil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class AdminController {
    public AdminController() {
    }

    //打印待审核的学生信息
    public boolean temporarily() throws SQLException {
        ArrayList<String> select = new ArrayList<>();
        select.add("name");
        select.add("id");
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("istrue", "2");
        ArrayList<LinkedHashMap<String, Object>> result = Jdbcutil.select("user", select, map);
        if (result.isEmpty()) {
            System.out.println("没有学生信息");
            return false;
        } else {
            System.out.println(result);
            return true;
        }
    }
}
