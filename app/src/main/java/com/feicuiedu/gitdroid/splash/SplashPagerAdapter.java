package com.feicuiedu.gitdroid.splash;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.feicuiedu.gitdroid.R;
import com.feicuiedu.gitdroid.pager.Pager0;
import com.feicuiedu.gitdroid.pager.Pager1;
import com.feicuiedu.gitdroid.pager.Pager2;

/**
 * Created by wangzhenkai on 2016/6/29.
 */
public class SplashPagerAdapter extends PagerAdapter {

    private final View[]views;


    public SplashPagerAdapter(Context context) {

        LayoutInflater inflater=LayoutInflater.from(context);
//     直接使用视图 修改动画不方便
//     views=new View[]{
//                  inflater.inflate(R.layout.content_pager_0,null,false),
//                  inflater.inflate(R.layout.content_pager_1,null,false),
//                  inflater.inflate(R.layout.content_pager_1,null,false),
//
//
//          };
        views=new View[]{
                new Pager0(context),new Pager1(context),new Pager2(context),
        };
    }

    @Override
    public int getCount() {
        return views.length;
}

    @Override
    public boolean isViewFromObject(View view, Object object) {

        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        container.addView(views[position]);
        return views[position];
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView((View) object);
    }

    //创建共有方法 别人可以拿到视图
    public View getView(int position){
        return views[position];
    }
}
