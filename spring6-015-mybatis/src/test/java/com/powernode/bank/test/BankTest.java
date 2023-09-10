package com.powernode.bank.test;

import com.powernode.bank.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.FileNotFoundException;

/**
 * @author Solider
 * @version 1.0
 * @Date 2023/9/10 22:35
 * @since 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class BankTest {

    @Autowired
    @Qualifier("accountServiceImpl")
    AccountService accountService;

    @Test
    public void testAccountService() throws FileNotFoundException {
        try {
            accountService.transfer("act-001", "act-002", 1000.00);
            System.out.println("转账成功！！！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
