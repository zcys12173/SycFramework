package com.syc.framework.router;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;

import com.syc.framework.router.annotation.RouterSubscribe;
import com.syc.framework.router.listener.PipeCallBack;
import com.syc.framework.router.pipe.Pipe;

import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
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

    public static Router getInstance() {
        if (instance == null) synchronized (Router.class) {
            if (instance == null) {
                instance = new Router();
            }
        }
        return instance;
    }

    public void broadcast(String uri, Bundle bundle) {
        for (Map.Entry<String, Receiver> entry : receivers.entrySet()) {
            if (TextUtils.equals(uri, entry.getKey())) {
                Receiver receiver = entry.getValue();
                receiver.invoke(bundle);
            }

        }
    }

    public void registerReceiver(Object obj) {
        Method[] methods = obj.getClass().getMethods();
        for (Method method : methods) {
            RouterSubscribe subscribe = method.getAnnotation(RouterSubscribe.class);
            if (subscribe != null) {
                Receiver receiver = new Receiver();
                receiver.setObject(obj);
                receiver.setMethod(method);
                receivers.put(subscribe.uri(), receiver);
            }
        }
    }


    public void unRegisterReceiver(Object obj) {
        for (Map.Entry<String, Receiver> entry : receivers.entrySet()) {
            Receiver receiver = entry.getValue();
            if (receiver.getObject() == obj) {
                receivers.remove(entry.getKey());
            }

        }
    }

    private HashMap<String, Receiver> receivers = new HashMap<>();


    private HashMap<String, RouterRule> routerMaps = new HashMap<String, RouterRule>();

    public void register(String uri, RouterRule routerRule) {
        routerMaps.put(uri, routerRule);
    }

    public void router(Builder builder) {
        for (Map.Entry<String, RouterRule> entry : routerMaps.entrySet()) {
            String uri = entry.getKey();
            if (TextUtils.equals(uri, builder.getUri())) {
                RouterRule routerRule = entry.getValue();
                if (routerRule != null) {
                    Pipe pipe = new Pipe();
                    pipe.setCallBack(builder.getCallBack());
                    pipe.setBundle(builder.getBundle());
                    pipe.setContext(builder.getContext());
                    routerRule.onRouter(pipe);
                    break;//如果注册多个执行第一个注册的
                }
            }


        }
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {
        private WeakReference<Context> context;
        private String uri;
        private Router router;
        private PipeCallBack callBack;
        private Bundle bundle;

        public Builder from(Context context) {
            this.context = new WeakReference<Context>(context);
            return this;
        }

        public Builder uri(String uri) {
            this.uri = uri;
            return this;
        }

        public Builder params(Bundle bundle) {
            this.bundle = bundle;
            return this;
        }


        public Builder callBack(PipeCallBack callBack) {
            this.callBack = callBack;
            return this;
        }

        public Context getContext() {
            return context.get();
        }

        public String getUri() {
            return uri;
        }

        public Router getRouter() {
            return router;
        }

        public PipeCallBack getCallBack() {
            return callBack;
        }

        public Bundle getBundle() {
            return bundle;
        }

        public void buildAndRouter() {
            Router.getInstance().router(this);
        }

    }
}
