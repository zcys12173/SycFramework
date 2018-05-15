package com.syc.framework.router;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by shiyucheng on 2018\5\15 0015.
 */

public class Receiver {
    private Object object;
    private Method method;
    public void invoke(Object... params){
        if(method != null){
            try {
                method.invoke(object,params);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }
}
