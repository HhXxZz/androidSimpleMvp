package com.singgo.cn.timewindows.base;

import android.app.Application;
import android.content.Context;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;

import me.drakeet.materialdialog.MaterialDialog;

/**
 * Created by hxz on 16/9/2 17:30.
 */
public class BaseApplication extends Application {
    public static Context mContext;
    public static String cachePath = Environment.getExternalStorageDirectory().getPath()+"/timewindow/cache/";
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static void showDialog(Context context,String message) {

        final MaterialDialog dialog = new MaterialDialog(context);
        dialog.setMessage(message);
        dialog.setPositiveButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                }
        );
        dialog.show();
    }

    public static void showToast(Context context,String message){
        Toast.makeText(mContext,message,Toast.LENGTH_SHORT).show();
    }

}
