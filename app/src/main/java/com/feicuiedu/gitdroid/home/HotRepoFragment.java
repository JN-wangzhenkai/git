package com.feicuiedu.gitdroid.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.feicuiedu.gitdroid.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by wangzhenkai on 2016/6/30.
 * 最热门仓库fragment
 * <p/>本Fragment是被添加到MainActivity中。
 *
 * <p/>它上面有一个ViewPager和一个相对应的TabLayout。
 *
 * <p/>ViewPager上，每一个页面都是一个RepoListFragment
 */
public class HotRepoFragment extends Fragment {

    @Bind(R.id.viewPager)ViewPager viewPager;
    @Bind(R.id.tabLayout)TabLayout tabLayout;

    private  HotRepoPagerAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_hot_repo,null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this,view);

        //注意此处是在Fragment 中添加的Fragment 属于嵌套Fraagment
        adapter=new HotRepoPagerAdapter(getChildFragmentManager(),getContext());
        viewPager.setAdapter(adapter);

        //将Viewpager 绑定到Tablelayout上
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
