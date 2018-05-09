package com.syc.acount.view

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.syc.acount.R
import com.syc.acount.databinding.ActivityAccountsBinding
import com.syc.framework.framework.BaseActivity
import com.syc.framework.sycframework.model.Account
import com.syc.framework.sycframework.view.adapter.AccountsAdapter

/**
 * Created by shiyucheng on 2018/1/11.
 */
class AccountsActivity : BaseActivity<ActivityAccountsBinding>() {
    override fun getContent(): Int {
        return R.layout.activity_accounts
    }

    override fun initView(bundle: Bundle?) {
        val accounts: MutableList<Account> = mutableListOf()
        val manager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        val account = Account("aaa", "bbb", "ccc")
        accounts.add(account)
        binding.rvAccounts.layoutManager = manager
        binding.rvAccounts.adapter = AccountsAdapter(baseContext, accounts)
    }
}