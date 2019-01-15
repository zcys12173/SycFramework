package com.syc.framework.im

import android.content.ComponentName
import android.content.Context
import android.content.Context.BIND_AUTO_CREATE
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder

/**
 * Created by shiyucheng on 2019/1/6.
 */
object IMManager {
    private var msgBinder :IMessageAidlInterface? = null
    private val imServiceConnection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {
            msgBinder = null
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            msgBinder = IMessageAidlInterface.Stub.asInterface(service)
        }

    }
    /**
     * 初始化
     * @param context: 上下文
     */
    fun init(context: Context){
        val applicationContext = context.applicationContext
        val intent = Intent(applicationContext,IMService::class.java)
        applicationContext.bindService(intent, imServiceConnection,BIND_AUTO_CREATE)
    }


}