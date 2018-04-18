package com.chen.design.base.creation.builder.gof;

/**
 * Created by ChenTian on 2018/4/18.
 */
public interface Builder {
    public Builder buildPartA();
    public Builder buildPartB();
    public Product getResult();
}
