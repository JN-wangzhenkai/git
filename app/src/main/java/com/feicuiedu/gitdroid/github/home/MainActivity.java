package com.feicuiedu.gitdroid.github.home;

import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.feicuiedu.gitdroid.R;
import com.feicuiedu.gitdroid.commons.ActivityUtils;
import com.feicuiedu.gitdroid.github.hotrepo.pager.HotRepoFragment;
import com.feicuiedu.gitdroid.github.login.LoginActivity;
import com.feicuiedu.gitdroid.github.login.model.CurrentUser;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Bind(R.id.drawerLayout)DrawerLayout drawerLayout;

    @Bind(R.id.navigationView)NavigationView navigationView;

    @Bind(R.id.toolbar)Toolbar toolbar;

   // private ActivityUtils activityUtils;

    private  MenuItem menuItem;
    //热门仓库页面fragment
    private HotRepoFragment hotRepoFragment;

    private Button btnLogin;
    private ImageView ivTcon;

    private ActivityUtils activityUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //
       // getActionBar().setBackgroundDrawable();

    }
//执行完11行后执行此方法 视图内容更改触发此 方法
    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);

        activityUtils=new ActivityUtils(this);

        //设置navigationview监听器
        navigationView.setNavigationItemSelectedListener(this);
        //默认第一个menu项 为选中
        menuItem=navigationView.getMenu().findItem(R.id.github_hot_coder);
        menuItem.setChecked(true);

        //actionBar 处理
        setSupportActionBar(toolbar);
      //  getSupportActionBar().setDisplayHomeAsUpEnabled(true); 往左返回的箭头

        //设置toolbar 左上角切换侧滑菜单的按钮
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,
                drawerLayout,toolbar, R.string.navigation_drawer_open,
                                      R.string.navigation_drawer_close);

        drawerLayout.addDrawerListener(toggle);
       toggle.syncState();
        ivTcon=ButterKnife.findById(navigationView.getHeaderView(0), R.id.ivIcon);


        //登陆  直接butterknife Bind 访问不到  id在 navigationView里面第一位
        btnLogin=(Button)ButterKnife.findById(navigationView.getHeaderView(0), R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                activityUtils.startActivity(LoginActivity.class);
            }
        });

        //默认显示的是hotrepofragment 热门仓库
        hotRepoFragment=new HotRepoFragment();
        replaceFragment(hotRepoFragment);
//        FragmentManager fragmentManager=getSupportFragmentManager();
//        FragmentTransaction transaction=fragmentManager.beginTransaction();
//        transaction.replace(R.id.container,hotRepoFragment);
//        transaction.commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
       //还没有授权登陆
        if(CurrentUser.isEmpty()){
            btnLogin.setText(R.string.login_github);
            return;
        }
        //已经授权登陆
        btnLogin.setText(R.string.switch_account);
        getSupportActionBar().setTitle(CurrentUser.getUser().getName());
        //设置用户头像

        String photoUrl=CurrentUser.getUser().getAvatar();
        //1.看内存有没有2。看硬盘有没有 3，根据URl 去下载 下载存到硬盘，/下载存到内存
        ImageLoader.getInstance().displayImage(photoUrl,ivTcon);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
       //将默认选中项手动设置为false
        if(menuItem.isChecked()){
            menuItem.setChecked(false);
        }

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

    //替换不同内容的fragment
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.commit();
    }
}