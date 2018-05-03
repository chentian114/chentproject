package com.chen.design.base.construction.facade.gof;

/**
 * Created by ChenTian on 2018/5/3.
 */
public class Facade {
    private SubSystemPartA subSystemPartA;
    private SubSystemPartB subSystemPartB;
    private SubSystemPartC subSystemPartC;

    public Facade(SubSystemPartA a,SubSystemPartB b,SubSystemPartC c){
        this.subSystemPartA = a;
        this.subSystemPartB = b;
        this.subSystemPartC = c;
    }

    public void operationA(){
        subSystemPartA.operationA();
    }
    public void operationB(){
        subSystemPartB.operationB();
    }
    public void operationC(){
        subSystemPartC.operationC();
    }

}
