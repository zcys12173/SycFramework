package com.syc.framework.framework;

import android.app.Activity;
import android.content.Context;
import android.databinding.BaseObservable;
import android.os.Bundle;

import java.lang.ref.WeakReference;

/**
 * Created by shiyucheng on 2018/1/9.
 */

public class BaseViewModel extends BaseObservable implements ViewModelLiftCycle {
    private WeakReference<Context> wrContext;

    public BaseViewModel(Context context) {
        this.wrContext = new WeakReference<Context>(context);
    }


    protected Context getContext() {
        return wrContext.get();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onReStart() {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }

    protected void finish() {
        if (wrContext.get() != null) {
            ((Activity) wrContext.get()).finish();
        }
    }
}
