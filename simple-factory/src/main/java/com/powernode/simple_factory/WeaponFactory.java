package com.powernode.simple_factory;

/**
 * 工厂类角色
 * @author Solider
 * @version 1.0
 * @since 1.0
 */
public class WeaponFactory {

    /**
     * 根据不同的武器生产不同的武器类型
     * @param weaponType 产品类型
     * @return 武器对象
     */
    public static Weapon get(String weaponType) {
        if (weaponType == null || weaponType.trim().isEmpty()) {
            return null;
        }

        Weapon weapon = switch (weaponType) {
            case "TANK" -> new Tank();
            case "FIGHTER" -> new Fighter();
            case "DAGGER" -> new Dagger();
            default -> throw new RuntimeException("不支持该武器的生产");
        };
        return weapon;
    }
}
