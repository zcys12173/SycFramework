package com.syc.acount.view

import android.os.Bundle
import android.os.Handler
import com.syc.acount.R
import com.syc.acount.databinding.ActivityRegisterBinding
import com.syc.framework.framework.BaseActivity
import com.syc.framework.framework.ViewModelFactory
import com.syc.framework.sycframework.viewmodel.RegisterViewModel
import kotlinx.android.synthetic.main.activity_register.*

/**
 * Created by shiyucheng on 2018/1/10.
 */
class RegisterActivity : BaseActivity<ActivityRegisterBinding>() {
    override fun getContent(): Int {
        return R.layout.activity_register
    }

    override fun initView(bundle: Bundle?) {
        val viewModel: RegisterViewModel = ViewModelFactory.create(this, RegisterViewModel::class.java)
        binding.vm = viewModel
//        memoryOut()
    }

    private fun memoryOut(){
        Handler().postDelayed(Runnable {
            edt_username.setText("ddddd")
        },5000)
    }

}