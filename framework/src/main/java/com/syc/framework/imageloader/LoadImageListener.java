package com.syc.framework.imageloader;

/**
 * Created by Administrator on 2018\5\10 0010.
 */

public interface LoadImageListener<T> {
    void onFinish(T result);
}