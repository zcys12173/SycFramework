package com.syc.common.network.download;

import android.os.Environment;

import com.syc.common.database.DBManager;
import com.syc.common.utils.MD5Util;

import java.io.File;
import java.io.IOException;

import io.reactivex.functions.Function;

/**
 * Created by shiyucheng on 2018/9/7.
 */
public class CreateInfoFunction implements Function<DownloadInfo, DBEntry> {
    @Override
    public DBEntry apply(DownloadInfo downloadInfo) throws Exception {
        String id = MD5Util.md5(downloadInfo.url + downloadInfo.path + downloadInfo.name);
        DBEntry entry = DBManager.getDefaultInstance().where(DBEntry.class).equalTo("id", id).findFirst();
        File file = new File(downloadInfo.path, downloadInfo.name);
        if (file.exists() && entry != null) {
            if (file.length() != entry.getStart()) {
                throw new Exception("the file named " + downloadInfo.name + " has exist, please rename");
            }
        }else{
            createFile(downloadInfo.path,downloadInfo.name);
            entry = new DBEntry();
            entry.setId(id);
            entry.setStart(0);
            entry.setPath(downloadInfo.path);
            entry.setUrl(downloadInfo.url);
            entry.setName(downloadInfo.name);
        }
        return entry;
    }


    private void createFile(String path, String name) throws Exception {
        if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            throw new DownloadManager.DownLoadException("the sdcard is not available");
        }
        File file = new File(path);
        file.mkdirs();
        file = new File(path, name);
        if (file.exists()) {
            file.delete();
        }
        file.createNewFile();
    }
}
