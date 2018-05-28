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
import com.syc.common.utils.DensityUtils;

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
    private boolean leftLayoutVisible;
    private static final int DEFAULT_CENTER_TEXT_COLOR = 0xff333333;
    private static final int DEFAULT_CENTER_TEXT_SIZE = 18;
    private static final int HEIGHT_FOR_TITLE_BAR= 120;
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
            leftLayoutVisible = a.getBoolean(R.styleable.CommonTitleBar_leftVisible, true);
            titleText = a.getString(R.styleable.CommonTitleBar_titleText);
            if (leftLayoutRes != 0) {
                leftLayoutBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), leftLayoutRes, null, false);
            } else if (leftLayoutVisible) {
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
        defaultCenterLayout.setTextSize(TypedValue.COMPLEX_UNIT_SP, DEFAULT_CENTER_TEXT_SIZE);
        defaultCenterLayout.setTextColor(DEFAULT_CENTER_TEXT_COLOR);
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

    private View getStatusBarPlaceHolderLayout() {
        View view = new View(getContext());
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, getStatusBarHeight());
        view.setLayoutParams(params);
        return view;
    }

    private void init(Context context, AttributeSet attrs) {
        removeAllViews();
        getAttrs(context, attrs);
        setBackgroundColor(0xffff8899);
//        setFitsSystemWindows(true);
        setClipToPadding(true);
        setOrientation(VERTICAL);
        addView(getStatusBarPlaceHolderLayout());
        RelativeLayout titleLayout = new RelativeLayout(getContext());
        RelativeLayout.LayoutParams leftLayoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        leftLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        leftLayoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
        if (leftLayoutBinding != null) {
            titleLayout.addView(leftLayoutBinding.getRoot(), leftLayoutParams);
        } else {
            if (leftLayoutVisible) {
                RelativeLayout.LayoutParams defaultLayoutParams = new RelativeLayout.LayoutParams(80,80);
                defaultLayoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
                defaultLayoutParams.leftMargin=10;
                titleLayout.addView(defaultBackLayout,defaultLayoutParams);
            }
        }

        RelativeLayout.LayoutParams centerLayoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        centerLayoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        if (centerLayoutBinding != null) {
            titleLayout.addView(centerLayoutBinding.getRoot(), centerLayoutParams);
        } else {
            titleLayout.addView(defaultCenterLayout, centerLayoutParams);
        }

        RelativeLayout.LayoutParams rightLayoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        rightLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        rightLayoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
        if (rightLayoutBinding != null) {
            titleLayout.addView(rightLayoutBinding.getRoot(), rightLayoutParams);
        }
        LayoutParams titleLayoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, HEIGHT_FOR_TITLE_BAR);
        addView(titleLayout, titleLayoutParams);
    }

    public ViewDataBinding getLeftLayoutBinding() {
        return leftLayoutBinding;
    }

    public void setLeftLayoutBinding(ViewDataBinding leftLayoutBinding) {
        this.leftLayoutBinding = leftLayoutBinding;
        init(null, null);
    }

    public ViewDataBinding getRightLayoutBinding() {
        return rightLayoutBinding;
    }

    public void setRightLayoutBinding(ViewDataBinding rightLayoutBinding) {
        this.rightLayoutBinding = rightLayoutBinding;
        init(null, null);
    }

    public ViewDataBinding getCenterLayoutBinding() {
        return centerLayoutBinding;
    }

    public void setCenterLayoutBinding(ViewDataBinding centerLayoutBinding) {
        this.centerLayoutBinding = centerLayoutBinding;
        init(null, null);
    }

    public String getTitleText() {
        return titleText;
    }

    public void setTitleText(String titleText) {
        this.titleText = titleText;
        if (defaultCenterLayout != null) {
            defaultCenterLayout.setText(titleText);
        }
    }

    public boolean isLeftLayoutVisible() {
        return leftLayoutVisible;
    }

    public void setLeftLayoutVisible(boolean leftLayoutVisible) {
        this.leftLayoutVisible = leftLayoutVisible;
        int visible = leftLayoutVisible ? View.VISIBLE : View.GONE;
        if (defaultBackLayout != null) {
            defaultBackLayout.setVisibility(visible);
        } else if (leftLayoutBinding != null) {
            leftLayoutBinding.getRoot().setVisibility(visible);
        }
    }

    public void setOnBackClickListener(OnClickListener onBackClickListener) {
        if (defaultBackLayout != null) {
            defaultBackLayout.setOnClickListener(onBackClickListener);
        }
    }

    @BindingAdapter("backClick")
    public static void backClick(CommonTitleBar titleBar, OnClickListener onBackClickListener) {
        titleBar.setOnBackClickListener(onBackClickListener);
    }

}
