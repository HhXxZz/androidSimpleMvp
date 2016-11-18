package com.singgo.cn.timewindows.mvp.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hxz on 16/9/3 12:06.
 */
public class Content implements Serializable {
    private List<ContentDetail> content;
    public Content(){}

    public List<ContentDetail> getContent() {
        return content;
    }

    public void setContent(List<ContentDetail> content) {
        this.content = content;
    }
}
