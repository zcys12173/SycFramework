package com.syc.framework.router;

import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by shiyucheng on 2018\5\15 0015.
 */

public class Receiver {
    private Class<?> clazz;
    private WeakReference<Method> method;

    public void invoke(Object... params){
        if(method != null  && method.get() != null){
            try {
                method.get().invoke(clazz.newInstance(),params);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (InstantiationException e){
                e.printStackTrace();
            }
        }
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }

    public Method getMethod() {
        return method.get();
    }

    public void setMethod(Method method) {
        this.method = new WeakReference<Method>(method);
    }
}
