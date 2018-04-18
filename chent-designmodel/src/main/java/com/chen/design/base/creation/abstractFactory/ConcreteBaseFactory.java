package com.chen.design.base.creation.abstractFactory;

import com.chen.design.base.dto.Door;
import com.chen.design.base.dto.Room;
import com.chen.design.base.dto.Wall;

/**
 * Created by ChenTian on 2018/4/17.
 */
public class ConcreteBaseFactory implements AbstractFactory {
    public Door createDoor() {
        return new Door();
    }

    public Room createRoom() {
        return new Room();
    }

    public Wall createWall() {
        return new Wall();
    }
}
