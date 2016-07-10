package com.feicuiedu.gitdroid.splash;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.feicuiedu.gitdroid.github.home.MainActivity;
import com.feicuiedu.gitdroid.R;
import com.feicuiedu.gitdroid.commons.ActivityUtils;
import com.feicuiedu.gitdroid.github.login.LoginActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*
  首页面
 */
public class SplashActivity extends AppCompatActivity {

    @Bind(R.id.btnLogin)
    Button btnLogin;
    @Bind(R.id.btnEnter)
    Button btnEnter;

    private ActivityUtils activityUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    //内容修改时进行初始化工作
    @Override
    public void onContentChanged() {
        super.onContentChanged();

        activityUtils = new ActivityUtils(this);
        ButterKnife.bind(this);


    }

    @OnClick(R.id.btnLogin)

    public void login() {
        activityUtils.startActivity(LoginActivity.class);
    }

    @OnClick(R.id.btnEnter)
    public void enter() {
        activityUtils.startActivity(MainActivity.class);
    }
}
