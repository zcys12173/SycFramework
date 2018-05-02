package com.syc.framework.framework;

import android.app.Application;

import com.syc.framework.database.DBManager;
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
        HttpConfig config = new HttpConfig();
        config.baseUrl = "http://www.oschina.net/";
        config.debug = true;
        HttpManager.getInstance().init(getApplicationContext(),config);
        DBManager.init(this);
    }
}
