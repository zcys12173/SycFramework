package com.syc.acount.viewmodel

import android.content.Intent
import android.os.Bundle
import com.syc.acount.view.LoginActivity
import com.syc.framework.framework.BaseViewModel

/**
 * Created by shiyucheng on 2018/5/28.
 */
class MainMineViewModel : BaseViewModel() {
    override fun onCreate(savedInstanceState: Bundle?) {

    }

    fun login() {
        val intent = Intent(context, LoginActivity::class.java)
        startActivity(intent)
    }
}