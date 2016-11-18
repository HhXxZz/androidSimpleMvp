package com.singgo.cn.timewindows.app;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.singgo.cn.timewindows.base.BaseApplication;
import com.singgo.cn.timewindows.cache.ACache;
import com.singgo.cn.timewindows.cache.CacheManage;

/**
 * Created by hxz on 16/9/2.
 */
public class AppContext extends BaseApplication {
    //用户标示ID
    public static String SID = "SID";

    @Override
    public void onCreate() {
        super.onCreate();
        CacheManage.cache = ACache.get(this);
        Fresco.initialize(this);
    }


}
