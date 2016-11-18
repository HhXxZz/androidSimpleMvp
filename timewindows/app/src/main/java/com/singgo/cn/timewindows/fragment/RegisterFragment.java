package com.singgo.cn.timewindows.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.singgo.cn.timewindows.R;
import com.singgo.cn.timewindows.app.AppContext;
import com.singgo.cn.timewindows.base.BaseFragment;
import com.singgo.cn.timewindows.cache.CacheManage;
import com.singgo.cn.timewindows.mvp.bean.Code;
import com.singgo.cn.timewindows.mvp.presenter.UserPresenter;
import com.singgo.cn.timewindows.mvp.view.IUserRegisterView;
import com.singgo.cn.timewindows.ui.LoginActivity;
import com.singgo.cn.timewindows.ui.MainActivity;
import com.singgo.cn.timewindows.ui.SimpleBackActivity;
import com.singgo.cn.timewindows.util.StringUtils;
import com.singgo.cn.timewindows.util.UIHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hxz on 16/9/6 10:59.
 * <p/>
 * 集成fragment基类
 * oncreadview需要绑定view  UI操作在initView
 */
public class RegisterFragment extends BaseFragment implements IUserRegisterView {
    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.bt_code)
    Button btCode;
    @BindView(R.id.et_userpwd)
    EditText etUserpwd;
    @BindView(R.id.check)
    CheckBox check;
    @BindView(R.id.tv_deal)
    TextView tvDeal;
    @BindView(R.id.bt_register)
    Button btRegister;
    @BindView(R.id.iv_eye)
    ImageView ivEye;
    @BindView(R.id.view_deal)
    RelativeLayout viewDeal;
    @BindView(R.id.bt_repwd)
    Button btRepwd;

    private boolean isOpeneye;
    private String code;
    private String codeType;

    private UserPresenter presenter = new UserPresenter();

    @Override
    public void initView(View view) {
        super.initView(view);
        init(view);
        btCode.setOnClickListener(this);
        btRegister.setOnClickListener(this);
        ivEye.setOnClickListener(this);
        btRepwd.setOnClickListener(this);
        tvDeal.setOnClickListener(this);
    }

    void init(View view) {
        String flag = getActivity().getIntent().getBundleExtra(SimpleBackActivity.BUNDLE_KEY_ARGS).getString("flag");
        if ("register".equals(flag)) {
            codeType = "1";
            viewDeal.setVisibility(View.VISIBLE);
            btRegister.setVisibility(View.VISIBLE);
            btRepwd.setVisibility(View.GONE);
        } else {
            codeType = "2";
            viewDeal.setVisibility(View.GONE);
            btRegister.setVisibility(View.GONE);
            btRepwd.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.bt_code:
                if (StringUtils.isEmpty(getUsername())) {
                    etUsername.setError("手机号码不能为空!");
                } else if (!StringUtils.isPhoneNumberValid(getUsername())) {
                    etUsername.setError("请输入正确的手机号码!");
                } else {
                    presenter.sendCode(codeType,this);
                }
                break;
            case R.id.bt_repwd:
                if (StringUtils.isEmpty(getUsername())) {
                    etUsername.setError("手机号不能为空");
                } else if (!StringUtils.isPhoneNumberValid(getUsername())) {
                    etUsername.setError("请输入正确的手机号码!");
                } else if (StringUtils.isEmpty(getCode())) {
                    etCode.setError("验证码不能为空");
                } else if (StringUtils.isEmpty(getPassword())) {
                    etUserpwd.setError("密码不能为空");
                } else if (!getCode().equals(code)) {
                    etCode.setError("验证码错误");
                } else if (etUserpwd.getText().toString().length() < 6) {
                    etUserpwd.setError("请输入正确的6-12位密码");
                }  else {
                    presenter.repwd(this);
                }
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
            case R.id.bt_register:
                if (StringUtils.isEmpty(getUsername())) {
                    etUsername.setError("手机号不能为空");
                } else if (!StringUtils.isPhoneNumberValid(getUsername())) {
                    etUsername.setError("请输入正确的手机号码!");
                } else if (StringUtils.isEmpty(getCode())) {
                    etCode.setError("验证码不能为空");
                } else if (StringUtils.isEmpty(getPassword())) {
                    etUserpwd.setError("密码不能为空");
                } else if (!getCode().equals(code)) {
                    etCode.setError("验证码错误");
                } else if (etUserpwd.getText().toString().length() < 6) {
                    etUserpwd.setError("请输入正确的6-12位密码");
                } else if (!check.isChecked()) {
                    AppContext.showToast(getActivity(),"请阅读平台协议");
                } else {
                    presenter.register(this);
                }
                break;
            case R.id.tv_deal:
                UIHelper.showSimpleBack(getActivity(),SimpleBackPage.DEAL);
                break;
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_register;
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
    public String getCode() {
        return etCode.getText().toString().trim();
    }

    @Override
    public void sendCodeBtn(Code code) {
        if ("true".equals(code.getFlag())) {
            this.code = code.getCode();
            new CountDownTimer(60000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    btCode.setClickable(false);
                    btCode.setBackgroundResource(R.drawable.botton_style2);
                    btCode.setText(millisUntilFinished / 1000 + "秒后可重新发送");
                }

                @Override
                public void onFinish() {
                    btCode.setClickable(true);
                    btCode.setBackgroundResource(R.drawable.botton_style);
                    btCode.setText("重新发送");
                }
            }.start();
        } else {
            AppContext.showToast(getActivity(),"发送失败");
        }
    }

    @Override
    public void registerSuccess(String respone) {
        //保存用户唯一标示
        CacheManage.cacheStr(AppContext.SID, respone);
        startActivity(new Intent(getActivity(), MainActivity.class));
        getActivity().finish();
    }

    @Override
    public void registerFaild() {
        AppContext.showToast(getActivity(),"注册失败,该用户已存在!");
    }

    @Override
    public void repwdSuccess() {
        AppContext.showToast(getActivity(),"重置成功");
        startActivity(new Intent(getActivity(), LoginActivity.class));
    }

    @Override
    public void repwdFaild() {
        AppContext.showToast(getActivity(),"重置密码失败");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
