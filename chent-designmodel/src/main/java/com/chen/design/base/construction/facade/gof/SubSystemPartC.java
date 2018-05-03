package com.chen.design.base.construction.facade.gof;

/**
 * Created by ChenTian on 2018/5/3.
 */
public class SubSystemPartC {
    public void operationC(){
        String name = this.getClass().getName();
        System.out.println(name);
    }

    public void otherOperationC(){
        System.out.println("other...");
    }
}
