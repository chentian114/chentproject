package com.chen.design.base.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ChenTian on 2018/4/17.
 */
@Getter
@Setter
public class Room implements Mapsite{
    private int roomNo;
    private Map<Direction,Mapsite> sites = new HashMap<Direction, Mapsite>();
    public Room(){}
    public Room(int roomNo){
        this.roomNo = roomNo;
    }
    public Room(int roomNo,Map sites){
        this.roomNo = roomNo;
        this.sites = sites;
    }
    public void setSite(Direction d,Mapsite mapsite){
        sites.put(d,mapsite);
    }
    public void enter() {
        System.out.println("room");
    }
}
