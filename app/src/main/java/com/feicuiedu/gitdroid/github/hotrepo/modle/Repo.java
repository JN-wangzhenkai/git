package com.feicuiedu.gitdroid.github.hotrepo.modle;

import com.feicuiedu.gitdroid.github.login.model.user;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by wangzhenkai on 2016/7/7.
 */
public class Repo implements Serializable{

    @SerializedName("id")
    private int id;

    //仓库名称
    @SerializedName("name")
    private String name;
    //仓库全名
    @SerializedName("full_name")
    private String fullName;
    //仓库描述
    @SerializedName("description")
    private String description;
    //star数量
    @SerializedName("stargazers_count")
    private int   stargazerCount;
    //fork数量
    @SerializedName("forks_count")
    private int  forksCount ;

    //本仓库的拥有着
    @SerializedName("owner")
    private user owner;


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFullName() {
        return fullName;
    }

    public String getDescription() {
        return description;
    }

    public int getStargazerCount() {
        return stargazerCount;
    }

    public int getForksCount() {
        return forksCount;
    }
    public user getOwner() {
        return owner;
    }

}
