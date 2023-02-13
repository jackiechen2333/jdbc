package com.cx.connnection;

import org.junit.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author cx
 * @data 2023-02-{DAY}-{TIME}
 */
public class ConnectionTest {
    @Test
    public void testConnection1() throws SQLException {
        Driver driver = new com.mysql.jdbc.Driver();

        String url = "jdbc:mysql://localhost:3306/fruitdb";

        Properties info = new Properties();
        info.setProperty("user","root");
        info.setProperty("password","123456");

        Connection connection = driver.connect(url, info);

        System.out.println(connection);
    }

    @Test
    public void testConnection2() throws Exception {
        Class clazz = Class.forName("com.mysql.jdbc.Driver");
        Driver driver = (Driver) clazz.newInstance();

        String url = "jdbc:mysql://localhost:3306/fruitdb";

        Properties info = new Properties();
        info.setProperty("user","root");
        info.setProperty("password","123456");

        Connection connection = driver.connect(url, info);

        System.out.println(connection);
    }

    @Test
    public void testConnection3() throws Exception {
        Class clazz = Class.forName("com.mysql.jdbc.Driver");
        Driver driver = (Driver) clazz.newInstance();

        String url = "jdbc:mysql://localhost:3306/fruitdb";
        String user = "root";
        String password = "123456";

        DriverManager.registerDriver(driver);

        Connection connection = DriverManager.getConnection(url, user, password);

        System.out.println(connection);
    }

    @Test
    public void testConnection4() throws Exception {
        String url = "jdbc:mysql://localhost:3306/fruitdb";
        String user = "root";
        String password = "123456";

        Class.forName("com.mysql.jdbc.Driver");
//        Driver driver = (Driver) clazz.newInstance();
//        DriverManager.registerDriver(driver);

        Connection connection = DriverManager.getConnection(url, user, password);

        System.out.println(connection);
    }
    @Test
    public void testConnection5() throws Exception {
        InputStream resourceAsStream = ConnectionTest.class.getClassLoader().getResourceAsStream("jdbc.properties");

        Properties properties = new Properties();
        properties.load(resourceAsStream);

        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String url = properties.getProperty("url");
        String driverClass = properties.getProperty("driverClass");

        Class.forName(driverClass);
//        Driver driver = (Driver) clazz.newInstance();
//        DriverManager.registerDriver(driver);

        Connection connection = DriverManager.getConnection(url, user, password);

        System.out.println(connection);
    }
}
