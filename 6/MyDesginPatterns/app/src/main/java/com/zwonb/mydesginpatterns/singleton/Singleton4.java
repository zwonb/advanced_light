package com.zwonb.mydesginpatterns.singleton;

/**
 * 双重检查模式
 * Created by zwonb on 2018/3/9.
 */

public class Singleton4 {

    private volatile static Singleton4 instance;

    private Singleton4() {}

    public static Singleton4 getInstance() {
        if (instance == null) {
            synchronized (Singleton4.class) {
                if (instance == null) {
                    instance = new Singleton4();
                }
            }
        }
        return instance;
    }

}
