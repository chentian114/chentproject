package com.chen.design.base.creation.singleton;

/**
 * Created by ChenTian on 2018/4/17.
 */
public class MazeSingleton {
    private static final MazeSingleton instance = new MazeSingleton();

    private MazeSingleton(){}

    public static MazeSingleton getInstance(){
        return instance;
    }
}
