package com.feicuiedu.gitdroid.github.hotrepo.pager;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.feicuiedu.gitdroid.github.hotrepo.view.LanguageRepoFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangzhenkai on 2016/6/30.
 * 此适配器用于HotRepoFragment, 每一页都是一个RepoListFragment.
 *
 * 用户浏览过的所有子页面fragment都会保存在内存中，但当它们不可见时，其上的View可能被摧毁。
 * 这可能导致占用大量的内存，因为fragment实例能保存任意量的状态值。

 * 在我们的应用内，这是可以接收的，否则应该考虑使用FragmentStatePagerAdapter。
 */
public class HotRepoPagerAdapter extends FragmentPagerAdapter {

    private  final List<Language >la;


    public HotRepoPagerAdapter(FragmentManager fm, Context context) {
        super(fm);

        //从本地read 出来
        la=Language.getDefaultLanguage(context);

    }

    @Override
    public Fragment getItem(int position) {
        return LanguageRepoFragment.getInstance(la.get(position));
    }

    @Override
    public int getCount() {
        return la.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return la.get(position).getName();
    }
}
