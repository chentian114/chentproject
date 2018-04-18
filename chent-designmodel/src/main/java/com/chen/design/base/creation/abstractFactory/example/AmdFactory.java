package com.chen.design.base.creation.abstractFactory.example;

/**
 * Created by ChenTian on 2018/4/18.
 */
public class AmdFactory implements AbstractFactory {
    public Cpu createCpu() {
        AmdCpu cpu = new AmdCpu();
        cpu.setPins(938);
        return cpu;
    }

    public MainBoard createMainBoard() {
        AmdMainBoard mainBoard = new AmdMainBoard();
        mainBoard.setCpuHoles(938);
        return mainBoard;
    }
}
