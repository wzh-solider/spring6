package com.powernode.test;

import com.powernode.service.UserService;
import org.junit.Test;
import org.myspringframework.core.ApplicationContext;
import org.myspringframework.core.impl.ClassPathXmlApplicationContext;

/**
 * @author Solider
 * @version 1.0
 * @Date 2023/8/23 2:16
 * @since 1.0
 */
public class MySpringTest {

    @Test
    public void testUserService() {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("myspring.xml");
        UserService userServiceBean = (UserService)applicationContext.getBean("userServiceBean");
        userServiceBean.save();
    }
}
