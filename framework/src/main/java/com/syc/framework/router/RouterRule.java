package com.syc.framework.router;

import com.syc.framework.router.pipe.Pipe;

/**
 * Created by Administrator on 2018\5\14 0014.
 */

public interface RouterRule {
    void onRouter(Pipe pipe);
}
