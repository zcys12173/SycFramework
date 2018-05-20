package com.syc.common.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by shiyucheng on 2018/5/20.
 */

public class ToastUtils {
    public static void showToast(Context context,String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
