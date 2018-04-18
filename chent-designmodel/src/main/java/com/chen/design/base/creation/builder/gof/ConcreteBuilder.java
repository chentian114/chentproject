package com.chen.design.base.creation.builder.gof;

/**
 * Created by ChenTian on 2018/4/18.
 */
public class ConcreteBuilder implements Builder {
    private Product product = new Product();

    public Builder buildPartA() {
        product.setPartA("A");
        return this;
    }

    public Builder buildPartB() {
        product.setPartB("B");
        return this;
    }

    public Product getResult() {
        return product;
    }

}
