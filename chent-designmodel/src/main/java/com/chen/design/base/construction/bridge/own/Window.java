package com.chen.design.base.construction.bridge.own;

import lombok.Getter;
import lombok.Setter;

/**
 * 桥接模式--抽象类角色
 * Created by ChenTian on 2018/4/21.
 */
@Getter
@Setter
public class Window {
    private WindowImp windowImp;
    public void drawText(){
        System.out.println("Window drawText!");
    }
    public void drawRect(){
        windowImp.devDrawLine();
        windowImp.devDrawLine();
        windowImp.devDrawLine();
        windowImp.devDrawLine();
    }
}

