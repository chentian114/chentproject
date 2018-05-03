package com.chen.design.base.construction.flyweight;

import com.chen.design.base.construction.flyweight.gof.Flyweight;
import com.chen.design.base.construction.flyweight.gof.FlyweightFactory;
import org.junit.Test;

/**
 * Created by ChenTian on 2018/5/3.
 */
public class BaseDemo {

    /**
     * 意图：
     *  运用共享技术有效地支持大量细粒度的对象。
     * 适用性：
     *  一个应用程序使用了大量的对象。
     *  完全由于使用大量的对象，造成很大的存储开销。
     *  对象的大多数状态都可变为外部状态。
     *  如果删除对象的外部状态，那么可以用相对较少的共享对象取代很多组对象。
     *  应用程序不依赖于对象标识。由于Flyweight对象可以被共享，对于概念上明显有别的对象，标识测试将返回真值。
     */
    @Test
    public void testGof(){
        FlyweightFactory flyweightFactory = new FlyweightFactory();
        Flyweight flyweight1 = flyweightFactory.flyweightFactory(1);
        Flyweight flyweight2 = flyweightFactory.flyweightFactory(2);
        Flyweight flyweight3 = flyweightFactory.flyweightFactory(1);
        Flyweight flyweight4 = flyweightFactory.flyweightFactory(3);
        Flyweight flyweight5 = flyweightFactory.flyweightFactory(2);
        flyweight1.operation("A");
        flyweight2.operation("B");
        flyweight3.operation("C");
        flyweight4.operation("D");
        flyweight5.operation("E");

    }
}
