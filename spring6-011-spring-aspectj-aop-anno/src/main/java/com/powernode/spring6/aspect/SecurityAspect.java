package com.powernode.spring6.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author Solider
 * @version 1.0
 * @Date 2023/9/5 15:59
 * @since 1.0
 */
// 安全切面类
@Component
@Aspect
@Order(1)
public class SecurityAspect {

    // 前置通知
    @Before("com.powernode.spring6.aspect.LogAspect.pointcut()")
    public void beforeAdvice() {
        System.out.println("安全切面 --> 前置通知");
    }
}
