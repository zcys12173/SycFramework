package com.syc.framework.sycframework.designpatterns.visitor;

/**
 * Created by shiyucheng on 2018/1/10.
 */

public interface Visitor<T> {
    void visit(T obj);
}
