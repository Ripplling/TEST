package controller;

import dao.Jdbcutil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class AdminController {
    public AdminController() {
    }

    //打印待审核的学生信息
    public void temporarily() throws SQLException {
        ArrayList<String> select = new ArrayList<>();
        select.add("name");
        select.add("id");
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("istrue", "2");
        ArrayList<LinkedHashMap<String, Object>> result = Jdbcutil.select("user", select, map);
        System.out.println(result);
    }
}
