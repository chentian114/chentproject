package com.chen.design.base.creation.abstractFactory;

import com.chen.design.base.dto.Door;
import com.chen.design.base.dto.Room;
import com.chen.design.base.dto.Wall;

/**
 * Created by ChenTian on 2018/4/17.
 */
public interface AbstractFactory {
    public Door createDoor();
    public Room createRoom();
    public Wall createWall();
}
