package com.syc.common.network.download;

import com.syc.common.utils.LogUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import io.reactivex.Observable;
import io.reactivex.Observer;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by shiyucheng on 2018/9/7.
 */
public class DownloadObservable extends Observable<DownloadResult> {
    private DownloadInfo info;
    private OkHttpClient client;

    DownloadObservable(DownloadInfo info, OkHttpClient client) {
        this.info = info;
        this.client = client;
    }

    @Override
    protected void subscribeActual(Observer<? super DownloadResult> observer) {

        DownloadResult result = new DownloadResult();
        result.path = info.path + File.pathSeparator + info.name;
        result.status = DownloadResult.STATUS_PREPARE;
        LogUtil.d("DownloadObservable", "prepare");
        observer.onNext(result);
        InputStream is = null;
        OutputStream os = null;
        try {
            Request request = new Request.Builder()
                    .addHeader("RANGE", "bytes=" + info.start + "-")
                    .url(info.url)
                    .build();
            Call call = client.newCall(request);
            result.status = DownloadResult.STATUS_START;
            observer.onNext(result);
            LogUtil.d("DownloadObservable", "start");
            Response response = call.execute();
            File file = new File(info.path, info.name);
            ResponseBody responseBody = response.body();
            is = responseBody.byteStream();
            result.totalSize = responseBody.contentLength();
            os = new FileOutputStream(file, true);
            byte[] buffer = new byte[2048];//缓冲数组2kB
            int len;
            result.status = DownloadResult.STATUS_DOWNLOADING;
            observer.onNext(result);
            int currentProgress = 0;
            while ((len = is.read(buffer)) != -1) {
                os.write(buffer, 0, len);
                result.currentSize += len;
                //每下载3%  通知一次
                LogUtil.d("DownloadObservable", "currentSize:" + result.currentSize +
                        "------totalSize:" + result.totalSize + "------totalProgress:"
                        + result.getProgress() + "------currentProgress:" + currentProgress);
                if ((result.getProgress() - currentProgress) > 3) {
                    LogUtil.d("DownloadObservable", "progress");
                    observer.onNext(result);
                    currentProgress = result.getProgress();
                }
            }
            os.flush();
            result.status = DownloadResult.STATUS_SUCCEED;
            LogUtil.d("DownloadObservable", "succeed");
            observer.onNext(result);
        } catch (IOException e) {
            e.printStackTrace();
            result.status = DownloadResult.STATUS_FAILED;
            LogUtil.d("DownloadObservable", "failed");
            observer.onError(new Throwable(e));
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
