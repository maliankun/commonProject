package com.inspur.osp.presenter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.inspur.osp.AppConfig;
import com.inspur.osp.BaseView;
import com.inspur.topo.R;
import com.inspur.osp.data.source.SelfException;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by liuchen_ on 2017/12/7.
 */

public abstract class FragmentPresenter<T> {


    @NonNull
    protected Context mContext;

    protected T mView;


    protected CompositeDisposable compositeDisposable = new CompositeDisposable();

    public CompositeDisposable getCompositeDisposable() {
        return compositeDisposable;
    }

    public void clearDisposr() {
        compositeDisposable.clear();
    }

    public void selfExceptionDeal(Throwable throwable) {
        BaseView baseView = null;
        if (mView instanceof BaseView) {
            baseView = (BaseView) mView;
            baseView.setLoadingIndicator(false, null);
        }
        if (throwable instanceof SelfException) {
            SelfException selfException = (SelfException) throwable;
            if (selfException.getCode() == 401) {
                if (baseView != null) {
                    baseView.showHint(
                            TextUtils.isEmpty(selfException.getMessage()) ?
                                    mContext.getString(R.string.error_password)
                                    : selfException.getMessage());
                }
            } else if (selfException.getCode() == 405) {
                if (baseView != null) {
                    baseView.showHint(
                            TextUtils.isEmpty(selfException.getMessage()) ?
                                    mContext.getString(R.string.error_model_no_exsit)
                                    : selfException.getMessage());
                }
            } else if (selfException.getCode() == 400) {
                if (baseView != null) {
                    baseView.showHint(
                            TextUtils.isEmpty(selfException.getMessage()) ?
                                    mContext.getString(R.string.error_bad_request)
                                    : selfException.getMessage()
                    );
                }
            } else if (selfException.getCode() == 500) {
                if (baseView != null) {
                    baseView.showHint(
                            TextUtils.isEmpty(selfException.getMessage()) ?
                                    mContext.getString(R.string.error_server_inner)
                                    : selfException.getMessage()
                    );
                }
            } else {
                if (baseView != null) {
                    baseView.showHint(TextUtils.isEmpty(selfException.getMessage()) ?
                            mContext.getString(R.string.error_request)
                            : selfException.getMessage()
                    );
                }
            }

        } else {
            if (baseView != null) {
                if (AppConfig.IS_TEST) {
                    baseView.showHint(throwable.getMessage());
                } else {
                    baseView.showHint(mContext.getString(R.string.error_exception));
                }
            }
        }
    }

}
