package com.zwonb.mydesginpatterns.factory.method;

/**
 * 具体工厂，通过反射来生产不同厂家的计算机
 * Created by zwonb on 2018/3/9.
 */

public class GDComputerFactory extends ComputerFactory {

    @SuppressWarnings("unchecked cast")
    @Override
    public <T extends Computer> T createComputer(Class<T> clazz) {
        Computer computer = null;
        String clazzName = clazz.getName();
        try {
            computer = (Computer) Class.forName(clazzName).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (T) computer;
    }
}
