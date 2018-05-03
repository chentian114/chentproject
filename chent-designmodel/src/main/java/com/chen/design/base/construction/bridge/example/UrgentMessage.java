package com.chen.design.base.construction.bridge.example;

/**
 * 桥接模式--抽象类角色扩展类（紧急消息）
 * Created by ChenTian on 2018/4/21.
 */
public class UrgentMessage extends Message{
    public UrgentMessage(MessageImpl messageImpl){
        super(messageImpl);
    }
    public void send(){
        String className = this.getClass().getName();
        String processStr = new StringBuilder(className).append(" urgency send message:").toString();
        System.out.print(processStr);
        super.send();
    }
    public void urgencySend(){
        System.out.println("urgency message send!");
    }
}
