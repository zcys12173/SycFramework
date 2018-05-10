package com.syc.framework.imageloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.text.TextUtils;

import com.facebook.common.executors.UiThreadImmediateExecutorService;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.util.UriUtil;
import com.facebook.datasource.BaseDataSubscriber;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.animated.base.AnimatedImageResult;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.common.RotationOptions;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.image.CloseableAnimatedImage;
import com.facebook.imagepipeline.image.CloseableBitmap;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.request.BasePostprocessor;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.syc.framework.utils.LogUtil;

import javax.annotation.Nullable;

/**
 * Created by Administrator on 2018\5\10 0010.
 */

public class FrescoImageLoader {


    public static void loadImage(SimpleDraweeView simpleDraweeView,
                                 Uri uri,
                                 final int reqWidth,
                                 final int reqHeight,
                                 BasePostprocessor postprocessor,
                                 ControllerListener<ImageInfo> controllerListener,
                                 boolean isSmall) {

        ImageRequestBuilder imageRequestBuilder = ImageRequestBuilder.newBuilderWithSource(uri);
        imageRequestBuilder.setRotationOptions(RotationOptions.autoRotate());

        // 不支持图片渐进式加载，理由：https://github.com/facebook/fresco/issues/1204
        imageRequestBuilder.setProgressiveRenderingEnabled(false);

        if (isSmall) {
            imageRequestBuilder.setCacheChoice(ImageRequest.CacheChoice.SMALL);
        }

        if (reqWidth > 0 && reqHeight > 0) {
            imageRequestBuilder.setResizeOptions(new ResizeOptions(reqWidth, reqHeight));
        }

        if (UriUtil.isLocalFileUri(uri)) {
            imageRequestBuilder.setLocalThumbnailPreviewsEnabled(true);
        }

        if (postprocessor != null) {
            imageRequestBuilder.setPostprocessor(postprocessor);
        }

        ImageRequest imageRequest = imageRequestBuilder.build();

        PipelineDraweeControllerBuilder draweeControllerBuilder = Fresco.newDraweeControllerBuilder();
        draweeControllerBuilder.setOldController(simpleDraweeView.getController());
        draweeControllerBuilder.setImageRequest(imageRequest);

        if (controllerListener != null) {
            draweeControllerBuilder.setControllerListener(controllerListener);
        }

        draweeControllerBuilder.setTapToRetryEnabled(true); // 开启重试功能
        draweeControllerBuilder.setAutoPlayAnimations(true); // 自动播放gif动画
        DraweeController draweeController = draweeControllerBuilder.setControllerListener(new ControllerListener<ImageInfo>() {
            @Override
            public void onSubmit(String id, Object callerContext) {
                LogUtil.d("FrescoImageLoader","onSubmit");
            }

            @Override
            public void onFinalImageSet(String id, @Nullable ImageInfo imageInfo, @Nullable Animatable animatable) {
                LogUtil.d("FrescoImageLoader","onFinalImageSet");
            }

            @Override
            public void onIntermediateImageSet(String id, @Nullable ImageInfo imageInfo) {
                LogUtil.d("FrescoImageLoader","onIntermediateImageSet");
            }

            @Override
            public void onIntermediateImageFailed(String id, Throwable throwable) {
                LogUtil.d("FrescoImageLoader","onIntermediateImageFailed");
            }

            @Override
            public void onFailure(String id, Throwable throwable) {
                LogUtil.d("FrescoImageLoader","onFailure");
            }

            @Override
            public void onRelease(String id) {
                LogUtil.d("FrescoImageLoader","onRelease");
            }
        }).build();
        simpleDraweeView.setController(draweeController);
    }
    /**
     * 从本地文件或网络获取Bitmap
     */
    public static void loadImage(final Context context,
                                 String url,
                                 final int reqWidth,
                                 final int reqHeight,
                                 final LoadImageListener<Bitmap> loadImageResult) {
        if (TextUtils.isEmpty(url)) {
            return;
        }

        Uri uri = Uri.parse(url);
        if (!UriUtil.isNetworkUri(uri)) {
            uri = new Uri.Builder()
                    .scheme(UriUtil.LOCAL_FILE_SCHEME)
                    .path(url)
                    .build();
        }

        ImagePipeline imagePipeline = Fresco.getImagePipeline();

        ImageRequestBuilder imageRequestBuilder = ImageRequestBuilder.newBuilderWithSource(uri);
        if (reqWidth > 0 && reqHeight > 0) {
            imageRequestBuilder.setResizeOptions(new ResizeOptions(reqWidth, reqHeight));
        }
        ImageRequest imageRequest = imageRequestBuilder.build();

        // 获取已解码的图片，返回的是Bitmap
        DataSource<CloseableReference<CloseableImage>> dataSource = imagePipeline.fetchDecodedImage(imageRequest, context);
        dataSource.subscribe(new BaseDataSubscriber<CloseableReference<CloseableImage>>() {
            @Override
            public void onNewResultImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
                if (!dataSource.isFinished()) {
                    return;
                }

                CloseableReference<CloseableImage> imageReference = dataSource.getResult();
                if (imageReference != null) {
                    final CloseableReference<CloseableImage> closeableReference = imageReference.clone();
                    try {
                        CloseableImage closeableImage = closeableReference.get();
                        if (closeableImage instanceof CloseableAnimatedImage) {
                            AnimatedImageResult animatedImageResult = ((CloseableAnimatedImage) closeableImage).getImageResult();
                            if (animatedImageResult != null && animatedImageResult.getImage() != null) {
                                int imageWidth = animatedImageResult.getImage().getWidth();
                                int imageHeight = animatedImageResult.getImage().getHeight();

                                Bitmap.Config bitmapConfig = Bitmap.Config.ARGB_8888;
                                Bitmap bitmap = Bitmap.createBitmap(imageWidth, imageHeight, bitmapConfig);
                                animatedImageResult.getImage().getFrame(0).renderFrame(imageWidth, imageHeight, bitmap);
                                if (loadImageResult != null) {
                                    loadImageResult.onFinish(bitmap);
                                }
                            }
                        } else if (closeableImage instanceof CloseableBitmap) {
                            CloseableBitmap closeableBitmap = (CloseableBitmap) closeableImage;
                            Bitmap bitmap = closeableBitmap.getUnderlyingBitmap();
                            if (bitmap != null && !bitmap.isRecycled()) {
                                // https://github.com/facebook/fresco/issues/648
                                final Bitmap tempBitmap = bitmap.copy(bitmap.getConfig(), false);
                                if (loadImageResult != null) {
                                    loadImageResult.onFinish(tempBitmap);
                                }
                            }
                        }
                    } finally {
                        imageReference.close();
                        closeableReference.close();
                    }
                }
            }

            @Override
            public void onFailureImpl(DataSource dataSource) {
                if (loadImageResult != null) {
                    loadImageResult.onFinish(null);
                }

                Throwable throwable = dataSource.getFailureCause();
                if (throwable != null) {
                    LogUtil.e("ImageLoader", "onFailureImpl = " + throwable.toString());
                }
            }
        }, UiThreadImmediateExecutorService.getInstance());
    }
}
