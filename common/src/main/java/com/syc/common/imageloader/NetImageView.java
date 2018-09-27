package com.syc.common.imageloader;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Looper;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import com.syc.common.R;

/**
 * Created by shiyucheng on 2018/9/14.
 */
public class NetImageView extends AppCompatImageView {
    private float radius = 0;
    public NetImageView(Context context) {
        super(context);
    }


    public NetImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.NetImageView);
        radius = a.getFloat(R.styleable.NetImageView_radius,0);
        a.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (getWidth() > radius && getHeight() > radius) {
            Path path = new Path();
            path.moveTo(radius, 0);
            path.lineTo(getWidth() - radius, 0);
            path.quadTo(getWidth(), 0, getWidth(), radius);
            path.lineTo(getWidth(), getHeight() - radius);
            path.quadTo(getWidth(), getHeight(), getWidth() - radius, getHeight());
            path.lineTo(radius, getHeight());
            path.quadTo(0, getHeight(), 0, getHeight() - radius);
            path.lineTo(0, radius);
            path.quadTo(0, 0, radius, 0);
            canvas.clipPath(path);
        }

        super.onDraw(canvas);
    }
}
