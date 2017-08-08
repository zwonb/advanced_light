package com.zwonb.mycoordinnatorlayout;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by zyb on 2017/8/7.
 */

public class FooterBehaviorAppBar extends CoordinatorLayout.Behavior<View> {

    public FooterBehaviorAppBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * @return 关心的是AppBarLayout
     */
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency instanceof AppBarLayout;
    }

    /**
     * @param parent 当前的CoordinatorLayout
     * @param child 设置这个Behavior的View
     * @param dependency 我们关系的那个View
     */
    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        float translationY = Math.abs(dependency.getY());
        Log.e("binbin", "onDependentViewChanged: " + translationY);
        Log.e("binbin", "onDependentViewChanged: getTranslationY = " + dependency.getTranslationY());
        child.setTranslationY(translationY);
        return true;
    }
}
