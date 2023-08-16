package com.powernode.spring6.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author Solider
 * @version 1.0
 * @since 1.0
 */
public class LogBeanPostProcessor implements BeanPostProcessor {

    /**
     * @param bean 实例化的Bean对象
     * @param beanName 实例化Bean对象的名字
     * @return Bean对象，默认为Object
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName)
            throws BeansException {
        System.out.println("第四步、Bean后处理器的before方法执行，即将开始初始化。。。");
        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName)
            throws BeansException {
        System.out.println("第七步、Bean后处理器的after方法执行，已完成初始化。。。");
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }
}
