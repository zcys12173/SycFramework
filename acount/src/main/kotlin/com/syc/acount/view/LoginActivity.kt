package com.syc.acount.view

import android.os.Bundle
import com.syc.acount.R
import com.syc.acount.databinding.ActivityMainBinding
import com.syc.framework.framework.BaseActivity
import com.syc.framework.framework.ViewModelFactory
import com.syc.framework.sycframework.viewmodel.LoginViewModel

class LoginActivity : BaseActivity<ActivityMainBinding>() {
    override fun getContent(): Int {
        return R.layout.activity_main;
    }

    override fun initView(bundle: Bundle?) {
        val viewModel = ViewModelFactory.create(this,LoginViewModel::class.java)
        binding.vm = viewModel
        addViewModel(viewModel)
    }

}
