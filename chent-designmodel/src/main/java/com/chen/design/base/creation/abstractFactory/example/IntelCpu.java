package com.chen.design.base.creation.abstractFactory.example;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by ChenTian on 2018/4/18.
 */
@Getter
@Setter
public class IntelCpu implements Cpu {
    private int pins ;

    @Override
    public String toString() {
        return new StringBuilder("Intel CPU针脚数是").append(pins).toString();
    }
}
