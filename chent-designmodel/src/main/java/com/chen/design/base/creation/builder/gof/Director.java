package com.chen.design.base.creation.builder.gof;

/**
 * Created by ChenTian on 2018/4/18.
 */
public class Director {
    private   Builder builder;
    public Director(Builder builder){
        this.builder = builder;
    }
    public Product buildProduct(){
        return builder.buildPartA().buildPartB().getResult();
    }
}
