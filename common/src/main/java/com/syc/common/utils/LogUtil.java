package com.syc.common.utils;

import android.util.Log;

import com.syc.framework.framework.BuildConfig;

/**
 * Created by shiyucheng on 2018/1/3.
 * Log日志工具
 */

public class LogUtil {
    public static boolean DEBUG = BuildConfig.DEBUG;

    public static void d(String tag, String msg) {
        if (DEBUG) {
            Log.d(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (DEBUG) {
            Log.e(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (DEBUG) {
            Log.w(tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (DEBUG) {
            Log.i(tag, msg);
        }
    }
}
