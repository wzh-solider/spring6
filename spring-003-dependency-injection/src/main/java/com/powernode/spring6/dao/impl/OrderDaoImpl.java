package com.powernode.spring6.dao.impl;

import com.powernode.spring6.dao.OrderDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

/**
 * @author Solider
 * @version 1.0
 * @since 1.0
 */
public class OrderDaoImpl implements OrderDao {

    private static final Logger logger = LoggerFactory.getLogger(OrderDaoImpl.class);

    @Override
    public void deleteById() {
        logger.info("订单删除。。。");
    }

    @Override
    public void generate() {
        logger.info("订单正在生成。。。");
    }


}
