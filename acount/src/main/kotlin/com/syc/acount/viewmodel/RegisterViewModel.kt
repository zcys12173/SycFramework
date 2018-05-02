package com.syc.framework.sycframework.viewmodel

import android.content.Context
import android.databinding.ObservableField
import android.text.TextUtils
import android.widget.Toast
import com.syc.framework.database.DBManager
import com.syc.framework.framework.BaseViewModel
import com.syc.framework.sycframework.model.User

/**
 * Created by shiyucheng on 2018/1/10.
 */
class RegisterViewModel(context: Context) : BaseViewModel(context) {
    var userName: ObservableField<String> = ObservableField()
    var password: ObservableField<String> = ObservableField()
    var confirmPw: ObservableField<String> = ObservableField()

    init {

    }

    fun register() {
        if (checkUserName() && checkPassword()) {
            DBManager.getDefaultInstance().executeTransactionAsync(
                    { realm ->
                        val user = User(userName.get(), password.get())
                        realm.copyToRealm(user)
                    }, {
                        Toast.makeText(context, "注册成功", Toast.LENGTH_SHORT).show()
                        finish()
                     },
                    { t ->
                        Toast.makeText(context, "用户已经存在", Toast.LENGTH_SHORT).show()
                    })
        }

    }


    private fun checkUserName(): Boolean = !TextUtils.isEmpty(userName.get())

    private fun checkPassword(): Boolean {
        return (!TextUtils.isEmpty(password.get())) && TextUtils.equals(password.get(), confirmPw.get())
    }

}