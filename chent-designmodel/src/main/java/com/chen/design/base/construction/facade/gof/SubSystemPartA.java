package com.chen.design.base.construction.facade.gof;

/**
 * Created by ChenTian on 2018/5/3.
 */
public class SubSystemPartA {
    public void operationA(){
        String name = this.getClass().getName();
        System.out.println(name);
    }

    public void otherOperationA(){
        System.out.println("other...");
    }
}
