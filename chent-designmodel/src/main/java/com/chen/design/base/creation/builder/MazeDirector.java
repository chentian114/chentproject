package com.chen.design.base.creation.builder;

import com.chen.design.base.dto.Maze;

/**
 * Created by ChenTian on 2018/4/17.
 */
public class MazeDirector {
    MazeBuilder builder = new BaseMazeBuilder();

    public Maze buildBaseMaze(){
        builder.createMaze();
        builder.createRoom();
        builder.createDoor();
        return builder.getResult();
    }
}
