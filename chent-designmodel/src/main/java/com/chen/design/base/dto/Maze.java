package com.chen.design.base.dto;

import lombok.Getter;

import java.util.ArrayList;

/**
 * รินฌ
 * Created by ChenTian on 2018/4/17.
 */
@Getter
public class Maze {
    private ArrayList<Room> mapsites = new ArrayList<Room>();

    public void addRoom(Room room){
        mapsites.add(room);
    }

    public Room getRoom(int roomNo){
        for(Room obj : mapsites){
            if(obj.getRoomNo() == roomNo){
                return obj;
            }
        }
        return null;
    }
}
