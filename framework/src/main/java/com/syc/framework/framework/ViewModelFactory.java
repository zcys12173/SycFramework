package com.syc.framework.framework;

import android.content.Context;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by shiyucheng on 2018/1/10.
 */

public class ViewModelFactory {
    public static <T extends BaseViewModel>T create(Context context,Class<T> clazz){
        T viewModel = null;

            try {
                Constructor<T> constructor = clazz.getDeclaredConstructor(Context.class);
                viewModel = constructor.newInstance(context);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }


        return viewModel;
    }
}
