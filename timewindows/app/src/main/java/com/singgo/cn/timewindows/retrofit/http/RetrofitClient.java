package com.singgo.cn.timewindows.retrofit.http;



import com.singgo.cn.timewindows.mClass.gsonConverter.GsonConverterFactory;
import com.singgo.cn.timewindows.mClass.stringConverter.StringConverter;
import com.singgo.cn.timewindows.retrofit.ApiContants;

import retrofit2.Retrofit;


/**
 * author: baiiu
 * date: on 16/5/16 16:34
 * description: 初始化Retrofit
 */
public enum RetrofitClient implements ApiContants {
    INSTANCE;

    private final Retrofit retrofit;

    RetrofitClient() {
        retrofit = new Retrofit.Builder()
                //设置OKHttpClient
                .client(OKHttpFactory.INSTANCE.getOkHttpClient())

                //baseUrl
                .baseUrl(GITHUB_BASEURL)

                //string转化器
                .addConverterFactory(StringConverter.create())

                //gson转化器
                .addConverterFactory(GsonConverterFactory.create())

                //Rx
                //.addCallAdapterFactory(RxJavaCallAdapterFactory.create())

                //创建
                .build();
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
}
