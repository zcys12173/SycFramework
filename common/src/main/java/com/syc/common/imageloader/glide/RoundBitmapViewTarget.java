package com.syc.common.imageloader.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;

import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.syc.common.imageloader.NetImageView;

/**
 * Created by shiyucheng on 2018/9/14.
 */
public class RoundBitmapViewTarget extends BitmapImageViewTarget {
    private NetImageView imageView;
    private Context context;
    private int radius;


    public RoundBitmapViewTarget(NetImageView imageView, Context context, int radius) {
        super(imageView);
        this.imageView = imageView;
        this.context = context;
        this.radius = radius;
    }

    @Override
    protected void setResource(Bitmap resource) {
        RoundedBitmapDrawable bitmapDrawable = RoundedBitmapDrawableFactory.create(context.getResources(), resource);
        /**
         *   设置图片的shape为圆形.
         *
         *   若是需要制定圆角的度数，则调用setCornerRadius（）。
         */
        bitmapDrawable.setCircular(true);
        bitmapDrawable.setCornerRadius(radius);
        imageView.setImageDrawable(bitmapDrawable);
    }

}
