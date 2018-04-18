package com.chen.design.base.creation.builder.example;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * Created by ChenTian on 2018/4/18.
 */
@ToString
@Getter
@Setter
public class GoobyeMessage extends AutoMessage{
    private String to;
    private String from;
    private String subject;
    private String body;
    private Date sendDate;

    public void send(){
        System.out.println("say goodbye!");
    }
}
