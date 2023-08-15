package com.powernode.factory_method;

/**
 * @author Solider
 * @version 1.0
 * @since 1.0
 */
public class FighterFactory implements WeaponFactory{

    @Override
    public Weapon get() {
        return new Fighter();
    }
}
