package controller;

import dao.Jdbcutil;
import util.WriteUtil;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.TimerTask;

public class CreateMap extends TimerTask {
    private static ArrayList<LinkedHashMap<String, Object>> nowPatient;

    @Override
    public void run() {
        Jdbcutil jdbcutil = new Jdbcutil();
        boolean isContinue = false;
        FileWriter writer = null;
        String prepareWrite = null;
        ArrayList<LinkedHashMap<String, Object>> newPatients = null;
        //生成表
        try {
            writer = new FileWriter("C:\\Users\\23217\\Desktop\\javacode\\mypool\\src\\com.GuoChenglang.www\\common\\Patienttxt.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ArrayList<String> select = new ArrayList<>();
        select.add("name");
        select.add("ID");
        select.add("date");
        select.add("room");
        LinkedHashMap<String, Object> condition = new LinkedHashMap<>();
        condition.put("complete", 1);
        try {
            newPatients = jdbcutil.select("patient", select, condition);
            for (int index = 0; index < newPatients.size(); index++) {
                LinkedHashMap<String, Object> patient = newPatients.get(index);
                //nowPatient为空（第一次写入）以及不为空的情况（多次写入,不写入重复数据）
                if (nowPatient == null) {
                    /*Set<String> keys = patient.keySet();
                    //循环对键值对进行赋值
                    for (String key : keys) {
                        if (key.equals("name")) {
                            name = (String) patient.get(key);
                        }
                        if (key.equals("ID")) {
                            id = (String) patient.get(key);
                        }
                        if (key.equals("date")) {
                            date = (String) patient.get(key);
                        }
                        if (key.equals("room")) {
                            room = (String) patient.get(key);
                        }
                    }
                    String prepareWrite = new StringBuilder().append("姓名：").append(name).append("  学号：").append(id).append("  时间").append(date).append("  科室").append(room).append("\r\n").toString();*/
                    //将上述重复内容变成一个工具类方法
                    prepareWrite = WriteUtil.writeUtil(patient);
                    //写入txt
                    try {
                        writer.write(prepareWrite);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    isContinue = false;
                    for (int i = 0; i < nowPatient.size(); i++) {
                        //patient.equals(nowPatient.get(i));
                        if (patient.equals(nowPatient.get(i))) {
                            isContinue = true;
                            break;
                        }
                    }
                    if (isContinue) {
                        continue;
                    }
                    prepareWrite = WriteUtil.writeUtil(patient);
                    try {
                        writer.write(prepareWrite);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }

            }
            try {
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //循环结束进行替换
        nowPatient = newPatients;
    }
}
