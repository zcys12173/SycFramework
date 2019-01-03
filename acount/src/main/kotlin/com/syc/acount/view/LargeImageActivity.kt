package com.syc.acount.view

import android.os.Bundle
import com.syc.acount.R
import com.syc.acount.databinding.ActivityLargeImageBinding
import com.syc.common.imageloader.ImageLoader
import com.syc.framework.framework.BaseActivity

/**
 * Created by shiyucheng on 2018/12/27.
 */
class LargeImageActivity : BaseActivity<ActivityLargeImageBinding>() {
    val url = "http://img5.imgtn.bdimg.com/it/u=415293130,2419074865&fm=27&gp=0.jpg"
    override fun getContent(): Int {
        return R.layout.activity_large_image
    }

    override fun initView(savedInstanceState: Bundle?) {
        ImageLoader.getInstance().with(context).url(url).into(binding.ivLarge)
    }
}