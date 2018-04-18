package com.chen.design.base.creation.builder.example;

import java.util.Date;

/**
 * Created by ChenTian on 2018/4/18.
 */
public class GoodByeBuilder implements Builder {
    private AutoMessage msg = new GoobyeMessage();

    public Builder buildTo(String to) {
        msg.setTo(to);
        return this;
    }

    public Builder buildFrom(String from) {
        msg.setFrom(from);
        return this;
    }

    public Builder buildSubject() {
        msg.setSubject("goodbye");
        return this;
    }

    public Builder buildBody() {
        msg.setBody("say goodbye");
        return this;
    }

    public Builder buildSendDate() {
        msg.setSendDate(new Date());
        return this;
    }

    public AutoMessage sendMessage() {
        return msg;
    }


}
