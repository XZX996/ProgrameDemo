package com.company;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException {
	// write your code here

        Scanner sc = new Scanner(System.in);
        System.out.println("请输入数据库地址？");
        String db = sc.nextLine();
        System.out.println("请输入用户名？");
        String name = sc.nextLine();
        System.out.println("请输入密码？");
        String pwd = sc.nextLine();

        JdbcHelp test = new JdbcHelp(db,name,pwd);
        System.out.println(test.getUrl()+":"+test.getUsername()+":"+test.getPassword());
        test.getConn();
        test.close();
    }
}
