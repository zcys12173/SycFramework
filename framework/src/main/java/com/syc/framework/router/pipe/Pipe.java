package com.syc.framework.router.pipe;

import android.os.Bundle;

import com.syc.framework.ActivityLauncher;
import com.syc.framework.router.listener.PipeCallBack;

import java.lang.ref.WeakReference;

/**
 * Created by shiyucheng on 2018\5\14 0014.
 */

public class Pipe {
    private WeakReference<ActivityLauncher> wrActivityLauncher;
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

    public ActivityLauncher getActivityLauncher() {
        return wrActivityLauncher.get();
    }

    public void setActivityLauncher(ActivityLauncher activityLauncher) {
        this.wrActivityLauncher = new WeakReference<ActivityLauncher>(activityLauncher);
    }

    public Bundle getParams() {
        return bundle;
    }

    public void setParams(Bundle bundle) {
        this.bundle = bundle;
    }
}
