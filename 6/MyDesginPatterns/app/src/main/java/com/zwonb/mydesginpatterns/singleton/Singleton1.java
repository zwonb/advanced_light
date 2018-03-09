package com.zwonb.mydesginpatterns.singleton;

/**
 * 饿汉式单例
 * Created by zwonb on 2018/3/9.
 */

public class Singleton1 {

    private static Singleton1 instance = new Singleton1();

    private Singleton1() {}

    public static Singleton1 getInstance() {
        return instance;
    }

}
