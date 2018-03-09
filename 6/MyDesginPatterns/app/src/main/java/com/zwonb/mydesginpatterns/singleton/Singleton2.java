package com.zwonb.mydesginpatterns.singleton;

/**
 * 懒汉式(线程不安全)
 * Created by zwonb on 2018/3/9.
 */

public class Singleton2 {

    private static Singleton2 instance;

    private Singleton2() {}

    public static Singleton2 getInstance() {
        if (instance == null) {
            instance = new Singleton2();
        }
        return instance;
    }

}
