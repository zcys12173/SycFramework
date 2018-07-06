package com.example.business.viewmodel

import android.os.Bundle
import android.os.Handler
import com.syc.common.utils.ToastUtils
import com.syc.common.widget.StateView
import com.syc.framework.framework.BaseViewModel

/**
 * Created by shiyucheng on 2018/7/6.
 */
class MainBusinessViewModel:BaseViewModel(){
    var state = StateView.STATE_LOADING
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Handler().postDelayed({
            state = StateView.STATE_NORMAL
            notifyChange()
        },10000)
    }

    fun onRetryClick(){
        ToastUtils.showToast(context,"点击了重试按钮")
    }
}