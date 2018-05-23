package com.example.business.view

import android.os.Bundle
import com.example.business.R
import com.example.business.databinding.FragmentMainBusinessBinding
import com.syc.framework.framework.BaseFragment

/**
 * Created by shiyucheng on 2018\5\23 0023.
 */
class MainBusinessFragment:BaseFragment<FragmentMainBusinessBinding>(){
    override fun getContent(): Int {
        return R.layout.fragment_main_business
    }

    override fun init(savedInstanceState: Bundle?) {

    }

}