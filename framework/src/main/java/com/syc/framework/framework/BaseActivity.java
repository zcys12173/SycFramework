package com.syc.framework.framework;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.view.WindowManager;

import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * Created by shiyucheng on 2018/1/9.
 */

public abstract class BaseActivity<T extends ViewDataBinding> extends SwipeBackActivity {
    private ViewModelManager viewModelManager;
    private T binding;

    protected abstract int getContent();

    protected abstract void initView(@Nullable Bundle savedInstanceState);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, getContent());
        initState();
        viewModelManager = new ViewModelManager();
        initView(savedInstanceState);
        viewModelManager.onCreate(savedInstanceState);
    }

    /**
     * 沉浸式状态栏
     */
    private void initState() {
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }
    }


    public void addViewModel(BaseViewModel viewModel) {
        viewModelManager.addViewModel(viewModel);
    }

    protected T getBinding() {
        return binding;
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        viewModelManager.onSaveInstanceState(outState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        viewModelManager.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModelManager.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        viewModelManager.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        viewModelManager.onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        viewModelManager.onReStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewModelManager.onDestroy();
        viewModelManager.release();
    }
}
