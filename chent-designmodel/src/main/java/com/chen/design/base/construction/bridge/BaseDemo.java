package com.chen.design.base.construction.bridge;

import com.chen.design.base.construction.bridge.example.*;
import com.chen.design.base.construction.bridge.gof.Abstraction;
import com.chen.design.base.construction.bridge.gof.ConcreteImplementorA;
import com.chen.design.base.construction.bridge.gof.ConcreteImplementorB;
import com.chen.design.base.construction.bridge.gof.RefinedAbstraction;
import org.junit.Test;

/**
 * Created by ChenTian on 2018/4/20.
 */
public class BaseDemo {

    /**
     *意图
     *  将抽象部分与它的实现部分分离，使它们都可以独立地变化.
     *适用性
        你不希望在抽象和它的实现部分之间有一个固定的绑定关系。
        类的抽象以及它的实现都应该可以通过生成子类的方法加以扩充。
        有许多类要生成。这样一种类层次结构说明你必须将一个对象分解成两个部分。
        你想在多个对象间共享实现（可能使用引用计数），但同时要求客户并不知道这一点。
     *
     */
    @Test
    public void testGof(){
        Abstraction abstraction = new RefinedAbstraction();
        ConcreteImplementorA implementorA = new ConcreteImplementorA();
        abstraction.setImplementor(implementorA);
        abstraction.operation();

        ConcreteImplementorB implementorB = new ConcreteImplementorB();
        abstraction.setImplementor(implementorB);
        abstraction.operation();

        RefinedAbstraction refinedAbstraction = (RefinedAbstraction)abstraction;
        refinedAbstraction.refinedOperation();

    }

    /**
     *　桥梁模式的用意是“将抽象化(Abstraction)与实现化(Implementation)脱耦，使得二者可以独立地变化”。
     *  抽象化
     *      从众多的事物中抽取出共同的、本质性的特征，而舍弃其非本质的特征，就是抽象化。
     *      一组对象如果具有相同的特征，那么它们就可以通过一个共同的类来描述。
     *  实现化
     *      抽象化给出的具体实现，就是实现化。
     *    　一个类的实例就是这个类的实例化，一个具体子类是它的抽象超类的实例化。
     *  脱耦
     *      所谓耦合，就是两个实体的行为的某种强关联。而将它们的强关联去掉，就是耦合的解脱，或称脱耦。
     *      所谓强关联，就是在编译时期已经确定的，无法在运行时期动态改变的关联；
     *      所谓弱关联，就是可以动态地确定并且可以在运行时期动态地改变的关联。
     *      在Java语言中，继承关系是强关联，而聚合关系是弱关联。
     *  GOF的Bridge的UML这个系统含有两个等级结构：
     *      一、由抽象化角色和修正抽象化角色组成的抽象化等级结构。
     *      二、由实现化角色和两个具体实现化角色所组成的实现化等级结构。
     *  桥梁模式所涉及的角色有：
     *      抽象化(Abstraction)角色：抽象化给出的定义，并保存一个对实现化对象的引用。
     *      修正抽象化(RefinedAbstraction)角色：扩展抽象化角色，改变和修正父类对抽象化的定义。
     *      实现化(Implementor)角色：这个角色给出实现化角色的接口，但不给出具体的实现。
     *          必须指出的是，这个接口不一定和抽象化角色的接口定义相同，实际上，这两个接口可以非常不一样。
     *          实现化角色应当只给出底层操作，而抽象化角色应当只给出基于底层操作的更高一层的操作。
     *      具体实现化(ConcreteImplementor)角色：这个角色给出实现化角色接口的具体实现。
     *  桥梁模式的优点
     *      分离抽象和实现部分;让抽象部分和实现部分独立出来，分别定义接口，这有助于对系统进行分层，从而产生更好的结构化的系统。
     *      更好的扩展性;抽象部分和实现部分可以分别独立地扩展，而不会相互影响，从而大大提高了系统的可扩展性。
     *
     *  根据业务的功能要求，业务的变化具有两个维度:
     *      一个维度是抽象的消息，包括普通消息、加急消息和特急消息;
     *      一个维度是在具体的消息发送方式上，包括系统内短消息、邮件和手机短消息;
     *  把这两个纬度分开，也就是将抽象部分和实现部分分开，让它们相互独立，这样就可以实现独立的变化，使扩展变得简单。
     *  桥梁模式在Java应用中的一个非常典型的例子就是JDBC驱动器。
     *      JDBC为所有的关系型数据库提供一个通用的界面。一个应用系统动态地选择一个合适的驱动器，
     *      然后通过驱动器向数据库引擎发出指令。这个过程就是将抽象角色的行为委派给实现角色的过程。
     *
     */
    @Test
    public void testExample(){
        //发送加急Email消息
        MessageImpl emailMessageImpl = new EmailMessageImpl();
        Message urgentMessage = new UrgentMessage(emailMessageImpl);
        urgentMessage.send();

        //发送特急短信消息
        MessageImpl smsMessageImpl = new SMSMessageImpl();
        Message veryUrgentMessage = new VeryUrgentMessage(smsMessageImpl);
        veryUrgentMessage.send();
    }
}
