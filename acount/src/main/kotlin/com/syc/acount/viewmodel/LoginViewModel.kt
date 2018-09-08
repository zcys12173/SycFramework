package com.syc.framework.sycframework.viewmodel

import android.app.Activity
import android.content.Intent
import android.databinding.ObservableField
import android.os.Bundle
import com.syc.acount.api.RequestService
import com.syc.acount.view.AccountsActivity
import com.syc.acount.view.RegisterActivity
import com.syc.common.RxBaseViewModel
import com.syc.common.network.HttpManager
import com.syc.framework.router.annotation.RouterSubscribe
import com.syc.framework.sycframework.model.User


/**
 * Created by shiyucheng on 2018/1/10.
 */
class LoginViewModel : RxBaseViewModel() {
    var userName: ObservableField<String> = ObservableField()
    var password: ObservableField<String> = ObservableField()


    init {
        userName.set("")
        password.set("")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val params = intent.getStringExtra("params1")
        Toast("收到参数：$params")

    }

    fun onLoginClick() {
        val observable = HttpManager.getInstance().getService(RequestService::class.java).login(userName.get()!!, password.get()!!)
        subscribe(observable, object : RxCallBack<User> {
            override fun onFinish(result: User?) {
                Toast("登录成功")
                val intent = Intent()
                intent.putExtra("user", result)
                setResult(Activity.RESULT_OK, intent)
                finish()
            }

            override fun onError(msg: String?) {
                Toast(msg)
            }

        })

    }

    fun onAddClick() {
        val intent = Intent(context, RegisterActivity::class.java)
        startActivity(intent)
//        val bundle = Bundle()
//        bundle.putString("age", "18")
//        Router.newBuilder().uri("/business/BusinessMainActivityOpen").from(activityLauncher).callBack { obj ->
//            val bundle = obj as Bundle
//            LogUtil.d("上一个页面返回数据", bundle.getString("name"))
//        }.params(bundle).buildAndRouter()

    }

    fun onBackClick() {
        val intent = Intent();
        intent.putExtra("data", "返回参数")
        setResult(Activity.RESULT_OK, intent)
        finish()
    }


    private fun goMainActivity() {
        val intent = Intent(context, AccountsActivity::class.java)
        context.startActivity(intent)

    }

    @RouterSubscribe(uri = "login/annoTest")
    fun onAnnotationTest(bundle: Bundle) {
        Toast(bundle.getString("anno"))
    }

    private fun getData() {
//        HttpManager.getInstance().getService(RequestService::class.java).request("key")
//                .subscribeOn(Schedulers.computation())
//                .observeOn(AndroidSchedulers.mainThread())
//                .map { t -> t!!.data }.subscribe({s->
//
//        },{
//
//        },{
//
//        })
    }
}