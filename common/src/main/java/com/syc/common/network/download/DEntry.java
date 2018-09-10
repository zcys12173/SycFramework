package com.syc.common.network.download;

import io.reactivex.disposables.Disposable;
import retrofit2.Call;

/**
 * Created by shiyucheng on 2018/9/10.
 */
public class DEntry {
    private String url;
    private Disposable disposable;
    private Call call;

    void setUrl(String url) {
        this.url = url;
    }

    void setDisposable(Disposable disposable) {
        this.disposable = disposable;
    }

    void setCall(Call call) {
        this.call = call;
    }

    public String getUrl() {
        return url;
    }

    public Disposable getDisposable() {
        return disposable;
    }

    public Call getCall() {
        return call;
    }

    public void cancel(){
        if(disposable != null && !disposable.isDisposed()){
            disposable.dispose();
        }

        if(call != null && call.isExecuted() && !call.isCanceled()){
            call.cancel();
        }
    }
}
