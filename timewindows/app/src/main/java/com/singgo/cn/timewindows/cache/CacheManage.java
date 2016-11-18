package com.singgo.cn.timewindows.cache;

import com.singgo.cn.timewindows.app.AppContext;

/**
 * Created by hxz on 16/9/14 16:07.
 */
public class CacheManage {
    public static ACache cache;

    /*
   缓存没有时间
    */
    public static void cacheStr(String key,String str){
        cache.put(key,str);
    }

    /*
    缓存时间为data
     */
    public static void cacheStr(String key,String str,int data){
        cache.put(key,str,data);
    }

    /**
     * 返回实体类
     */
    public static <T extends Object> T getObj(String key,T t){
        return (T) cache.getAsObject(key);
    }

    public static String getSid(){
        return cache.getAsString(AppContext.SID);
    }
}
