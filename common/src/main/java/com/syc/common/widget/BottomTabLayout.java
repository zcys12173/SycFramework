package com.syc.common.widget;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * 自定义底部TAB控件
 * Created by shiyucheng on 2018\5\22 0022.
 */

public class BottomTabLayout extends LinearLayout implements View.OnClickListener, ViewGroup.OnHierarchyChangeListener {
    private int currentIndex;

    private OnBottomSelectedListener onSelectedListener;

    public BottomTabLayout(@NonNull Context context) {
        super(context);
        init();
    }

    public BottomTabLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    private void init() {
        setOnHierarchyChangeListener(this);
        setOrientation(HORIZONTAL);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    @Override
    public void onClick(View view) {
        int index = (int) view.getTag();
        setCurrentIndex(index);
    }

    /**
     * 获取当前选中按钮
     *
     * @return 按钮的index
     */
    public int getCurrentIndex() {
        return currentIndex;
    }

    /**
     * 设置选中监听
     */
    public void setOnSelectedListener(OnBottomSelectedListener onSelectedListener) {
        this.onSelectedListener = onSelectedListener;
    }

    /**
     * 手动设置选中按钮
     */
    public void setCurrentIndex(int index) {
        if (index == currentIndex) {
            return;
        }
        for (int i = 0; i < getChildCount(); i++) {
            View childView = getChildAt(i);
            setAllChildViewEnable(childView, index == (int) childView.getTag());
        }
        if (onSelectedListener != null) {
            onSelectedListener.onSelected(index,currentIndex);
        }
        currentIndex = index;
    }

    private void setAllChildViewEnable(View view, boolean enable) {
        if (view instanceof ViewGroup) {
            ViewGroup parent = (ViewGroup) view;
            for (int i = 0; i < parent.getChildCount(); i++) {
                if (parent.getChildAt(i) instanceof ViewGroup) {
                    setAllChildViewEnable(parent.getChildAt(i), enable);
                } else {
                    View childView = parent.getChildAt(i);
                    childView.setEnabled(enable);
                }
            }
        } else {
            view.setEnabled(enable);
        }

    }

    @Override
    public void onChildViewAdded(View view, View childView) {
        if (getChildCount() == 1) {
            setAllChildViewEnable(childView, true);
        } else {
            setAllChildViewEnable(childView, false);
        }
        childView.setTag(getChildCount() - 1);
        childView.setOnClickListener(this);
    }

    @Override
    public void onChildViewRemoved(View view, View view1) {

    }


    public interface OnBottomSelectedListener {
        void onSelected(int currentIndex,int lastIndex);
    }

    @BindingAdapter("selectedListener")
    public static void selectedListener(BottomTabLayout view, OnBottomSelectedListener onSelectedListener) {
        view.setOnSelectedListener(onSelectedListener);
    }
}
