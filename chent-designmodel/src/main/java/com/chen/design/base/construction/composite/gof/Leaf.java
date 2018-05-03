package com.chen.design.base.construction.composite.gof;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by ChenTian on 2018/5/3.
 */
@Getter
@Setter
public class Leaf implements Component{
    private int leafId;

    public Leaf(int leafId){
        this.leafId = leafId;
    }
    public void opertation(){
        StringBuilder sbr = new StringBuilder();
        sbr.append("leaf node leadId:").append(leafId).append(" no child");
        System.out.println(sbr.toString());
    }

    public int getComponetId() {
        return leafId;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Leaf){
            if(((Leaf)obj).getLeafId() == this.getLeafId()){
                return true;
            }
        }
        return false;
    }
}
