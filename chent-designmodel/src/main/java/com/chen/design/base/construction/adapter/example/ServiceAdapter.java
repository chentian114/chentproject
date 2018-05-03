package com.chen.design.base.construction.adapter.example;

/**
 * Created by ChenTian on 2018/4/20.
 */
public class ServiceAdapter extends AbstractService {
    private Adaptee adaptee;
    public ServiceAdapter(Adaptee adaptee){
        this.adaptee = adaptee;
    }
    @Override
    public void sampleOperation1() {
        adaptee.sampleOperation1();
    }
}
