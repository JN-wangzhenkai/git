package com.feicuiedu.gitdroid.pager;

import android.content.Context;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.feicuiedu.gitdroid.R;

/**
 * Created by wangzhenkai on 2016/6/29.
 */
public class Pager0 extends FrameLayout {

    public Pager0(Context context) {
        super(context);
        init();
    }

    public Pager0(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }


    public Pager0(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

   init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.content_pager_0,this,true);//把现在这个布局直接添加到当前控件上所以用 true
//或者
//        View view=LayoutInflater.from(getContext()).inflate(R.layout.content_pager_0,this,false);
//        addView(view);

    }
}
