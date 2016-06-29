package com.feicuiedu.gitdroid.pager;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.feicuiedu.gitdroid.R;

/**
 * Created by wangzhenkai on 2016/6/29.
 */
public class Pager1 extends FrameLayout {
    public Pager1(Context context) {
        super(context);
        init();
    }

    public Pager1(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Pager1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {

        LayoutInflater.from(getContext()).inflate(R.layout.content_pager_1,this,true);//把现在这个布局直接添加到当前控件上所以用 true

    }
}
