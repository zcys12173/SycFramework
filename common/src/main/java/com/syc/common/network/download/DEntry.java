package com.syc.common.network.download;

import java.util.Map;

import io.reactivex.disposables.Disposable;
import retrofit2.Call;

/**
 * Created by shiyucheng on 2018/9/10.
 */
public class DEntry {
    private String url;
    private Disposable disposable;
    private Map map;

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    void setUrl(String url) {
        this.url = url;
    }

    void setDisposable(Disposable disposable) {
        this.disposable = disposable;
    }

    public String getUrl() {
        return url;
    }

    public Disposable getDisposable() {
        return disposable;
    }

    public void cancel(){
        if(disposable != null && !disposable.isDisposed()){
            disposable.dispose();
            map.remove(url);
        }
    }
}
