package com.chen.design.base.creation.builder.example;

/**
 * Created by ChenTian on 2018/4/18.
 */
public class Director {
    private Builder builder;
    public Director(Builder builder){
        this.builder = builder;
    }
    public AutoMessage construct(String to,String from){
        return  builder.buildTo(to).buildFrom(from).buildBody().buildSendDate()
                .buildSubject().sendMessage();
    }

}
