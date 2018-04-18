package com.chen.design.base.creation.builder;

import com.chen.design.base.dto.*;

/**
 * Created by ChenTian on 2018/4/17.
 */
public class BaseMazeBuilder implements MazeBuilder{
    private Maze maze;

    public void createMaze() {
        maze = new Maze();
    }

    public void createRoom() {
        Room room1 = new Room(1);
        maze.addRoom(room1);
        Room room2 = new Room(2);
        maze.addRoom(room2);
    }

    public void createDoor() {
        Room room1 = maze.getRoom(1);
        Room room2 = maze.getRoom(2);
        Door door = new Door(maze.getRoom(1),maze.getRoom(2));
        commonDoor(room1,Direction.EAST,door);
        commonDoor(room2,Direction.WEST,door);
    }

    public void commonDoor(Room room,Direction direction,Door door){
        for (Direction obj : Direction.values()) {
            if(obj.equals(direction)) {
                room.setSite(obj, door);
            }else {
                room.setSite(obj,new Wall());
            }
        }
    }
    public Maze getResult() {
        return maze;
    }
}
