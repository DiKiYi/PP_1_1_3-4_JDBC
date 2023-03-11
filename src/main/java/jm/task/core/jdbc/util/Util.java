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

    public static Connection getConnections() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

//    public static Configuration getConfiguration () {
//        Configuration configuration = new Configuration();
//        Properties properties = new Properties();
//        properties.put(Environment.URL, "jdbc:mysql://localhost:3306/test1");
//        properties.put(Environment.USER, "root");
//        properties.put(Environment.PASS, "root");
//        properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
//        properties.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
//        properties.put(Environment.SHOW_SQL, "true");
//        properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
//        properties.put(Environment.HBM2DDL_AUTO, "create-drop");
//        configuration.setProperties(properties);
//
//
//    }


//     реализуйте настройку соеденения с БД
}
