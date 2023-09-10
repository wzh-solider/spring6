package com.powernode.test;

import com.powernode.config.SpringConfig;
import com.powernode.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * @author Solider
 * @version 1.0
 * @Date 2023/9/10 17:42
 * @since 1.0
 */
@ContextConfiguration(classes = SpringConfig.class)
@ExtendWith(SpringExtension.class)
public class SpringJUnit5Test {
    @Autowired
    private UserService userService;

    @Test
    public void testUserService() {
        userService.getName();
    }
}
