package com.zwonb.mydesginpatterns.builder.builder1;

/**
 * 客户端调用
 * Created by zwonb on 2018/3/11.
 */

public class Main {

    public static void main(String[] args) {
        Loader loader = new Loader.Builder()
                .setWhiteBG(true)
                .setText("这是一段文字")
                .build();

        System.out.println(loader.getText() + loader.isWhiteBG());
    }
}
