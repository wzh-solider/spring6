package com.powernode.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

/**
 * @author Solider
 * @version 1.0
 * @Date 2023/9/5 17:15
 * @since 1.0
 */
@Component
@Aspect
public class SecurityLogAspect {

    @Pointcut("execution(* com.powernode.service.biz..*(..))")
    public void savePointcut() {}
    @Pointcut("execution(* com.powernode.service.biz..*(..))")
    public void updatePointcut() {}
    @Pointcut("execution(* com.powernode.service.biz..*(..))")
    public void deletePointcut() {}
    @Pointcut("execution(* com.powernode.service.biz..*(..))")
    public void getPointcut() {}


    @Before("savePointcut() || updatePointcut() || deletePointcut() || getPointcut()")
    public void beforeAdvice(JoinPoint joinPoint) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowTime = sdf.format(new Date());
        System.out.println(nowTime + "张三" + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
    }
}
