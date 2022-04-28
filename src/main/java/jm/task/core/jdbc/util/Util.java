package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    public static Connection getConnection() {
        // реализуйте настройку соеденения с БД
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/mydb", "root", "root");
            System.out.println("Connection to Store DB succesfull!");
        } catch (SQLException e) {
            System.out.println("Connection failed..." + e);
        }
        return connection;
    }
}