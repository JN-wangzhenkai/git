package com.feicuiedu.gitdroid.neetwork;


import com.feicuiedu.gitdroid.github.hotrepo.modle.RepoResult;
import com.feicuiedu.gitdroid.github.login.model.AccessTokenResult;
import com.feicuiedu.gitdroid.github.login.model.user;
import com.feicuiedu.gitdroid.github.repo.RepoContentResult;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by wangzhenkai on 2016/7/7.
 */
public interface GithubApi {

    //GitHub 开发者 申请时填写的（重定向返回的一个标记）
    String CALL_BACK="zhenkai";

    //github　开发者　在 github 上注册APp 获得
    String CLIENT_ID="bc33e1b4ca65c9f38770";

    String CLIENT_SECRET="9b923e2c698b370b2f64cb9530a17527cee22e92";

    //授权时申请的可访问域
    String INITIAL_SCOPE="user,public_repo,repo";

    //webview 加载url 显示github 登录页面
    String AUTH_URL=
            "https://github.com/login/oauth/authorize?client_id="+
                     CLIENT_ID+"&scpoe="+INITIAL_SCOPE;

    /**
     * 获取访问令牌 API
     * 获取OAuth 2.0协议的AccessToken
     *
     * @param client       @see {@link #CLIENT_ID}
     * @param clientSecret @see {@link #CLIENT_SECRET}
     * @param code         授权码
     * @return 授权结果
     *
     */
    @Headers("Accept:application/json")
    @FormUrlEncoded
    @POST("https://github.com/login/oauth/access_token")
    Call<AccessTokenResult> getOAuthToken(

            @Field("client_id")String client,
            @Field("client_secret")String clientSecret,
            @Field("code")String code
    );

    /**
     *
     * @return 获取用户信息
     */
    @GET("user")Call<user>getUserInfo();

    /**
     * @param query  查询参数           java [232323123] items[{},{},{}]
     * @param pageId 查询页数，从1开始
     * @return 查询结果
     */
    @GET("/search/repositories")
    Call<RepoResult>searchRepo(
            @Query("q")String query,
            @Query("page") int pageId
    );


    //GET/repos/:owner/:repo/readme
    /**
     * owner 仓库拥有着
     * repo 仓库名称
     * return  仓库的 readme 页面内容  markdown 格式
     */

    @GET("repos/{owner}/{repo}/readme")
    Call<RepoContentResult>getReadme(
            @Path("owner") String owner,
            @Path("repo") String  repo
    );

    /**
          获取markdown 的html 页面
     */
    //post/markdown/raw

    @Headers({"Content-Type:text/plain"})

    @POST("/markdown/raw")
    Call<ResponseBody>markdown(@Body RequestBody body);
}
