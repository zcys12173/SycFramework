package com.syc.demo

import com.syc.acount.AcountModule
import com.syc.framework.framework.FrameworkApplication

/**
 * Created by Administrator on 2018\5\14 0014.
 */
class MyApplication:FrameworkApplication(){
    override fun onCreate() {
        super.onCreate()
        AcountModule.install()
    }
}