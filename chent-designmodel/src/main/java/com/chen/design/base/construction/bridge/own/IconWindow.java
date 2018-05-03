package com.chen.design.base.construction.bridge.own;

import lombok.Getter;
import lombok.Setter;

/**
 * 桥接模式--抽象类角色
 * Created by ChenTian on 2018/4/21.
 */
@Getter
@Setter
public class IconWindow extends Window{

    public void drawBorder(){
        System.out.println("IconWindow drawBorder start!");
        drawRect();
        drawText();
        System.out.println("IconWindow drawBorder end!");
    }

}

