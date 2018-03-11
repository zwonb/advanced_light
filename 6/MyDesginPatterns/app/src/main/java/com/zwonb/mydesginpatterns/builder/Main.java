package com.zwonb.mydesginpatterns.builder;

/**
 * 客户端调用
 * Created by zwonb on 2018/3/11.
 */

public class Main {

    public static void main(String[] args) {
        Builder builder = new ComputerBuilder();
        Director director = new Director(builder);
        director.createComputer("i7-7700", "雷神-玄武版", "三星DDR4-8*2");
    }
}
