package com.syc.framework.sycframework.viewmodel

import android.content.Context
import android.content.Intent
import android.databinding.ObservableField
import android.os.Bundle
import android.widget.Toast
import com.syc.acount.view.AccountsActivity
import com.syc.framework.database.DBManager
import com.syc.framework.framework.BaseViewModel
import com.syc.framework.router.Router
import com.syc.framework.sycframework.model.User
import com.syc.framework.utils.LogUtil


/**
 * Created by shiyucheng on 2018/1/10.
 */
class LoginViewModel(context: Context) : BaseViewModel(context) {
    var userName: ObservableField<String> = ObservableField()
    var password: ObservableField<String> = ObservableField()

    init {
//        userName.set("")
//        password.set("")
    }

    fun onLoginClick() {
        DBManager.getDefaultInstance().executeTransactionAsync({ realm ->
            realm.where(User::class.java).equalTo("userName", userName.get()).equalTo("password", password.get()).findFirst()
                    ?: throw NoSuchFieldException("账号密码错误")

        }, {
            Toast.makeText(context, "登录成功", Toast.LENGTH_SHORT).show()
            goMainActivity()
        }, {
            Toast.makeText(context, "账号密码不对", Toast.LENGTH_SHORT).show()
        })
    }

    fun onAddClick() {
        val bundle = Bundle()
        bundle.putString("age", "18")
        Router.newBuilder().uri("/account/SwipeBackTestActivityOpen").from(context).callBack({ bundle ->
            LogUtil.d("上一个页面返回数据", bundle.getString("name"))
        }).params(bundle).buildAndRouter()
    }


    fun goMainActivity() {
        var intent: Intent = Intent(context, AccountsActivity::class.java)
        context.startActivity(intent)

    }
}