package com.chen.design.base.creation.prototype.example;

import com.chen.design.base.creation.prototype.gof.Prototype;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ChenTian on 2018/4/18.
 */
public class PrototypeManager {
    private static Map<String,Prototype> map = new HashMap<String,Prototype>();

    public static synchronized void setPrototype(String name,Prototype prototype){
        map.put(name,prototype);
    }

    public static synchronized void removePrototype(String name){
        map.remove(name);
    }

    public static Prototype getProtoType(String name){
        return map.get(name);
    }
}
