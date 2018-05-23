package com.syc.entry

import android.os.Bundle
import android.support.v4.app.Fragment
import com.example.entry.R
import com.example.entry.databinding.ActivityMainBinding
import com.syc.framework.framework.BaseActivity
import com.syc.framework.router.Router

/**
 * Created by shiyucheng on 2018\5\21 0021.
 */

class MainActivity : BaseActivity<ActivityMainBinding>() {
    private val MAIN_MINE_FRAGMENT_TAB = "main_mine_fragment"
    private val fragments = ArrayList<Fragment>()
    override fun getContent(): Int {
        return R.layout.activity_main
    }

    override fun initView(savedInstanceState: Bundle?) {
        Router.newBuilder().from(this).uri("/account/getMainMineFragment").callBack({ obj ->
            fragments.add(obj as Fragment)
        }).buildAndRouter()

        Router.newBuilder().from(this).uri("/business/getMainBusinessFragment").callBack({ obj ->
            fragments.add(obj as Fragment)
        }).buildAndRouter()

        val fragmentManager = supportFragmentManager
        val translation = fragmentManager.beginTransaction()
        translation.add(binding.content.id, fragments[0], MAIN_MINE_FRAGMENT_TAB)
        translation.commit()
    }


    fun onSecondTabClick() {

    }

}