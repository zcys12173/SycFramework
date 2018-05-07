package com.syc.framework.sycframework.viewmodel

import android.content.Context
import android.content.Intent
import android.databinding.ObservableField
import android.text.TextUtils
import android.widget.Toast
import com.syc.framework.database.DBManager
import com.syc.framework.utils.LogUtil
import com.syc.framework.utils.SharedPreferencesUtil
import com.syc.framework.framework.BaseViewModel
import com.syc.framework.sycframework.model.User
import com.syc.framework.sycframework.view.AccountsActivity
import com.syc.framework.sycframework.view.RegisterActivity
import android.content.ComponentName



/**
 * Created by shiyucheng on 2018/1/10.
 */
class LoginViewModel(context:Context) : BaseViewModel(context){
    var userName: ObservableField<String> = ObservableField()
    var password: ObservableField<String> = ObservableField()
    init {
//        userName.set("")
//        password.set("")
    }

    fun onLoginClick(){
        DBManager.getDefaultInstance().executeTransactionAsync({
            realm ->
          realm.where(User::class.java).equalTo("userName",userName.get()).equalTo("password",password.get()).findFirst() ?: throw NoSuchFieldException("账号密码错误")

        },{
            Toast.makeText(context,"登录成功",Toast.LENGTH_SHORT).show()
            goMainActivity()
        },{
            Toast.makeText(context,"账号密码不对",Toast.LENGTH_SHORT).show()
        })
    }

    fun onAddClick(){
        var intent = Intent(context,RegisterActivity::class.java)
        context.startActivity(intent)

    }


    fun goMainActivity(){
        var intent:Intent = Intent(context,AccountsActivity::class.java)
        context.startActivity(intent)

    }
}