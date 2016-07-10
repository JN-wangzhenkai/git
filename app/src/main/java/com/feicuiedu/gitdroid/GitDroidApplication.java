package com.feicuiedu.gitdroid;

import android.app.Application;
import android.graphics.BitmapFactory;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

/**
 * Created by wangzhenkai on 2016/7/7.
 */
public class GitDroidApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        DisplayImageOptions Options=new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.drawable.ic_avatar)// url为空的时候  也可以在使用的时候设置
                .showImageOnLoading(R.drawable.ic_avatar)//加载中显示的图片
                .showImageOnFail(R.drawable.ic_avatar)//加载失败时
                .displayer(new RoundedBitmapDisplayer(getResources().getDimensionPixelOffset(R.dimen.dp_10)))//圆角 像素
                .cacheInMemory(true)//打开内存缓存
                .cacheOnDisk(true)//打开硬盘缓存
                .resetViewBeforeLoading(true)//在imageview加载前清除他上面之前的图片
                .build();

        //Imageloader 的配置
        ImageLoaderConfiguration config=new ImageLoaderConfiguration.Builder(this)
                .memoryCacheSize(5*1024*1024)// 设置内存缓存为5
                .defaultDisplayImageOptions(Options)//设置默认的显示选项
                .build();
        //初始化ImageLoader
        ImageLoader.getInstance().init(config);
    }

}
