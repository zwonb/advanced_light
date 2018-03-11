package com.zwonb.mydesginpatterns.builder;

/**
 * 导演类-统一组装过程
 * Created by zwonb on 2018/3/11.
 */

public class Director {

    Builder mBuilder;

    public Director(Builder builder) {
        mBuilder = builder;
    }

    public Computer createComputer(String cpu, String mainboard, String ram) {
        mBuilder.setMainboard(mainboard);
        mBuilder.setCpu(cpu);
        mBuilder.setRam(ram);
        return mBuilder.build();
    }

}
