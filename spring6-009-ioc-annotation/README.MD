# Spring IoC注解式开发

> 注解的存在，主要是为了简化XML配置，**Spring6倡导全注解开发**



## 声明Bean的注解

> 负责声明Bean的注解，常见的包括四个：
>
> - @Component
> - @Controller：一般用在表示层，web层
> - @Service：一般用在业务层，service层
> - @Repository：一般用在持久层，dao层

**@Component**

```java
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.springframework.stereotype;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Indexed
public @interface Component {
    String value() default "";
}
```

**@Controller**

```java
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.springframework.stereotype;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.core.annotation.AliasFor;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Controller {
    @AliasFor(
        annotation = Component.class
    )
    String value() default "";
}
```

**@Service**

```java
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.springframework.stereotype;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.core.annotation.AliasFor;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Service {
    @AliasFor(
        annotation = Component.class
    )
    String value() default "";
}
```

**@Repository**

```java
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.springframework.stereotype;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.core.annotation.AliasFor;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Repository {
    @AliasFor(
        annotation = Component.class
    )
    String value() default "";
}
```

- 注意：在`@Controller`、`@Service`、`@Repository`注解中含有`@AliasFor`
  - 可以说明此注解是`@Component`注解的别名
  - 因此，这四个注解类的功能是一样的
  - **取不同别名，为了增强程序的可读性**
  - **注解存在默认值，如果没有指定，那么默认值为首字母小写的类名**



## Spring注解的使用

> 1、加入AOP依赖（在加入了`spring-context`依赖后，会自动关联aop依赖）
>
> 2、在配置文件中，添加context命名空间
>
> 3、在配置文件中，指定被扫描的包
>
> 4、在Bean类上，使用注解



**第一步：**

![image-20230825183849040](D:\home\coding\java\spring6\spring6-009-ioc-annotation\imgs\aop.png)



**第二步：**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
                           http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd">

</beans>
```



**第三步：**

```xml
<context:component-scan base-package="com.powernode.bean"/>
```



**第四步：**

```java
package com.powernode.bean;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * @author Solider
 * @version 1.0
 * @Date 2023/8/25 18:46
 * @since 1.0
 */
// @Component("userBean") 注解存在默认值，可以省略value值
@Component // 默认为user
public class User {
}
```



### 指定多个包

- 1、在xml配置文件中，指定多个包名，使用`,`隔开

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <beans xmlns="http://www.springframework.org/schema/beans"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xmlns:context="http://www.springframework.org/schema/context"
         xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
                             http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd">
      <context:component-scan base-package="com.powernode.bean, 
                                            com.powernode.service, 
                                            com.powernode.controller"/>
  </beans>
  ```

- 2、指定**父包包名**

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <beans xmlns="http://www.springframework.org/schema/beans"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xmlns:context="http://www.springframework.org/schema/context"
         xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
                             http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd">
      <context:component-scan base-package="com.powernode"/>
  </beans>
  ```



## 选择性实例化Bean



*测试类*

```java
package com.powernode.bean2;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * @author Solider
 * @version 1.0
 * @Date 2023/8/25 19:17
 * @since 1.0
 */
@Component
public class A {
    public A() {
        System.out.println("A的无参构造方法执行");
    }
}

@Service
class B{
    public B() {
        System.out.println("B的无参构造方法执行");
    }
}

@Controller
class C {
    public C() {
        System.out.println("C的无参构造方法执行");
    }
}

@Repository
class D {
    public D() {
        System.out.println("D的无参构造方法执行");
    }
}

@Controller
class E {
    public E() {
        System.out.println("E的无参构造方法执行");
    }
}
```



`use-default-filters = false`


```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
                           http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd">

    <!--
       采用use-default-filters="false"方式
            use-default-filters="false": 表示在这个包下关于Bean的注解不会被扫描到
            @Component、@Repository、@Service、@Controller，都会失效
       只对包含在标签中的注解生效
     -->
    <context:component-scan base-package="com.powernode.bean2" use-default-filters="false">
        <!--
            type: 过滤类型
            expression: 过滤类型的全限定类名
            只有Service注解的类才会被扫描到
         -->
        <context:include-filter type="annotation" expression="org.springframework.stereotype.Service"/>
    </context:component-scan>
</beans>
```

![image-20230825194716516](D:\home\coding\java\spring6\spring6-009-ioc-annotation\imgs\annotationTest.png)



`use-default-filters = true`（**默认值**）

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
                           http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd">
    
    <!--
        use-default-filters="true"方式
            use-default-filters="true": 表示在这个包下关于Bean的注解会被扫描到
            @Component、@Repository、@Service、@Controller，都会生效
     -->
    <context:component-scan base-package="com.powernode.bean2" use-default-filters="true">
        <!--
            type: 过滤类型
            expression: 过滤类型的全限定类名
            除了Service注解的类都会被扫描到
         -->
        <context:exclude-filter type="annotation" expression="org.springframework.stereotype.Service"/>
    </context:component-scan>
</beans>
```

![image-20230825195514284](D:\home\coding\java\spring6\spring6-009-ioc-annotation\imgs\annotationTest_true.png)