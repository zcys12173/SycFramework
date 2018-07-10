package com.syc.common.database;

import android.content.Context;

import com.syc.common.widget.refresh.adapter.BaseViewHolder;
import com.syc.common.widget.refresh.adapter.PTRAdapter;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by shiyucheng on 2018/1/15.
 */

public class DBManager {
    private static final int DB_VERSION = 1;
    private static final String DEFAULT_NAME = "default_name";


    public static void init(Context context){
        Realm.init(context.getApplicationContext());
    }

    public static Realm getDefaultInstance() {
        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .name(DEFAULT_NAME)
                .schemaVersion(DB_VERSION)
                .deleteRealmIfMigrationNeeded()
                .build();
        return Realm.getInstance(configuration);
    }

    public static Realm getRealmInstance(String name) {

        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .name(name)
                .schemaVersion(DB_VERSION)
                .deleteRealmIfMigrationNeeded()
                .build();
        return Realm.getInstance(configuration);
    }

}
