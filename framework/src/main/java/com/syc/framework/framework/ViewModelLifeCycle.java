package com.syc.framework.framework;

import android.content.Intent;
import android.os.Bundle;

/**
 * Created by shiyucheng on 2018\5\9 0009.
 */

public interface ViewModelLifeCycle {
    void onCreate(Bundle savedInstanceState);

    void onStart();

    void onResume();

    void onPause();

    void onStop();

    void onDestroy();

    void onReStart();

    void onSaveInstanceState(Bundle outState);

    void onActivityResult(int requestCode, int resultCode, Intent data);
}
