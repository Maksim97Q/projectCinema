package com.cinema.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {
    private static final String url = "jdbc:mysql://127.0.0.1:3306/public";
    private static final String username = "root";
    private static final String password = "password";
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static Connection conn;

    public static Connection connection() {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void close() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                try {
                    throw new Exception("НЕ закрылся", e);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
