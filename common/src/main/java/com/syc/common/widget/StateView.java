package com.syc.common.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.databinding.BindingAdapter;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.syc.common.R;

/**
 * 缺省页面
 * Created by shiyucheng on 2018/7/6.
 */

public class StateView extends RelativeLayout {
    public static final int STATE_NORMAL = 0;//不显示
    public static final int STATE_LOADING = 1;//加载中
    public static final int STATE_ERROR = 2;//网络错误
    public static final int STATE_EMPTY = 3;//数据为空
    private ImageView loadingIv;
    private TextView contextTv;
    private Button retryBtn;
    private int state = STATE_NORMAL;
    public StateView(Context context) {
        super(context,null);
        init(context, null);
    }

    public StateView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs){
        setBackgroundColor(0xffffffff);
        inflate(getContext(), R.layout.layout_state_view,this);
        loadingIv = findViewById(R.id.iv_loading);
        contextTv = findViewById(R.id.tv_content);
        retryBtn = findViewById(R.id.btn_retry);
        if(attrs != null){
            TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.StateView);
            String content = a.getString(R.styleable.StateView_contextText);
            Drawable drawable = a.getDrawable(R.styleable.StateView_loadingImageSrc);
            state = a.getInt(R.styleable.StateView_state,STATE_NORMAL);
            if (drawable != null) {
                setLoadingImageDrawable(drawable);
            }else{
                setLoadingImageResource(R.drawable.state_view_default_anim);
            }
            setContextText(content);
            a.recycle();
        }
        refreshView();
    }

    /**
     * 根据state的值来处理view的显示和内容
     */
    private void refreshView(){
        switch (state){
            case STATE_NORMAL:
               setNormalView();
                break;
            case STATE_LOADING:
                setLoadingView();
                break;
            case STATE_ERROR:
                setErrorView();
                break;
            case STATE_EMPTY:
                setEmptyView();
                break;
        }
    }

    private void setNormalView(){
        setVisibility(View.GONE);
    }

    private void setLoadingView(){
        setVisibility(View.VISIBLE);
        loadingIv.setVisibility(View.VISIBLE);
        contextTv.setVisibility(View.GONE);
        retryBtn.setVisibility(View.GONE);
    }
    private void setErrorView(){
        setVisibility(View.VISIBLE);
        loadingIv.setVisibility(View.GONE);
        contextTv.setVisibility(View.VISIBLE);
        retryBtn.setVisibility(View.VISIBLE);
        contextTv.setText("error");
    }
    private void setEmptyView(){
        setVisibility(View.VISIBLE);
        loadingIv.setVisibility(View.GONE);
        contextTv.setVisibility(View.VISIBLE);
        retryBtn.setVisibility(View.VISIBLE);
        contextTv.setText("empty");
    }

    public void setState(int state){
        this.state = state;
        refreshView();
    }

    public void setOnRetryClickListener(OnClickListener listener){
        if (retryBtn !=  null) {
            retryBtn.setOnClickListener(listener);
        }
    }

    public void setContextText(String context){
        if (contextTv != null) {
            contextTv.setText(context);
        }
    }

    public void setContextText(int resourceId){
        if (contextTv != null) {
            contextTv.setText(resourceId);
        }
    }
    public void setLoadingImageDrawable(Drawable drawable){
        if(drawable == null){
            return;
        }
        if (loadingIv != null) {
            stopAnimation();
            loadingIv.setImageDrawable(drawable);
            startAnimation();
        }
    }

    public void setLoadingImageResource(int resourceId){
        if (loadingIv != null) {
            stopAnimation();
            loadingIv.setImageResource(resourceId);
            startAnimation();
        }
    }

    private void startAnimation(){
        if(loadingIv != null && loadingIv.getDrawable() != null && loadingIv.getDrawable() instanceof AnimationDrawable){
            AnimationDrawable anim = (AnimationDrawable) loadingIv.getDrawable();
            anim.start();
        }
    }


    private void stopAnimation(){
        if(loadingIv != null && loadingIv.getDrawable() != null && loadingIv.getDrawable() instanceof AnimationDrawable){
            AnimationDrawable anim = (AnimationDrawable) loadingIv.getDrawable();
            anim.stop();
        }
    }

    @BindingAdapter("retryClick")
    public static void bindOnRetryClickListener(StateView view,OnClickListener listener){
        view.setOnRetryClickListener(listener);
    }
}
