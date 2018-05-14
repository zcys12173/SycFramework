package com.syc.acount

import android.content.Intent
import android.os.Bundle
import com.syc.acount.view.SwipeBackTestActivity
import com.syc.framework.router.Router
import com.syc.framework.utils.LogUtil

/**
 * Created by Administrator on 2018\5\14 0014.
 */
object AcountModule {
    fun install() {
        Router.getInstance().register("/account/SwipeBackTestActivityOpen", { pipe ->
            val intent = Intent(pipe.context, SwipeBackTestActivity::class.java)
            LogUtil.d("收到参数", pipe.bundle.getString("age", "11"))
            pipe.context.startActivity(intent)
            val bundle = Bundle();
            bundle.putString("name", "返回数据")
            pipe.onSucceed(bundle)
        })
    }
}