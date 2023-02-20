package com.cx3.dao;

import com.cx2.bean.Customer;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

/**
 * Description:此接口用于规范针对于customers的常用操作
 *
 * @Author cx
 * @Data 2023/2/20-23:39
 * @Version 2022.2 1.8
 */
public interface CustomerDAO {
    void insert(Connection connection, Customer customer);

    void deleteById(Connection connection, int id);

    void update(Connection connection, Customer customer);

    Customer getCustomerById(Connection connection, int id);

    List<Customer> getAll(Connection connection);

    Long getCount(Connection connection);

    Date getMaxBirth(Connection connection);
}
