package com.singgo.cn.timewindows.listener;

import com.singgo.cn.timewindows.mvp.bean.Code;

/**
 * Created by hxz on 16/9/6 16:56.
 */
public interface OnSendCodeListener {
    void success(Code code);

    void faild();
}
