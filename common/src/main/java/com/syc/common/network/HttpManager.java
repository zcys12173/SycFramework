package com.syc.common.network;

import android.content.Context;


import com.syc.common.network.config.HttpConfig;

import java.lang.ref.WeakReference;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by shiyucheng on 2018/1/2.
 * 网络请求工具
 */

public class HttpManager {

    private static HttpManager instance;

    private HttpManager() {
    }

    public static HttpManager getInstance() {
        if (null == instance) synchronized (HttpManager.class) {
            if (null == instance) {
                instance = new HttpManager();
            }
        }
        return instance;
    }

    private HttpConfig httpConfig;
    private Retrofit retrofit;
    private WeakReference<Context> wrContext;

    public void init(Context context,HttpConfig config){
        this.wrContext = new WeakReference<>(context.getApplicationContext());
        this.httpConfig = config;

    }

    private void initRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(httpConfig.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(OKHttpClientFactory.createHttpClient(wrContext.get()))
                .build();
    }

    public <T> T getService(Class<T> service) {
        if (retrofit == null) {
            initRetrofit();
        }
        return retrofit.create(service);
    }


}
