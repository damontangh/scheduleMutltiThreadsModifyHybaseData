package com.trs.damon.repository;

import com.trs.damon.entity.CaibianMysql;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcUtil {
    // JDBC driver name and database URL
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://10.100.63.160:3306/trswcmtest";

    //  Database credentials
    private static final String USER = "trs";
    private static final String PASS = "MLF@trs123";
    private static Connection connection = null;

    static {
        try {
            Class.forName(JDBC_DRIVER);
            connection = connection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Connection connection(){
        try {

           Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
           return conn;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<CaibianMysql> getRecords(int mlfDocid){
        PreparedStatement pstmt = null;
        List<CaibianMysql> list = new ArrayList<>();
        try {
            String sql = "select * from xwcmexternalpubdocrecord where DOCID = ?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1,mlfDocid);
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()){
                CaibianMysql c = new CaibianMysql();
                c.setDOCID(resultSet.getInt("DOCID"));
                c.setEXTPUBID(resultSet.getInt("EXTPUBID"));
                c.setEXTURL(resultSet.getString("EXTURL"));
                list.add(c);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void close(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
