package com.syc.framework.router.pipe;

import android.content.Context;
import android.os.Bundle;

import com.syc.framework.router.listener.PipeCallBack;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by shiyucheng on 2018\5\14 0014.
 */

public class Pipe {
    private WeakReference<Context> wrContext;
    private PipeCallBack callBack;
    private Bundle bundle;

    public void onSucceed(Object obj) {
        if (this.callBack != null) {
            callBack.callBack(obj);
        }
    }

    public void onSucceed() {
        if (this.callBack != null) {
            callBack.callBack(null);
        }
    }

    public PipeCallBack getCallBack() {
        return callBack;
    }

    public void setCallBack(PipeCallBack callBack) {
        this.callBack = callBack;
    }

    public Context getContext() {
        return wrContext.get();
    }

    public void setContext(Context context) {
        this.wrContext = new WeakReference<Context>(context);
    }

    public Bundle getParams() {
        return bundle;
    }

    public void setParams(Bundle bundle) {
        this.bundle = bundle;
    }
}
