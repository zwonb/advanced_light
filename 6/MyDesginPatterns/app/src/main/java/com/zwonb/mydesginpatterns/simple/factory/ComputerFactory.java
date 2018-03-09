package com.zwonb.mydesginpatterns.simple.factory;

/**
 * 计算机工厂类
 * Created by zwonb on 2018/3/9.
 */

public class ComputerFactory {

    public static Computer createComputer(String type) {
        Computer computer = null;
        switch (type) {
            case "lenovo":
                computer = new LenovoComputer();
                break;
            case "hp":
                computer = new HPComputer();
                break;
            case "asus":
                computer = new ASUSComputer();
                break;
        }
        return computer;
    }
}
