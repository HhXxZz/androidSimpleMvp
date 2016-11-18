package com.singgo.cn.timewindows.fragment;

import com.singgo.cn.timewindows.R;

/**
 * Created by hxz on 16/9/6 10:55.
 * 新增fragment需要再此声明
 *
 *
 */
public enum SimpleBackPage {
    REGISTER(1, R.string.register,RegisterFragment.class),
    REPASSWORD(2,R.string.repassword,RegisterFragment.class),
    DEAL(4,R.string.deal,DealFragment.class),
    ADD(3,R.string.add,AddFragment.class),
    EDITPWD(5,R.string.editpwd,EditPwdFragment.class);
    private int title;
    private Class<?> clz;
    private int value;

    private SimpleBackPage(int value, int title, Class<?> clz) {
        this.value = value;
        this.title = title;
        this.clz = clz;
    }

    public int getTitle() {
        return title;
    }

    public void setTitle(int title) {
        this.title = title;
    }

    public Class<?> getClz() {
        return clz;
    }

    public void setClz(Class<?> clz) {
        this.clz = clz;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public static SimpleBackPage getPageByValue(int val) {
        for (SimpleBackPage p : values()) {
            if (p.getValue() == val)
                return p;
        }
        return null;
    }

}
