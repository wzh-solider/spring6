package com.powernode.spring6.bean;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Solider
 * @version 1.0
 * @since 1.0
 */
public class SpringBeanScopeTest {

    @Test
    public void testMyThreadScope() {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("spring-scope.xml");
        SpringBean springBean = applicationContext.getBean("springBean", SpringBean.class);
        System.out.println("springBean" + springBean);
        SpringBean springBean1 = applicationContext.getBean("springBean", SpringBean.class);
        System.out.println("springBean" + springBean1);

        new Thread(new Runnable() {
            @Override
            public void run() {
                SpringBean springBean2 = applicationContext.getBean("springBean", SpringBean.class);
                System.out.println("springBean" + springBean2);
                SpringBean springBean3 = applicationContext.getBean("springBean", SpringBean.class);
                System.out.println("springBean" + springBean3);
            }
        }).start();
    }

    @Test
    public void testBeanScope() {
        /**
         * 1、Spring默认情况下Bean是单例的(单例：singleton)
         *     在Spring上下文对象被创建的时候，Bean会被实例化
         *     在实例化之后的调用每一次get方法，都只会返回那个被实例化的对象
         * 2、当xml文件中bean的scope属性被设置为"prototype"时
         *     Bean是多例的【prototype：原型 | 多例】
         *     此时，Spring上下文对象被创建的时候，Bean不会在被实例化
         *     而是，在每次调用get方法时，才会实例化对象
         */
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("spring-scope.xml");

        SpringBean springBean = applicationContext.getBean("springBean", SpringBean.class);
        System.out.println(springBean);

        SpringBean springBean2 = applicationContext.getBean("springBean", SpringBean.class);
        System.out.println(springBean2);
    }
}
