package com.chen.design.base.creation.singleton.gof;

/**
 * Created by ChenTian on 2018/4/18.
 */
public class Singleton {
    private Singleton(){}

    private static Singleton instance = new Singleton();

    public static Singleton getInstance(){
        return instance;
    }
}
