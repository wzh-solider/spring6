package com.powernode.test;

import com.powernode.bean.User;
import com.powernode.controller.UserController;
import com.powernode.service.VipService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Solider
 * @version 1.0
 * @Date 2023/8/25 18:57
 * @since 1.0
 */
public class IoCAnnotationTest {

    @Test
    public void testChoose() {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("spring6-choose.xml");

    }

    @Test
    public void testComponent() {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("spring6.xml");
        User userBean = applicationContext.getBean("userBean", User.class);
        System.out.println(userBean);
    }

    @Test
    public void testService() {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("spring6.xml");
        VipService vipServiceBean = applicationContext.getBean("vipServiceBean", VipService.class);
        System.out.println(vipServiceBean);
    }

    @Test
    public void testController() {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("spring6.xml");
        UserController userControllerBean = applicationContext.getBean("userControllerBean", UserController.class);
        System.out.println(userControllerBean);
    }
}
