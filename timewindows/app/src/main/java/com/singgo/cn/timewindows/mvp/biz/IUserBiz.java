package com.singgo.cn.timewindows.mvp.biz;

import com.singgo.cn.timewindows.mvp.bean.Code;
import com.singgo.cn.timewindows.mvp.bean.User;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by hxz on 16/9/2 12:00.
 */
public interface IUserBiz {
    /*
    获取验证码接口
    type:1注册
    2 找回密码
     */
    @GET("sendmessage/{type}")
    Call<Code> sendCodeApi(@Path("type") String type, @Query("phone") String phone);

    /*
    注册api
     */
    @POST("register")
    @FormUrlEncoded
    Call<ResponseBody>registerApi(@Field("phone")String phone,@Field("pwd")String pwd);

    //登录api
    @POST("login")
    @FormUrlEncoded
    Call<ResponseBody>loginApi(@Field("loginname")String username,@Field("password")String pwd);

    //重置密码api
    @POST("resetPwd")
    @FormUrlEncoded
    Call<ResponseBody>repwdApi(@Field("phone")String phone,@Field("pwd")String pwd);

    //获取用户资料
    @GET("getUserDetail")
    Call<User>getUser(@Query("sid")String sid);

}
