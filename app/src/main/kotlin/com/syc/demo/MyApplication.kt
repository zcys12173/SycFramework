package com.syc.demo

import com.example.business.BusinessModule
import com.syc.acount.AccountModule
import com.syc.framework.BuildConfig
import com.syc.framework.database.DBManager
import com.syc.framework.framework.FrameworkApplication
import com.syc.framework.imageloader.ImageLoader
import com.syc.framework.network.HttpManager
import com.syc.framework.network.config.HttpConfig

/**
 * Created by Administrator on 2018\5\14 0014.
 */
class MyApplication : FrameworkApplication() {
    override fun onCreate() {
        super.onCreate()
        AccountModule.install()
        BusinessModule.install()
        val config = HttpConfig()
        config.baseUrl = BuildConfig.URL
        config.debug = true
        HttpManager.getInstance().init(applicationContext, config)
        DBManager.init(this)
        ImageLoader.init(this)
    }
}