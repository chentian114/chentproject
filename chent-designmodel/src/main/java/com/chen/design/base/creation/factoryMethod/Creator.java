package com.chen.design.base.creation.factoryMethod;

import com.chen.design.base.dto.Mapsite;

/**
 * Created by ChenTian on 2018/4/17.
 */
public interface Creator {
    public <T extends Mapsite> T factoryMethod(Class<T> c);
}
