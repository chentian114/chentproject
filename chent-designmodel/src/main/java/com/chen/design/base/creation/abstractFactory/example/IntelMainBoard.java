package com.chen.design.base.creation.abstractFactory.example;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by ChenTian on 2018/4/18.
 */
@Getter
@Setter
public class IntelMainBoard implements MainBoard {
    private int cpuHoles ;


    public void installCPU() {
        System.out.println(new StringBuilder("Amd 主板CPU插孔数是").append(cpuHoles));
    }
}
