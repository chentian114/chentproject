package com.chen.design.base.construction.composite.gof;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ChenTian on 2018/5/3.
 */
@Getter
@Setter
public class Composite implements Component{
    private List<Component> childList = new ArrayList<Component>();
    private int compsiteId;

    public Composite(int compsiteId){
        this.compsiteId = compsiteId;
    }

    public void opertation(){
        StringBuilder sbr = new StringBuilder();
        int childSize = childList.size();
        sbr.append("Composite node compsiteId:").append(compsiteId).append(" child size:").append(childSize);
        System.out.println(sbr.toString());
        for (Component obj : childList){
            obj.opertation();
        }
    }

    public int getComponetId() {
        return compsiteId;
    }

    public void add(Component component){
        if(component==null){
            return;
        }
        childList.add(component);
    }

    public void remove(Component component){
        if(component==null){
            return;
        }
        for(Component obj : childList){
            if(component.equals(obj)){
                childList.remove(component);
            }
        }
    }

    public Component getComponetByComponetId(int compsiteId){
        for(Component obj : childList){
            int objId = obj.getComponetId();
            if(objId == compsiteId){
                return obj;
            }
        }
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Composite){
            if(((Composite)obj).getCompsiteId() == this.getCompsiteId()){
                return true;
            }
        }
        return false;
    }
}
