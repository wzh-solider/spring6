package com.powernode.factory_method;

/**
 * 具体产品实现类
 * @author Solider
 * @version 1.0
 * @since 1.0
 */
public class Fighter extends Weapon {

    @Override
    public void attack() {
        System.out.println("战斗机发射核弹！！！");
    }
}
