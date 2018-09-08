package com.syc.common.network.download;

import java.io.File;
import java.io.IOException;

import io.reactivex.functions.Function;

/**
 * Created by shiyucheng on 2018/9/7.
 */
public class CreateInfoFunction implements Function<DownloadInfo, DownloadInfo> {
    @Override
    public DownloadInfo apply(DownloadInfo downloadInfo) throws IOException {
        //todo 生成完整下载请求数据（DownloadInfo）从数据库读取相应下载进度
        File file = new File(downloadInfo.path);
        file.mkdirs();
        file = new File(downloadInfo.path, downloadInfo.name);
        if (file.exists()) {
            file.delete();
        }
        file.createNewFile();
        return downloadInfo;
    }
}
