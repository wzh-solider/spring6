package com.powernode.client;

import com.powernode.service.TimerMethodInterceptor;
import com.powernode.service.UserService;
import net.sf.cglib.proxy.Enhancer;

/**
 * @author Solider
 * @version 1.0
 * @Date 2023/9/3 16:23
 * @since 1.0
 */
public class Client {
    public static void main(String[] args) {
        // 1. 创建字节码增强器
            // 是CGLIB的核心类，用来生成代理类
        Enhancer enhancer = new Enhancer();

        // 2. 告诉CGLIB，父类是谁
        enhancer.setSuperclass(UserService.class);

        // 3. 设置回调函数
            // 在CGLIB中，不是InvocationHandler, 而是方法拦截器：MethodInterceptor
        enhancer.setCallback(new TimerMethodInterceptor());

        // 4. 创建代理对象
           // ① 在内存中生成UserService的子类对象，就是代理子类的字节码
           // ② 创建代理对象
        // 父类是UserService, 子类就是UserService
        UserService userServiceProxy = (UserService) enhancer.create();
        // 输出底层生成的userService对象的类名
            // com.powernode.service.UserService$$EnhancerByCGLIB$$e6d46763@67117f44
        System.out.println(userServiceProxy.toString());
        boolean bool = userServiceProxy.login("admin", "123");
        System.out.println(bool ? "登录成功" : "登录失败");

        userServiceProxy.logout();
    }
}
