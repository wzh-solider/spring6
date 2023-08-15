package com.powernode.simple_factory;

/**
 * 坦克(具体的产品角色)
 * @author Solider
 * @version 1.0
 * @since 1.0
 */
public class Tank extends Weapon {
    @Override
    public void attack() {
        System.out.println("把老子的意大利炮拿来。。。开炮！开炮！开炮！");
    }
}
