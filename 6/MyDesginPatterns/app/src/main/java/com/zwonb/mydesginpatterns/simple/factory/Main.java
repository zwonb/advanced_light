package com.zwonb.mydesginpatterns.simple.factory;

/**
 * 客户端调用
 * Created by zwonb on 2018/3/9.
 */

public class Main {

    public static void main(String[] args) {
        Computer asus = ComputerFactory.createComputer("asus");
        asus.start();
    }
}
