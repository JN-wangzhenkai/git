package com.feicuiedu.gitdroid.commons;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import java.lang.ref.WeakReference;

/**
 * Created by wangzhenkai on 2016/6/30.
 * 此类包含与Activity 相关的一些常用方法，例如startActivity和showToast
 * 使用这个类作为BaseActivity 的替代方案 避免过深的继承树引入的复杂性
 * 有点类似于一种不太标准的委托模式
 */
public class ActivityUtils {
    //使用弱引用 避免不恰当的持有Activity或Fragmien 的引用
    //持有Activity 的引用会阻碍Activity的内存回放 增大OOM的风险
    private WeakReference<Activity> activityWeakReference;
    private WeakReference<Fragment> fragmentWeakReference;

    private Toast toast;

    public ActivityUtils(Activity activity) {
       activityWeakReference =new WeakReference<>(activity);
    }

    public ActivityUtils(Fragment fragment){
        fragmentWeakReference=new WeakReference<Fragment>(fragment);
    }

    private  Activity getActivity() {

        if (activityWeakReference != null) return activityWeakReference.get();
        if (fragmentWeakReference != null) {
            Fragment fragment = fragmentWeakReference.get();
            return fragment == null? null : fragment.getActivity();
        }
        return null;
    }

    public void startActivity(Class<? extends Activity> clazz){
       Activity activity = getActivity();
        if (activity == null) return;
        Intent intent = new Intent(activity, clazz);
        activity.startActivity(intent);
    }
}
