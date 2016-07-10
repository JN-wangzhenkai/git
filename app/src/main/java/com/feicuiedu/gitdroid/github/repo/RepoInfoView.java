package com.feicuiedu.gitdroid.github.repo;

import com.hannesdorfmann.mosby.mvp.MvpView;

/**
 * Created by wangzhenkai on 2016/7/10.
 * 仓库详情信息显示
 */
public interface RepoInfoView extends MvpView {

    void showProgress();

    void hideProgress();

    //设置数据（heml 格式的readme文件）
    void setData(String data);

    void showMessage (String msg);
}
