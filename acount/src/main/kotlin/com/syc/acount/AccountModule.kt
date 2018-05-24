package com.syc.acount

import android.content.Intent
import android.os.Bundle
import com.syc.acount.view.MainFirstFragment
import com.syc.acount.view.MainMineFragment
import com.syc.acount.view.SwipeBackTestActivity
import com.syc.common.utils.LogUtil
import com.syc.framework.router.Router

/**
 * Created by shiyucheng on 2018\5\14 0014.
 */
object AccountModule {
    fun install() {
        Router.getInstance().register("/account/SwipeBackTestActivityOpen", { pipe ->
            val intent = Intent(pipe.context, SwipeBackTestActivity::class.java)
            LogUtil.d("收到参数", pipe.params.getString("age", "11"))
            pipe.context.startActivity(intent)
            val bundle = Bundle()
            bundle.putString("name", "返回数据")
            pipe.onSucceed(bundle)
        })

        Router.getInstance().register("/account/getMainMineFragment", { pipe ->
            pipe.onSucceed(MainMineFragment())
        })

        Router.getInstance().register("/account/getMainFirstFragment", { pipe ->
            pipe.onSucceed(MainFirstFragment())
        })
    }
}