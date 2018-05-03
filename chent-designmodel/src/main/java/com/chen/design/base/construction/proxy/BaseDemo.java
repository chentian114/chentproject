package com.chen.design.base.construction.proxy;

import com.chen.design.base.construction.proxy.gof.Proxy;
import com.chen.design.base.construction.proxy.gof.Subject;
import org.junit.Test;

/**
 * Created by ChenTian on 2018/5/3.
 */
public class BaseDemo {

    /**
     * 意图：
     *  为其他对象提供一种代理以控制对这个对象的访问。
     * 适用性：
     *  远程代理（Remote Proxy）为一个对象在不同的地址空间提供局部代表。
     *  虚代理（Virtual Proxy）根据需要创建开销很大的对象。
     *  保护代理（Protection Proxy）控制对原始对象的访问。保护代理用于对象应该有不同 的访问权限的时候。
     *  智能指引（Smart Reference）取代了简单的指针，它在访问对象时执行一些附加操作。
     */
    @Test
    public void testGof(){
        Subject subject = new Proxy();
        subject.request();
    }

}
