package com.chen.design.base.construction.decorator.gof;

/**
 * Created by ChenTian on 2018/5/3.
 */
public class ConcreteDecoratorB extends Decorator {
    public ConcreteDecoratorB(Component component){
        super(component);
    }
    public void operation() {
        addedBehavior();
        super.operation();
    }

    private void addedBehavior() {
        String className = this.getClass().getName();
        System.out.println(className+" add behavior...");
    }
}
