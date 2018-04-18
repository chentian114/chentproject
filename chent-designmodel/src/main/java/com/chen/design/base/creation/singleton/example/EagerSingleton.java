package com.chen.design.base.creation.singleton.example;

/**
 * Created by ChenTian on 2018/4/18.
 */
public class EagerSingleton {
    private static EagerSingleton instance = new EagerSingleton();

    private EagerSingleton(){}

    public static EagerSingleton getInstance(){
        return instance;
    }
}
