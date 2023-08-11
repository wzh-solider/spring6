# Spring对IoC的实现

## IoC控制反转

- 控制反转是一种思想
- 控制反转是为了降低程序的耦合度，提高程序的扩展力，以达到OCP、DIP原则
- 控制反转的方式：
    - 将对象创建的权力交由**第三方容器负责**
    - 将对象之间关系的维护权力交由**第三方容器负责**
- 控制反转思想的实现：DI(Dependency Injection)依赖注入

## DI(依赖注入)

> 依赖注入实现了控制反转的思想
> 依赖：对象之间的关联关系
> 注入：一种数据的传递行为，通过注入行为让对象之间产生关系
> Spring通过依赖注入的方式，完成对Bean的管理
> Bean管理：

- Bean对象的创建
- Bean对象中属性的赋值(Bean对象之间关系的维护)

依赖注入的方式：

- 1、set注入
    - xml文件配置
      - ```xml
        <!-- 通过bean管理对象 -->
        <bean id="userDaoBean" class="com.powernode.spring6.dao.impl.UserDaoImpl"/>
        <bean id="vipDaoBean" class="com.powernode.spring6.dao.impl.VipDaoImpl"/>
        <bean id="userServiceBean" class="com.powernode.spring6.service.impl.UserServiceImpl">
           <!-- set注入将对象联系起来 -->
           <!--
               name: set方法名去除set【setUserDao ==> userDao】
               ref: 指定需要注入bean的id
           -->
           <property name="userDao" ref="userDaoBean"/>
           <property name="vipDao" ref="vipDaoBean"/>
        </bean>
        ```
- 2、构造注入
    - xml文件配置
    - ```xml
      <bean id="orderDaoBean" class="com.powernode.spring6.dao.impl.OrderDaoImpl"/>
      <bean id="orderServiceBean" class="com.powernode.spring6.service.impl.OrderServiceImpl">
            <!-- 构造注入将对象联系起来 -->
            <!--
                ref: 指定需要注入bean的id 
                根据配置的参数数量，自动匹配相应参数数量的构造器
                并且自动进行类型匹配，顺序不会影响
            -->
            <constructor-arg ref="userDaoBean"/>
            <constructor-arg ref="orderDaoBean"/>

            <!--
                index: 有参构造器的参数下标【从0开始】
            -->
            <constructor-arg index="0" ref="orderDaoBean"/> 
            <constructor-arg index="1" ref="userDaoBean"/> 

            <!--
                name: 构造器参数的参数名
            -->
            <constructor-arg name="userDao" ref="userDaoBean"/>
            <constructor-arg name="orderDao" ref="orderDaoBean"/>
      </bean>
      ```

### set注入-进阶

> 配置文件【set-di.xml】

1、注入内部Bean、外部Bean

```xml
<!-- 声明外部bean -->
<bean id="orderDaoBean" class="...">
    <!-- 使用ref属性来引入，就是注入外部bean -->
    <property name="" ref="orderDaoBean"/>
</bean>

<bean id="" class="...">
<!-- 注入内部bean -->
<property name="">
    <bean class="..."/>
</property>
</bean>
```

2、注入简单类型

```xml
<bean id="" class="">
    <!-- 通过value属性赋值 -->
    <property name="" value=""/>
</bean>
```

- Spring支持的简单类型：
  - [八种基本数据类型(包装类) | Enum | CharSequence | Number | Date | URI | URL | Locale | Class]
  - ```java
    public static boolean isSimpleValueType(Class<?> type) {
        return Void.class != type && Void.TYPE != type
        && (ClassUtils.isPrimitiveOrWrapper(type) // 八种基本数据类型，及其包装类
        || Enum.class.isAssignableFrom(type) // 枚举类
        || CharSequence.class.isAssignableFrom(type) // 字符串序列(String implements CharSequence)
        || Number.class.isAssignableFrom(type) // 数字类型(Integer... implements Number)
        || Date.class.isAssignableFrom(type) // 日期类型(推荐使用ref类型，如果需要必须按规定格式，否则报异常)
        || Temporal.class.isAssignableFrom(type) // Java8之后提供的时间和时区类型
        || URI.class == type // URI地址
        || URL.class == type // URL地址
        || Locale.class == type // 语言类
        || Class.class == type); // Class类
    }
    ```

3、级联属性赋值
```xml
<bean id="studentBean" class="com.powernode.spring6.bean.Student">
    <property name="name" value="张三"/>

    <!-- 通过级联属性赋值, 下面两个顺序不可颠倒 -->
    <!-- 使用级联属性赋值, 需要提供get方法 -->
    <property name="clazz" ref="clazzBean"/>
    <property name="clazz.name" value="高三一班"/>
</bean>

<bean id="clazzBean" class="com.powernode.spring6.bean.Clazz"/>
```

