package com.syc.framework.router;

import android.content.Context;
import android.text.TextUtils;

import com.syc.framework.router.listener.RouterListener;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/**
 * 路由
 * Created by shiyucheng on 2018/5/13.
 */

public class Router {
    private static Router instance;
    private Router() {
    }

    public static Router getInstance(){
        if(instance == null) synchronized (Router.class){
            if(instance == null){
                instance = new Router();
            }
        }
        return instance;
    }


    private HashMap<String,RouterListener> routerMaps = new HashMap<String,RouterListener>();

    public void register(Builder builder) {
        routerMaps.put(builder.uri, builder.getListener());
    }

    public void router(Context context) {
//        for (Map.Entry<String, RouterListener> entry : routerMaps.entrySet()) {
//            String uriKey = entry.getKey();
//            RouterListener listener = entry.getValue();
//            if (TextUtils.equals(uri, uriKey)) {
//                listener.onFinish(context);
//            }
//        }
    }

    public static class Builder {
        private WeakReference<Context> context;
        private String uri;
        private RouterListener listener;
        private Router router;
        public Builder(Context context) {
            this.context = new WeakReference<Context>(context);
        }

        public Builder uri(String uri){
            this.uri = uri;
            return this;
        }

        public Builder setRouterListener(RouterListener listener){
            this.listener = listener;
            return this;
        }

        public Context getContext() {
            return context.get();
        }

        public String getUri() {
            return uri;
        }

        public RouterListener getListener() {
            return listener;
        }

        public Router getRouter() {
            return router;
        }

        public void build(){
            Router.getInstance().register(this);
        }

    }
}
