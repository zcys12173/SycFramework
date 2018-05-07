package com.syc.framework.sycframework.view

import android.os.Bundle
import com.syc.acount.R
import com.syc.acount.databinding.ActivityNewAccountBinding
import com.syc.framework.framework.BaseActivity

/**
 * Created by shiyucheng on 2018/1/12.
 */
class NewAccountActivity:BaseActivity<ActivityNewAccountBinding>(){
    override fun getContent(): Int {
        return R.layout.activity_new_account
    }

    override fun initView(bundle: Bundle?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}