package com.chen.design.base.construction.bridge.example;

import lombok.Getter;
import lombok.Setter;

/**
 * 桥接模式--抽象类角色（消息）
 * Created by ChenTian on 2018/4/21.
 */
@Setter
@Getter
public abstract class Message {
    private MessageImpl messageImpl;
    public Message(){}
    public Message(MessageImpl messageImpl){
        this.messageImpl = messageImpl;
    }
    public void send(){
        System.out.println("send message!");
    }
}
