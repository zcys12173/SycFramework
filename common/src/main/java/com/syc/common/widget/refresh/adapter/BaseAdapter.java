package com.syc.common.widget.refresh.adapter;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by shiyucheng on 2018/7/12.
 */

public abstract class BaseAdapter extends RecyclerView.Adapter {
    private List<Object> list;

    public abstract void notifyChanged();

    public Object getItem(int position){
        return list.get(position);
    }

    public BaseAdapter() {
        list = new ArrayList<>();
    }

    public int getSize() {
        return list.size();
    }

    public void addItem(Object item) {
        list.add(item);
        notifyChanged();
    }

    public void addAll(Collection<?> items) {
        list.addAll(items);
        notifyChanged();
    }


    public void addItem(int index, Object item) {
        list.add(index, item);
        notifyChanged();
    }

    public void removeItem(Object item) {
        list.remove(item);
        notifyChanged();
    }

    public void removeAll(Collection<?> items){
        list.removeAll(items);
        notifyChanged();
    }

    public void setItem(int index, Object item) {
        list.set(index, item);
        notifyChanged();
    }

    public void clearAll() {
        list.clear();
        notifyChanged();
    }

}
