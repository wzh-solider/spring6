package com.powernode.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author Solider
 * @version 1.0
 * @Date 2023/9/5 16:57
 * @since 1.0
 */
@Configuration
@ComponentScan({"com.powernode.aspect", "com.powernode.service"})
@EnableAspectJAutoProxy
public class SpringConfig {
}
