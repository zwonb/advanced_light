package com.zwonb.mydesginpatterns.singleton;

/**
 * 静态内部类
 * Created by zwonb on 2018/3/9.
 */

public class Singleton5 {


    private Singleton5() {}

    public static Singleton5 getInstance() {
        return SingletonHolder.sInstance;
    }

    private static class SingletonHolder {
        private static final Singleton5 sInstance = new Singleton5();
    }

    public void dos(){
        System.out.println("111");
    }

}
