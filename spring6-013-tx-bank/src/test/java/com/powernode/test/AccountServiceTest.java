package com.powernode.test;

import com.powernode.service.AccountService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;

/**
 * @author Solider
 * @version 1.0
 * @Date 2023/9/6 14:33
 * @since 1.0
 */
public class AccountServiceTest {
    @Test
    public void testTransfer() throws SQLException {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("spring6.xml");
        applicationContext.getBean("accountServiceImpl", AccountService.class)
                .transfer("act-002", "act-001", 100.0);
    }
}
