package com.singgo.cn.timewindows.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.singgo.cn.timewindows.R;
import com.singgo.cn.timewindows.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hxz on 16/9/12 14:16.
 * 平台协议  webview
 */
public class DealFragment extends BaseFragment {

    @BindView(R.id.wv_deal)
    WebView wvDeal;

    @Override
    public void initView(View view) {
        super.initView(view);
        WebSettings settings = wvDeal.getSettings();
        settings.setJavaScriptEnabled(true);
        wvDeal.loadUrl("http://192.168.1.61:8080/protocol.html");
        wvDeal.setWebViewClient(new WebViewClient());

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_deal;
    }

}
