package view;

import controller.AdminController;
import services.AdminControl;
import services.impl.AdminService;

import java.sql.SQLException;
import java.util.Scanner;

public class AdminView {
    public static void majioMenu() throws SQLException {
        Scanner sc = new Scanner(System.in);
        boolean isBreak = false;
        while (true) {
            System.out.println("1.查看为审核学生");
            System.out.println("2.添加医生");
            System.out.println("3.查看就诊记录");
            System.out.println("4.退出");
            String keyHit = sc.nextLine();
            switch (keyHit) {
                case "1" -> {
                    firstMenu();
                }
                case "2" -> {
                }
                case "3" -> {
                }
                case "4" -> {
                    isBreak = true;
                }
                default -> {
                    System.out.println("非法字符，请重新输入");
                }
            }
            if ((isBreak)) {
                break;
            }
        }
    }

    public static void firstMenu() throws SQLException {
        Scanner sc = new Scanner(System.in);
        String keyHit = null;
        boolean isBreak = false;
        AdminController adminController = new AdminController();
        adminController.temporarily();
        while (true) {
            System.out.println("1.同意学生");
            System.out.println("2.拒绝学生");
            System.out.println("3.返回");
            keyHit = sc.nextLine();
            switch (keyHit) {
                case "1" -> {
                    while (true) {
                        System.out.println("请输入同意学生的学号");
                        keyHit = sc.nextLine();
                        AdminService adminService = new AdminService();
                        int line = adminService.acceptStudent(keyHit);
                        if (line == 0) {
                            System.out.println("没有该学号");
                        } else {
                            System.out.println("同意成功");
                            break;
                        }

                    }
                }
                case "2" -> {
                    while (true) {
                        System.out.println("请输入拒绝学生的学号");
                        keyHit = sc.nextLine();
                        AdminService adminService = new AdminService();
                        int line = adminService.rejectStudent(keyHit);
                        if (line == 0) {
                            System.out.println("没有该学号");
                        } else {
                            System.out.println("拒绝成功");
                            break;
                        }
                    }
                }
                case "3" -> {
                    System.out.println("返回");
                    isBreak = true;
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

    public static void secondMenu(){

    }
}
