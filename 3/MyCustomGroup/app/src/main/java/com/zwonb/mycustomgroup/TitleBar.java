package com.zwonb.mycustomgroup;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by zyb on 2017/8/14.
 */

public class TitleBar extends RelativeLayout {


    private ImageView titlebar_left;
    private TextView titlebar_title;
    private ImageView titlebar_right;
    private RelativeLayout titlebar_rootlayout;
    private int mColor = Color.BLUE; //背景颜色
    private int mTextColor = Color.WHITE; //标题颜色
    private String mTitleName;

    public TitleBar(Context context) {
        super(context);
        init(context);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TitleBar);
        mTextColor = typedArray.getColor(R.styleable.TitleBar_title_text_color, Color.WHITE);
        mColor = typedArray.getColor(R.styleable.TitleBar_title_bg, mColor);
        mTitleName = typedArray.getString(R.styleable.TitleBar_title_text);
        typedArray.recycle();

        init(context);
    }

    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.view_custom_title, this, true);
        titlebar_left = (ImageView) findViewById(R.id.iv_titlebar_left);
        titlebar_title = (TextView) findViewById(R.id.tv_titlebar_title);
        titlebar_right = (ImageView) findViewById(R.id.iv_titlebar_right);
        titlebar_rootlayout = (RelativeLayout) findViewById(R.id.layout_titlebar_rootlayout);

        //设置背景颜色
        titlebar_rootlayout.setBackgroundColor(mColor);
        //设置标题字体颜色
        titlebar_title.setTextColor(mTextColor);
        setTitleName(mTitleName);
    }

    /**
     * 设置标题名称
     */
    public void setTitleName(String titleName) {
        if (!TextUtils.isEmpty(titleName)) {
            titlebar_title.setText(titleName);
        }
    }

    /**
     * 设置左边的按钮监听
     */
    public void setLeftListener(OnClickListener onClickListener) {
        titlebar_left.setOnClickListener(onClickListener);
    }

    /**
     * 设置右边按钮的监听
     */
    public void setRightListener(OnClickListener onClickListener) {
        titlebar_right.setOnClickListener(onClickListener);
    }

}
