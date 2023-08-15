package com.powernode.factory_method;

import org.junit.Test;

/**
 * @author Solider
 * @version 1.0
 * @since 1.0
 */
public class FactoryMethodTest {

    @Test
    public void testFactoryMethod() {
        WeaponFactory gun = new GunFactory();
        Weapon weapon = gun.get();
        weapon.attack();

        WeaponFactory fighter = new FighterFactory();
        Weapon weapon1 = fighter.get();
        weapon1.attack();
    }
}
