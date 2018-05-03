package com.chen.design.base.construction.proxy.gof;

/**
 * Created by ChenTian on 2018/5/3.
 */
public class Proxy implements Subject{
    private Subject subject;
    public Proxy(Subject subject){
        this.subject = subject;
    }
    public Proxy(){
        this.subject = new RealSubject();
    }
    @Override
    public void request(){
        System.out.println("proxy request...");
        subject.request();
    }
}
