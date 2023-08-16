package com.powernode.spring6.test;

import com.powernode.spring6.bean.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Solider
 * @version 3.0
 * @since 1.0
 */
public class BeanLifecycleTest {

    @Test
    public void testBeanLifecycle() {
        /**
         * Spring容器只对singleton的Bean进行完整的生命周期管理
         * 如果是prototype作用域的Bean，Spring容器只负责将该Bean初始化完毕
         *      等客户端程序一旦获取到该Bean之后，Spring容器就不再管理该对象的生命周期了
         */
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("spring.xml");
        User user = applicationContext.getBean("user", User.class);
        System.out.println("第八步、使用User对象：" + user);

        // 手动销毁IoC容器
        ClassPathXmlApplicationContext context = (ClassPathXmlApplicationContext) applicationContext;
        context.close();
    }
}
