package com.syc.framework.sycframework.viewmodel

import com.syc.framework.framework.BaseViewModel
import com.syc.framework.sycframework.model.Account

/**
 * Created by shiyucheng on 2018/1/11.
 */
class AccountsItemViewModel : BaseViewModel() {

    fun setAccount(account: Account) {
        name = account.name
    }

    var name: String? = null
}