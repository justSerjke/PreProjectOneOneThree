package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    String createUsersTable = "CREATE TABLE IF NOT EXISTS USER"
            + "(id BIGINT PRIMARY KEY not null auto_increment,"
            + " name VARCHAR(50), "
            + " lastname VARCHAR(50), "
            + " age int)";
    String dropUsersTable = "DROP TABLE IF EXISTS USER";
    String cleanUsersTable = "TRUNCATE TABLE USER";
    String saveUser = "INSERT INTO USER(name, lastName, age) VALUES (?, ?, ?)";
    String removeUserById = "DELETE FROM USERS WHERE id=?";
    String getAllUsers = "SELECT * FROM USER";

    Connection connection = Util.getConnection();

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(createUsersTable);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void dropUsersTable() {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(dropUsersTable);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(saveUser);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void removeUserById(long id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(removeUserById);
            preparedStatement.setLong(1, id);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public List<User> getAllUsers() {
        List<User> result = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getAllUsers);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastname"));
                user.setAge(resultSet.getByte("age"));
                result.add(user);
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    public void cleanUsersTable() {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(cleanUsersTable);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
