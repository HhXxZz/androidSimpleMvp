package com.singgo.cn.timewindows.mvp.presenter;

import com.singgo.cn.timewindows.listener.OnSendCodeListener;
import com.singgo.cn.timewindows.mvp.bean.Code;
import com.singgo.cn.timewindows.mvp.biz.impl.UserBiz;
import com.singgo.cn.timewindows.mvp.view.IUserInfoView;
import com.singgo.cn.timewindows.mvp.view.IUserLoginView;
import com.singgo.cn.timewindows.mvp.view.IUserRegisterView;

/**
 * Created by hxz on 16/9/6 17:01.
 */
public class UserPresenter {
    private UserBiz userBiz = new UserBiz();

    /*
    1 的类型是 注册 获取的验证码
     */
    public void sendCode(String type,final IUserRegisterView view){
        userBiz.sendCode(type,view);
    }

    /*
    注册
     */
    public void register(IUserRegisterView view){
        userBiz.register(view);
    }
    //重置密码
    public void repwd(IUserRegisterView view){
        userBiz.repwd(view);
    }

    public void login(IUserLoginView view){
        userBiz.login(view);
    }

    public void getUser(IUserInfoView view){userBiz.getUser(view);}

}
