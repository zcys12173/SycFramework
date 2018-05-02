package com.syc.framework.sycframework.view

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.syc.framework.sycframework.R
import com.syc.framework.sycframework.databinding.ActivityMainBinding
import com.syc.framework.sycframework.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  DataBindingUtil.setContentView(this, R.layout.activity_main)
        var viewModel = LoginViewModel(this)
        binding.vm = viewModel

    }


}
