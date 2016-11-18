package com.singgo.cn.timewindows.mvp.view;

import com.singgo.cn.timewindows.mvp.bean.Code;
import com.singgo.cn.timewindows.mvp.bean.User;

/**
 * Created by hxz on 16/9/6 15:59.
 */
public interface IUserRegisterView {
    String getUsername();
    String getPassword();
    String getCode();
    void sendCodeBtn(Code code);
    void registerSuccess(String respone);
    void registerFaild();
    void repwdSuccess();
    void repwdFaild();
}
