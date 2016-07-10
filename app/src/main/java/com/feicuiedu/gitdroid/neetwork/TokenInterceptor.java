package com.feicuiedu.gitdroid.neetwork;

import com.feicuiedu.gitdroid.github.login.model.CurrentUser;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by wangzhenkai on 2016/7/7.
 */
public class TokenInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {

        // 拦截器目的 每次请求，都加一个头进去 Authorization: token+值

        Request request=chain.request();//先拿到请求
        Request.Builder builder=request.newBuilder();

        // 是否有token,有的话，在当前这次请求中加入token这个请求头

        if(CurrentUser.hasAccessTok()){
            builder.header("Authorization","token "+CurrentUser.getAccessToken());//token 后面要有空格
        }

        //拿到响应
        Response response=chain.proceed(builder.build());

        if(response.isSuccessful()) {
            return response;
        }
        if(response.code()==401||response.code()==403){
            throw new IOException("未经授权的!限制是每分钟10次!");
        }else {
            throw new IOException("响应码:" + response.code());
        }
    }
}
