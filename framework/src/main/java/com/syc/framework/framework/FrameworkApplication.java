package com.syc.framework.framework;

import android.app.Application;

import com.syc.framework.database.DBManager;
import com.syc.framework.imageloader.ImageLoader;
import com.syc.framework.network.HttpManager;
import com.syc.framework.network.config.HttpConfig;

import io.realm.Realm;


/**
 * Created by shiyucheng on 2018/1/3.
 */

public class FrameworkApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
    }
}
