package com.syc.acount.view

import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import com.syc.acount.R
import com.syc.acount.databinding.FragmentMainMineBinding
import com.syc.acount.viewmodel.MainMineViewModel
import com.syc.common.imageloader.ImageLoader
import com.syc.framework.framework.BaseFragment
import com.syc.framework.framework.ViewModelFactory

/**
 *
 * Created by shiyucheng on 2018\5\21 0021.
 */

class MainMineFragment:BaseFragment<FragmentMainMineBinding>(){
    val url = "http://img5.imgtn.bdimg.com/it/u=415293130,2419074865&fm=27&gp=0.jpg"
    override fun getContent(): Int {
        return R.layout.fragment_main_mine
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun init(savedInstanceState: Bundle?) {
        val vm = ViewModelFactory.create(context,MainMineViewModel::class.java)
        addViewModel(vm)
        binding.vm = vm
        ImageLoader.getInstance().with(context).url(url).into(binding.iv)
        binding.iv.setOnClickListener {
            val intent = Intent(context,LargeImageActivity::class.java)
            startActivity(intent,ActivityOptions.makeSceneTransitionAnimation(activity,binding.iv,"image").toBundle())
        }
    }

}