package com.syc.common.imageloader.listener;

import android.graphics.drawable.Animatable;

import com.facebook.drawee.controller.ControllerListener;

import javax.annotation.Nullable;

/**
 * Created by shiyucheng on 2018/5/13.
 */

public class ImageLoaderListener<T> implements ControllerListener<T> {

    @Override
    public void onSubmit(String id, Object callerContext) {

    }

    @Override
    public void onFinalImageSet(String id, @Nullable T imageInfo, @Nullable Animatable animatable) {

    }

    @Override
    public void onIntermediateImageSet(String id, @Nullable T imageInfo) {

    }

    @Override
    public void onIntermediateImageFailed(String id, Throwable throwable) {

    }

    @Override
    public void onFailure(String id, Throwable throwable) {

    }

    @Override
    public void onRelease(String id) {

    }
}
