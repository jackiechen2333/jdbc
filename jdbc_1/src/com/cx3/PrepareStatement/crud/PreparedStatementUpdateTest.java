package com.cx3.PrepareStatement.crud;

import com.cx3.util.JDBCUtils;
import org.junit.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * @author cx
 * @version 2022.2 1.8
 * @data 2023/2/19-15:39
 * @description IntelliJ IDEA
 */
public class PreparedStatementUpdateTest {

    @Test
    public void testCommonUpdate(){
//        String sql = "delete from customers where id = ?";
//        update(sql,3);

        String sql = "update `order` set order_name = ? where order_id = ?";
        update(sql,"DD",2);
    }

    public void update(String sql,Object ...args){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1,args[i]);
            }
            preparedStatement.execute();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            JDBCUtils.closeResource(connection,preparedStatement);
        }
    }

    @Test
    public void testUpdate(){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            //1.获取数据库的连接
            connection = JDBCUtils.getConnection();
            //2.预编译sql语句，返回PreparedStatement的实例
            String sql = "update customers set name = ? where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            //3.填充占位符
            preparedStatement.setObject(1,"莫扎特");
            preparedStatement.setObject(2,18);
            //4.执行
            preparedStatement.execute();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        //5.资源的关闭
        JDBCUtils.closeResource(connection,preparedStatement);
    }

    @Test
    public void testInsert() throws Exception{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            InputStream resourceAsStream = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");

            Properties properties = new Properties();
            properties.load(resourceAsStream);

            String user = properties.getProperty("user");
            String password = properties.getProperty("password");
            String url = properties.getProperty("url");
            String driverClass = properties.getProperty("driverClass");

            Class.forName(driverClass);
//        Driver driver = (Driver) clazz.newInstance();
//        DriverManager.registerDriver(driver);

            connection = DriverManager.getConnection(url, user, password);

//        System.out.println(connection);
            String sql = "INSERT INTO customers(NAME,email,birth) VALUES(?,?,?)";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1,"哪吒");
            preparedStatement.setString(2,"nezha@gmail.com");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date= simpleDateFormat.parse("2000-01-01");
            preparedStatement.setDate(3,new java.sql.Date(date.getTime()));

            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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


    }
}
