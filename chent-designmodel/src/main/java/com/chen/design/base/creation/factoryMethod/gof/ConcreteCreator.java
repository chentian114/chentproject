package com.chen.design.base.creation.factoryMethod.gof;

/**
 * Created by ChenTian on 2018/4/18.
 */
public class ConcreteCreator implements Creator {
    public Product factoryMethod() {
        return new ConcreteProduct();
    }
}
