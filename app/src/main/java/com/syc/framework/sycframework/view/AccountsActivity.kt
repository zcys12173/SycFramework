package com.syc.framework.sycframework.view

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.syc.framework.framework.BaseActivity
import com.syc.framework.sycframework.R
import com.syc.framework.sycframework.databinding.ActivityAccountsBinding
import com.syc.framework.sycframework.model.Account
import com.syc.framework.sycframework.view.adapter.AccountsAdapter

/**
 * Created by shiyucheng on 2018/1/11.
 */
class AccountsActivity :BaseActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding:ActivityAccountsBinding = DataBindingUtil.setContentView(this,R.layout.activity_accounts)
        val accounts:MutableList<Account> = mutableListOf()
        val manager :RecyclerView.LayoutManager = LinearLayoutManager(baseContext)
        val account = Account("aaa","bbb","ccc")
        accounts.add(account)
        binding.rvAccounts.layoutManager = manager
        binding.rvAccounts.adapter = AccountsAdapter(baseContext,accounts)
    }
}