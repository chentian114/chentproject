package com.chen.design.base.construction.flyweight.gof;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ChenTian on 2018/5/3.
 */
public class FlyweightFactory {
    private Map<Integer,Flyweight> flyweightMap = new HashMap<Integer, Flyweight>();

    public Flyweight flyweightFactory(int exState){
        Flyweight flyweight = flyweightMap.get(exState);
        if(flyweight==null){
            flyweight = new ConcreteFlyweight(exState);
            flyweightMap.put(exState,flyweight);
        }
        return flyweight;
    }

}
