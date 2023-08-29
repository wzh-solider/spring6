package org.powernode.dao.impl;

import jakarta.annotation.Resource;
import org.powernode.dao.OrderDao;
import org.springframework.stereotype.Repository;

/**
 * @author Solider
 * @version 1.0
 * @Date 2023/8/29 18:47
 * @since 1.0
 */
@Repository
public class OrderDaoImplForMySQL implements OrderDao {

    @Resource
    @Override
    public void insert() {
        System.out.println("MySQL正在存储用户数据。。。");
    }
}
