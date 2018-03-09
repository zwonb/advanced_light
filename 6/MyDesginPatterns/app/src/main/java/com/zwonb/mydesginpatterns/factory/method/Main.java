package com.zwonb.mydesginpatterns.factory.method;

/**
 * 工厂方法客户端
 * Created by zwonb on 2018/3/9.
 */

public class Main {

    public static void main(String[] args) {
        // 生产不同厂家的计算机，如果有新的厂家计算机，比如苹果计算机，直接新建产品类即可
        // 简单工厂则需要在工厂类增加一个case类型，工厂方法则不需要

        ASUSComputer asusComputer = new GDComputerFactory().createComputer(ASUSComputer.class);
        asusComputer.start();

        HPComputer hpComputer = new GDComputerFactory().createComputer(HPComputer.class);
        hpComputer.start();

        LenovoComputer lenovoComputer = new GDComputerFactory().createComputer(LenovoComputer.class);
        lenovoComputer.start();
    }
}
