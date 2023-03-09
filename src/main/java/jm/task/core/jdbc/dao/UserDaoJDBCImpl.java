package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionCreator;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class UserDaoJDBCImpl implements UserDao {
    Connection connection = Util.getConnections();
    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        try {
            connection.createStatement().execute("create table users (id int auto_increment,name " +
                    "varchar(40) null,lastName varchar(40) null,age int null, " +
                    "constraint users_pk primary key (id));");
        } catch (SQLException e) {
        }

    }

    public void dropUsersTable() {
        try {
            connection.createStatement().execute("drop table users;");
        } catch (SQLException e) {
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            connection.createStatement().execute("INSERT INTO users (name, lastName, age) VALUES " +
                    "('" + name + "', '" + lastName + "', " + age + ")");
            System.out.println("User с именем – " + name +" добавлен в базу данных");
//            PreparedStatement preparedStatement = connection.prepareStatement()
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void removeUserById(long id) {
        try {
            connection.createStatement().execute("DELETE FROM users WHERE id = " + id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<User> getAllUsers() {
        List<User> bufList = new ArrayList<>();
        try {
            ResultSet resultSet = connection.createStatement().executeQuery("select * from users");
            while (resultSet.next()) {
                User bufUser = new User(resultSet.getString("name"), resultSet.getString("lastName"), resultSet.getByte("age"));
                bufList.add(bufUser);
                bufUser.setId(resultSet.getLong("id"));
            }
            return bufList;
        } catch (SQLException e){}
        return null;
    }

    public void cleanUsersTable() {
        try {
            connection.createStatement().execute("TRUNCATE users;");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
