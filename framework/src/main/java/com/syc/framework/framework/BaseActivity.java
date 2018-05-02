package com.syc.framework.framework;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shiyucheng on 2018/1/9.
 */

public class BaseActivity extends AppCompatActivity {
    private List<BaseViewModel> models = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
