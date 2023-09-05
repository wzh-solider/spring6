package com.powernode.spring6.test;

import com.powernode.config.SpringConfig;
import com.powernode.service.biz.UserService;
import com.powernode.service.transcation.AccountService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Solider
 * @version 1.0
 * @Date 2023/9/5 16:59
 * @since 1.0
 */
public class RealAppTest {

    @Test
    public void testSecurityLog() {
        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(SpringConfig.class);

        UserService userService = applicationContext.getBean("userService", UserService.class);
        userService.saveUser();
        userService.updateUser();
        userService.deleteUser();
        userService.getUser();
    }

    @Test
    public void testTransaction() {
        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(SpringConfig.class);

        AccountService accountService =
                applicationContext.getBean("accountService", AccountService.class);
        accountService.transfer();
        accountService.withdraw();
    }
}
