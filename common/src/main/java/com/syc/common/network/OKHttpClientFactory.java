package com.syc.common.network;

import android.content.Context;

import com.syc.common.network.interceptor.HeaderIntercepor;
import com.syc.common.network.utils.OkHttpsUtils;
import com.syc.common.utils.LogUtil;

import java.io.IOException;
import java.io.InputStream;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by shiyucheng on 2018/1/3.
 */

public class OKHttpClientFactory {
    public static OkHttpClient createHttpClient(Context context) {
        return new HttpClient().create(context);
    }

    public static OkHttpClient createHttpsClient(Context context) {
        return new HttpsClient().create(context);
    }

    private static abstract class Http extends Factory {
        @Override
        protected void buildOKHttpClient(Context context, OkHttpClient.Builder builder) {

        }

    }

    private static abstract class Https extends Factory {
        @Override
        protected void buildOKHttpClient(Context context, OkHttpClient.Builder builder) {
            InputStream[] certs;
            try {
                certs = new InputStream[]{
                        context.getAssets().open(""),
                        context.getAssets().open("")
                };
            } catch (IOException e) {
                // 打不开公钥文件是个严重错误
                throw new RuntimeException("Failed to open cert files", e);
            }

            SSLSocketFactory sslFactory = OkHttpsUtils.getSslSocketFactory(certs, null, null);
            if (sslFactory != null) {
                builder.sslSocketFactory(sslFactory);
            }
            HostnameVerifier verifier = OkHttpsUtils.getHostnameVerifier();
            builder.hostnameVerifier(verifier);
            for (InputStream cert : certs) {
                try {
                    cert.close();
                } catch (IOException e) {
                    // ignore the closing errors.
                }
            }
        }
    }

    private static abstract class Factory {
        protected abstract void buildOKHttpClient(Context context, OkHttpClient.Builder builder);

        public OkHttpClient create(Context context) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {
                    LogUtil.d("HTTP", message);
                }
            }).setLevel(HttpLoggingInterceptor.Level.BASIC));
            buildOKHttpClient(context, builder);
            return builder.build();
        }
    }

    private static class HttpClient extends Http {
        @Override
        protected void buildOKHttpClient(Context context, OkHttpClient.Builder builder) {
            super.buildOKHttpClient(context, builder);
            builder.addNetworkInterceptor(new HeaderIntercepor(context));
        }
    }

    private static class HttpsClient extends Https {
        @Override
        protected void buildOKHttpClient(Context context, OkHttpClient.Builder builder) {
            super.buildOKHttpClient(context, builder);
            builder.addNetworkInterceptor(new HeaderIntercepor(context));
        }
    }
}
