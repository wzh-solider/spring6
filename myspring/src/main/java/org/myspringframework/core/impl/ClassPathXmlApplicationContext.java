package org.myspringframework.core.impl;


import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.myspringframework.core.ApplicationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author Solider
 * @version 1.0
 * @Date 2023/8/22 22:19
 * @since 1.0
 */
public class ClassPathXmlApplicationContext implements ApplicationContext {
    private static final Logger logger = LoggerFactory.getLogger(ClassPathXmlApplicationContext.class);

    private final Map<String, Object> singletonObjects = new HashMap<>();

    /**
     * 从类路径中加载xml文件，解析xml，并初始化所有的Bean对象
     * 配置文件应当放到类路径下
     *
     * @param configLocation xml配置文件路径
     */
    public ClassPathXmlApplicationContext(String configLocation) {
        // 解析myspring.xml文件，并实例化，将其放到singletonObjects集合中

        // 解析xml文件的核心对象
        SAXReader reader = new SAXReader();
        try {
            // 获取一个流对象，指向配置文件
            InputStream in = ClassLoader
                    .getSystemClassLoader()
                    .getResourceAsStream(configLocation);

            // 读文档
            Document document = reader.read(in);
            // 获取所有的Bean标签
            List<Node> nodes = document.selectNodes("//bean");
            // 遍历所有的node节点
            nodes.forEach(node -> {
                // System.out.println(node);
                // 向下转型，为了使用Element中更加丰富的方法
                Element BeanElt = (Element) node;
                String id = BeanElt.attributeValue("id");
                String className = BeanElt.attributeValue("class");
                // logger.info("id=" + id);
                // logger.info("className=" + className);
                try {
                    // 通过反射机制创建对象，并放到Map集合中，提前曝光
                    Class<?> clazz = Class.forName(className);
                    Object o = clazz.getConstructor().newInstance();
                    singletonObjects.put(id, o);
                    // 记录日志
                    logger.info(singletonObjects.toString());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });

            // 重新遍历bean对象，对属性赋值
            nodes.forEach(node -> {
                Element beanElt = (Element) node;
                String id = beanElt.attributeValue("id");
                String classPath = beanElt.attributeValue("class");
                // 获取对象
                try {
                    Class<?> clazz = Class.forName(classPath);
                    // 获取此标签下所有的子标签
                    List<Element> properties = beanElt.elements("property");
                    // 遍历所有的标签
                    properties.forEach(propertyElt -> {
                        try {
                            // 获取属性名
                            String name = propertyElt.attributeValue("name");
                            // 获取属性类型
                            Field filed = clazz.getDeclaredField(name);
                            logger.info("name = " + name);
                            // 拼接set方法
                            String setMethodName = "set" + name.toUpperCase().charAt(0) + name.substring(1);
                            Method method = clazz.getMethod(setMethodName, filed.getType());
                            // 获取value | ref属性值
                            Object actualValue = null;
                            String value = propertyElt.attributeValue("value");
                            String ref = propertyElt.attributeValue("ref");
                            if (value != null) {
                                // 简单类型
                                // 获取属性类型名
                                String simpleName = filed.getType().getSimpleName();
                                // 声明支持的简单类型有哪些
                                switch(simpleName) {
                                    case "byte", "Byte" -> actualValue = Byte.parseByte(value);
                                    case "short", "Short" -> actualValue = Short.parseShort(value);
                                    case "int", "Character", "Integer" -> actualValue = Integer.parseInt(value);
                                    case "long", "Long" -> actualValue = Long.parseLong(value);
                                    case "float", "Float" -> actualValue = Float.parseFloat(value);
                                    case "double", "Double" -> actualValue = Double.parseDouble(value);
                                    case "boolean", "Boolean" -> actualValue = Boolean.parseBoolean(value);
                                    case "char" -> actualValue = value.charAt(0);
                                    case "String" -> actualValue = value;
                                }
                                method.invoke(singletonObjects.get(id), actualValue);
                            }
                            if (ref != null) {
                                // 复杂类型
                                method.invoke(singletonObjects.get(id), singletonObjects.get(ref));
                            }
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    });
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Object getBean(String name) {
        return singletonObjects.get(name);
    }
}
