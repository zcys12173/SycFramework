package com.syc.acount.view

import android.app.job.JobScheduler
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import com.syc.acount.R
import com.syc.acount.databinding.ActivityLoginBinding
import com.syc.framework.framework.BaseActivity
import com.syc.framework.framework.ViewModelFactory
import com.syc.framework.sycframework.viewmodel.LoginViewModel

class LoginActivity : BaseActivity<ActivityLoginBinding>() {
    override fun getContent(): Int {
        return R.layout.activity_login

    }

    override fun initView(bundle: Bundle?) {
        val viewModel = ViewModelFactory.create(this, LoginViewModel::class.java)
        binding.vm = viewModel
        addViewModel(viewModel)
    }


    override fun finish() {
        super.finish()
        overridePendingTransition(0, R.anim.exit_from_bottom)
    }

}
