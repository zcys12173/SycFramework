package com.syc.common.widget.refresh.adapter;


import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * 适配起
 * Created by shiyucheng on 2018/7/7.
 */

public class PTRAdapter extends BaseAdapter{

    @Override
    public void notifyChanged() {

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
    }


    @Override
    public int getItemCount() {
        return getSize();
    }

    private class ViewHolder extends RecyclerView.ViewHolder{
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
