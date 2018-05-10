package com.syc.framework.imageloader;

import android.content.Context;
import android.net.Uri;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * 网络图片加载器  基于Fresco
 * Created by shiyucheng on 2018\5\10 0010.
 */

public class ImageLoader {
    private FrescoImageLoader imageLoader;

    private ImageLoader() {
        if (null == imageLoader) {
            imageLoader = new FrescoImageLoader();
        }
    }

    private static ImageLoader instance;

    public static ImageLoader getInstance() {
        if (instance == null) synchronized (ImageLoader.class) {
            if (instance == null) {
                instance = new ImageLoader();
            }
        }
        return instance;
    }

    public static void init(Context context) {
        Fresco.initialize(context, ImageLoaderConfig.getImagePipelineConfig(context));
    }

    public void loadImage(String url, DraweeView draweeView) {
        imageLoader.loadImage(draweeView, Uri.parse(url),0,0,null,null,false);
    }
}
