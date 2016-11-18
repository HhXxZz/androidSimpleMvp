package com.singgo.cn.timewindows.mvp.bean;

import java.io.Serializable;

/**
 * Created by hxz on 16/9/6 16:58.
 */
public class Code implements Serializable {
    private String flag;
    private String code;
    private String msg;
    public Code(){}

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
