package com.zwonb.mydesginpatterns.builder;

/**
 * Builder类规范产品的组建
 * Created by zwonb on 2018/3/11.
 */

public abstract class Builder {

    public abstract void setCpu(String cpu);

    public abstract void setMainboard(String mainboard);

    public abstract void setRam(String ram);

    public abstract Computer build();
}
