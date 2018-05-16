package com.syc.framework.sycframework.viewmodel

import android.content.Intent
import com.syc.acount.view.NewAccountActivity
import com.syc.framework.framework.BaseViewModel

/**
 * Created by shiyucheng on 2018/1/12.
 */
class AccountsViewModel : BaseViewModel() {

    fun onAddClick() {
        var intent = Intent(context, NewAccountActivity::class.java)
        context.startActivity(intent)
    }
}