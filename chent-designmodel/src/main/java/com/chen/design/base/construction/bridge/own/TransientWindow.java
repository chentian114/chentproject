package com.chen.design.base.construction.bridge.own;

import lombok.Getter;
import lombok.Setter;

/**
 * 桥接模式--抽象类角色
 * Created by ChenTian on 2018/4/21.
 */
@Getter
@Setter
public class TransientWindow extends Window{

    public void drawCloseBox(){
        System.out.println("TransientWindow drawCloseBox start!");
        drawRect();
        System.out.println("TransientWindow drawCloseBox end!");
    }

}

