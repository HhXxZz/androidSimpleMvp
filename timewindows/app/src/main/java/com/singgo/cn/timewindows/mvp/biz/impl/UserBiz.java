package com.singgo.cn.timewindows.mvp.biz.impl;

import android.util.Log;

import com.singgo.cn.timewindows.listener.OnSendCodeListener;
import com.singgo.cn.timewindows.mvp.bean.Code;
import com.singgo.cn.timewindows.mvp.bean.Content;
import com.singgo.cn.timewindows.mvp.bean.User;
import com.singgo.cn.timewindows.listener.OnLoginListener;
import com.singgo.cn.timewindows.mvp.view.IUserInfoView;
import com.singgo.cn.timewindows.mvp.view.IUserLoginView;
import com.singgo.cn.timewindows.mvp.view.IUserRegisterView;
import com.singgo.cn.timewindows.retrofit.ApiFactory;
import com.singgo.cn.timewindows.util.StringUtils;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by hxz on 16/9/2 14:03.
 */
public class UserBiz {
    private static String TAG = UserBiz.class.getSimpleName();

    public void sendCode(String type,final IUserRegisterView view){
        Call<Code>call = ApiFactory.getUserApi().sendCodeApi(type,view.getUsername());
        call.enqueue(new Callback<Code>() {
            @Override
            public void onResponse(Call<Code> call, Response<Code> response) {
                Log.i(TAG,response.body().getMsg()+""+response.body().getCode());
                view.sendCodeBtn(response.body());
            }

            @Override
            public void onFailure(Call<Code> call, Throwable t) {
            }
        });
    }

    public void register(final IUserRegisterView view){
        Call<ResponseBody>call = ApiFactory.getUserApi().registerApi(view.getUsername(),view.getPassword());
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String data =response.body().string();
                    Log.i(TAG, "注册成功" + data);
                    if ("false".equals(data)) {
                        view.registerFaild();
                    } else {
                        view.registerSuccess(data);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.i(TAG,"网络故障");
            }
        });
    }

    public void login(final IUserLoginView view){
        Call<ResponseBody>call = ApiFactory.getUserApi().loginApi(view.getUsername(),view.getPassword());
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String data = response.body().string();
                    if("false".equals(data)){
                        view.loginFalid();
                    }else{
                        Log.i(TAG,"登录成功"+data);
                        view.loginSuccess(data);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.i(TAG,"网络故障");
            }
        });
    }

    public void repwd(final IUserRegisterView view){
        Call<ResponseBody>call = ApiFactory.getUserApi().repwdApi(view.getUsername(),view.getPassword());
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String data = response.body().string();
                    if("true".equals(data)){
                        view.repwdSuccess();
                    }else{
                        view.repwdFaild();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
            }
        });
    }

    public void getUser(final IUserInfoView view){
        Call<User> call = ApiFactory.getUserApi().getUser(view.getSid());
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                view.setUser(response.body());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
            }
        });
    }
}
