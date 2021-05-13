package com.inspur.osp.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.inspur.osp.BasePresenter;
import com.inspur.osp.ui.inter.IWaitDialog;

//import butterknife.ButterKnife;

/**
 * Created by liuchen_ on 2017/12/6.
 */

public abstract class BaseFragment<T> extends Fragment implements IWaitDialog {

    protected T mPresenter;
    protected Context mContext;
    protected View rootView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this.getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(getLayoutId(), container, false);
        }
//        ButterKnife.bind(this, rootView);
        afterContentView(savedInstanceState);
        initView();
        initData();
        initListener();
        if (mPresenter != null && mPresenter instanceof BasePresenter) {
            ((BasePresenter) mPresenter).start();
        }
        return rootView;
    }

    protected abstract void initView();

    protected void afterContentView(Bundle savedInstanceState) {

    }

    public abstract void initData();

    protected abstract void initListener();

    protected abstract int getLayoutId();

    @Override
    public void showProgressLoading(int resId) {
        if (mContext instanceof IWaitDialog) {
            ((IWaitDialog) mContext).showProgressLoading(resId);
        }
    }

    @Override
    public void showHint(String message) {
        if (mContext instanceof IWaitDialog) {
            ((IWaitDialog) mContext).showHint(message);
        }
    }

    @Override
    public void showProgressLoading(String message) {
        if (mContext instanceof IWaitDialog) {
            ((IWaitDialog) mContext).showProgressLoading(message);
        }
    }

    @Override
    public void dismissProgressLoading() {
        if (mContext instanceof IWaitDialog) {
            ((IWaitDialog) mContext).dismissProgressLoading();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null && mPresenter instanceof BasePresenter) {
            ((BasePresenter) mPresenter).desctroyView();
        }

    }

}
