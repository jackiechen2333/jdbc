package com.cx5.blob;

import com.cx3.bean.Customer;
import com.cx3.util.JDBCUtils;
import org.junit.Test;

import javax.crypto.interfaces.PBEKey;
import java.io.*;
import java.sql.*;

/**
 * Description:
 *
 * @Author cx
 * @Data 2023/2/20-8:47
 * @Version 2022.2 1.8
 */
public class BlobTest {

    @Test
    public void testSelect(){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        InputStream inputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            connection = JDBCUtils.getConnection();

            String sql = "select id,name,email,birth,photo from customers where id = ?";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, 21);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String email = resultSet.getString(3);
                Date birth = resultSet.getDate(4);

                Customer customer = new Customer(id, name, email, birth);
                System.out.println(customer);

                Blob photo = resultSet.getBlob("photo");
                inputStream = photo.getBinaryStream();
                fileOutputStream = new FileOutputStream("../zhangyuhao.jpg");
                byte[] buffer = new byte[1024];
                int len;
                while ((len = inputStream.read(buffer)) != -1) {
                    fileOutputStream.write(buffer, 0, len);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(inputStream != null)
                inputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                if(fileOutputStream != null)
                fileOutputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            JDBCUtils.closeResource(connection,preparedStatement,resultSet);
        }
    }

    @Test
    public void testInsert() throws Exception {
        Connection connection = JDBCUtils.getConnection();

        String sql = "insert into customers(name,email,birth,photo) values(?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setObject(1, "张宇豪");
        preparedStatement.setObject(2, "zhang@qq.com");
        preparedStatement.setObject(3, "1992-09-08");
        FileInputStream fileInputStream = new FileInputStream(new File("../playgirl.jpg"));
        preparedStatement.setBlob(4, fileInputStream);

        preparedStatement.execute();

        JDBCUtils.closeResource(connection, preparedStatement);

    }
}
