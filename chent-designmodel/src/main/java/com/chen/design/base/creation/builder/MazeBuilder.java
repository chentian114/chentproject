package com.chen.design.base.creation.builder;

import com.chen.design.base.dto.Maze;

/**
 * Created by ChenTian on 2018/4/17.
 */
public interface MazeBuilder {
    public void createMaze();
    public void createRoom();
    public void createDoor();
    public Maze getResult();

}
