package com.example.business.view

import android.os.Bundle
import com.example.business.R
import com.example.business.databinding.FragmentMainBusinessBinding
import com.example.business.viewmodel.MainBusinessViewModel
import com.syc.framework.framework.BaseFragment
import com.syc.framework.framework.ViewModelFactory

/**
 * Created by shiyucheng on 2018\5\23 0023.
 */
class MainBusinessFragment:BaseFragment<FragmentMainBusinessBinding>(){
    override fun getContent(): Int {
        return R.layout.fragment_main_business
    }

    override fun init(savedInstanceState: Bundle?) {
        val viewModel = ViewModelFactory.create(context,MainBusinessViewModel::class.java)
        binding.vm = viewModel
        addViewModel(viewModel)
    }

}