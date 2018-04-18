package com.chen.design.base.creation.singleton;

/**
 * Created by ChenTian on 2018/4/17.
 */
public class MazeSingleton3 {
    private MazeSingleton3(){}

    private static class MazeSingletonHolder{
        private static final MazeSingleton3 INSTANCE = new MazeSingleton3();
    }

    public static final MazeSingleton3 getInstance(){
        return MazeSingletonHolder.INSTANCE;
    }

}
