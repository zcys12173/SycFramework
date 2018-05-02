package com.syc.framework.sycframework.view.adapter

import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView

/**
 * Created by shiyucheng on 2018/1/11.
 */
class BaseViewHolder<T : ViewDataBinding>(var binding: T) : RecyclerView.ViewHolder(binding.root)