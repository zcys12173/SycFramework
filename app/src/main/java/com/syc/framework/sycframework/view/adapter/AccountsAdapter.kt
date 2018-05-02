package com.syc.framework.sycframework.view.adapter


import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.syc.framework.framework.ViewModelFactory
import com.syc.framework.sycframework.R
import com.syc.framework.sycframework.databinding.ItemAccountsBinding
import com.syc.framework.sycframework.model.Account
import com.syc.framework.sycframework.viewmodel.AccountsItemViewModel
import java.lang.ref.WeakReference

/**
 * Created by shiyucheng on 2018/1/11.
 */
class AccountsAdapter(context: Context ,private var accounts: List<Account>) : RecyclerView.Adapter<BaseViewHolder<ItemAccountsBinding>>() {
    private var wrContext:WeakReference<Context> = WeakReference(context)

    override fun onBindViewHolder(holder: BaseViewHolder<ItemAccountsBinding>?, position: Int) {
        var itemViewModel:AccountsItemViewModel = ViewModelFactory.create(wrContext.get(),AccountsItemViewModel::class.java)
        itemViewModel.setAccount(accounts[position])
        holder?.binding?.vm = itemViewModel
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BaseViewHolder<ItemAccountsBinding> {
        var binding = DataBindingUtil.inflate<ItemAccountsBinding>(LayoutInflater.from(wrContext.get()), R.layout.item_accounts, parent, false)
        var viewHolder = BaseViewHolder(binding)
        return viewHolder
    }

    override fun getItemCount(): Int {
        return accounts.size
    }

}
