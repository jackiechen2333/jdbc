package com.cx4.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.commons.dbutils.DbUtils;

import javax.sql.DataSource;
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

    public static void closeResource1(Connection connection, Statement preparedStatement, ResultSet resultSet){
//        try {
//            DbUtils.close(connection);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        try {
//            DbUtils.close(preparedStatement);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        try {
//            DbUtils.close(resultSet);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }

        DbUtils.closeQuietly(connection);
        DbUtils.closeQuietly(preparedStatement);
        DbUtils.closeQuietly(resultSet);
    }
    
    private static DataSource source;
    static {
        try {
            Properties properties = new Properties();

            InputStream resourceAsStream = ClassLoader.getSystemClassLoader().getResourceAsStream("druid.properties");
            properties.load(resourceAsStream);

            source = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static Connection getConnection1() throws SQLException {
        Connection connection = source.getConnection();
        return connection;
    }
    
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


}
