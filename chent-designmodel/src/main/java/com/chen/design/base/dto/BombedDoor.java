package com.chen.design.base.dto;

/**
 *
 * Created by ChenTian on 2018/4/17.
 */
public class BombedDoor extends Door {
    @Override
    public void enter() {
        System.out.println("bomedDoor");
    }
}
