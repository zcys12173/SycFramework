package com.syc.framework.sycframework.viewmodel

import android.content.Context
import android.databinding.ObservableField
import com.syc.framework.database.DBManager
import com.syc.framework.framework.BaseViewModel
import com.syc.framework.sycframework.SP_KEY_PASSWORD
import com.syc.framework.sycframework.SP_KEY_USERNAME
import com.syc.framework.sycframework.model.Account
import com.syc.framework.utils.MD5Util
import com.syc.framework.utils.SharedPreferencesUtil
import io.realm.Realm

/**
 * Created by shiyucheng on 2018/1/15.
 */
class NewAccountViewModel(context: Context) : BaseViewModel(context) {
    var title: ObservableField<String> = ObservableField()
    var userName: ObservableField<String> = ObservableField()
    var password: ObservableField<String> = ObservableField()

    init {
        userName.set("");
        password.set("")
    }

    fun create() {
        var name: String = MD5Util.md5(SharedPreferencesUtil.get(context, SP_KEY_USERNAME, "") as String + SharedPreferencesUtil.get(context, SP_KEY_PASSWORD, "") as String)
        DBManager.getRealmInstance(name).executeTransactionAsync(Realm.Transaction { realm ->
            var account = Account(title.get(), userName.get(), password.get())
            realm.copyToRealmOrUpdate(account)
        }, Realm.Transaction.OnSuccess {
            finish()
        })
    }
}