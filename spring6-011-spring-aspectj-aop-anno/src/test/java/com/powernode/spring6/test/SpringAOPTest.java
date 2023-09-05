    package com.powernode.spring6.test;

    import com.powernode.spring6.config.SpringConfig;
    import com.powernode.spring6.service.UserService;
    import org.junit.Test;
    import org.springframework.context.ApplicationContext;
    import org.springframework.context.annotation.AnnotationConfigApplicationContext;
    import org.springframework.context.support.ClassPathXmlApplicationContext;

    /**
     * @author Solider
     * @version 1.0
     * @Date 2023/9/5 15:12
     * @since 1.0
     */
    public class SpringAOPTest {

        @Test
        public void testNoXML() {
            ApplicationContext ApplicationContext =
                    new AnnotationConfigApplicationContext(SpringConfig.class);
            UserService userService = ApplicationContext.getBean("userService", UserService.class);
            userService.login();
        }

        @Test
        public void testBefore() {
            ApplicationContext applicationContext =
                    new ClassPathXmlApplicationContext("spring.xml");
            UserService userService = applicationContext.getBean("userService", UserService.class);
            userService.login();
        }
    }
