package com.syc.common.network.download;

import com.syc.common.utils.LogUtil;

/**
 * Created by shiyucheng on 2018/9/7.
 * 下载成功回调
 */
public class SuccessConsumer implements FinishConsumer<DownloadResult> {
    private DownloadManager.DownloadCallback callback;

    SuccessConsumer(DownloadManager.DownloadCallback callback) {
        this.callback = callback;
    }

    @Override
    public void accept(DownloadResult result) {
        LogUtil.d("SuccessConsumer",result.status+"---");
        switch (result.status) {
            case DownloadResult.STATUS_PREPARE:
                if (callback != null) {
                    callback.prepare();
                }
                break;
            case DownloadResult.STATUS_START:
                if (callback != null) {
                    callback.start();
                }
                break;
            case DownloadResult.STATUS_DOWNLOADING:
                if (callback != null) {
                    callback.progress(result.getProgress());
                }
                break;
            case DownloadResult.STATUS_SUCCEED:
                finish();
                if (callback != null) {
                    callback.success(result.path);
                }
                break;
            case DownloadResult.STATUS_FAILED:
                finish();
                if (callback != null) {
                    callback.failed(new DownloadManager.DownLoadException("download failed"));
                }
                break;
        }
    }

    @Override
    public void finish() {

    }
}
