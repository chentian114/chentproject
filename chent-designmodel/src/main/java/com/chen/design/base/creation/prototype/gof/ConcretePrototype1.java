package com.chen.design.base.creation.prototype.gof;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by ChenTian on 2018/4/18.
 */
@Getter
@Setter
@ToString
public class ConcretePrototype1 implements Prototype {
    private String name ;
    public ConcretePrototype1(String name){
        this.name = name;
    }
    public Prototype clone() {
        return new ConcretePrototype1(this.name);
    }
}
