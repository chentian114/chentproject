package com.chen.design.base.creation.factoryMethod;

import com.chen.design.base.dto.Mapsite;

/**
 * 工厂方法
 * Created by ChenTian on 2018/4/17.
 */
public class ConcreteCreator implements Creator{

    public <T extends Mapsite> T factoryMethod(Class<T> c) {
        Mapsite product = null;
        try{
            product = (Mapsite)Class.forName(c.getName()).newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return (T)product;
    }
}
