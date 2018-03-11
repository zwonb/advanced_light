package com.zwonb.mydesginpatterns.builder;

/**
 * 组装计算机
 * Created by zwonb on 2018/3/11.
 */

public class ComputerBuilder extends Builder {

    private Computer mComputer = new Computer();

    @Override
    public void setCpu(String cpu) {
        mComputer.setCpu(cpu);
    }

    @Override
    public void setMainboard(String mainboard) {
        mComputer.setMainboard(mainboard);
    }

    @Override
    public void setRam(String ram) {
        mComputer.setRam(ram);
    }

    @Override
    public Computer build() {
        return mComputer;
    }
}
