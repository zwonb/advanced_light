package com.zwonb.mycoordinnatorlayout;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewPropertyAnimator;

/**
 * Created by zyb on 2017/8/7.
 */

public class FooterBehavior extends CoordinatorLayout.Behavior<View> {

    private int directionChange;

    public FooterBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
        return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
//Log.e("binbin", "onNestedScroll: " + dyConsumed);
    }

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dx, int dy, int[] consumed) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);
        Log.e("binbin", "onNestedPreScroll: dy = " + dy);
        Log.e("binbin", "onNestedPreScroll: dx = " + dx);
        Log.e("binbin", "onNestedPreScroll: target.getHeight = " + target.getHeight());
        if (dy > 0 && directionChange < 0 || dy < 0 && directionChange > 0) {
            child.animate().cancel();
            directionChange = 0;
        }
        directionChange += dy;
        Log.e("binbin", "onNestedPreScroll: dire = " + directionChange);
        if (directionChange > child.getHeight() && child.getVisibility() == View.VISIBLE) {
            hide(child);
        } else if (directionChange < 0 && child.getVisibility() == View.GONE) {
            show(child);
        }
    }

    private void hide(final View view) {
        ViewPropertyAnimator animator = view.animate().translationY(view.getHeight()).setInterpolator(new FastOutSlowInInterpolator()).setDuration(200);
        animator.setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animator) {
                view.setVisibility(View.GONE);
            }
        });
        animator.start();
    }

    private void show(final View view) {
        ViewPropertyAnimator animator = view.animate().translationY(0).setInterpolator(new FastOutSlowInInterpolator()).setDuration(200);
        animator.setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animator) {
                view.setVisibility(View.VISIBLE);
            }
        });
        animator.start();
    }
}
