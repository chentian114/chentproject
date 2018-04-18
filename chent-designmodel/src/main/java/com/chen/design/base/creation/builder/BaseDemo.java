package com.chen.design.base.creation.builder;

import com.alibaba.fastjson.JSON;
import com.chen.design.base.creation.builder.example.AutoMessage;
import com.chen.design.base.creation.builder.example.GoodByeBuilder;
import com.chen.design.base.creation.builder.example.InsuranceContract;
import com.chen.design.base.creation.builder.example.WelcomeBuilder;
import com.chen.design.base.creation.builder.gof.Builder;
import com.chen.design.base.creation.builder.gof.ConcreteBuilder;
import com.chen.design.base.creation.builder.gof.Director;
import com.chen.design.base.creation.builder.gof.Product;
import org.junit.Test;

/**
 * Created by ChenTian on 2018/4/18.
 */
public class BaseDemo {

    /**
     *
     意图
      将一个复杂对象的构建与它的表示分离，使得同样的构建过程可以创建不同的表示。
     适用性
      当创建复杂对象的算法应该独立于该对象的组成部分以及它们的装配方式时。
      当构造过程必须允许被构造的对象有不同的表示时。
     */
    @Test
    public void testGof(){
        Builder builder = new ConcreteBuilder();
        Director director = new Director(builder);
        Product product = director.buildProduct();
        System.out.println(product);
        System.out.println(JSON.toJSONString(product));
    }

    /**
     * 建造模式可以将一个产品的内部表象与产品的生产过程分割开来，从而可以使一个建造过程生成具有不同的内部表象的产品对象。
     * 产品的内部表象
     　　一个产品常有不同的组成成分作为产品的零件，这些零件有可能是对象，也有可能不是对象，它们通常又叫做产品的内部表象。
        不同的产品可以有不同的内部表象，也就是不同的零件。
       角色
        抽象建造者（Builder）角色：给出一个抽象接口，以规范产品对象的各个组成成分的建造。
        具体建造者（ConcreteBuilder）角色：它们在应用程序调用下创建产品的实例。这个角色要完成的任务包括：
            1.实现抽象建造者Builder所声明的接口，给出一步一步地完成创建产品实例的操作。
            2.在建造过程完成后，提供产品的实例。
        产品（Product）角色：产品便是建造中的复杂对象。
       在什么情况下使用建造模式
     　　1. 需要生成的产品对象有复杂的内部结构，每一个内部成分本身可以是对象，也可以仅仅是一个对象（即产品对象）的一个组成部分。
     　　2. 需要生成的产品对象的属性相互依赖。建造模式可以强制实行一种分步骤进行的建造过程，因此，如果产品对象的一个属性必须在另一个属性被赋值之后才可以被赋值，使用建造模式是一个很好的设计思想。
     　　3. 在对象创建过程中会使用到系统中的其他一些对象，这些对象在产品对象的创建过程中不易得到。
     *
     */
    @Test
    public void testExample(){
        com.chen.design.base.creation.builder.example.Builder builder = new WelcomeBuilder();
        com.chen.design.base.creation.builder.example.Director director = new com.chen.design.base.creation.builder.example.Director(builder);
        AutoMessage msg = director.construct("abc@qq.com", "xyz@qq.com");
        System.out.println(msg.toString());
        msg.send();

        com.chen.design.base.creation.builder.example.Builder builder2 = new GoodByeBuilder();
        com.chen.design.base.creation.builder.example.Director director2 = new com.chen.design.base.creation.builder.example.Director(builder2);
        AutoMessage msg2 = director2.construct("abc@qq.com", "xyz@qq.com");
        System.out.println(msg2.toString());
        msg2.send();

        InsuranceContract.ConcreteBuilder builder3 =  new InsuranceContract.ConcreteBuilder("9527", 123L, 456L);
        InsuranceContract contract = builder3.buildCompanyName("Oracle").buildPersonName("jery").buildOtherData("say hello!").build();
        System.out.println(contract.toString());
    }
}
