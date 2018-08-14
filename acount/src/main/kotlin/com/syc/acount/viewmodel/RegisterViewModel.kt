package com.syc.framework.sycframework.viewmodel

import android.databinding.ObservableField
import android.text.TextUtils
import com.syc.acount.api.RequestService
import com.syc.common.RxBaseViewModel
import com.syc.common.network.HttpManager
import com.syc.common.utils.LogUtil
import com.syc.framework.framework.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by shiyucheng on 2018/1/10.
 */
class RegisterViewModel : RxBaseViewModel() {
    var userName: ObservableField<String> = ObservableField()
    var password: ObservableField<String> = ObservableField()
    var confirmPw: ObservableField<String> = ObservableField()

    init {

    }

    fun register() {
        if (checkUserName() && checkPassword()) {
            val observable =  HttpManager.getInstance()
                    .getService(RequestService::class.java).register(userName.get()!!, password.get()!!)
           subscribe(observable, object : RxCallBack<String> {
               override fun onFinish(result: String?) {
                   Toast("注册成功")
                   finish()
               }

               override fun onError(msg: String?) {
                   Toast("注册失败")
               }

           })
        }

    }


    private fun checkUserName(): Boolean = !TextUtils.isEmpty(userName.get())

    private fun checkPassword(): Boolean {
        return (!TextUtils.isEmpty(password.get())) && TextUtils.equals(password.get(), confirmPw.get())
    }

}