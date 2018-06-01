package com.syc.framework.framework;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.os.Bundle;
import android.widget.Toast;

import com.syc.framework.ActivityLauncher;
import com.syc.framework.router.Router;

import java.lang.ref.WeakReference;

/**
 * Created by shiyucheng on 2018/1/9.
 */

public class BaseViewModel extends BaseObservable implements ViewModelLifeCycle {
    private WeakReference<Context> wrContext;
    private ActivityLauncher activityLauncher;

    public BaseViewModel() {

    }

    public void setContext(Context context) {
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    protected ActivityLauncher getActivityLauncher(){
        return activityLauncher;
    }

    protected void finish() {
        if (wrContext.get() != null) {
            ((Activity) wrContext.get()).finish();
        }
    }

    protected void Toast(String message) {
        if (wrContext.get() != null) {
            Toast.makeText(wrContext.get(), message, Toast.LENGTH_SHORT).show();
        }
    }

    protected void startActivity(Intent intent) {
        if (activityLauncher != null) {
            activityLauncher.startActivity(intent);
        }
    }

    protected void startActivityForResult(Intent intent, int requestCode) {
        if (activityLauncher != null) {
            activityLauncher.startActivityForResult(intent, requestCode);
        }
    }

    protected void setResult(int resultCode, Intent intent) {
        if (activityLauncher != null) {
            activityLauncher.setResult(resultCode, intent);
        }
    }

    protected void setResult(int resultCode) {
        if (activityLauncher != null) {
            activityLauncher.setResult(resultCode);
        }
    }


    protected Intent getIntent() {
        if (wrContext.get() != null) {
            return ((Activity) wrContext.get()).getIntent();
        }
        return null;
    }

    void setActivityLauncher(ActivityLauncher activityLauncher) {
        this.activityLauncher = activityLauncher;
    }
}
