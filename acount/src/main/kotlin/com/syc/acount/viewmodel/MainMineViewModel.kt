package com.syc.acount.viewmodel

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.syc.acount.view.AccountsActivity
import com.syc.acount.view.LoginActivity
import com.syc.acount.view.RegisterActivity
import com.syc.common.network.download.DEntry
import com.syc.common.network.download.DownloadManager
import com.syc.common.utils.LogUtil
import com.syc.framework.framework.BaseViewModel
import com.syc.framework.sycframework.model.User

/**
 * Created by shiyucheng on 2018/5/28.
 */
class MainMineViewModel : BaseViewModel() {
    private var entry:DEntry?=null;
    var progress:Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {

    }

    fun login() {
        val intent = Intent(context, LoginActivity::class.java)
        intent.putExtra("params1", "跳转参数")
        startActivityForResult(intent, 1)
    }

    fun download(){
        entry = DownloadManager.getInstance().download("http://down.gome.com.cn/g/120/Gome/GomeEShop.apk?down.myapp.com", object : DownloadManager.DownloadCallback {
            override fun prepare() {
                Toast("prepare")
            }

            override fun start() {
                Toast("start")
            }

            override fun progress(progress: Int) {
                this@MainMineViewModel.progress = progress
                notifyChange()
                Toast("progress$progress")
            }

            override fun success(path: String?) {
                Toast("success$path")
            }

            override fun failed(e: DownloadManager.DownLoadException?) {
                LogUtil.d("MainMineViewModel",e?.message)
                Toast("failed${e?.message}")
            }

        })
    }

    fun cancel(){
        entry?.cancel()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode== Activity.RESULT_OK) {
            val user:User = data?.getParcelableExtra("user") as User
            LogUtil.d("loginResult",user.userName)
        }
    }
}