package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        try (Connection connection = Util.getConnections(); Statement statement = connection.createStatement()) {
            statement.execute("create table users (id int auto_increment,name " +
                    "varchar(40) null,lastName varchar(40) null,age int null, " +
                    "constraint users_pk primary key (id));");
        } catch (SQLException e) {
        }

    }

    public void dropUsersTable() {
        try (Connection connection = Util.getConnections(); Statement statement = connection.createStatement()) {
            statement.execute("drop table users;");
        } catch (SQLException e) {
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = Util.getConnections(); Statement statement = connection.createStatement()) {
            statement.execute("INSERT INTO users (name, lastName, age) VALUES " +
                    "('" + name + "', '" + lastName + "', " + age + ")");
            System.out.println("User с именем – " + name +" добавлен в базу данных");
//            PreparedStatement preparedStatement = connection.prepareStatement()
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void removeUserById(long id) {
        try (Connection connection = Util.getConnections(); Statement statement = connection.createStatement()) {
            statement.execute("DELETE FROM users WHERE id = " + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<User> getAllUsers() {
        List<User> bufList = new ArrayList<>();
        try (Connection connection = Util.getConnections(); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select * from users");
            while (resultSet.next()) {
                User bufUser = new User(resultSet.getString("name"), resultSet.getString("lastName"), resultSet.getByte("age"));
                bufList.add(bufUser);
                bufUser.setId(resultSet.getLong("id"));
            }
            return bufList;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public void cleanUsersTable() {
        try (Connection connection = Util.getConnections(); Statement statement = connection.createStatement()) {
            statement.execute("TRUNCATE users;");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
