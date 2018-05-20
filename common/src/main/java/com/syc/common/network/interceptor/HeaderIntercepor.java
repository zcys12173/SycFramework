package com.syc.common.network.interceptor;

import android.content.Context;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by shiyucheng on 2018/1/3.
 * okHttp headerpareams 拦截器
 */

public class HeaderIntercepor implements Interceptor {
    public HeaderIntercepor(Context context) {

    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        //添加header公共参数
        builder.addHeader("Content-Type","application/json;charset=utf-8");
        // TODO: 2018/1/3 添加所需公参
        Request request = builder.build();
        return chain.proceed(request);
    }
}
