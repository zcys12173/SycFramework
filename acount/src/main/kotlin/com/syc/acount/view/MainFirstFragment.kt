package com.syc.acount.view

import android.os.Bundle
import com.syc.acount.R
import com.syc.acount.databinding.FragmentMainMineBinding
import com.syc.framework.framework.BaseFragment

/**
 *
 * Created by shiyucheng on 2018\5\21 0021.
 */

class MainFirstFragment:BaseFragment<FragmentMainMineBinding>(){

    override fun getContent(): Int {
        return R.layout.fragment_main_mine
    }

    override fun init(savedInstanceState: Bundle?) {
        binding.tvName.text = "first"
    }

}