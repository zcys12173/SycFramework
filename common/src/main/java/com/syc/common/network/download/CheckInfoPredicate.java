package com.syc.common.network.download;

import io.reactivex.functions.Predicate;

/**
 * Created by shiyucheng on 2018/9/7.
 */
public class CheckInfoPredicate implements Predicate<DownloadInfo> {
    @Override
    public boolean test(DownloadInfo info) throws Exception {
        //todo 检测条件是否满足
        return true;
    }
}
