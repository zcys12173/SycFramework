package com.syc.demo

import android.app.Application
import android.content.Intent
import android.os.StrictMode
import android.text.TextUtils
import com.example.business.BusinessModule
import com.squareup.leakcanary.LeakCanary
import com.syc.acount.AccountModule
import com.syc.common.database.DBManager
import com.syc.common.imageloader.fressco.ImageLoader
import com.syc.common.network.HttpManager
import com.syc.common.network.config.HttpConfig
import com.syc.common.utils.LogUtil
import com.syc.framework.BuildConfig
import com.syc.framework.framework.FrameworkApplication
import com.syc.framework.im.IMService

/**
 * Created by Administrator on 2018\5\14 0014.
 */
class MyApplication : FrameworkApplication() {
    override fun onCreate() {
        super.onCreate()
        if (TextUtils.equals(BuildConfig.BUILD_TYPE, "dev")) {
            optimizeCode(this)
        }

        AccountModule.install()
        BusinessModule.install()
        val config = HttpConfig()
        config.baseUrl = BuildConfig.URL
        config.debug = true
        HttpManager.getInstance().init(applicationContext, config)
        DBManager.init(this)
        ImageLoader.init(this)
        LogUtil.DEBUG = true
        startService(Intent(this,IMService::class.java))
    }

    /**
     * 开启严苛模式和内存检测
     */
    private fun optimizeCode(application: Application){
        //内存检测
        if(!LeakCanary.isInAnalyzerProcess(application)){
           LeakCanary.install(application)
        }


//      严苛模式
//        detectAll 检测所有潜在的违例
//                detectCustomSlowCalls 自定义耗时操作
//                detectDiskReads 读磁盘
//                detectDiskWrites 写磁盘
//                detectNetwork 检查网络
//                detectResourceMismatches 检查资源类型是否匹配
        StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .build())

//                detectAll 检测所有潜在的
//                detectActivityLeaks 检测Activity的泄露
//                detectCleartextNetwork 检测明文的网络
//                detectFileUriExposure 检测file://或者是content://
//                detectLeakedClosableObjects 检查为管理的Closable对象
//                detectLeakedRegistrationObjects 检测需要注册类型是否解注
//                detectLeakedSqlLiteObjects 检测sqlite对象，如cursors
        StrictMode.setVmPolicy(StrictMode.VmPolicy.Builder()
                .detectAll()
                .build())
    }


}