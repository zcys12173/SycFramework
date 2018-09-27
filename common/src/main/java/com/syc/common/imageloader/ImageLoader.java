package com.syc.common.imageloader;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.syc.common.imageloader.glide.GlideEngine;

/**
 * Created by shiyucheng on 2018/9/14.
 */
public class ImageLoader {
    private ImageLoaderEngine engine;

    private static ImageLoader instance;

    public static ImageLoader getInstance() {
        if (instance == null) synchronized (ImageLoader.class) {
            if (instance == null) {
                instance = new ImageLoader();
            }
        }
        return instance;
    }

    private ImageLoader() {
        engine = new GlideEngine();
    }

    public void init() {

    }

    public Builder with(Context context) {
        return new Builder(context);
    }

    private void load(NetImageView imageView, Builder builder) {
        engine.load(imageView, builder);
    }

    public final class Builder {
        private String url;
        private Context context;
        private int radius;
        private String thumbnail;
        private Drawable placeHolder;
        private Drawable errorHolder;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder corner(int radius) {
            this.radius = radius;
            return this;
        }

        public Builder thumbnail(String url) {
            this.thumbnail = thumbnail;
            return this;
        }

        public Builder placeHolder(int resourceId) {
            this.placeHolder = context.getResources().getDrawable(resourceId);
            return this;
        }

        public Builder placeHolder(Drawable drawable) {
            this.placeHolder = drawable;
            return this;
        }

        public Builder errorHolder(int resourceId) {
            this.errorHolder = context.getResources().getDrawable(resourceId);
            return this;
        }

        public Builder errorHolder(Drawable drawable) {
            this.errorHolder = drawable;
            return this;
        }

//        public void into(ViewTarget target) {
//
//        }

        public void into(NetImageView imageView) {
            load(imageView, this);
        }

        public String getUrl() {
            return url;
        }

        public Context getContext() {
            return context;
        }

        public int getRadius() {
            return radius;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public Drawable getPlaceHolder() {
            return placeHolder;
        }

        public Drawable getErrorHolder() {
            return errorHolder;
        }
    }
}
