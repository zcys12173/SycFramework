package com.syc.acount.view

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.syc.acount.R
import com.syc.acount.databinding.ActivityAccountsBinding
import com.syc.acount.databinding.ItemAccountsBinding
import com.syc.common.widget.refresh.adapter.BaseViewHolder
import com.syc.common.widget.refresh.adapter.PTRAdapter
import com.syc.framework.framework.BaseActivity
import com.syc.framework.sycframework.model.Account

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
        val account1 = Account("bbb", "bbb", "ccc")
        val account2 = Account("ccc", "bbb", "ccc")
        accounts.add(account)
        accounts.add(account1)
        accounts.add(account2)
        binding.prtAccounts.setLayoutManager(manager)
//        binding.prtAccounts.setAdapter( AccountsAdapter(baseContext, accounts))
        binding.prtAccounts.setAdapter(object : PTRAdapter<Account>(accounts,R.layout.item_accounts) {
            override fun onBindViewHolderListener(viewHolder: BaseViewHolder?, item: Account?, position: Int) {
                viewHolder?.getBinding<ItemAccountsBinding>()?.name?.text = item?.name
            }


//
//            override fun getItemLayout(viewType: Int): Int {
//                return super.getItemLayout(viewType)
//            }
//
//            override fun <String : Any?> getItemType(item: Account, position: Int): Int {
//                return super.getItemType(item, position)
//            }
        })
    }
}