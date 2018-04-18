package com.chen.design.base.creation.prototype.example;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * Created by ChenTian on 2018/4/18.
 */
@Getter
@Setter
@ToString
public class Monkey implements Cloneable {
    private int height;
    private int weight;
    private Date birthDate;
    private GoldRingedStaff staff;

    public Monkey(){
        this.birthDate = new Date();
        this.staff = new GoldRingedStaff();
    }
    @Override
    public Monkey clone() {
        try {
            return (Monkey)super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return  null;
    }
}
