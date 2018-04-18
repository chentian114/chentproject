package com.chen.design.base.dto;

/**
 * Created by ChenTian on 2018/4/17.
 */
public class BombedRoom extends Room{
    @Override
    public void enter() {
        System.out.println("BombedRoom");
    }
}
