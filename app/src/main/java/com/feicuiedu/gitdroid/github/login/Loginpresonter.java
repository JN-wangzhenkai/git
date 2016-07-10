package com.feicuiedu.gitdroid.github.login;

import com.feicuiedu.gitdroid.github.login.model.AccessTokenResult;
import com.feicuiedu.gitdroid.github.login.model.CurrentUser;
import com.feicuiedu.gitdroid.github.login.model.user;
import com.feicuiedu.gitdroid.neetwork.GithubApi;
import com.feicuiedu.gitdroid.neetwork.GithubClient;
import com.hannesdorfmann.mosby.mvp.MvpNullObjectBasePresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by wangzhenkai on 2016/7/6.
 * 此类是处理登录案例的 并且在登录过程中 触发并调用Loginview
 * 登陆过程遵循标准oauth2.0协议
 * 用户通过webview 登录githubg 网站 如果登陆成功 且用户给我们授权，github 会访问我们的回调地址 给我们一个授权码
 * 我们就通过授权码获得访问令牌，最终就有权利访问信息了
 *
 */
public class Loginpresonter extends MvpNullObjectBasePresenter<LoginView>{

    private Call<AccessTokenResult> tokenCall;
    private  Call<user> userCall;

    /**
     * 此方法是本Presenter中最重要的方法。视图会调用这个方法来触发登录用例。
     *
     * @param code 用户登录GitHub后，GitHub给我们的访问令牌。
     */
    public void login(String code){
        getView().showProgress();
        if(tokenCall!=null)tokenCall.cancel();
        tokenCall= GithubClient.getInstance().getOAuthToken(GithubApi.CLIENT_ID,GithubApi.CLIENT_SECRET,code);
        tokenCall.enqueue(tokenCallback);
    }
    //获取AccessToken的回调
    private Callback<AccessTokenResult>tokenCallback =new Callback<AccessTokenResult>() {
        @Override
        public void onResponse(Call<AccessTokenResult> call, Response<AccessTokenResult> response) {
            //保存token到内存里面
            String token=response.body().getAccessToken();
            //写了一个类存储到内存
            CurrentUser.setAccessToken(token);

            //z再次业务操作 使用这个token 去获取当前已认证的用户信息
            //从而拿到名称，头像……
            if(userCall!=null)
                userCall.cancel();

            userCall=GithubClient.getInstance().getUserInfo();//获取用户信息
            userCall.enqueue(userCallback);
        }

        @Override
        public void onFailure(Call<AccessTokenResult> call, Throwable t) {

            getView().showMessage("Fail:" + t.getMessage());
            // 失败，重置WebView
            getView().showProgress();
            getView().resetWeb();
        }
    };

    //获取用户信息的回调
    private Callback<user> userCallback=new Callback<user>() {
        @Override
        public void onResponse(Call<user> call, Response<user> response) {

            //保存user 到内存里面
            user user=response.body();
            CurrentUser.setUser(user);

            //导航至主页面
            getView().showMessage("登陆成功");
            getView().navigateMain();
        }

        @Override
        public void onFailure(Call<user> call, Throwable t) {

            //清除缓存的用户信息
            CurrentUser.clear();
            getView().showMessage("Fail:"+t.getMessage());
            //重置webview
            getView().showProgress();
            getView().resetWeb();
        }
    };

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
        if(!retainInstance){
            if(tokenCall!=null) tokenCall.cancel();
            if (userCall!=null)userCall.cancel();
        }
    }
}
