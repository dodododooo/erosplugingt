package com.eros.erosplugingt.manager;

import android.content.Context;
import android.util.Log;

import com.igexin.sdk.IUserLoggerInterface;
import com.igexin.sdk.PushManager;

/**
 * Created by liuyuanxiao on 18/4/8.
 */

public class GetuiManager {

    public static void pushInit(Context context){
        PushManager.getInstance().initialize(context);
        // 切勿在 release 版本上开启调试日志
//        PushManager.getInstance().setDebugLogger(context, new IUserLoggerInterface() {
//
//            @Override
//            public void log(String s) {
//                Log.i("PUSH_LOG", s);
//            }
//        });
    }
}
