package com.feicuiedu.gitdroid.splash;

import android.animation.ArgbEvaluator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.feicuiedu.gitdroid.R;
import com.feicuiedu.gitdroid.pager.Pager2;

import butterknife.Bind;
import butterknife.BindColor;
import butterknife.ButterKnife;
import me.relex.circleindicator.CircleIndicator;

/**
 * Created by wangzhenkai on 2016/6/29.
 */
public class SplashPagerFragment extends Fragment {


    @Bind(R.id.viewPager)
    ViewPager viewPager;
    @Bind(R.id.indicator)
    CircleIndicator indicator;//指示器

    @BindColor(R.color.colorGreen)
    int colorGreen;
    @BindColor(R.color.colorRed)
    int colorRed;
    @BindColor(R.color.colorYellow)
    int colorYellow;

    @Bind(R.id.content)
    FrameLayout frameLayout;//当前页面布局，用于显示背景色的渐变

    @Bind(R.id.layoutPhone)
    FrameLayout layoutPhone;//手机layout
    @Bind(R.id.ivPhoneFont)
    ImageView ivPhoneFont;
    @Bind(R.id.ivPhoneBlank)
    ImageView ivPhoneBlank;

    private SplashPagerAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_splash_pager, null);
        return view;
    }

    //拿到fragment 布局的内容
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);//和fragment 绑定

        adapter = new SplashPagerAdapter(getContext());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(pageChangeListener);
        viewPager.addOnPageChangeListener(phoneViewHandle);

        indicator.setViewPager(viewPager);
    }

    private final ViewPager.OnPageChangeListener phoneViewHandle = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            //在第一个和第二个页面之间 缩放 移动
            if (position == 0) {

                float scale = 0.3f + positionOffset * 0.7f;//0.3测试最佳的 +后面 比例不断变大
                layoutPhone.setScaleX(scale);
                layoutPhone.setScaleY(scale);

                //在移动过程中 字体变化 透明内容显示出来
                ivPhoneFont.setAlpha(positionOffset);

                //在移动过程中有一个平移的动画
                int scroll = (int) (300 * (positionOffset - 1));

                layoutPhone.setTranslationX(scroll);// 上来就左平移400
                // layoutPhone.setTranslationY(scroll);
                return;
            }

            //当viewPager 在第二个页面和第三个页面之间 手机跟着一起平移
            if (position == 1) {
                layoutPhone.setTranslationX(-positionOffsetPixels);
                return;
            }
        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
    private final ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {

        //ARGB 颜色取值器
        final ArgbEvaluator evaluator = new ArgbEvaluator();

        /**
         * 滑动过程中
         * @param position 索引号 在哪个页面
         * @param positionOffset float  滑动比例 相当于滑动距离 0.0-1.0
         * @param positionOffsetPixels 像素值 从哪移到那 同上 类型不同
         */
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            // 三个参数  当前scroll比例 positionOffset
            //  开始颜色 结束颜色  自己做一个
            //颜色值取出来 在自己设置 在其他地方  目的：修改背景
            if (position == 0) {
                int color = (int) evaluator.evaluate(positionOffset, colorGreen, colorRed);

                frameLayout.setBackgroundColor(color);
                return;
            }

            //第二个页面和第三个页面之间
            if (position == 1) {
                int color = (int) evaluator.evaluate(positionOffset, colorRed, colorYellow);

                frameLayout.setBackgroundColor(color);
                return;
            }
        }

        @Override
        public void onPageSelected(int position) {

            if (position == 2) {
                //拿到page2
                Pager2 pager2 = (Pager2) adapter.getView(2);
                pager2.showAnimaion();
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
