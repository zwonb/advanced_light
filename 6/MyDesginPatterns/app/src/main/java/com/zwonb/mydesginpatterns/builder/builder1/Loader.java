package com.zwonb.mydesginpatterns.builder.builder1;

/**
 * 产品类
 * Created by zwonb on 2018/3/11.
 */

public class Loader {

    private boolean isWhiteBG;

    private String text;

    public boolean isWhiteBG() {
        return isWhiteBG;
    }

    public void setWhiteBG(boolean whiteBG) {
        isWhiteBG = whiteBG;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public static class Builder {

        private final Loader mLoader;

        public Builder() {
            mLoader = new Loader();
        }

        public Builder setWhiteBG(boolean isWhiteBG){
            mLoader.isWhiteBG = isWhiteBG;
            return this;
        }

        public Builder setText(String text) {
            mLoader.text = text;
            return this;
        }

        public Loader build(){
            return mLoader;
        }

    }
}
