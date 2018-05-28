package com.syc.framework.sycframework.viewmodel

import android.app.Activity
import android.content.Intent
import android.databinding.ObservableField
import android.os.Bundle
import com.syc.acount.view.AccountsActivity
import com.syc.common.database.DBManager
import com.syc.common.utils.LogUtil
import com.syc.framework.framework.BaseViewModel
import com.syc.framework.router.Router
import com.syc.framework.router.annotation.RouterSubscribe
import com.syc.framework.sycframework.model.User


/**
 * Created by shiyucheng on 2018/1/10.
 */
class LoginViewModel : BaseViewModel() {
    var userName: ObservableField<String> = ObservableField()
    var password: ObservableField<String> = ObservableField()

    init {
        userName.set("")
        password.set("")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val params = intent.getStringExtra("params1")
        Toast("收到参数：" + params);
    }

    fun onLoginClick() {
        DBManager.getDefaultInstance().executeTransactionAsync({ realm ->
            realm.where(User::class.java).equalTo("userName", userName.get()).equalTo("password", password.get()).findFirst()
                    ?: throw NoSuchFieldException("账号密码错误")

        }, {
            Toast("登录成功")
            goMainActivity()
        }, {
            Toast("账号密码不对")
        })
    }

    fun onAddClick() {
        val bundle = Bundle()
        bundle.putString("age", "18")
        Router.newBuilder().uri("/business/BusinessMainActivityOpen").from(context).callBack({ obj ->
            val bundle = obj as Bundle
            LogUtil.d("上一个页面返回数据", bundle.getString("name"))
        }).params(bundle).buildAndRouter()

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
}