package com.example.business

import android.content.Intent
import android.os.Bundle
import com.example.business.view.BusinessMainActivity
import com.syc.framework.router.Router
import com.syc.framework.utils.LogUtil

/**
 * Created by Administrator on 2018\5\14 0014.
 */
object BusinessModule {
    fun install() {
        Router.getInstance().register("/business/BusinessMainActivityOpen", { pipe ->
            val intent = Intent(pipe.context, BusinessMainActivity::class.java)
            LogUtil.d("收到参数", pipe.bundle.getString("age", "11"))
            pipe.context.startActivity(intent)
            val bundle = Bundle();
            bundle.putString("name", "返回数据")
            pipe.onSucceed(bundle)
        })
    }
}