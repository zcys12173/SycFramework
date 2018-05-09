package com.syc.framework.framework;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
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
        SwipeBackLayout backLayout = getSwipeBackLayout();
        backLayout.setEdgeSize(SwipeBackLayout.EDGE_LEFT);
        viewModelManager = new ViewModelManager();
        initView(savedInstanceState);
        viewModelManager.onCreate(savedInstanceState);
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
