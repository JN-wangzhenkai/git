package com.feicuiedu.gitdroid.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangzhenkai on 2016/6/30.
 */
public class HotRepoPagerAdapter extends FragmentPagerAdapter {

    private  final List<String >la;


    public HotRepoPagerAdapter(FragmentManager fm) {
        super(fm);
        la=new ArrayList<>();

        la.add("java 1");
        la.add("java 2");
        la.add("java 3");
        la.add("java 4");
        la.add("java 5");
        la.add("java 6");
        la.add("java 7");


    }

    @Override
    public Fragment getItem(int position) {
        return RepoListFragment.getInstance(la.get(position));
    }

    @Override
    public int getCount() {
        return la.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return la.get(position);
    }
}
