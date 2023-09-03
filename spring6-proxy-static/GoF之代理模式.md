# GoF之代理模式

> 代理模式的作用：
>
> 1、当一个对象，需要受到保护
>
> 2、需要给某个对象的功能进行功能增强
>
> 3、**A对象无法和B对象直接进行交互**



**代理模式中的三大角色：**

- 目标对象
- 代理对象
- 目标对象和代理对象的公共接口

![image-20230903083004853](D:\home\coding\image-20230903083004853.png)

**实现：静态代理、动态代理**



## 静态代理

```java
package com.powernode.service;

/**
 * @author Solider
 * @version 1.0
 * @Date 2023/9/3 8:35
 * @since 1.0
 */
public interface OrderService {

    /**
     * 查询订单
     */
    void selectOrder();

    /**
     * 修改订单
     */
    void updateOrder();

    /**
     * 删除订单
     */
    void deleteOrder();
}
```

```java
package com.powernode.service.impl;

import com.powernode.service.OrderService;

/**
 * 通过静态代理模式，实现相关业务方法
 * @author Solider
 * @version 1.0
 * @Date 2023/9/3 8:47
 * @since 1.0
 */
public class OrderServiceProxy implements OrderService {
    OrderService target;

    public OrderServiceProxy(OrderService target) {
        this.target = target;
    }

    @Override
    public void selectOrder() {
        long begin = before();
        this.target.selectOrder();
        long end = after();
        System.out.println("耗时：" + (end - begin) + "ms");
    }

    @Override
    public void updateOrder() {
        long begin = before();
        this.target.updateOrder();
        long end = after();
        System.out.println("耗时：" + (end - begin) + "ms");
    }

    @Override
    public void deleteOrder() {
        long begin = before();
        this.target.deleteOrder();
        long end = after();
        System.out.println("耗时：" + (end - begin) + "ms");
    }

    public long before() {
        System.out.println("前置通知");
        return System.currentTimeMillis();
    }

    public long after() {
        System.out.println("后置通知");
        return System.currentTimeMillis();
    }
}
```

```java
package com.powernode.service.impl;

import com.powernode.service.OrderService;

/**
 * @author Solider
 * @version 1.0
 * @Date 2023/9/3 8:36
 * @since 1.0
 */
public class OrderServiceImpl implements OrderService {
    @Override
    public void selectOrder() {
        // 模拟订单查询耗时操作
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("查询订单");
    }

    @Override
    public void updateOrder() {
        // 模拟订单修改耗时操作
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("修改订单");
    }

    @Override
    public void deleteOrder() {
        // 模拟订单删除耗时操作
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("删除订单");
    }
}
```

```java
package com.powernode.client;

import com.powernode.service.OrderService;
import com.powernode.service.impl.OrderServiceImpl;
import com.powernode.service.impl.OrderServiceProxy;

/**
 * @author Solider
 * @version 1.0
 * @Date 2023/9/3 8:41
 * @since 1.0
 */
public class Test {
    public static void main(String[] args) {
        // 通过代理类，进行业务方法的调用
        OrderService orderService = new OrderServiceProxy(new OrderServiceImpl());
        orderService.selectOrder();
        orderService.updateOrder();
        orderService.deleteOrder();
    }
}
```

![image-20230903093520795](D:\home\coding\java\spring6\spring6-proxy-static\imgs\proxy_static_result.png)

- 缺点：
  - 类爆炸
  - 不容易维护



## 动态代理

> 在程序运行阶段，在内存中动态生成代理类，被称为动态代理
>
> 目的是，为了减少代理类的数量，解决代码复用问题

在内存中动态生成类的技术包括：

- JDK动态代理技术：只能代理接口

- CGLIB动态代理技术：CGLIB（Code Generation Library）是一个开源项目，

  是一个强大的、高性能的、高质量的Code生成类库，可以在运行期扩展java类

  与实现java接口

  - 既可以代理接口，又可以代理类，底层是通过继承的方式实现的
  - 性能比JDK动态代理好（底层有一个小而快的字节码处理框架ASM）

- Javassist动态代理技术：是一个开源的分析、编辑和创建Java字节码的类库

  通过Javassist对字节码操作为JBoss实现动态”AOP“框架



## CGLIB动态代理

> CGLIB既可以代理接口，又可以代理类
>
> 底层采用继承的方式实现，所以被代理的目标类不能被final修饰
>
> 使用CGLIB，需要引入他的依赖

```xml
 <dependency>
	<groupId>cglib</groupId>
	<artifactId>cglib</artifactId>
	<version>3.3.0</version>
</dependency>
```

```java
package com.powernode.service;

/**
 * @author Solider
 * @version 1.0
 * @Date 2023/9/3 16:19
 * @since 1.0
 */
public class UserService {

    // 目标方法
    public boolean login(String name, String password) {
        System.out.println("系统正在验证身份。。。");
        return "admin".equals(name) && "123".equals(password);
    }

    // 目标方法
    public void logout() {
        System.out.println("系统正在退出。。。");
    }
}
```

```java
package com.powernode.service;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author Solider
 * @version 1.0
 * @Date 2023/9/3 16:36
 * @since 1.0
 */
public class TimerMethodInterceptor implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy)
            throws Throwable {

        // 前面增强
        long start = System.currentTimeMillis();

        // 调用目标方法
        Object retValue = methodProxy.invokeSuper(o, objects);

        // 后面增强
        long end = System.currentTimeMillis();

        System.out.println("目标方法执行时间：" + (end - start) + "毫秒");

        return retValue;
    }
}
```

```java
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
```

最后在执行程序的时候，还需要在指定运行参数

![image-20230903164643244](D:\home\coding\java\spring6\spring6-dynamic-cglib\imgs\image-20230903164643244.png)

![image-20230903164817261](D:\home\coding\java\spring6\spring6-dynamic-cglib\imgs\image-20230903164817261.png)



**需要在下面的位置指定两个参数**

- --add-opens java.base/java.lang=ALL-UNNAMED
- --add-opens java.base/sun.net.util=ALL-UNNAMED

![image-20230903165004657](D:\home\coding\java\spring6\spring6-dynamic-cglib\imgs\image-20230903165004657.png)