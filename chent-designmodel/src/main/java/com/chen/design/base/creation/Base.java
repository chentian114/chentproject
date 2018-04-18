package com.chen.design.base.creation;

import com.alibaba.fastjson.JSON;
import com.chen.design.base.creation.abstractFactory.AbstractFactory;
import com.chen.design.base.creation.abstractFactory.ConcreteBaseFactory;
import com.chen.design.base.creation.abstractFactory.ConcreteBombedFactory;
import com.chen.design.base.creation.builder.MazeDirector;
import com.chen.design.base.creation.factoryMethod.ConcreteCreator;
import com.chen.design.base.creation.factoryMethod.Creator;
import com.chen.design.base.creation.prototype.WallPrototype;
import com.chen.design.base.creation.singleton.MazeSingleton;
import com.chen.design.base.creation.singleton.MazeSingleton2;
import com.chen.design.base.creation.singleton.MazeSingleton3;
import com.chen.design.base.dto.Door;
import com.chen.design.base.dto.Maze;
import com.chen.design.base.dto.Room;
import com.chen.design.base.dto.Wall;
import org.junit.Test;

/**
 * Created by ChenTian on 2018/4/17.
 */
public class Base {
    @Test
    public void testFactoryMethod(){
        System.out.println("hello");
        Creator creator = new ConcreteCreator();
        Wall wall = creator.factoryMethod(Wall.class);
        wall.enter();
        Door door = creator.factoryMethod(Door.class);
        door.enter();
        Room room = creator.factoryMethod(Room.class);
        room.enter();
    }

    @Test
    public void testAbstractFactory(){
        AbstractFactory baseFactory = new ConcreteBaseFactory();
        AbstractFactory bombedFactory = new ConcreteBombedFactory();
        Wall wall = baseFactory.createWall();
        System.out.println(wall);wall.enter();
        Door door = baseFactory.createDoor();
        System.out.println(door);door.enter();
        Room room = baseFactory.createRoom();
        System.out.println(room);room.enter();

        wall = bombedFactory.createWall();
        System.out.println(wall);wall.enter();
        door = bombedFactory.createDoor();
        System.out.println(door);door.enter();
        room = bombedFactory.createRoom();
        System.out.println(room);room.enter();
    }

    @Test
    public void testPrototype(){
        WallPrototype prototype = new WallPrototype();
        WallPrototype clone1 = prototype.clone();
        System.out.println(clone1);clone1.enter();
        WallPrototype clone2 = prototype.clone();
        System.out.println(clone2);clone2.enter();
        WallPrototype clone3 = prototype.clone();
        System.out.println(clone3);clone3.enter();
    }

    @Test
    public void testBuilder(){
        MazeDirector director = new MazeDirector();
        Maze maze = director.buildBaseMaze();
        System.out.println(JSON.toJSONString(maze));
    }

    @Test
    public void testSingleton(){
        System.out.println(MazeSingleton.getInstance());
        System.out.println(MazeSingleton2.getInstance());
        System.out.println(MazeSingleton3.getInstance());
    }
}
