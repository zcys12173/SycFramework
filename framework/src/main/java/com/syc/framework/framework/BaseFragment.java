package com.syc.framework.framework;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 *
 * Created by shiyucheng on 2018\5\21 0021.
 */

public abstract class BaseFragment<T extends ViewDataBinding> extends Fragment {
    private ViewModelManager viewModelManager;

    public T getBinding() {
        return binding;
    }

    private T binding;

    protected abstract int getContent();

    protected abstract void init(@Nullable Bundle savedInstanceState);

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModelManager = new ViewModelManager();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, getContent(), container, false);
        init(savedInstanceState);
        viewModelManager.onCreate(savedInstanceState);
        return binding.getRoot();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        viewModelManager.onSaveInstanceState(outState);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModelManager.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        viewModelManager.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onStart() {
        super.onStart();
        viewModelManager.onStart();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        viewModelManager.onDestroy();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
