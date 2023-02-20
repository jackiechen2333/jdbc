package com.cx1.util;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * Description:
 * @Author cx
 * @Data 2023/2/19-16:04
 * @Version 2022.2 1.8
 */


public class JDBCUtils {
    /**
     * @Description:获取数据库的连接
     * @author:cx
     * @data:2023/2/19-16:16
     */
    public static Connection getConnection() throws Exception{
        //1.读取配置文件中的4个基本信息
        InputStream resourceAsStream = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");

        Properties properties = new Properties();
        properties.load(resourceAsStream);

        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String url = properties.getProperty("url");
        String driverClass = properties.getProperty("driverClass");

        //2.加载驱动
        Class.forName(driverClass);
//        Driver driver = (Driver) clazz.newInstance();
//        DriverManager.registerDriver(driver);
        //3.获取连接
        Connection connection = DriverManager.getConnection(url, user, password);
        return connection;
    }

    public static void closeResource(Connection connection){
        try {
            if(connection != null)
                connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void closeResource(Statement preparedStatement){
        try {
            if(preparedStatement != null)
                preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void closeResource(Connection connection, Statement preparedStatement){
        try {
            if(preparedStatement != null)
                preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            if(connection != null)
                connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void closeResource(Connection connection, Statement preparedStatement, ResultSet resultSet){
        try {
            if(preparedStatement != null)
                preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            if(connection != null)
                connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            if (resultSet != null)
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static void closeResource(PreparedStatement preparedStatement, ResultSet resultSet) {
        try {
            if(preparedStatement != null)
                preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            if (resultSet != null)
                resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
