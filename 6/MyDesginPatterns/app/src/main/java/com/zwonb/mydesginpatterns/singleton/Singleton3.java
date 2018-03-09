package com.zwonb.mydesginpatterns.singleton;

/**
 * 懒汉式(线程安全)
 * Created by zwonb on 2018/3/9.
 */

public class Singleton3 {

    private static Singleton3 instance;

    private Singleton3() {}

    public static synchronized Singleton3 getInstance() {
        if (instance == null) {
            instance = new Singleton3();
        }
        return instance;
    }

}
