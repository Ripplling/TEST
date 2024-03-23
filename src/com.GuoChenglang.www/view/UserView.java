package view;

import controller.LogIn;
import controller.SignIn;
import dao.Jdbcutil;
import pojo.User;
import util.Determind;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class UserView {
    public static void initMenu() throws SQLException {
        Scanner sc = new Scanner(System.in);
        boolean isBreak = false;
        while (true) {
            System.out.println("1.登录");
            System.out.println("2.注册");
            System.out.println("请输入：");
            String keyHit = sc.nextLine();
            switch (keyHit) {
                case "1" -> {
                    System.out.println("登录");
                    User user = LogIn.logIn();
                    if (user != null) {
                        isBreak = true;
                        //进入用户界面
                        System.out.println("登录成功");
                        majorMenu(user);
                    }
                }
                case "2" -> {
                    System.out.println("注册");
                    SignIn.signIn();
                }
                default -> {
                    System.out.println("非法字符，请重新输入");
                }
            }
            if (isBreak) {
                break;
            }
        }
    }

    public static void majorMenu(User user) throws SQLException {
        Scanner sc = new Scanner(System.in);
        boolean isBreak = false;
        while (true) {
            System.out.println("欢迎你" + user.getName());
            System.out.println("1.查看个人信息");
            System.out.println("2.预约挂号");
            System.out.println("3.查看挂号记录");
            System.out.println("4.退出");
            String keyHit = sc.nextLine();
            switch (keyHit) {
                case "1" -> {
                    firstMenu(user);
                }
                case "2" -> {
                }
                case "3" -> {
                }
                case "4" -> {
                    isBreak = true;
                }
                default -> {
                    System.out.println("无效字符，请重新输入");
                }
            }
            if (isBreak) {
                break;
            }
        }
    }

    public static void firstMenu(User user) throws SQLException {
        Scanner sc = new Scanner(System.in);
        boolean isBreak = false;
        System.out.println(user);
        while (true) {
            System.out.println("1.完善个人信息");
            System.out.println("2.返回菜单");
            String keyHit = sc.nextLine();
            switch (keyHit) {
                case "1" -> {
                    while (true) {
                        if (user.getPhone() != null) {
                            System.out.println("请输入新的的手机号");
                        }else {
                            System.out.println("请完善你的手机号");
                        }
                        String newphone = sc.nextLine();
                        if (Determind.isPhone(newphone)) {
                            Jdbcutil jdbc = new Jdbcutil();
                            LinkedHashMap<String, Object> set = new LinkedHashMap<>();
                            set.put("phone", newphone);
                            LinkedHashMap<String, Object> condition = new LinkedHashMap<>();
                            condition.put("username", user.getUsername());
                            jdbc.update("user", set, condition, true);
                            user.setPhone(newphone);
                            break;
                        } else {
                            System.out.println("输入的手机号不合法，请重新输入");
                        }
                    }

                }
                case "2" -> {
                    isBreak = true;
                }
                default -> System.out.println("无效字符，请重新输入");
            }
            if (isBreak) {
                break;
            }
        }
    }
    public static void secondMenu(User user){

    }
}
