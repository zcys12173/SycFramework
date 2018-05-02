package com.syc.framework.sycframework.viewmodel

import android.content.Context
import android.content.Intent
import com.syc.framework.framework.BaseViewModel
import com.syc.framework.sycframework.view.NewAccountActivity

/**
 * Created by shiyucheng on 2018/1/12.
 */
class AccountsViewModel(context:Context): BaseViewModel(context){

    fun onAddClick(){
        var intent = Intent(context,NewAccountActivity::class.java)
        context.startActivity(intent)
    }
}