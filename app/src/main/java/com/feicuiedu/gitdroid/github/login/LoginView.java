package com.feicuiedu.gitdroid.github.login;

import com.hannesdorfmann.mosby.mvp.MvpView;

/**
 * Created by wangzhenkai on 2016/7/6.
 * 这是登录页面的视图抽象
 */
public interface LoginView extends MvpView {
    //显示进度条
    void showProgress();

    //重置webview
    void resetWeb();

    void showMessage(String msg);

    //导航主页面
    void navigateMain();


}
