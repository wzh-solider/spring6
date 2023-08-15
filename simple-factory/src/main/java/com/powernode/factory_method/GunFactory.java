package com.powernode.factory_method;

/**
 * 具体工厂实现
 * @author Solider
 * @version 1.0
 * @since 1.0
 */
public class GunFactory implements WeaponFactory{

    @Override
    public Weapon get() {
        return new Gun();
    }
}
