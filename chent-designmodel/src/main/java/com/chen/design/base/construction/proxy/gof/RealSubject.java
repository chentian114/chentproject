package com.chen.design.base.construction.proxy.gof;

/**
 * Created by ChenTian on 2018/5/3.
 */
public class RealSubject implements Subject{
    public void request(){
        System.out.println("request...");
    }
}
