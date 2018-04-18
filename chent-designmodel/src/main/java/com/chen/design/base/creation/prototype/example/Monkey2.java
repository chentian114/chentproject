package com.chen.design.base.creation.prototype.example;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.*;
import java.util.Date;

/**
 * Created by ChenTian on 2018/4/18.
 */
@Getter
@Setter
@ToString
public class Monkey2 implements Cloneable,Serializable {
    private int height;
    private int weight;
    private Date birthDate;
    private GoldRingedStaff staff;

    public Monkey2(){
        this.birthDate = new Date();
        this.staff = new GoldRingedStaff();
    }
    @Override
    public Monkey2 clone() throws CloneNotSupportedException {
        return (Monkey2)super.clone();
    }
    public Monkey2 deepClone(){
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(this);

            ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bis);
            return (Monkey2)ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return  null;


    }
}
