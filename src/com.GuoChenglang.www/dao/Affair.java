package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Affair {
    public static void startAffair(Connection conn, boolean start) throws SQLException {
        if (start) {
            conn.setAutoCommit(false);
            //System.out.println("你同意吗");
            Scanner sc = new Scanner(System.in);
            while (true) {
                System.out.println("你确定吗 Y/N");
                sc = new Scanner(System.in);
                String keyhit = sc.nextLine();
                if (keyhit.equals("Y") || keyhit.equals("y")) {
                    System.out.println("确定");
                    conn.commit();
                    break;
                } else if (keyhit.equals("N") || keyhit.equals("n")) {
                    System.out.println("不确定");
                    conn.rollback();
                    break;
                } else {
                    System.out.println("非法输入字符,请重新输入");
                }
            }
        }
    }
}
