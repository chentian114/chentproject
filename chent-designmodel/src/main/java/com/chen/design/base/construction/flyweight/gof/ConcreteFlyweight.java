package com.chen.design.base.construction.flyweight.gof;

/**
 * Created by ChenTian on 2018/5/3.
 */
public class ConcreteFlyweight implements Flyweight {
    private int state ;
    public ConcreteFlyweight(int state){
        this.state = state;
    }

    public void operation(String exState) {
        StringBuilder sbr = new StringBuilder();
        sbr.append(this).append(" operation state:").append(state).append(" exState:").append(exState);
        System.out.println(sbr.toString());
    }
}
