package com.syc.common.network.download;

/**
 * Created by shiyucheng on 2018/9/7.
 */
public class DownloadResult {
    public static final int STATUS_PREPARE = 0;
    public static final int STATUS_START= 1;
    public static final int STATUS_DOWNLOADING = 2;
    public static final int STATUS_SUCCEED = 3;
    public static final int STATUS_FAILED = 4;
    public static final int STATUS_PAUSE = 5;

    public long currentSize;
    public long totalSize;
    public String name;
    public String path;
    public int status;
    public String url;
    /**
     * 获取当前下载进度
     * @return
     */
    public int getProgress(){
        return (int) (currentSize*100/totalSize );
    }
}
