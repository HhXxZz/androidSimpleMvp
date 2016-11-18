package com.singgo.cn.timewindows.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;

import com.singgo.cn.timewindows.R;
import com.singgo.cn.timewindows.base.BaseActivity;
import com.singgo.cn.timewindows.fragment.SimpleBackPage;

import java.lang.ref.WeakReference;

/**
 * Created by hxz on 16/9/6 10:49.
 */
public class SimpleBackActivity extends BaseActivity {
    public final static String BUNDLE_KEY_PAGE = "BUNDLE_KEY_PAGE";
    public final static String BUNDLE_KEY_ARGS = "BUNDLE_KEY_ARGS";
    private static final String TAG = "FLAG_TAG";
    protected WeakReference<Fragment> mFragment;
    protected int mPageValue = -1;


    protected int getLayoutId() {
        return R.layout.activity_simple_fragment;
    }

    @Override
    protected boolean hasBackButton() {
        return true;
    }

    @Override
    protected boolean hasBarLogo() {
        return false;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mPageValue == -1) {
            mPageValue = getIntent().getIntExtra(BUNDLE_KEY_PAGE, 0);
        }
        initFromIntent(mPageValue, getIntent());
    }

    protected void initFromIntent(int pageValue, Intent data) {
        if (data == null) {
            throw new RuntimeException( "you must provide a page info to display");
        }
        SimpleBackPage page = SimpleBackPage.getPageByValue(pageValue);
        if (page == null) {
            throw new IllegalArgumentException("can not find page by value:"+ pageValue);
        }

        setToolBarTitle(page.getTitle());

        try {
            Fragment fragment = (Fragment) page.getClz().newInstance();

            Bundle args = data.getBundleExtra(BUNDLE_KEY_ARGS);
            if (args != null) {
                fragment.setArguments(args);
            }
            FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
            trans.replace(R.id.container, fragment, TAG);
            trans.commitAllowingStateLoss();

            mFragment = new WeakReference<Fragment>(fragment);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("generate fragment error. by value:" + pageValue);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
//        if (mFragment.get() instanceof TweetsFragment) {
//            setActionBarTitle("话题");
//        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//        case R.id.public_menu_send:
//            if (mFragment.get() instanceof TweetsFragment) {
//                sendTopic();
//            } else {
//                return super.onOptionsItemSelected(item);
//            }
//            break;
//        default:
//            break;
//        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        if (mFragment.get() instanceof TweetsFragment) {
//            getMenuInflater().inflate(R.menu.pub_topic_menu, menu);
//        }
        return super.onCreateOptionsMenu(menu);
    }
}
