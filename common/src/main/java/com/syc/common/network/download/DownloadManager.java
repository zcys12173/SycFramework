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
 * 下载管理器
 */
public class DownloadManager {
    private static int MAX_SIZE = 3;
    private static DownloadManager instance;
    private String defaultDownloadPath = Environment.getExternalStorageDirectory().getPath() + File.separator + "download"+File.separator;
    private ConcurrentHashMap<String, Disposable> downloadMap;
    private OkHttpClient client;

    private DownloadManager() {
        downloadMap = new ConcurrentHashMap<>();
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
        String name = url.substring(url.lastIndexOf("/")+1);
        return download(url, defaultDownloadPath, name, callback);
    }

    public synchronized DEntry download(@NotNull final String url, String path, String name, @NotNull DownloadCallback callback) {
        if(!canAddToTasks(url)){
            callback.failed(new DownLoadException("add download task failed,the max task num is "+MAX_SIZE));
            return null;
        }
        DEntry entry = new DEntry();
        entry.setMap(downloadMap);
        Disposable disposable = Observable.just(getDownloadInfo(url, path, name))
                .filter(new CheckInfoPredicate())
                .map(new CreateInfoFunction())
                .flatMap(new Function<DBEntry, ObservableSource<DownloadResult>>() {
                    @Override
                    public ObservableSource<DownloadResult> apply(DBEntry dbEntry) {
                        return Observable.create(new DownloadSubscribe(dbEntry, client));
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
        return info;
    }

    private boolean canAddToTasks(String url){
        if (downloadMap.containsKey(url) ) {
           if(downloadMap.get(url).isDisposed()){
               downloadMap.remove(url);
               return true;
           }else{
               return false;
           }
        }
        return downloadMap.size() < MAX_SIZE;
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
