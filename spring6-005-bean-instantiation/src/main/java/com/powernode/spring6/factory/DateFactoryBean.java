package com.powernode.spring6.factory;

import org.springframework.beans.factory.FactoryBean;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Solider
 * @version 1.0
 * @since 1.0
 */
public class DateFactoryBean implements FactoryBean<Date> {
    private String strDate;

    public DateFactoryBean(String strDate) {
        this.strDate = strDate;
    }

    @Override
    public Date getObject() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.parse(this.strDate);
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }
}
