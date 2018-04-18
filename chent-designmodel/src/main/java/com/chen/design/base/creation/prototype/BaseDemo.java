package com.chen.design.base.creation.prototype;

import com.chen.design.base.creation.prototype.example.Monkey;
import com.chen.design.base.creation.prototype.example.Monkey2;
import com.chen.design.base.creation.prototype.example.PrototypeManager;
import com.chen.design.base.creation.prototype.gof.ConcretePrototype1;
import com.chen.design.base.creation.prototype.gof.Prototype;
import org.junit.Test;

/**
 * Created by ChenTian on 2018/4/18.
 */
public class BaseDemo {
    /**
     意图
        用原型实例指定创建对象的种类，并且通过拷贝这些原型创建新的对象。
     适用性
        当要实例化的类是在运行时刻指定时，例如，通过动态装载；
        为了避免创建一个与产品类层次平行的工厂类层次时；
        当一个类的实例只能有几个不同状态组合中的一种时。
            建立相应数目的原型并克隆它们可能比每次用合适的状态手工实例化该类更方便一些。
     */
    @Test
    public void testGof(){
        Prototype prototype1 = new ConcretePrototype1("gof");
        System.out.println(prototype1);
        System.out.println(prototype1.clone());
    }

    /**
     *用意
     *  通过给出一个原型对象来指明所有创建的对象的类型，然后用复制这个原型对象的办法创建出更多同类型的对象。
     *浅度克隆
     　　只负责克隆按值传递的数据（比如基本数据类型、String类型），而不复制它所引用的对象，换言之，
        所有的对其他对象的引用都仍然指向原来的对象。
     深度克隆
     　　除了浅度克隆要克隆的值外，还负责克隆引用类型的数据。
        那些引用其他对象的变量将指向被复制过的新对象，而不再是原有的那些被引用的对象。换言之，
        深度克隆把要复制的对象所引用的对象都复制了一遍，而这种对被引用到的对象的复制叫做间接复制。
     利用序列化实现深度克隆
     原型模式的优点
     　　原型模式允许在运行时动态改变具体的实现类型。
            原型模式可以在运行期间，由客户来注册符合原型接口的实现类型，也可以动态地改变具体的实现类型，
            看起来接口没有任何变化，但其实运行的已经是另外一个类实例了。因为克隆一个原型就类似于实例化一个类。
     原型模式的缺点
     　　原型模式最主要的缺点是每一个类都必须配备一个克隆方法。
        配备克隆方法需要对类的功能进行通盘考虑，这对于全新的类来说不是很难，
        而对于已经有的类不一定很容易，特别是当一个类引用不支持序列化的间接对象，或者引用含有循环结构的时候。
     */
    @Test
    public void testExample(){
        Prototype prototype1 = new ConcretePrototype1("type1");
        PrototypeManager.setPrototype("type1", prototype1);
        Prototype prototype2 = new ConcretePrototype1("type2");
        PrototypeManager.setPrototype("type2", prototype2);

        System.out.println(PrototypeManager.getProtoType("type1"));
        System.out.println(PrototypeManager.getProtoType("type1").clone());

        Monkey monkey = new Monkey();
        monkey.setHeight(18);
        monkey.setWeight(20);
        Monkey cloneMonkey = monkey.clone();
        System.out.println(monkey);
        System.out.println(cloneMonkey);
        System.out.println(monkey == cloneMonkey);

        Monkey2 monkey2 = new Monkey2();
        monkey2.setHeight(18);
        monkey2.setWeight(20);
        Monkey2 cloneMonkey2 = monkey2.deepClone();
        System.out.println(monkey2);
        System.out.println(cloneMonkey2);
        System.out.println(monkey2 == cloneMonkey2);
    }
}
