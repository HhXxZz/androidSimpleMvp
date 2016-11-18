package com.singgo.cn.timewindows.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.singgo.cn.timewindows.R;
import com.singgo.cn.timewindows.base.BaseFragment;
import com.singgo.cn.timewindows.util.UIHelper;

/**
 * Created by hxz on 16/9/10 10:24.
 */
public class RedirectFragment extends BaseFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_simple_fragment;
    }
}
