package com.zwonb.mydesginpatterns.factory.method;


/**
 * 惠普计算机
 * Created by zwonb on 2018/3/9.
 */

public class HPComputer extends Computer {

    @Override
    public void start() {
        System.out.println("惠普计算机启动");
    }
}
