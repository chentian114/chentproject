package com.chen.design.base.creation.singleton;

/**
 * Created by ChenTian on 2018/4/17.
 */
public class MazeSingleton2 {
    private static volatile MazeSingleton2 instance ;

    private MazeSingleton2(){}

    public static MazeSingleton2 getInstance(){
        if(instance==null){
            synchronized (MazeSingleton2.class){
                if(instance==null){
                    instance = new MazeSingleton2();
                }
            }
        }
        return instance;
    }
}
