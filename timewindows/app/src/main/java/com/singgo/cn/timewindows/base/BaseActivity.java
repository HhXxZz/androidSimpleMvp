package com.singgo.cn.timewindows.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.singgo.cn.timewindows.R;
import com.singgo.cn.timewindows.app.AppManager;
import com.singgo.cn.timewindows.listener.BaseViewInterface;
import com.singgo.cn.timewindows.ui.ToolBarActivity;
import com.zhy.autolayout.AutoLayoutActivity;

import butterknife.ButterKnife;

/**
 * Created by hxz on 16/9/2 11:25.
 */
public class BaseActivity extends ToolBarActivity implements BaseViewInterface,View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getAppManager().addActivity(this);
        if (getLayoutId() != 0) {
            setContentView(getLayoutId());
        }
        // 通过注解绑定控件
        ButterKnife.bind(this);
        initView();
        initData();
    }

    /*
    得到layout
     */
    protected int getLayoutId(){
        return 0;
    }


    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {

    }
}
