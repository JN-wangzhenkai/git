package com.feicuiedu.gitdroid;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.feicuiedu.gitdroid.commons.ActivityUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Bind(R.id.drawerLayout)DrawerLayout drawerLayout;

    @Bind(R.id.navigationView)NavigationView navigationView;

    private ActivityUtils activityUtils;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
//执行完11行后执行此方法 视图内容更改触发此 方法
    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
        //设置navigationview监听器
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.github_hot_repo:
                break;
            case R.id.tips_share:
                break;
        }
        return true;//返回true 表明将该菜单变为checked 状态
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //如果navigationview 开着  关闭
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        //如果navigationview 关着 退出当前activity
        else{
            super.onBackPressed();//父类本身方法默认关闭
        }
    }
}