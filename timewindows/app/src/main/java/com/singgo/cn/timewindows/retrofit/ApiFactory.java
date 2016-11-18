package com.singgo.cn.timewindows.retrofit;


import com.singgo.cn.timewindows.mvp.biz.IUserBiz;
import com.singgo.cn.timewindows.retrofit.http.RetrofitClient;

/**
 * author: baiiu
 * date: on 16/5/16 17:42
 * description:
 */
public enum ApiFactory {
  INSTANCE;

  private static IUserBiz userBiz;
  private static GitHubAPI git;

  ApiFactory() {
  }

  public static IUserBiz getUserApi() {
    if (userBiz == null) {
      userBiz = RetrofitClient.INSTANCE.getRetrofit().create(IUserBiz.class);
    }
    return userBiz;
  }

  public static GitHubAPI get(){
    if (git == null) {
      git = RetrofitClient.INSTANCE.getRetrofit().create(GitHubAPI.class);
    }
    return git;
  }

}
