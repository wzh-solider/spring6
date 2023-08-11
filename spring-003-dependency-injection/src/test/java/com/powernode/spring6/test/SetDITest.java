package com.powernode.spring6.test;

import com.powernode.spring6.bean.SimpleValueType;
import com.powernode.spring6.bean.Student;
import com.powernode.spring6.bean.User;
import com.powernode.spring6.service.OrderService;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;


/**
 * @author Solider
 * @version 1.0
 * @since 1.0
 */
public class SetDITest {

    private static final Logger logger = LoggerFactory.getLogger(SetDITest.class);

    @Test
    public void testCascade() {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("cascade.xml");
        Student student = applicationContext.getBean("studentBean", Student.class);
        System.out.println(student);
    }

    @Test
    public void testDataSource() {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("set-di.xml");
        DataSource dataSource =
                applicationContext.getBean("myDataSourceBean", DataSource.class);
        logger.info(dataSource.toString());
    }

    @Test
    public void testSimpleTypeSet2() {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("set-di.xml");
        SimpleValueType simpleValueType =
                applicationContext.getBean("simpleValueTypeBean", SimpleValueType.class);
        logger.info(simpleValueType.toString());
    }

    @Test
    public void testSimpleTypeSet() {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("set-di.xml");
        User user = applicationContext.getBean("userBean", User.class);
        logger.info(user.toString());
    }

    /**
     * 内部Bean
     */
    @Test
    public void testSetDIInterior() {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("set-di.xml");
        OrderService orderService =
                applicationContext.getBean("orderServiceBean2", OrderService.class);
        orderService.generate();
    }

    /**
     * 外部Bean
     */
    @Test
    public void testSetDIWithout() {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("set-di.xml");
        OrderService orderService =
                applicationContext.getBean("orderServiceBean", OrderService.class);
        orderService.generate();
    }
}
