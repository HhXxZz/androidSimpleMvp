package com.singgo.cn.timewindows.mvp.view;

import com.singgo.cn.timewindows.mvp.bean.User;

/**
 * Created by hxz on 16/9/8 11:15.
 */
public interface IUserInfoView {
    void setUser(User user);
    User getUser();
    String getSid();
}
