package com.zwonb.myviewslide;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

/**
 * Created by zyb on 2017/8/8.
 */

public class SlideView extends View {

    private int lastY;
    private int lastX;
    private Scroller mScroller;

    public SlideView(Context context) {
        super(context);
        init();
    }

    public SlideView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mScroller = new Scroller(getContext());
    }

    public SlideView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //获取到手指触摸时的横坐标和纵坐标
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                //计算出移动的距离
                int offsetX = x - lastX;
                int offsetY = y - lastY;

                //1.使用layout方法重新放置view的位置
//                layout(getLeft() + offsetX, getTop() + offsetY,
//                        getRight() + offsetX, getBottom() + offsetY);

                //2.对left和right进行偏移
//                offsetLeftAndRight(offsetX);
                //对top和bottom进行偏移
//                offsetTopAndBottom(offsetY);

                //3.LayoutParams改变布局参数
//                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) getLayoutParams();
//                layoutParams.leftMargin = getLeft()+offsetX;
//                layoutParams.topMargin = getTop()+offsetY;
//                setLayoutParams(layoutParams);

                //4.MarginLayoutParams改变布局参数
//                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) getLayoutParams();
//                marginLayoutParams.leftMargin = getLeft() + offsetX;
//                marginLayoutParams.topMargin = getTop() + offsetY;
//                setLayoutParams(marginLayoutParams);

                //5.使用动画，在anim文件中设置动画

                //6.scrollBy/scrollTo----移动的是view的内容
                // 如果是ViewGroup移动的是所有子view
//                ((View) getParent()).scrollBy(-offsetX, -offsetY);

                //7.scroller

                break;
        }
        return true;
    }

    public void smoothScrollTo(int destX, int destY) {
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        int offsetX = destX - scrollX;
        int offsetY = destY - scrollY;
        mScroller.startScroll(scrollX, scrollY, offsetX, offsetY, 2000);
        invalidate();
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            ((View) getParent()).scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            invalidate();
        }
    }
}
