package com.singgo.cn.timewindows.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.DragEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.singgo.cn.timewindows.R;
import com.singgo.cn.timewindows.app.AppContext;
import com.singgo.cn.timewindows.base.BaseActivity;
import com.singgo.cn.timewindows.cache.CacheManage;
import com.singgo.cn.timewindows.entity.Constants;
import com.singgo.cn.timewindows.entity.TabEntity;
import com.singgo.cn.timewindows.fragment.AddFragment;
import com.singgo.cn.timewindows.fragment.MainFragment;
import com.singgo.cn.timewindows.fragment.MeFragment;
import com.singgo.cn.timewindows.fragment.RedirectFragment;
import com.singgo.cn.timewindows.fragment.SimpleBackPage;
import com.singgo.cn.timewindows.mvp.bean.User;
import com.singgo.cn.timewindows.mvp.presenter.UserPresenter;
import com.singgo.cn.timewindows.mvp.view.IUserInfoView;
import com.singgo.cn.timewindows.util.StringUtils;
import com.singgo.cn.timewindows.util.UIHelper;

import java.util.ArrayList;

import butterknife.BindView;


public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener,IUserInfoView {
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private String[] title = {"首页", "", "我的"};
    private int[] mIconUnselectIds = {R.drawable.home, R.drawable.add_icon, R.drawable.me_icon};
    private int[] mIconSelectIds = {R.drawable.home_icon, R.drawable.add_icon, R.drawable.me};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    @BindView(R.id.fl_fragment)
    FrameLayout flFragment;
    @BindView(R.id.tl)
    CommonTabLayout tl;

    private UserPresenter presenter = new UserPresenter();
    public User user;


    @Override
    public void initView() {
        super.initView();
        presenter.getUser(this);
        initDrawerLayout();
        mFragments.add(new MainFragment());
        mFragments.add(new Fragment());
        mFragments.add(new MeFragment());
        //设置tab的标题、选中图标、未选中图标
        for (int i = 0; i < title.length; i++) {
            mTabEntities.add(new TabEntity(title[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        tl.setTabData(mTabEntities, this, R.id.fl_fragment, mFragments);
        tl.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                if(position == 1){
                    UIHelper.showSimpleBack(MainActivity.this, SimpleBackPage.ADD);
                }
            }
            @Override
            public void onTabReselect(int position) {
            }
        });
        tl.setCurrentTab(0);
    }

    /**
     * 初始化DrawerLayout
     */
    private void initDrawerLayout(){
        navView.setNavigationItemSelectedListener(this);
        //禁止侧滑
       // drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
       // drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_drawerlayout;
    }

    @Override
    protected boolean hasToolBar() {
        return true;
    }

    @Override
    protected String getToolBarTitle() {
        return "";
    }

    @Override
    protected boolean hasBarLogo() {
        return true;
    }

    @Override
    protected void openDrawerLayout() {
        if(!isOpen){
            drawerLayout.openDrawer(GravityCompat.START);
        }else{
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        isOpen = !isOpen;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return true;
    }


    @Override
    public void setUser(User user) {
        CacheManage.cache.put("user",user);
        Intent intent = new Intent();
        intent.setAction(Constants.GET_USER_SUCCESS);
        MainActivity.this.sendBroadcast(intent);
    }

    @Override
    public User getUser() {
        return user;
    }

    public String getSid(){
       if(StringUtils.isEmpty(CacheManage.getSid())){
           Intent intent = new Intent(MainActivity.this,LoginActivity.class);
           startActivity(intent);
           finish();
           return "";
       }
        return CacheManage.getSid();
    }
}
