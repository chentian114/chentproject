package com.chen.design.base.construction.bridge.example;

/**
 * 桥接模式--实现角色实现类（短信类型消息）
 * Created by ChenTian on 2018/4/21.
 */
public class SMSMessageImpl extends MessageImpl{
    @Override
    public void send(){
        String className = this.getClass().getName();
        System.out.println(className+" send message!");
    }
}
