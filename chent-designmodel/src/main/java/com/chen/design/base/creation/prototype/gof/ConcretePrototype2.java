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
public class ConcretePrototype2 implements Prototype {
    private int age ;
    public ConcretePrototype2(int age){
        this.age = age;
    }
    public Prototype clone() {
        return new ConcretePrototype2(this.age);
    }
}
