package com.chen.design.base.construction.adapter.example;

/**
 * Created by ChenTian on 2018/4/20.
 */
public class Adapter2 implements Target {
    private Adaptee adaptee;
    public Adapter2(Adaptee adaptee){
        this.adaptee = adaptee;
    }

    public void sampleOperation1() {
        adaptee.sampleOperation1();
    }

    public void sampleOperation2() {
        System.out.println("sampleOper2!");
    }
}
