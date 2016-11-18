package com.singgo.cn.timewindows.entity;

import java.util.List;

/**
 * Created by hxz on 16/11/15 15:18.
 */
public class BaseList<T extends Base> {
    private List<T> list;
    private int totalPage;
    private int page;
    public BaseList(){}

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
