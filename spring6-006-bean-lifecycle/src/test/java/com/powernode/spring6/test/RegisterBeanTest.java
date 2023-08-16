package com.powernode.spring6.test;

import com.powernode.spring6.bean.Person;
import org.junit.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

/**
 * @author Solider
 * @version 1.0
 * @since 1.0
 */
public class RegisterBeanTest {

    @Test
    public void testBeanRegister() {

        // 自己new对象
        Person person = new Person();
        System.out.println(person);

        // 创建 默认的可列表Bean工厂
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        // 注册单例Bean
        factory.registerSingleton("personBean", person);

        // 从Spring容器中获取Bean
        Person personBean = factory.getBean("personBean", Person.class);
        System.out.println(personBean);
    }
}
