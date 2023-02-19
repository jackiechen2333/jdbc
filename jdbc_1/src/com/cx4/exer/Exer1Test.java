package com.cx4.exer;

import com.cx3.util.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

/**
 * Description:
 *
 * @Author cx
 * @Data 2023/2/19-23:34
 * @Version 2022.2 1.8
 */
public class Exer1Test {
    @Test
    public void testInsert(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入用户名：");
        String name = scanner.next();
        System.out.print("请输入邮箱：");
        String email = scanner.next();
        System.out.print("请输入生日：");
        String birthday = scanner.next();//'1992-09-08'

        String sql = "insert into customers(name,email,birth)values(?,?,?)";
        int insertCount = update(sql, name, email, birthday);
        if(insertCount > 0){
            System.out.println("添加成功");

        }else{
            System.out.println("添加失败");
        }

    }

    public int update(String sql, Object... args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }
            return preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection, preparedStatement);
        }
        return 0;
    }
}
