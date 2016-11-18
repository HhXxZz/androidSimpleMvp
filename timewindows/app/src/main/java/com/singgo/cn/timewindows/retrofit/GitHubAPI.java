package com.singgo.cn.timewindows.retrofit;


import com.singgo.cn.timewindows.mvp.bean.Content;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * author: baiiu
 * date: on 16/5/16 14:40
 * description:
 *
 * github的API
 *
 * baiiu is an example user.
 */
public interface GitHubAPI {

//    @GET("{type}/getAllContent/{detail}")
//    @FormUrlEncoded
//    Call<ResponseBody>getData(@Path("type")String type, @Path("detail")String datail, @Field("username")String username,@Field("password")String password);

      @POST("{type}/getAllContent/{detail}")
      @FormUrlEncoded
      Call<List<Content>>getData(@Path("type")String type, @Path("detail")String datail, @Field("number")String n);
}
