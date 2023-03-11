package jm.task.core.jdbc.util;

import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {

    private static final String URL = "jdbc:mysql://localhost:3306/test1";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    public static Connection getConnections(){
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

//    public static Configuration configuration () {
//        Configuration configuration = new Configuration();
//        Properties properties = new Properties();
//        properties.put(Environment.URL, "jdbc:mysql://localhost:3306/test1");
//        properties.put(Environment.USER, "root");
//        properties.put(Environment.PASS, "root");
//        properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
//        configuration.setProperties(properties);
//
//
//    }


    // реализуйте настройку соеденения с БД
}
