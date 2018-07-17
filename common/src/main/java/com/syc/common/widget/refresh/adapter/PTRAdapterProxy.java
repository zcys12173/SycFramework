package com.syc.common.widget.refresh.adapter;

import android.databinding.ViewDataBinding;

/**
 * Created by shiyucheng on 2018/7/11.
 */

public abstract class PTRAdapterProxy<T>{
    public abstract RecycleItemViewModel getItemViewModeType(T item);

    public abstract ViewDataBinding getViewDataBinding(RecycleItemViewModel itemViewModelType);
}
