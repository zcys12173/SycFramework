package com.syc.acount.view

import android.os.Bundle
import com.syc.acount.R
import com.syc.acount.databinding.FragmentMainHomepageBinding
import com.syc.acount.viewmodel.FirstFragmentViewModel
import com.syc.framework.framework.BaseFragment
import com.syc.framework.framework.ViewModelFactory

/**
 *
 * Created by shiyucheng on 2018\5\21 0021.
 */

class MainFirstFragment : BaseFragment<FragmentMainHomepageBinding>() {

    override fun getContent(): Int {
        return R.layout.fragment_main_homepage
    }

    override fun init(savedInstanceState: Bundle?) {
        val vm = ViewModelFactory.create(context, FirstFragmentViewModel::class.java)
        addViewModel(vm)
        binding.vm = vm
    }

}