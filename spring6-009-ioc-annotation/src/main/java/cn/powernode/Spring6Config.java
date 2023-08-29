package cn.powernode;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 编写一个类，代表Spring的配置文件
 * @author Solider
 * @version 1.0
 * @Date 2023/8/29 20:26
 * @since 1.0
 */
@Configuration
@ComponentScan({"cn.powernode.service","cn.powernode.dao"})
public class Spring6Config {
}
