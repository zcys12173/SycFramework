package com.syc.entry

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import com.example.entry.R
import com.example.entry.databinding.ActivityMainBinding
import com.syc.framework.framework.BaseActivity
import com.syc.framework.router.Router

/**
 *
 * Created by shiyucheng on 2018\5\21 0021.
 */

class MainActivity : BaseActivity<ActivityMainBinding>() {
    private val MAIN_MINE_FRAGMENT_TAB = "main_mine_fragment"
    private val MAIN_FIRST_FRAGMENT_TAB = "main_first_fragment"
    private val MAIN_SECOND_FRAGMENT_TAB = "main_second_fragment"
    private val fragments = ArrayList<Fragment>()
    override fun getContent(): Int {
        return R.layout.activity_main
    }

    override fun initView(savedInstanceState: Bundle?) {
        Router.newBuilder().from(this).uri("/account/getMainFirstFragment").callBack({ obj ->
            fragments.add(obj as Fragment)
        }).buildAndRouter()

        Router.newBuilder().from(this).uri("/business/getMainBusinessFragment").callBack({ obj ->
            fragments.add(obj as Fragment)
        }).buildAndRouter()

        Router.newBuilder().from(this).uri("/account/getMainMineFragment").callBack({ obj ->
            fragments.add(obj as Fragment)
        }).buildAndRouter()

        val fragmentManager = supportFragmentManager
        val translation = fragmentManager.beginTransaction()
        translation.add(binding.content.id, fragments[0], MAIN_FIRST_FRAGMENT_TAB)
        translation.commit()
        binding.bottomTabLayout.setOnSelectedListener { currentIndex,lastIndex ->
            onTabClick(currentIndex,lastIndex)
        }
    }

    private fun onTabClick(index:Int,lastIndex:Int){
        var  nextTag:String =  MAIN_FIRST_FRAGMENT_TAB
        var  currentTag:String =  MAIN_FIRST_FRAGMENT_TAB
            when(index){
            0->{
                nextTag =  MAIN_FIRST_FRAGMENT_TAB
            }
            1->{
                nextTag =  MAIN_SECOND_FRAGMENT_TAB
            }
            2->{
                nextTag =  MAIN_MINE_FRAGMENT_TAB
            }
        }
        when(lastIndex){
            0->{
                currentTag =  MAIN_FIRST_FRAGMENT_TAB
            }
            1->{
                currentTag =  MAIN_SECOND_FRAGMENT_TAB
            }
            2->{
                currentTag =  MAIN_MINE_FRAGMENT_TAB
            }
        }
        val fragmentManager = supportFragmentManager
        val translation = fragmentManager.beginTransaction()
        val currentFragment = fragmentManager.findFragmentByTag(currentTag)
        var nextFragment = fragmentManager.findFragmentByTag(nextTag)
        if(currentFragment != null){
            translation.hide(currentFragment)
        }
        if(nextFragment != null){
            translation.show(nextFragment)
        }else{
            nextFragment = fragments[index]
            translation.add(binding.content.id,nextFragment,nextTag)
        }
        translation.commit()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
    }




}