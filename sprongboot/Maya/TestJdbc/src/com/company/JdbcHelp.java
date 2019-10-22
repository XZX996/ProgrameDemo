package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcHelp {
    //数据库连接对象
    private static Connection conn = null;

    private static String driver = "oracle.jdbc.driver.OracleDriver"; //驱动

    private  String url;// = "jdbc:oracle:thin:@//127.0.0.1:1521/orcl"; //连接字符串

    private  String username ;//= "system"; //用户名

    private  String password ;//= "123456"; //密码

    public  String getUrl() {
        return url;
    }

    public  void setUrl(String url) {
        this.url = "jdbc:oracle:thin:@//"+url;
    }

    public  String getUsername() {
        return username;
    }

    public  void setUsername(String username) {
        this.username = username;
    }

    public  String getPassword() {
        return password;
    }

    public  void setPassword(String password) {
        this.password = password;
    }

    public JdbcHelp(String url, String name, String pwd){
        setUsername(name);
        setPassword(pwd);
        setUrl(url);
    }

    // 获得连接对象
    public   synchronized Connection getConn(){
        if(conn == null){
            try {
                Class.forName(driver);
                conn = DriverManager.getConnection(this.getUrl(), this.username, this.password);
                System.out.println("连接成功:"+this.url+"用户名"+this.username+"密码:"+this.password
                );
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }

   /* //执行查询语句
    public void query(String sql, boolean isSelect) throws SQLException{
        PreparedStatement pstmt;

        try {
            pstmt = getConn().prepareStatement(sql);
            //建立一个结果集，用来保存查询出来的结果
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                System.out.println(name);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void query(String sql) throws SQLException{
        PreparedStatement pstmt;
        pstmt = getConn().prepareStatement(sql);
        pstmt.execute();
        pstmt.close();
    }
*/

    //关闭连接
    public void close(){
        try {
            getConn().close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
