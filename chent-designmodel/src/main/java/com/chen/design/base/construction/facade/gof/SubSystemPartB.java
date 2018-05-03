package com.chen.design.base.construction.facade.gof;

/**
 * Created by ChenTian on 2018/5/3.
 */
public class SubSystemPartB {
    public void operationB(){
        String name = this.getClass().getName();
        System.out.println(name);
    }

    public void otherOperationB(){
        System.out.println("other...");
    }
}
