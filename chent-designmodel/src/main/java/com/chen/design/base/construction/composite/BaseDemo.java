package com.chen.design.base.construction.composite;

import com.chen.design.base.construction.composite.gof.Component;
import com.chen.design.base.construction.composite.gof.Composite;
import com.chen.design.base.construction.composite.gof.Leaf;
import org.junit.Test;

/**
 * Created by ChenTian on 2018/5/3.
 */
public class BaseDemo {

    /**
     * 意图：
     *  将对象组合成树形结构以表示“部分-整体”的层次结构。Composite使得用户对单个对象和组合对象的使用具有一致性。
     * 适用性：
     *  你想表示对象的部分—整体层次结构。
     *  你希望用户忽略组合对象与单个对象的不同，用户将统一地使用组合结构中的所有对象。
     */
    @Test
    public void testGof(){
        Composite root = new Composite(0);

        Composite compsite1 = new Composite(1);
        Composite compsite2 = new Composite(2);
        Composite compsite3 = new Composite(3);
        Composite compsite4 = new Composite(4);
        Component leaf1 = new Leaf(5);
        Component leaf2 = new Leaf(6);
        Component leaf3 = new Leaf(7);
        Component leaf4 = new Leaf(8);
        compsite2.add(leaf2);
        compsite3.add(leaf3);
        compsite4.add(leaf4);

        root.add(compsite1);
        root.add(compsite2);
        root.add(compsite3);
        root.add(compsite4);
        root.add(leaf1);

        root.opertation();
    }
}
