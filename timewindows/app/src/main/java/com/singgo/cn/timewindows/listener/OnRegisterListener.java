package com.singgo.cn.timewindows.listener;

import com.singgo.cn.timewindows.mvp.bean.User;

/**
 * Created by hxz on 16/9/6 15:56.
 */
public interface OnRegisterListener {
    void registerSuccess(User user);

    void registerFaild();

}
