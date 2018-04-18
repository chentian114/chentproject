package com.chen.design.base.creation.abstractFactory;

import com.chen.design.base.dto.*;

/**
 * Created by ChenTian on 2018/4/17.
 */
public class ConcreteBombedFactory implements AbstractFactory {
    public Door createDoor() {
        return new BombedDoor();
    }

    public Room createRoom() {
        return new BombedRoom();
    }

    public Wall createWall() {
        return new BombedWall();
    }
}
