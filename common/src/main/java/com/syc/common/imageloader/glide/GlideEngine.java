package com.syc.common.imageloader.glide;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.text.TextUtils;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.syc.common.imageloader.ImageLoader;
import com.syc.common.imageloader.ImageLoaderEngine;
import com.syc.common.imageloader.NetImageView;

/**
 * Created by shiyucheng on 2018/9/14.
 */
public class GlideEngine implements ImageLoaderEngine {
    @SuppressLint("CheckResult")
    @Override
    public void load(NetImageView imageView, ImageLoader.Builder builder) {
        RequestOptions options = new RequestOptions();

        //处理展位图
        if (builder.getPlaceHolder() != null) {
            options.placeholder(builder.getPlaceHolder());
        }
        //处理加载失败展位图
        if (builder.getErrorHolder() != null) {
            options.error(builder.getErrorHolder());
        }
        RequestBuilder<Bitmap> requestBuilder = Glide.with(builder.getContext()).asBitmap().load(builder.getUrl());
        //处理缩略图
        if (TextUtils.isEmpty(builder.getThumbnail())) {
            requestBuilder.thumbnail(Glide.with(builder.getContext()).asBitmap().load(builder.getUrl()));
        }
        requestBuilder.apply(options);
        //处理圆角
        if (builder.getRadius() != 0) {
            requestBuilder.into(new RoundBitmapViewTarget(imageView,builder.getContext(),builder.getRadius()));
        }else{
            requestBuilder.into(imageView);
        }
    }
}
