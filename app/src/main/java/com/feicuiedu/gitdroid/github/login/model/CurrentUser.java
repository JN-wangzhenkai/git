package com.feicuiedu.gitdroid.github.login.model;

/**
 * Created by wangzhenkai on 2016/7/7.
 * 此类是一个用来缓存当前用户信息的极简单的实现。
 *
 * 没有使用数据库或SharedPreferences来持久化这些数据，
 * 可以自己尝试来做
 */
public class CurrentUser {
    //此类不可实例化
    private CurrentUser(){}

    //存个字符串
    private static String accessToken;

    private static user user;

    public static void setAccessToken(String accessToken){
        CurrentUser.accessToken=accessToken;
    }

    public static void setUser(user user){
        CurrentUser.user=user;
    }

    //清除缓存信息
    public static void clear(){
        accessToken=null;
        user=null;
    }

    public static String getAccessToken(){
        return accessToken;
    }

    public static user getUser() {
        return user;
    }
    //当前是否有访问令牌
        public static Boolean hasAccessTok(){
            return accessToken!=null;
        }

    public static boolean isEmpty(){
        return accessToken==null||user==null;
    }
}
