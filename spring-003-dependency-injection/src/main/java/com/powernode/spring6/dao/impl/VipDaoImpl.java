package com.powernode.spring6.dao.impl;

import com.powernode.spring6.dao.UserDao;
import com.powernode.spring6.dao.VipDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Solider
 * @version 1.0
 * @since 1.0
 */
public class VipDaoImpl implements VipDao {

    private static final Logger logger = LoggerFactory.getLogger(VipDaoImpl.class);

    @Override
    public void insert() {
        logger.info("正在保存vip信息");
    }
}
