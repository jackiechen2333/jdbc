package com.cx5.blob;

import com.cx3.util.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Description:
 *
 * @Author cx
 * @Data 2023/2/20-9:27
 * @Version 2022.2 1.8
 */
public class InsertTest {
    @Test
    public void testInsert1(){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtils.getConnection();
            String sql = "insert into goods(name) values(?)";
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < 20000; i++) {
                preparedStatement.setObject(i + 1,"name_" + i + 1);
                preparedStatement.execute();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection,preparedStatement);
        }
    }

    @Test
    public void testInsert2(){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtils.getConnection();
            String sql = "insert into goods(name) values(?)";
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < 20000; i++) {
                preparedStatement.setObject(1,"name_" + i + 1);

                preparedStatement.addBatch();
                if(i % 500 == 0){
                    preparedStatement.executeBatch();
                    preparedStatement.clearBatch();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection,preparedStatement);
        }
    }

    @Test
    public void testInsert3(){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtils.getConnection();
            connection.setAutoCommit(false);
            String sql = "insert into goods(name) values(?)";
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < 20000; i++) {
                preparedStatement.setObject(1,"name_" + i + 1);

                preparedStatement.addBatch();
                if(i % 500 == 0){
                    preparedStatement.executeBatch();
                    preparedStatement.clearBatch();
                }
            }
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection,preparedStatement);
        }
    }
}
