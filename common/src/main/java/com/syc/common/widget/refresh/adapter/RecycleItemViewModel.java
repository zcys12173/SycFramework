package com.syc.common.widget.refresh.adapter;

/**
 * Created by shiyucheng on 2018/7/11.
 */

public abstract class RecycleItemViewModel<ItemType> {
    public abstract void OnItemViewChanged(ItemType item);
}
