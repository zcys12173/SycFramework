package com.example.business.viewmodel

import android.os.Bundle
import com.syc.common.utils.ToastUtils
import com.syc.framework.framework.BaseViewModel

/**
 * Created by shiyucheng on 2018/7/6.
 */
class MainBusinessViewModel:BaseViewModel(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun onRetryClick(){
        ToastUtils.showToast(context,"点击了重试按钮")
    }
}