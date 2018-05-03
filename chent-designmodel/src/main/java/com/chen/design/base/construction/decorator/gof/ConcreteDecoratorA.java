package com.chen.design.base.construction.decorator.gof;

/**
 * Created by ChenTian on 2018/5/3.
 */
public class ConcreteDecoratorA extends Decorator {
    private int addedState;
    public ConcreteDecoratorA(Component component,int addedState){
        super(component);
        this.addedState = addedState;
    }

    public void operation() {
        String className = this.getClass().getName();
        System.out.println(className+" added state:"+addedState);
        super.operation();
    }
}
