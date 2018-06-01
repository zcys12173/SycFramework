package com.syc.framework;

import android.content.Context;
import android.content.Intent;

/**
 * Created by shiyucheng on 2018/5/28.
 */

public interface ActivityLauncher {
    void startActivity(Intent intent);

    void startActivityForResult( Intent intent,int requestCode);

    void setResult(int resultCode);

    void setResult(int resultCode, Intent intent);

    Context getContext();
}
