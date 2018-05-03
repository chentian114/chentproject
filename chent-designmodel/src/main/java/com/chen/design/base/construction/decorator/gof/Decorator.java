package com.chen.design.base.construction.decorator.gof;

/**
 * Created by ChenTian on 2018/5/3.
 */
public class Decorator implements Component {
    private Component component;
    public Decorator(Component component){
        this.component = component;
    }

    public void operation() {
        component.operation();
    }
}
