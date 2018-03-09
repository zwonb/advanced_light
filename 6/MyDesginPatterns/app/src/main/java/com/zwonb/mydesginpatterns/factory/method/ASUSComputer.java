package com.zwonb.mydesginpatterns.factory.method;



/**
 * 华硕计算机
 * Created by zwonb on 2018/3/9.
 */

public class ASUSComputer extends Computer {

    @Override
    public void start() {
        System.out.println("华硕计算机启动");
    }
}
