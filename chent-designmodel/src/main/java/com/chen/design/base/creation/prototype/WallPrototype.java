package com.chen.design.base.creation.prototype;

import com.chen.design.base.dto.Mapsite;

/**
 * Created by ChenTian on 2018/4/17.
 */
public class WallPrototype implements Cloneable,Mapsite {
    @Override
    public WallPrototype clone(){
        WallPrototype prototype = null ;
        try {
            prototype = (WallPrototype)super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return prototype;
    }

    public void enter() {
        System.out.println("walprototype");
    }
}
