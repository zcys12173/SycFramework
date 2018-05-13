package com.syc.framework.imageloader;

import android.content.Context;
import android.net.Uri;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.syc.framework.imageloader.listener.ImageLoaderListener;

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

    public void loadImage(DraweeView draweeView,String url) {
        imageLoader.loadImage(draweeView, url,0,0,null,null,false);
    }


    public void loadImage(DraweeView draweeView,String url,int reqWidth,int reqHeight){
        imageLoader.loadImage(draweeView, url,reqWidth,reqHeight,null,null,false);
    }

    public void loadImage(DraweeView draweeView, String url, int reqWidth, int reqHeight, ImageLoaderListener loaderListener){
        imageLoader.loadImage(draweeView, url,reqWidth,reqHeight,null,loaderListener,false);
    }

    public void loadImage(DraweeView draweeView, String url, int reqWidth, int reqHeight, ImageLoaderListener loaderListener,
                          ImageProcessor processor){
        imageLoader.loadImage(draweeView, url,reqWidth,reqHeight,processor,loaderListener,false);
    }

    public void loadImage(DraweeView draweeView, String url, int reqWidth, int reqHeight, ImageLoaderListener loaderListener,
                          ImageProcessor processor,boolean isSmall){
        imageLoader.loadImage(draweeView, url,reqWidth,reqHeight,processor,loaderListener,isSmall);
    }

}
