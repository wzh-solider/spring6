package com.powernode.service.impl;

import com.powernode.dao.AccountDao;
import com.powernode.pojo.Account;
import com.powernode.service.AccountService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

/**
 * @author Solider
 * @version 1.0
 * @Date 2023/9/6 17:01
 * @since 1.0
 */
@Service
public class AccountServiceImpl2 implements AccountService {

    @Resource(name = "accountDaoImpl")
    private AccountDao accountDao;

    @Override
    public void transfer(String actNo1, String actNo2, double money) throws SQLException {

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void save(Account act) {
        accountDao.insert(act);

        // 模拟异常
        String str = null;
        str.length();
    }
}
