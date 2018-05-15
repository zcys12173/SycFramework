package com.example.business.view

import android.os.Bundle
import com.example.business.R
import com.example.business.databinding.ActivityBusinessMainBinding
import com.example.business.viewmodel.BusinessMainViewModel
import com.syc.framework.framework.BaseActivity
import com.syc.framework.framework.ViewModelFactory

/**
 * Created by shiyucheng on 2018\5\15 0015.
 */
class BusinessMainActivity:BaseActivity<ActivityBusinessMainBinding>(){


    override fun getContent(): Int {
        return R.layout.activity_business_main
    }
    override fun initView(savedInstanceState: Bundle?) {
        val viewModel = ViewModelFactory.create(this,BusinessMainViewModel::class.java)
        binding.vm = viewModel
        addViewModel(viewModel)
    }
}