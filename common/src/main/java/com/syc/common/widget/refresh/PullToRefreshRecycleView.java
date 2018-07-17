package com.syc.common.widget.refresh;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.syc.common.widget.refresh.adapter.BaseViewHolder;
import com.syc.common.widget.refresh.adapter.PTRAdapter;
import com.syc.common.widget.refresh.adapter.PTRAdapterProxy;
import com.syc.common.widget.refresh.adapter.RecycleItemViewModel;

import java.util.List;

/**
 * Created by shiyucheng on 2018/5/20.
 */

public class PullToRefreshRecycleView extends SmartRefreshLayout {
    private RecyclerView recyclerView;

    public PullToRefreshRecycleView(Context context) {
        super(context);
        init();
    }

    public PullToRefreshRecycleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {

        setRefreshHeader(new ClassicsHeader(getContext()));
        setRefreshFooter(new ClassicsFooter(getContext()));
        setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {

            }
        });

        setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

            }
        });
        recyclerView = new RecyclerView(getContext());
        setRefreshContent(recyclerView);
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        recyclerView.setAdapter(adapter);
    }

    public void setLayoutManager(RecyclerView.LayoutManager layoutManager){
        recyclerView.setLayoutManager(layoutManager);
    }

    public void setAdapterProxy(PTRAdapterProxy adapterProxy) {
    }

    @BindingAdapter("adapterProxy")
    public static void setPTRAdapterProxy(PullToRefreshRecycleView view,PTRAdapterProxy proxy){
        view.setAdapterProxy(proxy);
    }
}
