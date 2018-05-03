package com.chen.design.base.construction;

import com.chen.design.base.construction.adapter.own.Line;
import com.chen.design.base.construction.adapter.own.Shape;
import com.chen.design.base.construction.adapter.own.TextShape;
import com.chen.design.base.construction.adapter.own.TextView;
import com.chen.design.base.construction.bridge.own.*;
import org.junit.Test;

/**
 * Created by ChenTian on 2018/4/19.
 */
public class Base {

    @Test
    public void testAdapter(){
        TextView textView = new TextView();
        Shape shape = new TextShape(textView);
        shape.boundingBox();
        Shape line = new Line();
        line.boundingBox();
    }

    @Test
    public void testBridge(){
        Window window = new Window();
        window.drawText();

        WindowImp windowImp = new WindowImp();
        window.setWindowImp(windowImp);
        window.drawRect();

        IconWindow iconWindow = new IconWindow();
        WindowImp xWindowImp = new XWindowImp();
        iconWindow.setWindowImp(xWindowImp);
        iconWindow.drawBorder();

        TransientWindow transientWindow = new TransientWindow();
        WindowImp pmWindowImp = new PMWindowImp();
        transientWindow.setWindowImp(pmWindowImp);
        transientWindow.drawCloseBox();
    }



}
