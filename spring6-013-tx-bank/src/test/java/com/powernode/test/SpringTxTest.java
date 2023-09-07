package com.powernode.test;

import com.powernode.config.SpringConfig;
import com.powernode.pojo.Account;
import com.powernode.service.AccountService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;

/**
 * @author Solider
 * @version 1.0
 * @Date 2023/9/6 17:12
 * @since 1.0
 */
public class SpringTxTest {

    @Test
    public void testNoXML() throws SQLException {
        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(SpringConfig.class);
        AccountService accountService = applicationContext.getBean("accountServiceImpl", AccountService.class);
        accountService.transfer("act-003", "act-005", 1000.0);
    }

    @Test
    public void testPropagation() {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("spring6.xml");
        AccountService accountService =
                applicationContext.getBean("accountServiceImpl", AccountService.class);
        accountService.save(new Account("act-003", 5000.0));
    }

    @Test
    public void testTimeOut() {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("spring6.xml");
        AccountService accountService =
                applicationContext.getBean("accountServiceImpl", AccountService.class);
        accountService.withdraw();
    }
}
