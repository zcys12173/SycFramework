package com.syc.framework.framework;

import android.os.Bundle;

/**
 * Created by Administrator on 2018\5\9 0009.
 */

public interface ViewModelLiftCycle {
    void onCreate(Bundle savedInstanceState);

    void onStart();

    void onResume();

    void onPause();

    void onStop();

    void onDestroy();

    void onReStart();

    void onSaveInstanceState(Bundle outState);
}
