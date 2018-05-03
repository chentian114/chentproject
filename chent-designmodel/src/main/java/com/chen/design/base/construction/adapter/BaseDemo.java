package com.chen.design.base.construction.adapter;

import com.chen.design.base.construction.adapter.example.Adapter2;
import com.chen.design.base.construction.adapter.example.ServiceAdapter;
import com.chen.design.base.construction.adapter.gof.Adaptee;
import com.chen.design.base.construction.adapter.gof.Adapter;
import com.chen.design.base.construction.adapter.gof.Target;
import org.junit.Test;

/**
 * Created by ChenTian on 2018/4/19.
 */
public class BaseDemo {

    /**
     * 意图
     *  将一个类的接口转换成另外一个客户希望的接口。Adapter模式使得原本由于接口不兼容而不能一起工作的那些类可以一起工作。
     * 适用性
     *  你想使用一个已经存在的类，而它的接口不符合你的需求。
        你想创建一个可以复用的类，该类可以与其他不相关的类或不可预见的类（即那些接口可能不一定兼容的类）协同工作。
     （仅适用于对象Adapter）你想使用一些已经存在的子类，但是不可能对每一个都进行子类化以匹配它们的接口。对象适配器可以适配它的父类接口。
     */
    @Test
    public void testGof(){
        Adaptee adaptee = new Adaptee();
        Target target = new Adapter(adaptee);
        target.request();
    }

    /**
     *  适配器模式把一个类的接口变换成客户端所期待的另一种接口，从而使原本因接口不匹配而无法在一起工作的两个类能够在一起工作。
     *  适配器模式有类的适配器模式和对象的适配器模式两种不同的形式。
     *  模式所涉及的角色有：
     *      目标(Target)角色：这就是所期待得到的接口。
     *      源(Adapee)角色：现在需要适配的接口。
     *      适配器(Adaper)角色：适配器类是本模式的核心。适配器把源接口转换成目标接口。显然，这一角色不可以是接口，而必须是具体类。
     *  类适配器和对象适配器的权衡
            类适配器使用对象继承的方式，是静态的定义方式；而对象适配器使用对象组合的方式，是动态组合的方式。
            对于类适配器，由于适配器直接继承了Adaptee，使得适配器不能和Adaptee的子类一起工作，
                因为继承是静态的关系，当适配器继承了Adaptee后，就不可能再去处理Adaptee的子类了。
            对于对象适配器，一个适配器可以把多种不同的源适配到同一个目标。
                换言之，同一个适配器可以把源类和它的子类都适配到目标接口。因为对象适配器采用的是对象组合的关系，只要对象类型正确，是不是子类都无所谓。
            对于类适配器，适配器可以重定义Adaptee的部分行为，相当于子类覆盖父类的部分实现方法。
     　     对于对象适配器，要重定义Adaptee的行为比较困难，这种情况下，需要定义Adaptee的子类来实现重定义，然后让适配器组合子类。
                虽然重定义Adaptee的行为比较困难，但是想要增加一些新的行为则方便的很，而且新增加的行为可同时适用于所有的源。
        建议尽量使用对象适配器的实现方式，多用合成/聚合、少用继承。
        适配器模式的优点
            更好的复用性;系统需要使用现有的类，而此类的接口不符合系统的需要。那么通过适配器模式就可以让这些功能得到更好的复用。
            更好的扩展性;在实现适配器功能的时候，可以调用自己开发的功能，从而自然地扩展系统的功能。
        适配器模式的缺点
            过多的使用适配器，会让系统非常零乱，不易整体进行把握。
                比如，明明看到调用的是A接口，其实内部被适配成了B接口的实现，一个系统如果太多出现这种情况，无异于一场灾难。
                因此如果不是很有必要，可以不使用适配器，而是直接对系统进行重构。
        缺省适配模式
            缺省适配(Default Adapter)模式为一个接口提供缺省实现，这样子类型可以从这个缺省实现进行扩展，
                而不必从原有接口进行扩展。作为适配器模式的一个特例，缺省是适配模式在JAVA语言中有着特殊的应用。
     　  适配器模式的用意是要改变源的接口，以便于目标接口相容。缺省适配的用意稍有不同，它是为了方便建立一个不平庸的适配器类而提供的一种平庸实现。
     　　在任何时候，如果不准备实现一个接口的所有方法时，就可以使用“缺省适配模式”制造一个抽象类，给出所有方法的平庸的具体实现。
     */
    @Test
    public void testExample(){
        com.chen.design.base.construction.adapter.example.Target target = new com.chen.design.base.construction.adapter.example.Adapter();
        target.sampleOperation1();
        target.sampleOperation2();

        com.chen.design.base.construction.adapter.example.Adaptee adaptee = new com.chen.design.base.construction.adapter.example.Adaptee();
        com.chen.design.base.construction.adapter.example.Target adapter2 = new Adapter2(adaptee);
        adapter2.sampleOperation1();
        adapter2.sampleOperation2();

        com.chen.design.base.construction.adapter.example.Adaptee adaptee2 = new com.chen.design.base.construction.adapter.example.Adaptee();
        com.chen.design.base.construction.adapter.example.Target adapter3 = new ServiceAdapter(adaptee2);
        adapter3.sampleOperation1();

    }
}
