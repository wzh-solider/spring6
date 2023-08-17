package com.powernode.spring6.test;

import com.powernode.spring6.bean.Husband;
import com.powernode.spring6.bean.Wife;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Solider
 * @version 1.0
 * @since 1.0
 */
public class CircularDependencyTest {

    /**
     * set注入
     */
    @Test
    public void testCircularDependBySet() {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("spring6-set.xml");

        Husband husbandBean = applicationContext.getBean("husbandBean", Husband.class);
        Wife wifeBean = applicationContext.getBean("wifeBean", Wife.class);

        System.out.println(husbandBean);
        System.out.println(wifeBean);
    }

    /**
     * 构造方法注入
     */
    @Test
    public void testCircularDependByConstructor() {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("spring6-constructor.xml");
        com.powernode.spring6.bean2.Wife wife = applicationContext.getBean("wife", com.powernode.spring6.bean2.Wife.class);
        com.powernode.spring6.bean2.Husband husband = applicationContext.getBean("husband", com.powernode.spring6.bean2.Husband.class);
        System.out.println(wife);
        System.out.println(husband);
    }
}
