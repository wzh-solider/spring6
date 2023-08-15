package com.powernode.factory_method;

/**
 * 具体产品实现
 * @author Solider
 * @version 1.0
 * @since 1.0
 */
public class Gun extends Weapon{

    @Override
    public void attack() {
        System.out.println("开枪！！！");
    }
}
