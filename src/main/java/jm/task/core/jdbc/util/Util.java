package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    private static Connection connection;

    public static Connection getConnection() {
        // реализуйте настройку соеденения с БД
        if (connection == null) {
            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost/mydb", "root", "root");
                System.out.println("Connection to DB succesfull!");
                connection.setAutoCommit(false);
            } catch (SQLException e) {
                System.out.println("Connection failed..." + e);
            }
        }
        return connection;
    }
}