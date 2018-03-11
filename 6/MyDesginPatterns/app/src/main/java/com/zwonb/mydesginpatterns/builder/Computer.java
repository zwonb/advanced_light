package com.zwonb.mydesginpatterns.builder;

/**
 * 产品类
 * Created by zwonb on 2018/3/11.
 */

public class Computer {

    private String mCpu;
    private String mMainboard;
    private String mRam;

    public void setCpu(String cpu) {
        mCpu = cpu;
    }

    public void setMainboard(String mainboard) {
        mMainboard = mainboard;
    }

    public void setRam(String ram) {
        mRam = ram;
    }
}
