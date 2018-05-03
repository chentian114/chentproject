package com.chen.design.base.construction.decorator;

import com.chen.design.base.construction.decorator.gof.*;
import org.junit.Test;

/**
 * Created by ChenTian on 2018/5/3.
 */
public class BaseDemo {

    /**
     * 意图：
     *  动态地给一个对象添加一些额外的职责。就增加功能来说，Decorator模式相比生成子类更为灵活。
     * 适用性：
     *  在不影响其他对象的情况下，以动态、透明的方式给单个对象添加职责。
     *  处理那些可以撤消的职责。
     *  当不能采用生成子类的方法进行扩充时。
     */
    @Test
    public void testGof(){
        Component component = new ConcreteComponent();
        component.operation();

        Decorator decoratorA = new ConcreteDecoratorA(component,20);
        Decorator decoratorB = new ConcreteDecoratorB(component);
        decoratorA.operation();
        decoratorB.operation();
    }

}
