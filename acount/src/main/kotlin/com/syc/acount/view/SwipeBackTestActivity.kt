package com.syc.acount.view

import android.os.Bundle
import com.syc.acount.R
import me.imid.swipebacklayout.lib.app.SwipeBackActivity

/**
 * Created by Administrator on 2018\5\8 0008.
 */
class SwipeBackTestActivity:SwipeBackActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_swipe_back)
    }
}