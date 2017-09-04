package com.zwonb.myviewslide;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SlideView slideView = (SlideView) findViewById(R.id.slide_view);
        //使用动画让view动起来
//        slideView.setAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.translate));
//        ObjectAnimator.ofFloat(slideView, "translationX",0,300).setDuration(1000).start();

        slideView.smoothScrollTo(-400,-200);
    }
}
