package com.syc.acount.view

import android.os.Bundle
import com.syc.acount.R
import com.syc.framework.imageloader.DraweeView
import com.syc.framework.imageloader.ImageLoader
import me.imid.swipebacklayout.lib.app.SwipeBackActivity

/**
 * Created by Administrator on 2018\5\8 0008.
 */
class SwipeBackTestActivity:SwipeBackActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_swipe_back)
        val view:DraweeView = findViewById(R.id.image) as DraweeView
        ImageLoader.getInstance().loadImage(view,"https://img-blog.csdn.net/20161112180545080")
    }
}