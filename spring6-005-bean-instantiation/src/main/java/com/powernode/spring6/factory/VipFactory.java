package com.powernode.spring6.factory;

import com.powernode.spring6.bean.Vip;

/**
 * @author Solider
 * @version 1.0
 * @since 1.0
 */
public class VipFactory {

    public static Vip get() {
        return new Vip();
    }
}
