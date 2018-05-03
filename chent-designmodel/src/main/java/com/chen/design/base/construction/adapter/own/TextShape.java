package com.chen.design.base.construction.adapter.own;

/**
 * Created by ChenTian on 2018/4/19.
 */
public class TextShape implements Shape {
    private TextView textView;
    public TextShape(TextView view){
        this.textView = view;
    }

    public void boundingBox() {
        textView.getExtent();
    }

    public void createManipulator() {
        System.out.println("Manipulator "+this.getClass().getName());
    }
}
