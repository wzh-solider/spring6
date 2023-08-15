package com.powernode.spring6.factory;

import com.powernode.spring6.bean.Gun;

/**
 * 工厂方法模式中的具体工厂角色
 * @author Solider
 * @version 1.0
 * @since 1.0
 */
public class GunFactory {

    public Gun get() {
        return new Gun();
    }
}
