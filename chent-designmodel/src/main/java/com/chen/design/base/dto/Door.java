package com.chen.design.base.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by ChenTian on 2018/4/17.
 */
@Getter
@Setter
public class Door implements Mapsite{
    private Room room1 ;
    private Room room2;
    public Door(){}
    public Door(Room room1,Room room2){
        this.room1 = room1;
        this.room2 = room2;
    }
    public void enter() {
        System.out.println("door");
    }
}
