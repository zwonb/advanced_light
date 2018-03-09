package com.zwonb.mydesginpatterns.factory.method;

/**
 * 抽象工厂
 * Created by zwonb on 2018/3/9.
 */

public abstract class ComputerFactory {

    public abstract <T extends Computer> T createComputer(Class<T> clazz);

}
