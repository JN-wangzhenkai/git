package com.feicuiedu.gitdroid.github.hotrepo.view;

import com.feicuiedu.gitdroid.github.hotrepo.modle.Repo;
import com.feicuiedu.gitdroid.github.hotrepo.modle.RepoResult;
import com.feicuiedu.gitdroid.github.hotrepo.pager.Language;
import com.feicuiedu.gitdroid.neetwork.GithubApi;
import com.feicuiedu.gitdroid.neetwork.GithubClient;
import com.hannesdorfmann.mosby.mvp.MvpNullObjectBasePresenter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by wangzhenkai on 2016/7/7.
 */
public class LanguagePresenter extends MvpNullObjectBasePresenter<LanguageView> {

    private Call<RepoResult> reposcall;

    private int nextPage=0;

    private Language language;

    public LanguagePresenter(Language language){
        this.language=language;
    }

    //下拉刷新的业务逻辑
    public void refresh(){

        getView().hideLoadMore();//隐藏加载更多

        getView().showContentView();//显示内容

        nextPage=1;//刷新永远是第一页
        reposcall= GithubClient.getInstance().searchRepo("language:"+language.getPath(),nextPage);
        reposcall.enqueue(reposCallback);
    }

    //上拉加载更多的业务逻辑
    public void loadMore(){
        getView().showLoadMoreLoading();
        reposcall= GithubClient.getInstance().searchRepo("language:"+language.getPath(),nextPage);//搜索java 语言仓库 第一页
        reposcall.enqueue(loadMoreCallback);

    }

    private Callback<RepoResult> reposCallback=new Callback<RepoResult>() {
        @Override
        public void onResponse(Call<RepoResult> call, Response<RepoResult> response) {

            getView().stopRefresh();//视图停止刷新

            RepoResult repoResult=response.body();
            if(repoResult==null){
                getView().showErrorView("result is null");
                return;
            }
            //当前搜索的语言下，没有任何仓库
            if(repoResult.getTotalCount()<=0){
                getView().refreshData(null);
                getView().showEmptyView();
                return;
            }

            //取出当前搜索的语言下所有仓库
          List<Repo> repoList= repoResult.getRepoList();
            getView().refreshData(repoList);//视图数据刷新
           nextPage=2;//下拉刷新成功，当前为第一个 下一页则为 第二页
        }

        @Override
        public void onFailure(Call<RepoResult> call, Throwable t) {

            getView().stopRefresh();
            getView().showErrorView(t.getMessage());
        }
    };

    //上拉加载的回调
    private Callback<RepoResult>loadMoreCallback=new Callback<RepoResult>() {
        @Override
        public void onResponse(Call<RepoResult> call, Response<RepoResult> response) {

            getView().hideLoadMore();
            RepoResult result=response.body();

            if(result==null){
                getView().showLoadMoreErro("null");
                return;
            }
            //没有更多数据
            if(result.getTotalCount()<=0){
                getView().showLoadMoreEnd();
                return;
            }

            //取出当前搜索的语言下，所有仓库
            List<Repo> repoList=result.getRepoList();
            getView().addMoreData(repoList);
            nextPage++;
        }

        @Override
        public void onFailure(Call<RepoResult> call, Throwable t) {

            getView().hideLoadMore();
            getView().showLoadMoreErro(t.getMessage());
        }
    };

}
