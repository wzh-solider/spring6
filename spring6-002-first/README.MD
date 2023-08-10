# Spring的使用
1、pom文件中，配置spring依赖

2、项目的类路径下，创建spring的xml文件(文件名任意)
- 必须使用idea自带的spring的xml进行文件创建
- ```xml
  <?xml version="1.0" encoding="UTF-8"?>
    <beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
        <!-- 这就是Spring配置文件 -->
        <!-- IDEA工具为我们提供了这个文件的模板，必须使用这个模板来创建 -->
        <!-- 这个文件名可以自定义，最好放在类路径下(方便后期移植) -->
        <!-- 配置bean，这样spring才可以帮助我们管理这个对象 -->

        <!--
            bean标签的两个重要的属性：
                id: 唯一标识一个Bean对象，不可重复
                class: 必须写类的全路径，全限定类名
        -->
        <bean id="userBean" class="com.powernode.spring6.bean.User"/>

        <!-- 配置其他bean对象 -->
        <bean id="userDaoBean" class="com.powernode.spring6.dao.impl.UserDaoImplMySQL"/>
    </beans>
  ```

3、测试程序中，进行测试
```java
    @Test
    public void testFirstSpringCode() {
        // 1、获取Spring容器对象

        // ApplicationContext: 应用上下文(Spring容器)
        // ApplicationContext: 是一个接口，ClassPathXmlApplicationContext是其中一个实现类
        // ClassPathXmlApplicationContext: 专门从类路径中加载spring配置文件
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml", "beans.xml");

        // 2、根据bean的id，从Spring容器中获取这个对象
        Object userBean = applicationContext.getBean("userBean");
        System.out.println(userBean);

        Object userDaoBean = applicationContext.getBean("userDaoBean");
        System.out.println(userDaoBean);

        // 从beans.xml文件中获取vipBean对象
        Object vipBean = applicationContext.getBean("vipBean");
        System.out.println(vipBean);
    }
```

- 细节：
  - 1、<bean id="" class=""/>的id唯一
  - 2、spring的xml配置文件可以有多个
  - 3、因为spring的底层是使用反射机制，获取配置对象
    - 所以，bean对象中，必须重写无参构造器，否则创建不了对象
  - 4、如果id的值不存在，不会返回null，而是报异常
  - 5、使用`getBean()`时，第二个参数可以传入指定返回类型的参数
  - 6、ApplicationContext的超级父接口是：BeanFactory(Bean工厂)
    - Spring底层的IoC使用：XML解析 + 反射机制 + 工厂模式
  - 7、spring是在创建ClassPathXmlApplicationContext对象的时候创建Bean对象

## Spring框架中启用log4j日志框架
1、引入log4j2依赖
```xml
<!-- log4j2依赖 -->
<dependency>
    <groupId>org.apache.logging.log4j</groupId>
    <artifactId>log4j-core</artifactId>
    <version>2.19.0</version>
</dependency>
<dependency>
    <groupId>org.apache.logging.log4j</groupId>
    <artifactId>log4j-slf4j-impl</artifactId>
    <version>2.19.0</version>
    <scope>test</scope>
</dependency>
```

2、在类的根路径下配置`log4j2.xml`配置文件[路径、名称不可更改]
```xml
<!-- log4j2.xml -->
<?xml version="1.0" encoding="UTF-8"?>

<configuration>

  <loggers>
    <!--
        level指定日志级别，从低到高的优先级：
            ALL < TRACE < DEBUG < INFO < WARN < ERROR < FATAL < OFF
    -->
    <root level="DEBUG">
      <appender-ref ref="spring6log"/>
    </root>
  </loggers>

  <appenders>
    <!--输出日志信息到控制台-->
    <console name="spring6log" target="SYSTEM_OUT">
      <!--控制日志输出的格式-->
      <PatternLayout pattern="%d{yyyy-MM-dd HH:mm:ss SSS} [%t] %-3level %logger{1024} - %msg%n"/>
    </console>
  </appenders>

</configuration>
```