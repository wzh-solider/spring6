package com.powernode.service.impl;

import com.powernode.dao.AccountDao;
import com.powernode.pojo.Account;
import com.powernode.service.AccountService;
import jakarta.annotation.Resource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

/**
 * @author Solider
 * @version 1.0
 * @Date 2023/9/6 10:59
 * @since 1.0
 */
@Service
public class AccountServiceImpl implements AccountService {
    @Resource(name = "accountDaoImpl")
    private AccountDao accountDao;

    @Override
    @Transactional
    public void transfer(String actNo1, String actNo2, double money) throws SQLException {

        try {
            // 转入转出用户
            Account fromAct = accountDao.selectActNo(actNo1);
            if (fromAct.getBalance() < money) {
                throw new RuntimeException("余额不足");
            }
            Account toAct = accountDao.selectActNo(actNo2);
            fromAct.setBalance(fromAct.getBalance() - money);
            toAct.setBalance(toAct.getBalance() + money);

            // 模拟异常
            String str = null;

            // 更新
            int count = accountDao.updateAct(fromAct);
            count += accountDao.updateAct(toAct);

            if (count != 2) {
                throw new RuntimeException("转账失败");
            }
            System.out.println("转账成功");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Resource(name = "accountServiceImpl2")
    private AccountService accountService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void save(Account act) {
        accountDao.insert(act);

        Account account = new Account("act-004", 1000.0);
        try {
            accountService.save(account);
        } catch (Exception e) {

        }

        // 继续进行自己本身的事务
    }

    @Transactional(timeout = 10)
    public void withdraw() {
        // 先执行DML语句
        accountDao.insert(new Account("act-005", 1000.0));

        /**
         * 睡眠15秒
         */
        try {
            Thread.sleep(15 * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // 下面没有DML语句，所以不会超时异常
    }
}
