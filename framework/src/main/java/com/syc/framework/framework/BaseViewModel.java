package com.syc.framework.framework;

import android.app.Activity;
import android.content.Context;
import android.databinding.BaseObservable;
import android.os.Bundle;
import android.widget.Toast;

import com.syc.framework.router.Router;

import java.lang.ref.WeakReference;

/**
 *
 * Created by shiyucheng on 2018/1/9.
 */

public class BaseViewModel extends BaseObservable implements ViewModelLifeCycle {
    private WeakReference<Context> wrContext;

    public BaseViewModel(Context context) {
        this.wrContext = new WeakReference<Context>(context);
    }


    protected Context getContext() {
        return wrContext.get();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Router.getInstance().registerReceiver(this);
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
        Router.getInstance().unRegisterReceiver(this);
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

    protected void Toast(String message){
        if (wrContext.get() != null) {
            Toast.makeText(wrContext.get(), message, Toast.LENGTH_SHORT).show();
        }
    }
}
