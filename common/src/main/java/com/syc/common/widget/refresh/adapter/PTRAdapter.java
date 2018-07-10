package com.syc.common.widget.refresh.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by shiyucheng on 2018/7/7.
 */

public abstract class PTRAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {
    private List<T> list;
    private int layoutRes;

    public PTRAdapter(List<T> list, int resourceID) {
        this.list = list;
        this.layoutRes = resourceID;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), getItemLayout(viewType), parent, false);
        return new BaseViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder,  int position) {
        final int currentPosition = position;
        final T item = list.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick(item, currentPosition);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return onItemLongClick(item, currentPosition);
            }
        });
        onBindViewHolderListener(holder, item, position);
    }

    @Override
    public int getItemViewType(int position) {
        return getItemType(list.get(position), position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  int getItemType(T item, int position) {
        return super.getItemViewType(position);
    }

    public int getItemLayout(int viewType) {
        return layoutRes;
    }

    public abstract  void onBindViewHolderListener(BaseViewHolder viewHolder, T item, int position);

    public  void onItemClick(T item, int position) {

    }

    public boolean onItemLongClick(T item, int position) {
        return false;
    }
}
