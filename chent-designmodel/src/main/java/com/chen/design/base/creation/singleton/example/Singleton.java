package com.chen.design.base.creation.singleton.example;

/**
 * Created by ChenTian on 2018/4/18.
 *
 * 饿汉式是典型的空间换时间，当类装载的时候就会创建类的实例
 */
public class Singleton {
    private volatile static Singleton instance;
    private Singleton(){}

    public static Singleton getInstance(){
        if(instance==null){
            synchronized (Singleton.class){
                if(instance==null){
                    instance=new Singleton();
                }
            }
        }
        return instance;
    }
}
