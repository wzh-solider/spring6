package com.powernode.simple_factory;

/**
 * 战斗机(具体产品角色)
 * @author Solider
 * @version 1.0
 * @since 1.0
 */
public class Fighter extends Weapon{

    @Override
    public void attack() {
        System.out.println("战斗机向广岛投下原子弹");
    }
}
