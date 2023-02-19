package com.cx3.PrepareStatement.crud;

import com.cx3.bean.Order;
import com.cx3.util.JDBCUtils;
import com.sun.javafx.robot.FXRobotImage;
import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.*;

/**
 * Description:
 *
 * @Author cx
 * @Data 2023/2/19-21:35
 * @Version 2022.2 1.8
 */
public class OrderForQuery {

    @Test
    public void testOrderForQuery(){
        String sql = "select Order_id orderId,Order_name orderName,Order_date orderDate from `order` where Order_id = ?";
        Order order = orderForQuery(sql, 1);
        System.out.println(order);
    }

    /**
     * @Description:针对order表的通用查询操作
     * @author:cx
     * @data:2023/2/19-21:44
     */
    public Order orderForQuery(String sql, Object... args){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }
            resultSet = preparedStatement.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            if (resultSet.next()) {
                Order order = new Order();
                for (int i = 0; i < columnCount; i++) {
                    Object columnValue = resultSet.getObject(i + 1);
                    String columnLabel = metaData.getColumnLabel(i + 1);
                    Field declaredField = Order.class.getDeclaredField(columnLabel);
                    declaredField.setAccessible(true);
                    declaredField.set(order,columnValue);
                }
                return order;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection,preparedStatement,resultSet);
        }

        return null;
    }

    @Test
    public void testQuery1() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtils.getConnection();
            String sql = "select Order_id,Order_name,Order_date from `order` where Order_id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, 1);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int id = (int) resultSet.getObject(1);
                String name = (String) resultSet.getObject(2);
                Date date = (Date) resultSet.getObject(3);

                Order order = new Order(id, name, date);
                System.out.println(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection, preparedStatement, resultSet);
        }

    }
}
