package com.syc.common.widget.refresh.adapter;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by shiyucheng on 2018/7/7.
 */

public class BaseViewHolder extends RecyclerView.ViewHolder {
    public ViewDataBinding binding;
    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public BaseViewHolder(ViewDataBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }
}
