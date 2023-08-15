package com.powernode.simple_factory;

/**
 * 匕首(具体的产品角色)
 * @author Solider
 * @version 1.0
 * @since 1.0
 */
public class Dagger extends Weapon{

    @Override
    public void attack() {
        System.out.println("向鬼子们的头上砍去");
    }
}
