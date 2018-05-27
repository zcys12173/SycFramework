package com.syc.common.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.syc.common.R;

/**
 * Created by shiyucheng on 2018\5\27 0027.
 */

public class CommonTitleBar extends LinearLayout {
    private ViewDataBinding leftLayoutBinding;
    private ViewDataBinding rightLayoutBinding;
    private ViewDataBinding centerLayoutBinding;
    private ImageView defaultBackLayout;
    private TextView defaultCenterLayout;
    private String titleText;
    private static final int defaultCenterTextColor = 0xff333333;
    private static final int defaultCenterTextSize = 18;

    public CommonTitleBar(Context context) {
        super(context, null);
        init(context, null);
    }


    public CommonTitleBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void getAttrs(Context context, AttributeSet attrs) {
        if (attrs != null && context != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CommonTitleBar);
            int leftLayoutRes = a.getResourceId(R.styleable.CommonTitleBar_leftLayout, 0);
            int rightLayoutRes = a.getResourceId(R.styleable.CommonTitleBar_rightLayout, 0);
            int centerLayoutRes = a.getResourceId(R.styleable.CommonTitleBar_centerLayout, 0);
            titleText = a.getString(R.styleable.CommonTitleBar_titleText);
            if (leftLayoutRes != 0) {
                leftLayoutBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), leftLayoutRes, null, false);
            } else {
                initDefaultBackLayout();
            }
            if (centerLayoutRes != 0) {
                centerLayoutBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), centerLayoutRes, null, false);
            } else {
                initDefaultCenterLayout();
            }
            if (rightLayoutRes != 0) {
                rightLayoutBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), rightLayoutRes, null, false);
            }
            a.recycle();
        }
    }

    private void initDefaultBackLayout() {
        defaultBackLayout = new ImageView(getContext());
        defaultBackLayout.setScaleType(ImageView.ScaleType.CENTER);
        defaultBackLayout.setImageResource(R.drawable.title_bar_back_icon_selector);
    }

    private void initDefaultCenterLayout() {
        defaultCenterLayout = new TextView(getContext());
        defaultCenterLayout.setText(titleText);
        defaultCenterLayout.setTextSize(TypedValue.COMPLEX_UNIT_SP, defaultCenterTextSize);
        defaultCenterLayout.setTextColor(defaultCenterTextColor);
    }

    /**
     * 利用反射获取状态栏高度
     *
     * @return
     */
    private int getStatusBarHeight() {
        int result = 0;
        //获取状态栏高度的资源id
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    private View getStatusBarPlaceHolderLayout(){
        View view = new View(getContext());
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,getStatusBarHeight());
        view.setLayoutParams(params);
        return view;
    }

    private void init(Context context, AttributeSet attrs) {
        getAttrs(context, attrs);
        setBackgroundColor(0xffff8899);
//        setFitsSystemWindows(true);
        setClipToPadding(true);
        setOrientation(VERTICAL);
        addView(getStatusBarPlaceHolderLayout());
        RelativeLayout titleLayout = new RelativeLayout(getContext());
        RelativeLayout.LayoutParams leftLayoutParams = new  RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        leftLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        leftLayoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
        if (leftLayoutBinding != null) {
            titleLayout.addView(leftLayoutBinding.getRoot(), leftLayoutParams);
        } else {
            titleLayout.addView(defaultBackLayout);
        }

        RelativeLayout.LayoutParams centerLayoutParams = new  RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        centerLayoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        if (centerLayoutBinding != null) {
            titleLayout.addView(centerLayoutBinding.getRoot(), centerLayoutParams);
        } else {
            titleLayout.addView(defaultCenterLayout, centerLayoutParams);
        }

        RelativeLayout.LayoutParams rightLayoutParams = new  RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        rightLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        rightLayoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
        if (rightLayoutBinding != null) {
            titleLayout.addView(rightLayoutBinding.getRoot(), rightLayoutParams);
        }
        LayoutParams titleLayoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        addView(titleLayout,titleLayoutParams);
    }
}
