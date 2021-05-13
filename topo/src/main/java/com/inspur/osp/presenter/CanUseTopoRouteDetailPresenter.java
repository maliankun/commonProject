package com.inspur.osp.presenter;

import android.content.Context;

import com.inspur.osp.contract.CanUseTopoRouteDetailConstract;
import com.inspur.osp.data.source.remote.RemoteDataSource;
import com.inspur.osp.util.RxBus;

/**
 * Created by liuchen01 on 2019/9/25.
 */

public class CanUseTopoRouteDetailPresenter extends FragmentPresenter<CanUseTopoRouteDetailConstract.View> implements CanUseTopoRouteDetailConstract.Presenter {

//    private int requestNum = 2;
//    private ModelBean oldModelBean;
//    private ModelBean newModelBean;

    public CanUseTopoRouteDetailPresenter(Context context, CanUseTopoRouteDetailConstract.View mapView) {
        this.mContext = context;
        this.mView = mapView;
        mapView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void desctroyView() {

    }

    @Override
    public void getBindRelations(String topolinkId, String cableids) {

        mView.setLoadingIndicator(true, "数据加载中");

        RemoteDataSource.getInstance().getCheckedRouteByCableId(topolinkId, cableids)
                .subscribe(checkRouteStateBean -> {
                    mView.setLoadingIndicator(false, null);
                    mView.fillBindRelation(checkRouteStateBean);
                }, throwable -> selfExceptionDeal(throwable));

    }

    @Override
    public void saveCheckedRoute(String topolinkId, String cableids) {
        mView.setLoadingIndicator(true, "数据加载中");

        compositeDisposable.add(RemoteDataSource.getInstance().saveCheckedRoute(topolinkId, cableids)
                .subscribe(baseBean -> {
                    mView.setLoadingIndicator(false, null);
                    mView.showHint(baseBean.msg);
                    if (baseBean.code == 0) {
                        //绑定成功
                        mView.bindRouteSuccess();
                    }
                }, throwable -> selfExceptionDeal(throwable)));
    }

    @Override
    public void saveCheckedRoute4OLP(String topolinkId, String cableids) {

    }

    @Override
    public void cancelBind(String topolinkId, String cableids) {
        mView.setLoadingIndicator(true, "请求数据中");
        compositeDisposable.add(
                RemoteDataSource.getInstance().cancelCheckRouteCableId(topolinkId, cableids)
                        .subscribe(baseBean -> {
                            mView.setLoadingIndicator(false, null);
                            if (baseBean.code == 0) {
                                RxBus.getInstance().send("router_refresh");
                            } else {
                                mView.showHint(baseBean.msg);
                            }
                        }, throwable -> {

                        })
        );
    }

    @Override
    public void cancelBindByUuid(String uuid) {
        mView.setLoadingIndicator(true, "请求数据中");
        compositeDisposable.add(
                RemoteDataSource.getInstance().cancelCheckedRoute(uuid)
                        .subscribe(baseBean -> {
                            mView.setLoadingIndicator(false, null);
                            if (baseBean.code == 0) {
                                RxBus.getInstance().send("router_refresh");
                            } else {
                                mView.showHint(baseBean.msg);
                            }
                        }, throwable -> {

                        })
        );
    }
}
