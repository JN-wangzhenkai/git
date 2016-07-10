package com.feicuiedu.gitdroid.github.hotrepo.view;

import com.feicuiedu.gitdroid.github.hotrepo.modle.Repo;
import com.hannesdorfmann.mosby.mvp.MvpView;

import java.util.List;

/**
 * 不同语言仓库视图抽象
 */
public interface LanguageView extends MvpView,PtrView<List<Repo>>,LoadMoreView<List<Repo>>{

}
