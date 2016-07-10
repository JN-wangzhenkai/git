package com.feicuiedu.gitdroid.neetwork;


import com.feicuiedu.gitdroid.github.hotrepo.modle.RepoResult;
import com.feicuiedu.gitdroid.github.login.model.AccessTokenResult;
import com.feicuiedu.gitdroid.github.login.model.user;
import com.feicuiedu.gitdroid.github.repo.RepoContentResult;

import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by wangzhenkai on 2016/7/7.
 */
public class GithubClient implements GithubApi {

    private   static GithubClient sClient;

    public static GithubClient getInstance(){
        if(sClient==null){
            sClient=new GithubClient();
        }
        return sClient;
    }

    private final GithubApi githubApi;


    private GithubClient(){

        HttpLoggingInterceptor loggingInterceptor=new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);


        OkHttpClient okHttpClient=new OkHttpClient.Builder()
                                 .addInterceptor(new HttpLoggingInterceptor())//添加拦截器, 处理Log (注意添加依赖包)
                                 .addInterceptor(new TokenInterceptor()) // 添加拦截器, 处理AccessToken
                                 .build();

        Retrofit retrofit=new Retrofit.Builder()
                         .baseUrl("https://api.github.com")
                         .addConverterFactory(GsonConverterFactory.create())
                          .client(okHttpClient)
                          .build();

        githubApi=retrofit.create(GithubApi.class);
    }

    @Override public Call<AccessTokenResult> getOAuthToken(@Field("client_id") String client, @Field("client_secret") String clientSecret, @Field("code") String code) {
        return githubApi.getOAuthToken(client, clientSecret, code);
    }

    @Override public Call<user> getUserInfo() {
        return githubApi.getUserInfo();
    }

    @Override public Call<RepoResult> searchRepo(@Query("q") String query, @Query("page") int pageId) {
        return githubApi.searchRepo(query,pageId);
    }

    @Override
    public Call<RepoContentResult> getReadme(@Path("owner") String owner, @Path("repo") String repo) {
        return githubApi.getReadme(owner,repo);
}

    @Override
    public Call<ResponseBody> markdown(@Body RequestBody body) {
        return githubApi.markdown(body);
    }
}
