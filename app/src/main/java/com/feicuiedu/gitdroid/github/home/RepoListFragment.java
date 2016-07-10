package com.feicuiedu.gitdroid.github.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.feicuiedu.gitdroid.R;
import com.feicuiedu.gitdroid.github.hotrepo.pager.Language;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by wangzhenkai on 2016/6/30.
 * 用来显示不同语言的仓库列表  Fragment
 */
//public class RepoListFragment extends Fragment {
//
//    //用于下拉刷新
//    @Bind(R.id.ptrClassicFrameLayout)
//    PtrClassicFrameLayout ptrClassicFrameLayout;
//
//    @Bind(R.id.lvRepos)
//    ListView listView;
//
//    private ArrayAdapter<String> adapter;
//
//    private List<String> datas = new ArrayList<>();
//
//    public static RepoListFragment getInstance(Language language) {
//        RepoListFragment fragment = new RepoListFragment();
//        Bundle args = new Bundle();
//        args.putSerializable("key_language", language);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        //测试 假数据
//        for (int i = 0; i <= 10; i++) {
//            datas.add("" + i);
//        }
//    }
//
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.fragment_repo_list, container, false);
//
//    }
//
//    @Override
//    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//
//        ButterKnife.bind(this, view);
//
//        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1);
//        listView.setAdapter(adapter);
//
//        ptrClassicFrameLayout.setPtrHandler(new PtrDefaultHandler() {
//            @Override
//            public void onRefreshBegin(PtrFrameLayout frame) {
//
//                loadData(20);
//            }
//        });
//    }
//
//    private void loadData(final int size){
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//                try {
//
//                    datas.clear();
//                    for (int i = 0; i <=size ; i++) {
//                        datas.add(""+i);
//                    }
//
//                    Thread.sleep(3000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//                //到ui线程完成刷新工作
//                ptrClassicFrameLayout.post(new Runnable() {
//                    @Override
//                    public void run() {
//
//                        //添加刷新数据
//                        adapter.clear();
//
//                        adapter.addAll(datas);
//                        adapter.notifyDataSetChanged();
//
//                        //下拉刷新完成
//                        ptrClassicFrameLayout.refreshComplete();
//                    }
//                });
//
//            }
//        }).start();
//
//
//
//    }
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        ButterKnife.unbind(this);
//    }
//}
