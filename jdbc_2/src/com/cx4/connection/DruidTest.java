package com.cx4.connection;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

/**
 * Description:
 *
 * @Author cx
 * @Data 2023/2/21-0:31
 * @Version 2022.2 1.8
 */
public class DruidTest {
    @Test
    public void getConnection() throws Exception {
        Properties properties = new Properties();

        InputStream resourceAsStream = ClassLoader.getSystemClassLoader().getResourceAsStream("druid.properties");
        properties.load(resourceAsStream);

        DataSource source = DruidDataSourceFactory.createDataSource(properties);
        Connection connection = source.getConnection();
        System.out.println(connection);

    }
}
