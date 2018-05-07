package com.syc.framework.framework;

import android.databinding.ViewDataBinding;
import android.os.Bundle;

/**
 * Created by Administrator on 2018\5\7 0007.
 */

public interface LifeCycle <T extends ViewDataBinding>{

    int getContent();

    void initView(Bundle bundle);
}
