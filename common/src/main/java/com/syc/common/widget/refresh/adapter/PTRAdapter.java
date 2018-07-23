package com.syc.common.widget.refresh.adapter;


import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * 适配起
 * Created by shiyucheng on 2018/7/7.
 */

public abstract class PTRAdapter extends BaseAdapter {
    private RecycleItemViewModeFactory itemViewModeFactory;
    private List<Class<?>> vmTypes;

    public PTRAdapter(RecycleItemViewModeFactory itemViewModeFactory) {
        this.itemViewModeFactory = itemViewModeFactory;
        vmTypes = new ArrayList<>();
    }
    @Override
    public void notifyChanged() {
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        Object item = getItem(position);
        Class<?> type = itemViewModeFactory.getViewModelType(item);
        int index = vmTypes.indexOf(type);
        if (index == -1) {
            vmTypes.add(type);
            index = vmTypes.indexOf(type);
        }
        return index;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecycleItemViewModel vm = itemViewModeFactory.getItemViewModel(vmTypes.get(viewType));
        ViewHolder viewHolder = new ViewHolder(itemViewModeFactory.onViewHolderBinding(vm));
        viewHolder.setItemVm(vm);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.getItemVm().OnItemViewChanged(getItem(position));
    }


    @Override
    public int getItemCount() {
        return getSize();
    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        private ViewDataBinding binding;
        private RecycleItemViewModel itemVm;

        public ViewHolder(View itemView) {
            super(itemView);
        }

        public ViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
        }

        public ViewDataBinding getBinding() {
            return binding;
        }

        public void setBinding(ViewDataBinding binding) {
            this.binding = binding;
        }

        public RecycleItemViewModel getItemVm() {
            return itemVm;
        }

        public void setItemVm(RecycleItemViewModel itemVm) {
            this.itemVm = itemVm;
        }
    }


}
