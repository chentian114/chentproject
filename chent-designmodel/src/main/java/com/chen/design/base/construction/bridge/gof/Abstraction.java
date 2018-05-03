package com.chen.design.base.construction.bridge.gof;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by ChenTian on 2018/4/21.
 */
@Setter
@Getter
public abstract class Abstraction {
    private Implementor implementor;
    public Abstraction(){}
    public Abstraction(Implementor implementor){
        this.implementor = implementor;
    }
    public void operation(){
        System.out.print("Abstraction:");
        implementor.operationImp();
    }
}
