package com.chen.design.base.construction.bridge.example;

/**
 * 桥接模式--抽象类角色扩展类（非常紧急消息）
 * Created by ChenTian on 2018/4/21.
 */
public class VeryUrgentMessage extends Message{
    public VeryUrgentMessage(MessageImpl messageImpl){
        super(messageImpl);
    }
    public void send(){
        String className = this.getClass().getName();
        System.out.print(className+" very urgency send message:");
        super.send();
    }

    public void veryUrgencySend(){
        System.out.println("very urgent message send!");
    }
}
