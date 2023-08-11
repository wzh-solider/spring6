package com.powernode.spring6.test;

import com.powernode.spring6.service.OrderService;
import com.powernode.spring6.service.UserService;
import com.powernode.spring6.service.impl.UserServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Solider
 * @version 1.0
 * @since 1.0
 */
public class DITest {

    /**
     * 测试构造注入
     */
    @Test
    public void testConstructorDI() {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("spring.xml");
        OrderService orderService = applicationContext.getBean("orderServiceBean", OrderService.class);
        orderService.delete();
    }

    /**
     * 测试set注入
     */
    @Test
    public void testSetDI() {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("spring.xml");
        UserService userService = applicationContext.getBean("userServiceBean", UserService.class);
        userService.save();
    }
}
