package com.powernode.spring6.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author Solider
 * @version 1.0
 * @Date 2023/9/5 14:56
 * @since 1.0
 */
// 日志切面类
@Aspect
@Component
@Order(2)
public class LogAspect {

    // 切面 = 切点 + 通知
    // 通知就是具体要增强的代码

    // 定义通用的切点表达式
    @Pointcut("execution(* com.powernode.spring6.service.UserService.*(..))")
    public void pointcut() {} // 此方法只做标记，不需要填写代码

    // 1、前置通知
    @Before("pointcut()")
    public void beforeAdvice(JoinPoint joinPoint) {
        System.out.println("我是一个前置通知，一段增强代码");

        // 获取目标方法的签名
        // 什么是方法的签名？
        // 方法的签名 = 方法的修饰符 + 方法的返回值类型 + 方法名 + 方法的参数列表
        // public static final void login(..) ==> 这就是方法的签名
        // Signature signature = joinPoint.getSignature();
        // 可以通过签名对象获取方法的对应信息
        String name = joinPoint.getSignature().getName();
        System.out.println("目标方法的方法名：" + name);
    }

    // 2、后置通知
    @AfterReturning("execution(* com.powernode.spring6.service.UserService.*(..))")
    public void afterReturningAdvice() {
        System.out.println("我是一个后置通知，一段增强代码");
    }

    // 3、环绕通知
    @Around("execution(* com.powernode.spring6.service.UserService.*(..))")
    public void aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        // 前置通知
        System.out.println("前环绕");
        // 执行目标
        joinPoint.proceed();
        // 后置通知
        System.out.println("后环绕");
    }

    // 4、异常通知
    @AfterThrowing("execution(* com.powernode.spring6.service.UserService.*(..))")
    public void afterThrowsAdvice() {
        System.out.println("我是一个异常通知，一段增强代码");
    }

    // 5、最终通知
    @After("execution(* com.powernode.spring6.service.UserService.*(..))")
    public void afterAdvice() {
        System.out.println("我是一个最终通知，一段增强代码");
    }
}
