package com.syc.framework.sycframework.designpatterns.visitor;

import com.syc.framework.utils.LogUtil;

/**
 * Created by shiyucheng on 2018/1/10.
 */

public class Test implements Listener {
    @Override
    public void accept(Visitor v) {
        v.visit(this);
        LogUtil.e("Test","execute");
    }

    public void onCreate(){
        LogUtil.e("Test","onCreate");
    }


    public static void test(){
        Test test = new Test();
        final Visitor<Test> visitor = new Visitor<Test>() {
            @Override
            public void visit(Test obj) {
                obj.onCreate();
            }
        };
        test.accept(visitor);
    }
}
