package com.powernode.spring6.test;

import com.powernode.spring6.bean.*;
import com.powernode.spring6.dao.OrderDao;
import com.powernode.spring6.service.OrderService;
import com.powernode.spring6.service.UserService;
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

    /**
     * 从外部引入配置文件
     */
    @Test
    public void testExternalImport() {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("spring-properties.xml");
        MyDataSource myDataSource =
                applicationContext.getBean("mds", MyDataSource.class);
        logger.info(myDataSource.toString());
    }

    /**
     * 根据类型进行自动装配
     */
    @Test
    public void testAutowireByType() {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("spring-autowire.xml");
        UserService userService =
                applicationContext.getBean("userServiceBean", UserService.class);
        userService.save();
    }

    /**
     * 根据名字进行自动装配
     */
    @Test
    public void testAutowireByName() {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("spring-autowire.xml");
        OrderDao orderDao = applicationContext.getBean("orderDao", OrderDao.class);
        System.out.println(orderDao);
    }

    /**
     * 测试c命名空间注入
     */
    @Test
    public void testCNameSpace() {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("spring-c.xml");
        Cat cat = applicationContext.getBean("catBean", Cat.class);
        System.out.println(cat);
    }

    /**
     * 测试p命名空间注入
     */
    @Test
    public void testPNameSpace() {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("spring-p.xml");
        Dog dog = applicationContext.getBean("dogBean", Dog.class);
        System.out.println(dog);
    }

    /**
     * 测试特殊符号注入
     */
    @Test
    public void testSpecialSymbol() {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("set-di.xml");
        MathBean result = applicationContext.getBean("mathBean", MathBean.class);
        System.out.println(result);
    }

    /**
     * 测试Collection集合注入
     */
    @Test
    public void testCollectionSet() {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("spring-collection.xml");
        Person person = applicationContext.getBean("personBean", Person.class);
        System.out.println(person);
    }

    /**
     * 测试数组注入
     */
    @Test
    public void testArraySet() {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("spring-arr.xml");
        QianDaYe yuQian = applicationContext.getBean("yuQian", QianDaYe.class);
        System.out.println(yuQian);
    }

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
