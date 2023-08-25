package com.powernode.client;

import com.powernode.annotation.Component;

import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 扫描包，判断是否存在注解，然后创建对象
 * @author Solider
 * @version 1.0
 * @Date 2023/8/25 17:33
 * @since 1.0
 */
public class ComponentScan {
    public static void main(String[] args) {
        // map集合，存储对象
        Map<String, Object> map = new HashMap<>();
        // 获取包的路径
        String packageName = "com.powernode.bean";
        // 进行全扫描，获取包下所有的类
        String path = packageName.replaceAll("\\.", "/");
        URL url = ClassLoader.getSystemClassLoader().getResource(path);
        File file = new File(url.getPath());
        File[] files = file.listFiles();
        Arrays.stream(files).forEach(f -> {
            String fileName = f.getName();
            String className = packageName + "." + fileName.split("\\.")[0];
            try {
                Class<?> clazz = Class.forName(className);
                // 判断类是否存在注解
                if (clazz.isAnnotationPresent(Component.class)) {
                    // 创建对象，保存到map集合中
                    Object bean = clazz.getConstructor().newInstance();
                    map.put(clazz.getAnnotation(Component.class).value(), bean);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        // 输出map集合
        System.out.println(map);
    }
}
