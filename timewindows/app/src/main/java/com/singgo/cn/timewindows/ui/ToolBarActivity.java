package com.singgo.cn.timewindows.ui;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import android.view.ViewGroup.LayoutParams;
import com.singgo.cn.timewindows.R;
import com.singgo.cn.timewindows.app.AppContext;
import com.singgo.cn.timewindows.widget.StatusBarCompat;
import com.zhy.autolayout.AutoLayoutActivity;

/**
 * Created by hxz on 16/9/5 11:55.
 */
public class ToolBarActivity extends AutoLayoutActivity {

    protected TextView tvTitle;
    protected Toolbar toolbar;
    protected FrameLayout toolbarLayout;
    private LinearLayout contentView;
    private ImageView ivLogo;
    private ImageView ivLeft;
    private ImageView ivRight;
    private ImageView ivMenu;
    protected boolean isOpen;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarCompat.compat(this, getResources().getColor(R.color.colorBar));
        initToolBar();
        initContentView();
    }
    /*
    初始化toolbar
     */
    private void initToolBar(){
        toolbarLayout = new FrameLayout(this);
        LayoutInflater.from(this).inflate(R.layout.layout_toolbar, toolbarLayout,
                true);
        tvTitle = (TextView) toolbarLayout.findViewById(R.id.tv_toolbar_title);
        toolbar = (Toolbar) toolbarLayout.findViewById(R.id.toolbar);
        ivLeft = (ImageView) toolbarLayout.findViewById(R.id.iv_toolbar_left);
        ivLogo = (ImageView) toolbarLayout.findViewById(R.id.iv_toolbar_logo);
        ivRight = (ImageView) toolbarLayout.findViewById(R.id.iv_toolbar_right);
        ivMenu = (ImageView) toolbarLayout.findViewById(R.id.iv_toolbar_menu);
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorBar));

        tvTitle.setText(getToolBarTitle());
        setSupportActionBar(toolbar);
        if(hasBackButton()) {
            ivLeft.setVisibility(View.VISIBLE);
            ivLeft.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }else{
            ivLeft.setVisibility(View.GONE);
        }

        if(!hasToolBar()) {
            toolbar.setVisibility(View.GONE);
            if(Build.VERSION.SDK_INT >= 21) {
                View decorView = getWindow().getDecorView();
                int option = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
                decorView.setSystemUiVisibility(option);
                getWindow().setStatusBarColor(Color.TRANSPARENT);
                getWindow().setNavigationBarColor(Color.TRANSPARENT);
            }
        }
        if(hasBarLogo()){
            ivLogo.setVisibility(View.VISIBLE);
            ivRight.setVisibility(View.VISIBLE);
            ivMenu.setVisibility(View.VISIBLE);
            ivMenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openDrawerLayout();
                }
            });
            ivRight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            tvTitle.setText("");
        }else{
            ivLogo.setVisibility(View.GONE);
            ivRight.setVisibility(View.GONE);
            ivMenu.setVisibility(View.GONE);
        }
    }


    private void initContentView(){
        contentView = new LinearLayout(this);
        contentView.setOrientation(LinearLayout.VERTICAL);
    }
    /*
    activity是否有toolbar
     */
    protected boolean hasToolBar(){
        return true;
    }

    protected String getToolBarTitle(){
        return getString(R.string.app_name);
    }

    protected void setToolBarTitle(int titleId){
        if(titleId!=0){
            tvTitle.setText(getString(titleId));
        }
    }

    protected void openDrawerLayout(){}

    protected boolean hasBackButton(){
        return false;
    }

    protected boolean hasBarLogo(){
        return false;
    }


    protected View getContentView() {
        return contentView;
    }

    @Override
    public void setContentView(int layoutResID) {
        // 添加toolbar布局
        contentView.addView(toolbarLayout,new LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT));

        LayoutInflater.from(this).inflate(layoutResID, contentView, true);

        super.setContentView(contentView, new LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT));
    }

    @Override
    public void setContentView(View view) {
        setNewContentView(view, view.getLayoutParams());
    }

    @Override
    public void setContentView(View view, LayoutParams params) {
        setNewContentView(view, params);
    }

    private void setNewContentView(View view, LayoutParams params) {
        // 添加toolbar布局
        contentView.addView(toolbarLayout, new LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT));

        // content FrameLayout
        FrameLayout contentLayout = new FrameLayout(this);
        if (params == null) {
            params = new LayoutParams(
                    LayoutParams.MATCH_PARENT,
                    LayoutParams.MATCH_PARENT);
        }
        contentLayout.addView(view, params);

        // 添加content布局
        contentView.addView(contentLayout, new LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT));

        super.setContentView(contentView, new LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT));
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            back();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void back() {
        super.onBackPressed();
    }

}
