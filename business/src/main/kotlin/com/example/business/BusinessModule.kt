package com.example.business

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.example.business.view.MainBusinessFragment
import com.syc.common.utils.LogUtil
import com.syc.common.utils.ToastUtils
import com.syc.framework.router.Router

/**
 * Created by Administrator on 2018\5\14 0014.
 */
object BusinessModule {
    fun install() {
        Router.getInstance().register("/business/BusinessMainActivityOpen", { pipe ->
            val intent = Intent("com.syc.business.open")
            intent.addCategory("com.syc.category.business")
            val uri = Uri.parse("http://www.syc.com:8080/android/sycFramework")
            intent.data = uri
            val componentName = intent.resolveActivity(pipe.context.packageManager)
            if (componentName != null) {
                LogUtil.d("收到参数", pipe.params.getString("age", "11"))
                pipe.context.startActivity(intent)
                val bundle = Bundle();
                bundle.putString("name", "返回数据")
                pipe.onSucceed(bundle)
            } else {
                ToastUtils.showToast(pipe.context, "没有找到匹配的Activity")
            }
        })
        Router.getInstance().register("/business/getMainBusinessFragment", { pipe ->
            pipe.onSucceed(MainBusinessFragment())
        })

    }
}