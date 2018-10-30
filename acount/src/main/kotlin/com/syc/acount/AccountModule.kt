package com.syc.acount

import com.syc.acount.view.MainFirstFragment
import com.syc.acount.view.MainMineFragment
import com.syc.framework.router.Router

/**
 * Created by shiyucheng on 2018\5\14 0014.
 */
object AccountModule {
    fun install() {
        Router.getInstance().register("/account/getMainMineFragment") { pipe ->
            pipe.onSucceed(MainMineFragment())
        }

        Router.getInstance().register("/account/getMainFirstFragment") { pipe ->
            pipe.onSucceed(MainFirstFragment())
        }
    }
}