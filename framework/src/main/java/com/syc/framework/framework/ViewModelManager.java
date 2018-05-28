package com.syc.framework.framework;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shiyucheng on 2018\5\9 0009.
 */

public class ViewModelManager{
    private List<BaseViewModel> viewModels = new ArrayList<>();


    public void addViewModel(BaseViewModel viewModel) {
        viewModels.add(viewModel);
    }

    void onCreate(Bundle savedInstanceState) {
        for (BaseViewModel item : viewModels) {
            if (item != null) {
                item.onCreate(savedInstanceState);
            }

        }
    }

    void onStart() {
        for (BaseViewModel item : viewModels) {
            if (item != null) {
                item.onStart();
            }
        }
    }

    void onReStart() {
        for (BaseViewModel item : viewModels) {
            if (item != null) {
                item.onReStart();
            }
        }
    }

    void onPause() {
        for (BaseViewModel item : viewModels) {
            if (item != null) {
                item.onPause();
            }
        }
    }

    void onResume() {
        for (BaseViewModel item : viewModels) {
            if (item != null) {
                item.onResume();
            }
        }
    }

    void onStop() {
        for (BaseViewModel item : viewModels) {
            if (item != null) {
                item.onStop();
            }
        }
    }

    void onDestroy() {
        for (BaseViewModel item : viewModels) {
            if (item != null) {
                item.onDestroy();
            }
        }
    }

    void onSaveInstanceState(Bundle outState) {
        for (BaseViewModel item : viewModels) {
            if (item != null) {
                item.onSaveInstanceState(outState);
            }
        }
    }

    void release() {
        viewModels.clear();
    }

    void onActivityResult(int requestCode, int resultCode, Intent data) {
        for (BaseViewModel item : viewModels) {
            if (item != null) {
                item.onActivityResult(requestCode, resultCode, data);
            }

        }
    }

}
