package com.syc.acount.viewmodel

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.syc.acount.view.AccountsActivity
import com.syc.acount.view.LoginActivity
import com.syc.acount.view.RegisterActivity
import com.syc.common.utils.LogUtil
import com.syc.framework.framework.BaseViewModel
import com.syc.framework.sycframework.model.User

/**
 * Created by shiyucheng on 2018/5/28.
 */
class MainMineViewModel : BaseViewModel() {
    override fun onCreate(savedInstanceState: Bundle?) {

    }

    fun login() {
        val intent = Intent(context, LoginActivity::class.java)
        intent.putExtra("params1", "跳转参数")
        startActivityForResult(intent, 1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode== Activity.RESULT_OK) {
            val user:User = data?.getParcelableExtra("user") as User
            LogUtil.d("loginResult",user.userName)
        }
    }
}