package com.chen.design.base.construction.adapter.gof;

/**
 * Created by ChenTian on 2018/4/19.
 */
public class Adapter extends Target {
    private Adaptee adaptee;
    public Adapter(Adaptee adaptee){
        this.adaptee = adaptee;
    }
    @Override
    public void request() {
        adaptee.specificRequest();
    }
}
