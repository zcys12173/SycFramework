package com.syc.framework.utils;

import android.util.Log;

/**
 * Created by shiyucheng on 2018/1/3.
 * Log日志工具
 */

public class LogUtil {
    public static boolean LOGABLE = true;
    public static void d(String tag,String msg){
        if (LOGABLE) {
            Log.d(tag,msg);
        }
    }

    public static void e(String tag,String msg){
        if (LOGABLE) {
            Log.e(tag,msg);
        }
    }

    public static void w(String tag,String msg){
        if (LOGABLE) {
            Log.w(tag,msg);
        }
    }

    public static void i(String tag,String msg){
        if (LOGABLE) {
            Log.i(tag,msg);
        }
    }
}
