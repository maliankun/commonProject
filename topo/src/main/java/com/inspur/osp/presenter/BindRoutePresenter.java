package com.inspur.osp.presenter;

import android.content.Context;

import com.inspur.osp.contract.BindRouteContract;
import com.inspur.osp.data.source.remote.RemoteDataSource;
import com.inspur.osp.util.RxBus;

/**
 * Created by liuchen01 on 2019/10/26.
 */

public class BindRoutePresenter extends FragmentPresenter<BindRouteContract.View> implements BindRouteContract.Presenter {


    public BindRoutePresenter(Context context, BindRouteContract.View view) {
        mContext = context;
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void cancelBindRoute(String topolinkid, String cableids) {
        mView.setLoadingIndicator(true, "请求数据中");
        compositeDisposable.add(
                RemoteDataSource.getInstance().cancelCheckRouteCableId(topolinkid, cableids)
                        .subscribe(baseBean -> {
                            mView.setLoadingIndicator(false, null);
                            if (baseBean.code == 0) {
                                mView.showHint("操作成功");
                                RxBus.getInstance().send("router_refresh");
                            } else {
                                mView.showHint(baseBean.msg);
                            }
                        }, throwable -> selfExceptionDeal(throwable))
        );
    }

    @Override
    public void cancelCheckedRoute(String uuid) {
        mView.setLoadingIndicator(true, "请求数据中");
        compositeDisposable.add(

                RemoteDataSource.getInstance().cancelCheckedRoute(uuid)
                        .subscribe(baseBean -> {
                                    mView.setLoadingIndicator(false, null);
                                    if (baseBean.code == 0) {
                                        mView.showHint("操作成功");
                                        RxBus.getInstance().send("router_refresh");
                                    } else {
                                        mView.showHint(baseBean.msg);
                                    }
                                }, throwable ->
                                        selfExceptionDeal(throwable)
                        )
        );
    }

    @Override
    public void start() {

    }

    @Override
    public void desctroyView() {

    }
}
