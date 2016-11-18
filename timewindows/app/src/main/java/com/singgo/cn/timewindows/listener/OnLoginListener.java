package com.singgo.cn.timewindows.listener;

import com.singgo.cn.timewindows.mvp.bean.User;

/**
 * Created by hxz on 16/9/2 12:01.
 */
public interface OnLoginListener {
    void loginSuccesss(User user);

    void loginFailed();
}
