package com.feicuiedu.gitdroid.github.repo;

import android.util.Base64;

import com.feicuiedu.gitdroid.github.hotrepo.modle.Repo;
import com.feicuiedu.gitdroid.neetwork.GithubApi;
import com.feicuiedu.gitdroid.neetwork.GithubClient;
import com.hannesdorfmann.mosby.mvp.MvpNullObjectBasePresenter;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by wangzhenkai on 2016/7/10.
 * 仓库详情信息业务
 */
public class RepoInfoPresenter extends MvpNullObjectBasePresenter<RepoInfoView> {

    private Call<RepoContentResult> repoContentResultCall;
    private Call<ResponseBody>mdCall;

    //获取指定仓库的readme
    public void getReadme(Repo repo){
        getView().showProgress();

        String login=repo.getOwner().getLogin();
        String name=repo.getName();

        if(repoContentResultCall!=null) repoContentResultCall.cancel();
        repoContentResultCall=GithubClient.getInstance().getReadme(login,name);
        repoContentResultCall.enqueue(repoContentCallback);
    }

    private Callback<RepoContentResult>repoContentCallback= new Callback<RepoContentResult>() {
        @Override
        public void onResponse(Call<RepoContentResult> call, Response<RepoContentResult> response) {

            String content=response.body().getContent();
            //base64 解码
            byte[]data=Base64.decode(content,Base64.DEFAULT);
            String mdContent=new String (data);//markdown 格式的字符串

            //根据markdown  获取html格式的

            //吧获取到的内容 传过去
            RequestBody body=RequestBody.create(MediaType.parse("text/plain"),mdContent);

            if(mdCall!=null){mdCall.cancel();}
            mdCall=GithubClient.getInstance().markdown(body);
            mdCall.enqueue(mdCallback);

        }

        @Override
        public void onFailure(Call<RepoContentResult> call, Throwable t) {

            getView().hideProgress();
            getView().showMessage("Error"+t.getMessage());
        }
    };


    private Callback<ResponseBody>mdCallback=new Callback<ResponseBody>() {
        @Override
        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

            String  htmlContent= null;
            try {
                htmlContent = response.body().string();
                getView().setData(htmlContent);//设置到webview 上显示
                getView().hideProgress();
            } catch (IOException e) {
                onFailure(call,e);
            }

        }

        @Override
        public void onFailure(Call<ResponseBody> call, Throwable t) {

            getView().hideProgress();
            getView().showMessage("Error"+t.getMessage());
        }
    };
}
