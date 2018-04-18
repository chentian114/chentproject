package com.chen.design.base.creation.builder.example;

/**
 * Created by ChenTian on 2018/4/18.
 */
public interface Builder {
    public Builder buildTo(String to);
    public Builder buildFrom(String from);
    public Builder buildSubject();
    public Builder buildBody();
    public Builder buildSendDate();
    public AutoMessage sendMessage();
}
