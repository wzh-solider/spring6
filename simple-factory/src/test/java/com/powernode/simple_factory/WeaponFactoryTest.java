package com.powernode.simple_factory;

import org.junit.Test;

/**
 * @author Solider
 * @version 1.0
 * @since 1.0
 */
public class WeaponFactoryTest {

    @Test
    public void testFactory() {
        Weapon tank = WeaponFactory.get("TANK");
        tank.attack();

        Weapon fighter = WeaponFactory.get("FIGHTER");
        fighter.attack();

        Weapon dagger = WeaponFactory.get("DAGGER");
        dagger.attack();
    }
}
