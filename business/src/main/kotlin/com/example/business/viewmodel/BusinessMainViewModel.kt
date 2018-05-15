package com.example.business.viewmodel

import android.content.Context
import android.os.Bundle
import com.syc.framework.framework.BaseViewModel
import com.syc.framework.router.Router

/**
 * Created by shiyucheng on 2018\5\15 0015.
 */
class BusinessMainViewModel(context: Context) : BaseViewModel(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun onClick() {
        val bundle = Bundle()
        bundle.putString("anno", "clickByAnnotation")
        Router.getInstance().broadcast("login/annoTest", bundle)
    }

}