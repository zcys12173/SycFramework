package com.syc.acount.view

import android.os.Bundle
import com.syc.acount.R
import com.syc.acount.databinding.FragmentMainMineBinding
import com.syc.acount.viewmodel.MainMineViewModel
import com.syc.framework.framework.BaseFragment
import com.syc.framework.framework.ViewModelFactory

/**
 *
 * Created by shiyucheng on 2018\5\21 0021.
 */

class MainMineFragment:BaseFragment<FragmentMainMineBinding>(){

    override fun getContent(): Int {
        return R.layout.fragment_main_mine
    }

    override fun init(savedInstanceState: Bundle?) {
        val vm = ViewModelFactory.create(context,MainMineViewModel::class.java)
        binding.vm = vm
    }

}