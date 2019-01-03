package com.syc.framework.im

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.google.gson.Gson
import com.syc.common.utils.LogUtil
import com.syc.framework.im.Entry.Message
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.Socket

/**
 * Created by shiyucheng on 2018/10/31.
 */
class IMService :Service (){
    private lateinit var socket:Socket
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        LogUtil.d("IMService","onCreate")
        Thread {
            try {
                socket = Socket("10.0.2.2", 1028)
                val msg = Message("我是xxx，我要链接")
                val content = Gson().toJson(msg)
                sendMessage(content)
                Thread(MessageReceiverRunnable(socket)).start()
            } catch (e: Exception) {
                LogUtil.e("IMService",e.toString())
            }
        }.start()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_REDELIVER_INTENT
    }

    private fun sendMessage(msg:String){
        val outputStream = socket.getOutputStream()
        val printWriter = PrintWriter(outputStream)//将输出流包装成打印流
        printWriter.println(msg)
        printWriter.flush()
    }

    class MessageReceiverRunnable(private val socket: Socket) : Runnable{
        override fun run() {
            val inputStream = socket.getInputStream()
            val inputStreamReader = InputStreamReader(inputStream)//提高效率，将自己字节流转为字符流
            val bufferedReader = BufferedReader(inputStreamReader)//加入缓冲区
            var temp: String? = null
            var content = ""
            temp = bufferedReader.readLine()
            while (temp != null) {
                content = temp
                LogUtil.d("im", "收到服务端消息：" + content + ",当前服务端ip为：" + socket.inetAddress.hostAddress)
                temp = bufferedReader.readLine()
            }
        }

    }

}