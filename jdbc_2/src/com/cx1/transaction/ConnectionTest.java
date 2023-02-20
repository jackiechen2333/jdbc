package com.cx1.transaction;

import com.cx1.util.JDBCUtils;
import org.junit.Test;

import javax.xml.bind.annotation.XmlAnyAttribute;
import java.sql.Connection;

/**
 * Description:
 *
 * @Author cx
 * @Data 2023/2/20-10:20
 * @Version 2022.2 1.8
 */
public class ConnectionTest {
    @Test
    public void testGetConnection() throws Exception{
        Connection connection = JDBCUtils.getConnection();
        System.out.println(connection);
    }
}
