# Spring6整合Junit5



## Spring对Junit4的支持

准备：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.powernode</groupId>
    <artifactId>spring6-014-junit</artifactId>
    <version>1.0-SNAPSHOT</version>
    <packaging>jar</packaging>

    <properties>
        <maven.compiler.source>17</maven.compiler.source>
        <maven.compiler.target>17</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
            <version>6.0.10</version>
        </dependency>

        <!-- spring对junit支持的依赖 -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-test</artifactId>
            <!-- 这个版本，既支持JUnit4又支持JUnit5 -->
            <version>6.0.10</version>
        </dependency>

        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.13.2</version>
            <scope>test</scope>
        </dependency>
    </dependencies>

</project>
```

测试程序

```java
package com.powernode.test;

import com.powernode.config.SpringConfig;
import com.powernode.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Solider
 * @version 1.0
 * @Date 2023/9/10 16:45
 * @since 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class) // 使用Junit4支持
@ContextConfiguration(classes = SpringConfig.class) // 全注解方式
// @ContextConfiguration("classpath:spring.xml") // 读取xml配置文件
public class SpringJUnit4Test {

    // @Test
    // public void testUser() {
    //     ApplicationContext applicationContext =
    //             new AnnotationConfigApplicationContext(SpringConfig.class);
    //     UserService userService = applicationContext.getBean("userService", UserService.class);
    //     userService.getName();
    // }

    @Autowired
    UserService userService;

    @Test
    public void testUserService() {
        userService.getName();
    }
}
```



## Spring对JUnit5的支持

准备：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.powernode</groupId>
    <artifactId>spring6-014-junit</artifactId>
    <version>1.0-SNAPSHOT</version>
    <packaging>jar</packaging>

    <properties>
        <maven.compiler.source>17</maven.compiler.source>
        <maven.compiler.target>17</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
            <version>6.0.10</version>
        </dependency>
        <dependency>
            <groupId>jakarta.annotation</groupId>
            <artifactId>jakarta.annotation-api</artifactId>
            <version>2.1.1</version>
        </dependency>

        <!-- spring对junit支持的依赖 -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-test</artifactId>
            <!-- 这个版本，既支持JUnit4又支持JUnit5 -->
            <version>6.0.10</version>
        </dependency>

        <!-- Junit4依赖 -->
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.13.2</version>
            <scope>test</scope>
        </dependency>

        <!-- Junit5依赖 -->
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter</artifactId>
            <version>5.10.0</version>
            <scope>test</scope>
        </dependency>
        <!-- <dependency> -->
        <!--     <groupId>junit</groupId> -->
        <!--     <artifactId>junit</artifactId> -->
        <!--     <version>4.13.2</version> -->
        <!--     <scope>test</scope> -->
        <!-- </dependency> -->

    </dependencies>

</project>
```

测试：

```java
package com.powernode.test;

import com.powernode.config.SpringConfig;
import com.powernode.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * @author Solider
 * @version 1.0
 * @Date 2023/9/10 17:42
 * @since 1.0
 */
@ContextConfiguration(classes = SpringConfig.class)
@ExtendWith(SpringExtension.class)
public class SpringJUnit5Test {
    @Autowired
    private UserService userService;

    @Test
    public void testUserService() {
        userService.getName();
    }
}
```

