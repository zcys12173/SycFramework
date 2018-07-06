package com.syc.common.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.syc.common.R;

/**
 * Created by shiyucheng on 2018/7/6.
 */

public class StateView extends RelativeLayout {
    public static final int STATE_NORMAL = 0;
    public static final int STATE_LOADING = 1;
    public static final int STATE_ERROR = 2;
    public static final int STATE_EMPTY = 3;
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
            loadingIv.setImageDrawable(drawable);
            contextTv.setText(content);
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
                contextTv.setText("normal");
                break;
            case STATE_LOADING:
                contextTv.setText("loading");
                break;
            case STATE_ERROR:
                contextTv.setText("error");
                break;
            case STATE_EMPTY:
                contextTv.setText("empty");
                break;

        }
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
        loadingIv.setImageDrawable(drawable);
    }

    public void setLoadingImageResource(int resourceId){
        loadingIv.setImageResource(resourceId);
    }
    
    @BindingAdapter("retryClick")
    public static void bindOnRetryClickListener(StateView view,OnClickListener listener){
        view.setOnRetryClickListener(listener);
    }
}
