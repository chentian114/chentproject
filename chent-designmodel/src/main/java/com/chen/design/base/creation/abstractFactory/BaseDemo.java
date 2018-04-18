package com.chen.design.base.creation.abstractFactory;

import com.chen.design.base.creation.abstractFactory.example.AmdFactory;
import com.chen.design.base.creation.abstractFactory.example.Cpu;
import com.chen.design.base.creation.abstractFactory.example.IntelFactory;
import com.chen.design.base.creation.abstractFactory.example.MainBoard;
import com.chen.design.base.creation.abstractFactory.gof.ConcreteFactory1;
import com.chen.design.base.creation.abstractFactory.gof.ConcreteFactory2;
import com.chen.design.base.creation.abstractFactory.gof.ProductA;
import com.chen.design.base.creation.abstractFactory.gof.ProductB;
import org.junit.Test;

/**
 * Created by ChenTian on 2018/4/18.
 */
public class BaseDemo {

    /**
     GOF
     意图
        提供一个创建一系列相关或相互依赖对象的接口，而无需指定它们具体的类。
     适用性
        一个系统要独立于它的产品的创建、组合和表示时。
        一个系统要由多个产品系列中的一个来配置时。
        当你要强调一系列相关的产品对象的设计以便进行联合使用时。
        当你提供一个产品类库，而只想显示它们的接口而不是实现时。
     */
    @Test
    public void testGof(){
        com.chen.design.base.creation.abstractFactory.gof.AbstractFactory factory1 = new ConcreteFactory1();
        ProductA productA1 = factory1.createProductA();
        ProductB productB1 = factory1.createProductB();
        System.out.println(productA1);
        System.out.println(productB1);

        com.chen.design.base.creation.abstractFactory.gof.AbstractFactory factory2 = new ConcreteFactory2();
        ProductA productA2 = factory2.createProductA();
        ProductB productB2 = factory2.createProductB();
        System.out.println(productA2);
        System.out.println(productB2);
    }

    /**
     * 抽象工厂模式与工厂方法模式的最大区别就在于:
     *  工厂方法模式针对的是一个产品等级结构；而抽象工厂模式则需要面对多个产品等级结构。
     * 产品族，是指位于不同产品等级结构中，功能相关联的产品组成的家族。
     *  如AMD的主板、芯片组、CPU组成一个家族，Intel的主板、芯片组、CPU组成一个家族。
     * 产品等级，一个等级结构是由相同的结构的产品组成.
     *  如三个产品等级主板、芯片组、CPU。
     * 抽象工厂的功能是为一系列相关对象或相互依赖的对象创建一个接口。
     *  这些产品对象就构成了一个产品族，也就是抽象工厂定义了一个产品族。
     * 在什么情况下应当使用抽象工厂模式
     　　1.一个系统不应当依赖于产品类实例如何被创建、组合和表达的细节，这对于所有形态的工厂模式都是重要的。
     　　2.这个系统的产品有多于一个的产品族，而系统只消费其中某一族的产品。
     　　3.同属于同一个产品族的产品是在一起使用的，这一约束必须在系统的设计中体现出来。（比如：Intel主板必须使用Intel CPU、Intel芯片组）
     　　4.系统提供一个产品类的库，所有的产品以同样的接口出现，从而使客户端不依赖于实现。

       抽象工厂模式的优点
         分离接口和实现
         　　客户端使用抽象工厂来创建需要的对象，而客户端根本就不知道具体的实现是谁，客户端只是面向产品的接口编程而已。也就是说，客户端从具体的产品实现中解耦。
         使切换产品族变得容易
         　　因为一个具体的工厂实现代表的是一个产品族，比如上面例子的从Intel系列到AMD系列只需要切换一下具体工厂。
       抽象工厂模式的缺点
         不太容易扩展新的产品
         　　如果需要给整个产品族添加一个新的产品，那么就需要修改抽象工厂，这样就会导致修改所有的工厂实现类。
     */
    @Test
    public void testExample(){
        com.chen.design.base.creation.abstractFactory.example.AbstractFactory amdFactory = new AmdFactory();
        Cpu cpu1 = amdFactory.createCpu();
        MainBoard mainBoard1 = amdFactory.createMainBoard();
        System.out.println(cpu1.toString());
        mainBoard1.installCPU();

        com.chen.design.base.creation.abstractFactory.example.AbstractFactory intelFactory = new IntelFactory();
        Cpu cpu2 = intelFactory.createCpu();
        System.out.println(cpu2.toString());
        MainBoard mainBoard2 = intelFactory.createMainBoard();
        mainBoard2.installCPU();
    }


}
