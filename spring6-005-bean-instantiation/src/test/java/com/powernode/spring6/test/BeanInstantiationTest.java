package com.powernode.spring6.test;

import com.powernode.spring6.bean.*;
import com.powernode.spring6.factory.GunFactory;
import com.powernode.spring6.factory.PersonFactoryBean;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

/**
 * @author Solider
 * @version 1.0
 * @since 1.0
 */
public class BeanInstantiationTest {

    /**
     * 通过构造方法实例化Bean对象
     */
    @Test
    public void TestInstantiation_01() {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("spring.xml");
        User userBean = applicationContext.getBean("userBean", User.class);
    }

    /**
     * 通过简单工厂类实例化Bean对象
     */
    @Test
    public void TestInstantiation_02() {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("spring.xml");
        Vip vip = applicationContext.getBean("vipBean", Vip.class);
    }

    /**
     * 通过工厂方法设计模式实例化Bean对象
     */
    @Test
    public void TestInstantiation_03() {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("spring.xml");
        Gun gun = applicationContext.getBean("gun", Gun.class);
        System.out.println(gun);
    }

    /**
     * 通过实现FactoryBean接口的方法，实例化Bean对象
     */
    @Test
    public void TestInstantiation_04() throws Exception {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("spring.xml");
        Person person = applicationContext.getBean("personFactory", Person.class);
        System.out.println(person);
    }

    /**
     * 使用factoryBean注入Date
     */
    @Test
    public void testInstantiationDateByFactoryBean() throws Exception {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("spring.xml");
        Student student = applicationContext.getBean("student", Student.class);
        System.out.println(student);
    }
}
