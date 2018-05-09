package com.syc.framework.framework;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by shiyucheng on 2018\5\9 0009.
 */

public class ViewModelManager {
    private List<BaseViewModel> viewModels = new ArrayList<>();


    public void addViewModel(BaseViewModel viewModel) {
        viewModels.add(viewModel);
    }

    void onCreate(Bundle savedInstanceState) {
        for (BaseViewModel item : viewModels) {
            item.onCreate(savedInstanceState);
        }
    }

    void onStart() {
        for (BaseViewModel item : viewModels) {
            item.onStart();
        }
    }

    void onReStart() {
        for (BaseViewModel item : viewModels) {
            item.onReStart();
        }
    }

    void onPause() {
        for (BaseViewModel item : viewModels) {
            item.onPause();
        }
    }

    void onResume() {
        for (BaseViewModel item : viewModels) {
            item.onResume();
        }
    }

    void onStop() {
        for (BaseViewModel item : viewModels) {
            item.onStop();
        }
    }

    void onDestroy() {
        for (BaseViewModel item : viewModels) {
            item.onDestroy();
        }
    }

    void onSaveInstanceState(Bundle outState) {
        for (BaseViewModel item : viewModels) {
            item.onSaveInstanceState(outState);
        }
    }

    void release() {
        viewModels.clear();
    }


}
