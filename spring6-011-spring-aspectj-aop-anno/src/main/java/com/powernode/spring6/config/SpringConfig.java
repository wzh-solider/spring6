package com.powernode.spring6.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author Solider
 * @version 1.0
 * @Date 2023/9/5 16:27
 * @since 1.0
 */
@Configuration // 作为配置类，替代xml配置文件
@ComponentScan({"com.powernode.spring6.service", "com.powernode.spring6.aspect"}) // 组件扫描
@EnableAspectJAutoProxy // 开启AspectJ自动代理
public class SpringConfig {
}
