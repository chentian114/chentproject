package com.chen.design.base.construction.bridge.own;

/**
 * 桥接模式--抽象实现类角色
 * Created by ChenTian on 2018/4/21.
 */
public class XWindowImp extends WindowImp{
    @Override
    public void devDrawText(){
        System.out.println("XWindowImp devDrawTest!");
    }
    @Override
    public void devDrawLine(){
        System.out.println("XWindowImp devDrawLine!");
    }

}
