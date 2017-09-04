package com.zwonb.mycustomview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zyb on 2017/8/14.
 */

public class RectView extends View {

    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
//    private int mColor = Color.RED;
    private int mColor;

    public RectView(Context context) {
        super(context);
        init(context, null);
    }

    public RectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public RectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RectView);
        //提取RectView属性集合的rect_color属性。如果没有设置，默认值为Color.RED
        mColor = typedArray.getColor(R.styleable.RectView_rect_color, Color.RED);
        typedArray.recycle();

        mPaint.setColor(mColor);
        mPaint.setStrokeWidth(1.5f);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthMode == MeasureSpec.AT_MOST & heightMode == MeasureSpec.AT_MOST) {
            //宽高都是自适应的情况
            setMeasuredDimension(600, 600);
        } else if (widthMode == MeasureSpec.AT_MOST) {
            //宽度是自适应的情况
            setMeasuredDimension(600, heightSize);
        } else if (heightMode == MeasureSpec.AT_MOST) {
            //高度是自适应的情况
            setMeasuredDimension(widthSize, 600);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();

        int width = getWidth() - paddingLeft - paddingRight;
        int height = getHeight() - paddingTop - paddingBottom;

        canvas.drawRect(paddingLeft, paddingTop, width + paddingLeft, height + paddingTop, mPaint);
    }
}
