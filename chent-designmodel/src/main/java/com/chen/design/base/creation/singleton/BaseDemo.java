package com.chen.design.base.creation.singleton;

import com.chen.design.base.creation.singleton.example.EagerSingleton;
import com.chen.design.base.creation.singleton.example.Singleton2;
import com.chen.design.base.creation.singleton.example.Singleton3;
import com.chen.design.base.creation.singleton.gof.Singleton;
import org.junit.Test;

/**
 * Created by ChenTian on 2018/4/18.
 */
public class BaseDemo {
    /**
     *
     意图
        保证一个类仅有一个实例，并提供一个访问它的全局访问点。
     适用性
        当类只能有一个实例而且客户可以从一个众所周知的访问点访问它时。
        当这个唯一实例应该是通过子类化可扩展的，并且客户应该无需更改代码就能使用一个扩展的实例时。
     */
    @Test
    public void testGof(){
        Singleton singleton = Singleton.getInstance();
        System.out.println(singleton);
    }

    /**
     * 单例模式确保某一个类只有一个实例，而且自行实例化并向整个系统提供这个实例。这个类称为单例类。
     *  饿汉式是典型的空间换时间，当类装载的时候就会创建类的实例
     *  懒汉式是典型的时间换空间,就是每次获取实例都会进行判断，看是否需要创建实例，浪费判断的时间。
     *  类级内部类中，可以定义静态的方法。在静态方法中只能够引用外部类中的静态成员方法或者成员变量。
     　  类级内部类相当于其外部类的成员，只有在第一次被使用的时候才被会装载。
        使用枚举来实现单实例控制会更加简洁，而且无偿地提供了序列化机制，
         并由JVM从根本上提供保障，绝对防止多次实例化，是更简洁、高效、安全的实现单例的方式。
     */
    @Test
    public void testExample(){
        System.out.println(EagerSingleton.getInstance());
        System.out.println(Singleton.getInstance());
        System.out.println(Singleton2.getInstance());
        System.out.println(Singleton3.uniqueInstance);
    }
}
