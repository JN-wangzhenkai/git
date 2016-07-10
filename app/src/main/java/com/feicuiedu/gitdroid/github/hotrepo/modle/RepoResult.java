package com.feicuiedu.gitdroid.github.hotrepo.modle;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 热门仓库 API 结果
 */
public class RepoResult {
    //"total_count"  2103761
    //" incomplete_results:false
    //items []

    // 总量
    @SerializedName("total_count")
    private int totalCount;

    @SerializedName("incomplete_results")
    private  boolean incompleteResults;

    // 仓库列表
    @SerializedName("items")
    private List<Repo>repoList;

    public int getTotalCount() {
        return totalCount;
    }

    public List<Repo> getRepoList() {
        return repoList;
    }

    public boolean isIncompleteResults() {
        return incompleteResults;
    }
}
