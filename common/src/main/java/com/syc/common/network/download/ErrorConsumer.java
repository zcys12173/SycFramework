package com.syc.common.network.download;

/**
 * Created by shiyucheng on 2018/9/7.
 * 下载失败回调
 */
public class ErrorConsumer implements FinishConsumer<Throwable> {
    private DownloadManager.DownloadCallback callback;

    ErrorConsumer(DownloadManager.DownloadCallback callback) {
        this.callback = callback;
    }

    @Override
    public void accept(Throwable t) {
        finish();
        if (callback != null) {
            callback.failed(new DownloadManager.DownLoadException(t));
        }
    }

    @Override
    public void finish() {

    }
}
