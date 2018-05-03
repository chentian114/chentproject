package com.chen.design.base.construction.bridge.gof;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by ChenTian on 2018/4/21.
 */
@Setter
@Getter
public class RefinedAbstraction extends Abstraction{

    public void refinedOperation(){
        System.out.print("RefinedAbstraction refined:");
        getImplementor().operationImp();
    }
}
