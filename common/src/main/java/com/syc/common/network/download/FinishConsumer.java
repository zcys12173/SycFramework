package com.syc.common.network.download;

import io.reactivex.functions.Consumer;

/**
 * Created by shiyucheng on 2018/9/8.
 */
public interface FinishConsumer<T> extends Consumer<T> {
    void finish();
}
