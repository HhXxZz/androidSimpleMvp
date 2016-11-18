package com.singgo.cn.timewindows.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.singgo.cn.timewindows.R;
import com.singgo.cn.timewindows.app.AppContext;
import com.singgo.cn.timewindows.base.BaseFragment;
import com.singgo.cn.timewindows.cache.CacheManage;
import com.singgo.cn.timewindows.entity.Constants;
import com.singgo.cn.timewindows.mvp.bean.User;
import com.singgo.cn.timewindows.retrofit.ApiContants;
import com.singgo.cn.timewindows.util.UIHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hxz on 16/9/12 15:52.
 */
public class MeFragment extends BaseFragment {

    @BindView(R.id.iv_userimg)
    SimpleDraweeView ivUserimg;
    @BindView(R.id.tv_username)
    TextView tvUsername;

    User user;
    @BindView(R.id.sw_lock)
    SwitchCompat swLock;
    @BindView(R.id.rl_edit_pwd)
    RelativeLayout rlEditPwd;

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(Constants.GET_USER_SUCCESS)) {
                user = CacheManage.getObj("user", new User());
                initUser();
            }
        }
    };

    @Override
    public void initView(View view) {
        IntentFilter filter = new IntentFilter(Constants.GET_USER_SUCCESS);
        getActivity().registerReceiver(receiver, filter);
        RoundingParams roundingParams = RoundingParams.fromCornersRadius(5f);
        //roundingParams.setBorder(R.color.white,10f);
        roundingParams.setRoundAsCircle(true);
        ivUserimg.getHierarchy().setRoundingParams(roundingParams);
        rlEditPwd.setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_me;
    }

    private void initUser() {
        Uri uri = Uri.parse(ApiContants.BASEURL + user.getImage());
        //ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri).build();
        // ImageRequest request = ImageRequest.fromUri(uri);
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(uri)
                .setTapToRetryEnabled(true)
                .setOldController(ivUserimg.getController())
                //.setControllerListener(listener)
                .build();
        tvUsername.setText(user.getLoginname());
        ivUserimg.setController(controller);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_edit_pwd:
                //UIHelper.showSimpleBack(getActivity(),SimpleBackPage.EDITPWD);
                AppContext.showToast(getActivity(),"aaa");
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(receiver);
    }

}
