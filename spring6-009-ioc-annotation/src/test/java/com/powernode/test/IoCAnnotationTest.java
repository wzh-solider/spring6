package com.powernode.test;

import cn.powernode.Spring6Config;
import cn.powernode.service.StudentService;
import com.powernode.bean3.User;
import com.powernode.bean3.MyDataSource;
import com.powernode.controller.UserController;
import com.powernode.service.VipService;
import org.junit.Test;
import org.powernode.service.OrderService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Solider
 * @version 1.0
 * @Date 2023/8/25 18:57
 * @since 1.0
 */
public class IoCAnnotationTest {

    /**
     * 测试Spring配置"类"
     */
    @Test
    public void testSpring6Config() {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(Spring6Config.class);
        context.getBean("studentServiceImpl", StudentService.class).delete();
    }

    @Test
    public void testDIAnnotationByResource() {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("spring6-resource.xml");
        StudentService studentService = applicationContext.getBean("studentServiceImpl", StudentService.class);
        studentService.delete();
    }

    @Test
    public void testDIAnnotationByAutowired() {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("spring6-autowired.xml");
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);
        orderService.generate();
    }

    @Test
    public void testDIAnnotation() {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("spring6-di-annotation.xml");
        MyDataSource myDataSource = applicationContext.getBean("myDataSource", MyDataSource.class);
        System.out.println(myDataSource);

        User user = applicationContext.getBean("user", User.class);
        System.out.println(user);
    }

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
