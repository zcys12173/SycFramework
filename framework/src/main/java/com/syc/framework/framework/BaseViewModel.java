package com.syc.framework.framework;

import android.app.Activity;
import android.content.Context;
import android.databinding.BaseObservable;

import java.lang.ref.WeakReference;

/**
 * Created by shiyucheng on 2018/1/9.
 */

public class BaseViewModel extends BaseObservable {
    private WeakReference<Context> wrContext;

    public BaseViewModel(Context context) {
        this.wrContext = new WeakReference<Context>(context);
    }


    protected Context getContext() {
        return wrContext.get();
    }

    protected void oncreate() {

    }

    protected void onStop() {

    }

    protected void finish() {
        if (wrContext.get() != null) {
            ((Activity) wrContext.get()).finish();
        }
    }
}
