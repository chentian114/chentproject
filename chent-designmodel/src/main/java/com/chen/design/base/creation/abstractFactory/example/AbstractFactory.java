package com.chen.design.base.creation.abstractFactory.example;

/**
 * Created by ChenTian on 2018/4/18.
 */
public interface AbstractFactory {
    public Cpu createCpu();
    public MainBoard createMainBoard();
}
