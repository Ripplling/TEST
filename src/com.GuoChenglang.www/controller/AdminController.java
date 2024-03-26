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
        Jdbcutil jdbc = new Jdbcutil();
        ArrayList<String> select = new ArrayList<>();
        //查询的词条
        select.add("name");
        select.add("id");
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        //查询条件
        map.put("istrue", "2");
        ArrayList<LinkedHashMap<String, Object>> result = jdbc.select("user", select, map);
        //对查询结果进行非空判断
        if (result.isEmpty()) {
            System.out.println("没有学生信息");
            return false;
        } else {
            System.out.println(result);
            return true;
        }
    }
}
