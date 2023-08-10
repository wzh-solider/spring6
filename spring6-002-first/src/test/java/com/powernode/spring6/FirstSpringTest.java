package com.powernode.spring6;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 测试第一个Spring程序
 * @author Solider
 * @version 1.0
 * @since 1.0
 */
public class FirstSpringTest {

    @Test
    public void testBeginInitBean() {
        // spring不是在调用getBean()的时候创建对象
        // spring是在创建ClassPathXmlApplicationContext对象的时候创建Bean对象
        new ClassPathXmlApplicationContext("spring.xml");

        // 1、创建日志记录器对象
        // 获取FirstSpringTest类的日志记录器对象
        Logger logger = LoggerFactory.getLogger(FirstSpringTest.class);

        // 2、记录日志，根据不同的级别输出日志
        logger.info("我是一条日志信息");
        logger.debug("我是一条调试日志");
        logger.warn("我是一条错误日志");
    }

    @Test
    public void testFirstSpringCode() {
        // 1、获取Spring容器对象

        // ApplicationContext: 应用上下文(Spring容器)
        // ApplicationContext: 是一个接口，ClassPathXmlApplicationContext是其中一个实现类
        // ClassPathXmlApplicationContext: 专门从类路径中加载spring配置文件
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("spring.xml", "beans.xml", "xml/beans.xml");

        // 2、根据bean的id，从Spring容器中获取这个对象
        Object userBean = applicationContext.getBean("userBean");
        System.out.println(userBean);

        Object userDaoBean = applicationContext.getBean("userDaoBean");
        System.out.println(userDaoBean);

        Object vipBean = applicationContext.getBean("vipBean");
        System.out.println(vipBean);

        // Date nowTime = (Date)applicationContext.getBean("nowTime");
        // 如果不想进行强转，可以指定返回类型的参数
        Date nowTime = applicationContext.getBean("nowTime", Date.class);
        String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS").format(nowTime);
        System.out.println(format);

    }


}
