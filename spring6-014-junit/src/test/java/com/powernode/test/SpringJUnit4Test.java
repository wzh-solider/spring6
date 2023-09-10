package com.powernode.test;

import com.powernode.config.SpringConfig;
import com.powernode.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Solider
 * @version 1.0
 * @Date 2023/9/10 16:45
 * @since 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class) // 使用Junit4支持
@ContextConfiguration(classes = SpringConfig.class) // 全注解方式
// @ContextConfiguration("classpath:spring.xml") // 读取xml配置文件
public class SpringJUnit4Test {

    // @Test
    // public void testUser() {
    //     ApplicationContext applicationContext =
    //             new AnnotationConfigApplicationContext(SpringConfig.class);
    //     UserService userService = applicationContext.getBean("userService", UserService.class);
    //     userService.getName();
    // }

    @Autowired
    UserService userService;

    @Test
    public void testUserService() {
        userService.getName();
    }
}
