package com.syc.common.network.download;

import android.os.Environment;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.concurrent.ConcurrentHashMap;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;

/**
 * Created by shiyucheng on 2018/9/7.
 */
public class DownloadManager {
    private static int MAX_SIZE = 5;
    private static DownloadManager instance;
    private String defaultDownloadPath = Environment.getExternalStorageDirectory().getPath() + File.separator + "download";
    private ConcurrentHashMap<String, Disposable> downloadMap;
    private OkHttpClient client;

    private DownloadManager() {
        downloadMap = new ConcurrentHashMap<String, Disposable>();
        client = new OkHttpClient.Builder().build();
    }

    public static DownloadManager getInstance() {
        if (instance == null) synchronized (DownloadManager.class) {
            if (instance == null) {
                instance = new DownloadManager();
            }
        }
        return instance;
    }

    public DEntry download(@NotNull String url, @NotNull DownloadCallback callback) {
        String name = url.substring(url.lastIndexOf("/"));
        return download(url, defaultDownloadPath, name, callback);
    }

    public synchronized DEntry download(@NotNull final String url, String path, String name, @NotNull DownloadCallback callback) {
        if (downloadMap.containsKey(url)) {
            callback.failed(new DownLoadException(url + " is downloading"));
            return null;
        }
        if (downloadMap.size() >= MAX_SIZE) {
            callback.failed(new DownLoadException("The max download task number is " + MAX_SIZE));
            return null;
        }
        DEntry entry = new DEntry();
        Disposable disposable = Observable.just(getDownloadInfo(url, path, name))
                .filter(new CheckInfoPredicate())
                .map(new CreateInfoFunction())
                .flatMap(new Function<DownloadInfo, ObservableSource<DownloadResult>>() {
                    @Override
                    public ObservableSource<DownloadResult> apply(DownloadInfo downloadInfo) {
                        return Observable.create(new DownloadSubscribe(downloadInfo, client));
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new SuccessConsumer(callback) {
                    @Override
                    public void finish() {
                        downloadMap.remove(url);
                    }
                }, new ErrorConsumer(callback) {
                    @Override
                    public void finish() {
                        downloadMap.remove(url);
                    }
                });
        downloadMap.put(url, disposable);
        entry.setDisposable(disposable);
        entry.setUrl(url);
        return entry;
    }


    private DownloadInfo getDownloadInfo(String url, String path, String name) {
        DownloadInfo info = new DownloadInfo();
        info.name = name;
        info.url = url;
        info.path = path;
        //todo 本地检测文件是否已经下载过，以及下载进度
        return info;
    }


    /**
     * 下载回调接口
     */
    public interface DownloadCallback {
        void prepare();

        void start();

        void progress(int progress);

        void success(String path);

        void failed(DownLoadException e);
    }

    public static class DownLoadException extends Exception {
        DownLoadException(String message) {
            super(message);
        }

        DownLoadException(Throwable t) {
            super(t);
        }
    }

}
