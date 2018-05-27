package com.syc.acount.view

import android.content.Intent
import android.os.Bundle
import com.syc.acount.R
import com.syc.acount.databinding.FragmentMainHomepageBinding
import com.syc.acount.databinding.FragmentMainMineBinding
import com.syc.framework.framework.BaseFragment

/**
 *
 * Created by shiyucheng on 2018\5\21 0021.
 */

class MainFirstFragment:BaseFragment<FragmentMainHomepageBinding>(){

    override fun getContent(): Int {
        return R.layout.fragment_main_homepage
    }

    override fun init(savedInstanceState: Bundle?) {
        binding.tvName.setOnClickListener({
            val intent = Intent(context,LoginActivity::class.java)
            startActivity(intent)
        })
    }

}