package com.chen.design.base.creation.builder.example;

import java.util.Date;

/**
 * Created by ChenTian on 2018/4/18.
 */
public class WelcomeBuilder implements Builder {
    private AutoMessage msg = new WelcomeMessage();

    public Builder buildTo(String to) {
        msg.setTo(to);
        return this;
    }

    public Builder buildFrom(String from) {
        msg.setFrom(from);
        return this;
    }

    public Builder buildSubject() {
        msg.setSubject("hello world");
        return this;
    }

    public Builder buildBody() {
        msg.setBody("welcome");
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
