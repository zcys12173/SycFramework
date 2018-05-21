package com.syc.demo.view

import android.os.Bundle
import com.syc.acount.view.MainMineFragment
import com.syc.framework.R
import com.syc.framework.databinding.ActivityMainBinding
import com.syc.framework.framework.BaseActivity

/**
 * Created by shiyucheng on 2018\5\21 0021.
 */

class MainActivity : BaseActivity<ActivityMainBinding>(){
    private val MAIN_MINE_FRAGMENT_TAB = "main_mine_fragment"
    override fun getContent(): Int {
        return R.layout.activity_main
    }

    override fun initView(savedInstanceState: Bundle?) {
        val fragmentManager = supportFragmentManager
        val translation = fragmentManager.beginTransaction()
        translation.add(binding.content.id,MainMineFragment(),MAIN_MINE_FRAGMENT_TAB)
        translation.commit()
    }


    fun onSecondTabClick(){

    }

}