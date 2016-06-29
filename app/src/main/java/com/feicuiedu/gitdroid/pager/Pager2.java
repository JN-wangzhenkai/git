package com.feicuiedu.gitdroid.pager;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.feicuiedu.gitdroid.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by wangzhenkai on 2016/6/29.
 */
public class Pager2 extends FrameLayout {
    @Bind(R.id.ivBubble1)ImageView imageView1;
    @Bind(R.id.ivBubble2)ImageView imageView2;
    @Bind(R.id.ivBubble3)ImageView imageView3;

    public Pager2(Context context) {
        super(context);
        init();
    }

    public Pager2(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Pager2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {

        LayoutInflater.from(getContext()).inflate(R.layout.content_pager_2,this,true);//把现在这个布局直接添加到当前控件上所以用 true

        ButterKnife.bind(this);

      //  YoYo.with(动画效果).duration(时间).playOn(视图);

        imageView1.setVisibility(View.GONE);
        imageView2.setVisibility(View.GONE);
        imageView3.setVisibility(View.GONE);


    }

    /**
     * 用来显示当前页面内三个图像控件的进入动画 只会显示一次
     */
    public void showAnimaion(){
//  不可见时才执行
        if(imageView1.getVisibility()!= View.VISIBLE){

        postDelayed(new Runnable() {
            @Override
            public void run() {

                imageView1.setVisibility(View.VISIBLE);
                YoYo.with(Techniques.FadeInLeft).duration(300).playOn(imageView1);
            }
        },50);//50毫秒后开始动画

        postDelayed(new Runnable() {
            @Override
            public void run() {

                imageView2.setVisibility(View.VISIBLE);
                YoYo.with(Techniques.FadeInLeft).duration(300).playOn(imageView2);
            }
        },550);

        postDelayed(new Runnable() {
            @Override
            public void run() {

                imageView3.setVisibility(View.VISIBLE);
                YoYo.with(Techniques.FadeInLeft).duration(300).playOn(imageView3);
            }
        },1050);
    }

    }
}
