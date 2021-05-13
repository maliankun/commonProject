package com.inspur.osp.presenter;

import android.content.Context;

import com.inspur.osp.contract.NetTopoContract;
import com.inspur.osp.contract.OlpBindConstract;
import com.inspur.osp.data.source.remote.RemoteDataSource;

/**
 * 逻辑拓扑
 */
public class NetTopoPresenter extends FragmentPresenter<NetTopoContract.View> implements NetTopoContract.Presenter {

    public NetTopoPresenter(Context mContext, NetTopoContract.View view) {
        this.mContext = mContext;
        this.mView = view;
        this.mView.setPresenter(this);
    }



    @Override
    public void queryElemnetSubnet(String aElementId, String aPortId, String zElementId, String zPortId) {
        this.mView.setLoadingIndicator(true, "数据请求中");
        compositeDisposable.add(
                RemoteDataSource.getInstance().getsubnetElement(aElementId,aPortId,zElementId,zPortId)
                        .subscribe(elementSubnetInfo -> {
                            this.mView.setLoadingIndicator(false, null);
                            mView.showTopo(elementSubnetInfo);
                        }, throwable -> selfExceptionDeal(throwable))
        );
    }

    @Override
    public void start() {

    }

    @Override
    public void desctroyView() {

    }
}
