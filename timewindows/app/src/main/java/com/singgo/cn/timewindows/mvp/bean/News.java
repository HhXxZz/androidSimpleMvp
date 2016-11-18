package com.singgo.cn.timewindows.mvp.bean;

import com.singgo.cn.timewindows.entity.Base;
import com.singgo.cn.timewindows.entity.BaseList;

/**
 * Created by hxz on 16/11/16 10:30.
 */
public class News extends Base {
    private String title;
    private String content;
    private String commtic;
    private String date;

    public News(){}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCommtic() {
        return commtic;
    }

    public void setCommtic(String commtic) {
        this.commtic = commtic;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
