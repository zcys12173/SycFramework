package com.syc.acount.viewmodel

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.syc.acount.view.AccountsActivity
import com.syc.acount.view.LoginActivity
import com.syc.framework.framework.BaseViewModel

/**
 * Created by shiyucheng on 2018/5/28.
 */
class MainMineViewModel : BaseViewModel() {
    override fun onCreate(savedInstanceState: Bundle?) {

    }

    fun login() {
        val intent = Intent(context, AccountsActivity::class.java)
        intent.putExtra("params1", "跳转参数")
        startActivityForResult(intent, 1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode== Activity.RESULT_OK) {
            Toast("收到返回参数：" + data?.getStringExtra("data"))
        }
    }
}