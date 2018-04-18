package com.chen.design.base.creation.abstractFactory.gof;

/**
 * Created by ChenTian on 2018/4/18.
 */
public class ConcreteFactory2 implements AbstractFactory {

    public ProductA createProductA() {
        return new ConcreteProductA2();
    }

    public ProductB createProductB() {
        return new ConcreteProductB2();
    }
}
