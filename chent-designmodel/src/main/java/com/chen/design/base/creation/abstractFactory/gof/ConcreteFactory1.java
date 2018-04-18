package com.chen.design.base.creation.abstractFactory.gof;

/**
 * Created by ChenTian on 2018/4/18.
 */
public class ConcreteFactory1 implements AbstractFactory {

    public ProductA createProductA() {
        return new ConcreteProductA1();
    }

    public ProductB createProductB() {
        return new ConcreteProductB1();
    }
}
