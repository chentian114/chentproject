package com.chen.design.base.creation.abstractFactory.example;

/**
 * Created by ChenTian on 2018/4/18.
 */
public class IntelFactory implements AbstractFactory {
    public Cpu createCpu() {
        IntelCpu cpu = new IntelCpu();
        cpu.setPins(755);
        return cpu;
    }

    public MainBoard createMainBoard() {
        IntelMainBoard mainBoard = new IntelMainBoard();
        mainBoard.setCpuHoles(755);
        return mainBoard;
    }
}
