package com.chen.design.base.creation.factoryMethod;

import com.chen.design.base.creation.factoryMethod.example.ExportFactory;
import com.chen.design.base.creation.factoryMethod.example.ExportFile;
import com.chen.design.base.creation.factoryMethod.example.ExportHtmlFactory;
import com.chen.design.base.creation.factoryMethod.example.ExportPdfFactory;
import com.chen.design.base.creation.factoryMethod.gof.Product;
import org.junit.Test;

/**
 * Created by ChenTian on 2018/4/18.
 */
public class BaseDemo {

    /**
       意图
        定义一个用于创建对象的接口，让子类决定实例化哪一个类。Factory Method 使一个类的实例化延迟到其子类。
       适用性
         当一个类不知道它所必须创建的对象的类的时候。
         当一个类希望由它的子类来指定它所创建的对象的时候。
         当类将创建对象的职责委托给多个帮助子类中的某一个，并且你希望将哪一个帮助子类是代理者这一信息局部化的时候。
     */
    @Test
    public void testGof(){
        com.chen.design.base.creation.factoryMethod.gof.Creator creator = new com.chen.design.base.creation.factoryMethod.gof.ConcreteCreator();
        Product product = creator.factoryMethod();
        System.out.println(product);
    }

    /**
     * 工厂方法模式的用意是定义一个创建产品对象的工厂接口，将实际创建工作推迟到子类中。
     *  在工厂方法模式中，核心的工厂类不再负责所有的对象的创建，而是将具体创建的工作交给子类去做。
     *  这个核心类成为了一个抽象工厂角色，仅负责给出具体工厂子类必须实现的接口，而不接触哪一个类应当被实例化这种细节。
     * 角色
     *  抽象工厂角色：工厂方法模式的核心，任何在模式中创建对象的工厂类必须实现这个接口。在实际的系统中，这个角色也常常使用抽象类实现。
     *  具体工厂角色：实现了抽象工厂接口的具体JAVA类。具体工厂角色含有与业务密切相关的逻辑，并且受到使用者的调用。
     *  抽象产品角色：工厂方法模式所创建的对象的超类，也就是所有创建对象的共同父类或共同拥有的接口。
     *  具体产品角色：实现了抽象产品角色所声明的接口，工厂方法模式所创建的每一个对象都是某个具体产品角色的实例。
     *
     */
    @Test
    public void testExample(){
        ExportFactory factory1 = new ExportHtmlFactory();
        ExportFile file1 = factory1.factory("standard");
        ExportFile file2 = factory1.factory("financial");
        file1.export();
        file2.export();

        ExportFactory factory2 = new ExportPdfFactory();
        ExportFile file3 = factory2.factory("standard");
        ExportFile file4 = factory2.factory("financial");
        file3.export();
        file4.export();

    }
}
