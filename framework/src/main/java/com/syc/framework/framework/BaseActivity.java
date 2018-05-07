package com.syc.framework.framework;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * Created by shiyucheng on 2018/1/9.
 */

public abstract class BaseActivity<T extends ViewDataBinding> extends SwipeBackActivity implements LifeCycle<T>{
    private List<BaseViewModel> models = new ArrayList<>();
    protected T binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,getContent());
        SwipeBackLayout backLayout = getSwipeBackLayout();
        backLayout.setEdgeSize(SwipeBackLayout.EDGE_LEFT);
        initView(savedInstanceState);
    }
}
