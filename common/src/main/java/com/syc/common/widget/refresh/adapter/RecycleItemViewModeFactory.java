package com.syc.common.widget.refresh.adapter;

import android.content.Context;
import android.databinding.ViewDataBinding;

/**
 * Created by shiyucheng on 2018/7/17.
 */

public abstract class RecycleItemViewModeFactory<ItemType> {
    private Context context;
    public abstract Class<? extends RecycleItemViewModel> getViewModelType(ItemType itemType);
    public abstract ViewDataBinding onViewHolderBinding(RecycleItemViewModel<ItemType> vm);

    public RecycleItemViewModel<ItemType> getItemViewModel(Class<? extends RecycleItemViewModel> type){
        RecycleItemViewModel<ItemType> vm =  null;
        try { vm = type.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return vm;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
