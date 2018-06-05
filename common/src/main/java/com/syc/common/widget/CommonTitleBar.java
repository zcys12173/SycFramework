package com.syc.common.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.Nullable;
import android.text.TextUtils;
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
    public static final int LAYOUT_TYPE_TEXT = 0;
    public static final int LAYOUT_TYPE_IMAGE = 1;
    public static final int LAYOUT_TYPE_CUSTOM = 2;
    public static final int LAYOUT_TYPE_EMPTY = 3;
    private ViewDataBinding leftLayoutBinding;
    private ViewDataBinding rightLayoutBinding;
    private ViewDataBinding centerLayoutBinding;
    private View leftLayout;
    private View centerLayout;
    private View rightLayout;
    private int leftLayoutType;
    private int rightLayoutType;
    private int centerLayoutType;
    private static final int DEFAULT_CENTER_TEXT_COLOR = 0xff333333;
    private static final int DEFAULT_CENTER_TEXT_SIZE = 18;
    private static final int HEIGHT_FOR_TITLE_BAR = 120;

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

            //处理左侧布局
            int leftLayoutRes = a.getResourceId(R.styleable.CommonTitleBar_leftLayout, 0);
            int leftImageRes = a.getResourceId(R.styleable.CommonTitleBar_leftImageSrc, 0);
            String leftText = a.getString(R.styleable.CommonTitleBar_leftText);
            if (leftLayoutRes != 0) {
                leftLayoutType = LAYOUT_TYPE_CUSTOM;
                leftLayoutBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), leftLayoutRes, null, false);
                leftLayout = leftLayoutBinding.getRoot();
            } else if (leftImageRes != 0) {
                leftLayoutType = LAYOUT_TYPE_IMAGE;
                leftLayout = initImageLayout(leftImageRes);
            } else if (!TextUtils.isEmpty(leftText)) {
                leftLayoutType = LAYOUT_TYPE_TEXT;
                leftLayout = initTextLayout(leftText);
            } else {
                leftLayoutType = LAYOUT_TYPE_EMPTY;
            }

            //处理中间布局
            int centerLayoutRes = a.getResourceId(R.styleable.CommonTitleBar_centerLayout, 0);
            int centerImageRes = a.getResourceId(R.styleable.CommonTitleBar_centerImageSrc, 0);
            String centerText = a.getString(R.styleable.CommonTitleBar_centerText);
            if (centerLayoutRes != 0) {
                centerLayoutType = LAYOUT_TYPE_CUSTOM;
                centerLayoutBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), centerLayoutRes, null, false);
                centerLayout = centerLayoutBinding.getRoot();
            } else if (centerImageRes != 0) {
                centerLayoutType = LAYOUT_TYPE_IMAGE;
                centerLayout = initImageLayout(centerImageRes);
            } else if (!TextUtils.isEmpty(centerText)) {
                centerLayoutType = LAYOUT_TYPE_TEXT;
                centerLayout = initTextLayout(centerText);
            } else {
                centerLayoutType = LAYOUT_TYPE_EMPTY;
            }

            //处理右侧布局
            int rightLayoutRes = a.getResourceId(R.styleable.CommonTitleBar_rightLayout, 0);
            int rightImageRes = a.getResourceId(R.styleable.CommonTitleBar_rightImageSrc, 0);
            String rightText = a.getString(R.styleable.CommonTitleBar_rightText);
            if (rightLayoutRes != 0) {
                rightLayoutType = LAYOUT_TYPE_CUSTOM;
                rightLayoutBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), rightLayoutRes, null, false);
                rightLayout = rightLayoutBinding.getRoot();
            } else if (rightImageRes != 0) {
                rightLayoutType = LAYOUT_TYPE_IMAGE;
                rightLayout = initImageLayout(rightImageRes);
            } else if (!TextUtils.isEmpty(rightText)) {
                rightLayoutType = LAYOUT_TYPE_TEXT;
                rightLayout = initTextLayout(rightText);
            } else {
                rightLayoutType = LAYOUT_TYPE_EMPTY;
            }
            a.recycle();
        }
    }

    private View initImageLayout(int resourceId) {
        ImageView iv = new ImageView(getContext());
        iv.setScaleType(ImageView.ScaleType.CENTER);
        iv.setImageResource(resourceId);
        return iv;
    }

    private View initTextLayout(String text) {
        TextView tv = new TextView(getContext());
        tv.setText(text);
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, DEFAULT_CENTER_TEXT_SIZE);
        tv.setTextColor(DEFAULT_CENTER_TEXT_COLOR);
        return tv;
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

        if (leftLayoutType != LAYOUT_TYPE_EMPTY) {
            RelativeLayout.LayoutParams defaultLayoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            defaultLayoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
            defaultLayoutParams.leftMargin = 10;
            titleLayout.addView(leftLayout, defaultLayoutParams);
        }
        if (centerLayoutType != LAYOUT_TYPE_EMPTY) {
            RelativeLayout.LayoutParams centerLayoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            centerLayoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
            titleLayout.addView(centerLayout, centerLayoutParams);
        }

        if (rightLayoutType != LAYOUT_TYPE_EMPTY) {
            RelativeLayout.LayoutParams rightLayoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            rightLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            rightLayoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
            titleLayout.addView(rightLayout, rightLayoutParams);
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

    public View getLeftLayout() {
        return leftLayout;
    }

    public View getCenterLayout() {

        return centerLayout;
    }

    public View getRightLayout() {

        return rightLayout;
    }


    public void setOnBackClickListener(OnClickListener onBackClickListener) {

    }

    @BindingAdapter("backClick")
    public static void backClick(CommonTitleBar titleBar, OnClickListener onBackClickListener) {
        titleBar.setOnBackClickListener(onBackClickListener);
    }

}
