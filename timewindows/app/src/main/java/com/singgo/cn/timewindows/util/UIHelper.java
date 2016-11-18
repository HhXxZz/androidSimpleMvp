package com.singgo.cn.timewindows.util;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.singgo.cn.timewindows.fragment.SimpleBackPage;
import com.singgo.cn.timewindows.ui.SimpleBackActivity;

/**
 * Created by hxz on 16/9/6 11:13.
 * 页面跳转辅助类
 */
public class UIHelper {
    public static void showSimpleBack(Context context, SimpleBackPage page) {
        Intent intent = new Intent(context, SimpleBackActivity.class);
        intent.putExtra(SimpleBackActivity.BUNDLE_KEY_PAGE, page.getValue());
        context.startActivity(intent);
    }

    public static void showSimpleBack(Context context, SimpleBackPage page, Bundle args) {
        Intent intent = new Intent(context, SimpleBackActivity.class);
        intent.putExtra(SimpleBackActivity.BUNDLE_KEY_ARGS, args);
        intent.putExtra(SimpleBackActivity.BUNDLE_KEY_PAGE, page.getValue());
        context.startActivity(intent);
    }
}
