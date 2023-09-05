package com.powernode.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @author Solider
 * @version 1.0
 * @Date 2023/9/5 16:51
 * @since 1.0
 */
@Component
@Aspect
public class TransactionAspect {

    @Around("execution(* com.powernode.service..*(..))")
    public void aroundAdvice(ProceedingJoinPoint joinPoint) {
        try {
            // 前环绕
            System.out.println("开启事务。。。");

            // 执行目标
            joinPoint.proceed();

            // 后环绕
            System.out.println("提交事务。。。");
        } catch (Throwable e) {
            System.out.println("回滚事务。。。");
            throw new RuntimeException(e);
        }
    }
}
