package com.singgo.cn.timewindows.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.singgo.cn.timewindows.R;
import com.singgo.cn.timewindows.app.AppContext;
import com.singgo.cn.timewindows.base.BaseActivity;
import com.singgo.cn.timewindows.cache.CacheManage;
import com.singgo.cn.timewindows.fragment.SimpleBackPage;
import com.singgo.cn.timewindows.mvp.presenter.UserPresenter;
import com.singgo.cn.timewindows.mvp.view.IUserLoginView;
import com.singgo.cn.timewindows.util.StringUtils;
import com.singgo.cn.timewindows.util.UIHelper;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by hxz on 16/9/5 11:00.
 */
public class LoginActivity extends BaseActivity implements IUserLoginView {
    @BindView(R.id.tv_register)
    TextView tv_register;
    @BindView(R.id.iv_user)
    ImageView ivUser;
    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.iv_pwd)
    ImageView ivPwd;
    @BindView(R.id.et_userpwd)
    EditText etUserpwd;
    @BindView(R.id.bt_login)
    Button btLogin;
    @BindView(R.id.tv_repwd)
    TextView tvRepwd;
    @BindView(R.id.iv_wx_login)
    ImageView ivWxLogin;
    @BindView(R.id.iv_qq_login)
    ImageView ivQqLogin;
    @BindView(R.id.iv_weibo_login)
    ImageView ivWeiboLogin;
    @BindView(R.id.iv_eye)
    ImageView ivEye;

    private Bundle b = new Bundle();
    private UserPresenter presenter = new UserPresenter();
    private boolean isOpeneye;

    @Override
    public void initView() {
        tv_register.setOnClickListener(this);
        btLogin.setOnClickListener(this);
        ivEye.setOnClickListener(this);
        tvRepwd.setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }


    @Override
    protected boolean hasToolBar() {
        return false;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.tv_register:
                b.putString("flag","register");
                UIHelper.showSimpleBack(this, SimpleBackPage.REGISTER,b);
                break;
            case R.id.tv_repwd:
                b.putString("flag","repassword");
                UIHelper.showSimpleBack(this, SimpleBackPage.REPASSWORD,b);
                break;
            case R.id.iv_eye:
                if (!isOpeneye) {
                    etUserpwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    ivEye.setBackgroundResource(R.drawable.openeye_icon);
                } else {
                    etUserpwd.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
                    ivEye.setBackgroundResource(R.drawable.closeeye_icon);
                }
                isOpeneye = !isOpeneye;
                break;
            case R.id.bt_login:
                if (StringUtils.isEmpty(getUsername())) {
                    etUsername.setError("手机号不能为空");
                } else if (!StringUtils.isPhoneNumberValid(getUsername())) {
                    etUsername.setError("请输入正确的手机号码!");
                } else if (StringUtils.isEmpty(getPassword())) {
                    etUserpwd.setError("密码不能为空");
                } else if (etUserpwd.getText().toString().length() < 6) {
                    etUserpwd.setError("请输入正确的6-12位密码");
                } else {
                    presenter.login(this);
                }
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public String getUsername() {
        return etUsername.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return etUserpwd.getText().toString().trim();
    }

    @Override
    public void loginSuccess(String respone) {
        //保存用户唯一标示
        CacheManage.cacheStr(AppContext.SID, respone);
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void loginFalid() {
        AppContext.showToast(this,"用户名或密码错误");
    }

}
