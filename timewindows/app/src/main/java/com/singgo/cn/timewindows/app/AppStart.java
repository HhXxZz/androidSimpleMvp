package com.singgo.cn.timewindows.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import com.singgo.cn.timewindows.base.BaseActivity;
import com.singgo.cn.timewindows.cache.CacheManage;
import com.singgo.cn.timewindows.ui.LoginActivity;
import com.singgo.cn.timewindows.ui.MainActivity;
import com.singgo.cn.timewindows.R;
import com.singgo.cn.timewindows.util.StringUtils;

/**
 * Created by hxz on 16/9/2.
 */
public class AppStart extends BaseActivity {
    private String sid = CacheManage.getSid();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Activity aty = AppManager.getActivity(MainActivity.class);
        if (aty != null && !aty.isFinishing()) {
            finish();
        }
        final View view = View.inflate(this, R.layout.activity_start, null);
        setContentView(view);
        // 渐变展示启动屏
        AlphaAnimation aa = new AlphaAnimation(0.8f, 1.0f);
        aa.setDuration(2000);
        view.startAnimation(aa);
        aa.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd(Animation arg0) {
                if(StringUtils.isEmpty(sid)){
                    redirectToLogin();
                }else{
                    redirectToMain();
                }
            }
            @Override
            public void onAnimationRepeat(Animation animation) {}

            @Override
            public void onAnimationStart(Animation animation) {}
        });
    }

    @Override
    protected boolean hasToolBar() {
        return false;
    }

    /**
     * 跳转首页
     */
    private void redirectToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * 跳转登录页
     */
    private void redirectToLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
