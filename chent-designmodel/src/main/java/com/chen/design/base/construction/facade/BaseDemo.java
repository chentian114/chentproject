package com.chen.design.base.construction.facade;

import com.chen.design.base.construction.facade.gof.Facade;
import com.chen.design.base.construction.facade.gof.SubSystemPartA;
import com.chen.design.base.construction.facade.gof.SubSystemPartB;
import com.chen.design.base.construction.facade.gof.SubSystemPartC;
import org.junit.Test;

/**
 * Created by ChenTian on 2018/5/3.
 */
public class BaseDemo {

    /**
     * 意图：
     *  为子系统中的一组接口提供一个一致的界面，Facade模式定义了一个高层接口，这个接口使得这一子系统更加容易使用。
     * 适用性：
     *  当你要为一个复杂子系统提供一个简单接口时。
     *  客户程序与抽象类的实现部分之间存在着很大的依赖性。
     *  当你需要构建一个层次结构的子系统时，使用门面模式定义子系统中每层的入口点。
     */
    @Test
    public void testGof(){
        SubSystemPartA subA = new SubSystemPartA();
        SubSystemPartB subB = new SubSystemPartB();
        SubSystemPartC subC = new SubSystemPartC();

        Facade facade = new Facade(subA,subB,subC);
        facade.operationA();
        facade.operationB();
        facade.operationC();
    }
}
