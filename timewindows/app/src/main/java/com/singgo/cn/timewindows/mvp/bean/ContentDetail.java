package com.singgo.cn.timewindows.mvp.bean;

import java.io.Serializable;

/**
 * Created by hxz on 16/9/3 12:07.
 */
public class ContentDetail implements Serializable {
    private String id;
    private String name;
    private String description;
    private String url;
    private String image;
    private String clicknum;
    private String did;
    private String cid;
    public ContentDetail(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getClicknum() {
        return clicknum;
    }

    public void setClicknum(String clicknum) {
        this.clicknum = clicknum;
    }

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }
}
