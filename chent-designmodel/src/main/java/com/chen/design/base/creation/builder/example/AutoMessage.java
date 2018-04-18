package com.chen.design.base.creation.builder.example;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created by ChenTian on 2018/4/18.
 */
@Getter
@Setter
public abstract class AutoMessage {
    private String to;
    private String from;
    private String subject;
    private String body;
    private Date sendDate;

    public abstract void send();
}
