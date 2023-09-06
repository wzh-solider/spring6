package com.powernode.test;

import com.powernode.dao.AccountDao;
import com.powernode.pojo.Account;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Solider
 * @version 1.0
 * @Date 2023/9/6 14:04
 * @since 1.0
 */
public class AccountDaoTest {
    @Test
    public void testSelectActNo() {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("spring6.xml");
        AccountDao accountDaoImpl = applicationContext.getBean("accountDaoImpl", AccountDao.class);
        Account account = accountDaoImpl.selectActNo("act-001");
        System.out.println(account);
    }

    @Test
    public void testUpdateAct() {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("spring6.xml");
        AccountDao accountDaoImpl = applicationContext.getBean("accountDaoImpl", AccountDao.class);
        int i = accountDaoImpl.updateAct(new Account("act-001", 0.0));
        if (i > 0) {
            System.out.println("更新成功");
        } else {
            System.out.println("更新失败");
        }
    }
}
