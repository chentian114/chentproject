package com.chen.design.base.construction.bridge.example;

/**
 * 桥接模式--实现角色实现类（邮件类型消息）
 * Created by ChenTian on 2018/4/21.
 */
public class EmailMessageImpl extends MessageImpl{

    public void send(){
        String className = this.getClass().getName();
        System.out.println(className+" send message!");
    }
}
