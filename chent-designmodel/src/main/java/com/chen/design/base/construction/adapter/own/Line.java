package com.chen.design.base.construction.adapter.own;

/**
 * Created by ChenTian on 2018/4/19.
 */
public class Line implements Shape {
    public void boundingBox() {
        System.out.println("bounding "+this.getClass().getName());
    }

    public void createManipulator() {
        System.out.println("Manipulator "+this.getClass().getName());
    }
}
