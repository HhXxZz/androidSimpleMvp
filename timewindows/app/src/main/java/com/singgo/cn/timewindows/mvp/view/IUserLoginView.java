package com.singgo.cn.timewindows.mvp.view;


/**
 * Created by hxz on 16/9/2 14:44.
 */
public interface IUserLoginView {
    String getUsername();
    String getPassword();
    void loginSuccess(String repone);
    void loginFalid();
}
