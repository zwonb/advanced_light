package com.zwonb.mydesginpatterns.simple.factory;


/**
 * 联想计算机
 * Created by zwonb on 2018/3/9.
 */

public class LenovoComputer extends Computer {

    @Override
    public void start() {
        System.out.println("联想计算机启动");
    }
}
