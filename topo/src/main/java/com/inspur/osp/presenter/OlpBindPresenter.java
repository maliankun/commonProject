package com.inspur.osp.presenter;

import android.content.Context;

import com.inspur.osp.contract.OlpBindConstract;
import com.inspur.osp.data.source.remote.RemoteDataSource;

/**
 * Created by liuchen01 on 2019/10/25.
 */

public class OlpBindPresenter extends FragmentPresenter<OlpBindConstract.View> implements OlpBindConstract.Presenter {

    public OlpBindPresenter(Context mContext, OlpBindConstract.View view) {
        this.mContext = mContext;
        this.mView = view;
        this.mView.setPresenter(this);
    }

    @Override
    public void getAzOlpDeviceByRoom(String aRoomId, String zRoomId) {

        this.mView.setLoadingIndicator(true, "数据初始化中");
        compositeDisposable.add(
                RemoteDataSource.getInstance().getOlpDeviceByRoom(aRoomId, zRoomId)
                        .subscribe(olpByRoomBaseBean -> {
                                    this.mView.setLoadingIndicator(false, null);
                                    if (olpByRoomBaseBean.code == 0) {
                                        mView.fillAzDeviceByRoom(olpByRoomBaseBean.data);
                                    } else {
                                        mView.showHint(olpByRoomBaseBean.msg);
                                    }
                                }, throwable -> selfExceptionDeal(throwable)
                        )
        );
    }

    @Override
    public void getOlpCardByOlpDevice(String neId, int type) {
        this.mView.setLoadingIndicator(true, "数据请求中");
        compositeDisposable.add(
                RemoteDataSource.getInstance().getOlpCardByOlp(neId)
                        .subscribe(olpDeviceBeanBaseBean -> {
                            this.mView.setLoadingIndicator(false, null);

                            if (olpDeviceBeanBaseBean.code == 0) {
                                if (olpDeviceBeanBaseBean.data != null && olpDeviceBeanBaseBean.data.size() > 0) {
                                    mView.fillOlpCardByDevice(type, olpDeviceBeanBaseBean.data);
                                } else {
                                    mView.showHint("没有数据");
                                }
                            } else {
                                mView.showHint(olpDeviceBeanBaseBean.msg);
                            }
                        }, throwable -> selfExceptionDeal(throwable))
        );

    }

    @Override
    public void getEndInfoByOlpCard(String cardId, int type) {
        this.mView.setLoadingIndicator(true, "数据请求中");
        compositeDisposable.add(
                RemoteDataSource.getInstance().getEndInfoByOlpCard(cardId)
                        .subscribe(olpDeviceBeanBaseBean -> {
                            this.mView.setLoadingIndicator(false, null);
                            if (olpDeviceBeanBaseBean.code == 0) {
                                if (olpDeviceBeanBaseBean.data != null) {
                                    mView.fillEndInfoByOlpCard(type, olpDeviceBeanBaseBean.data);
                                } else {
                                    mView.showHint("没有数据");
                                }
                            } else {
                                mView.showHint(olpDeviceBeanBaseBean.msg);
                            }
                        }, throwable -> selfExceptionDeal(throwable))
        );

    }

    @Override
    public void saveOlpDeviceBind(String topolLinkId, String cableids, String orig_ne_id_olp, String orig_card_id_olp, String dest_ne_id_olp, String dest_card_id_olp) {

        mView.setLoadingIndicator(true, "正在绑定。。。");
        compositeDisposable.add(
                RemoteDataSource.getInstance().saveCheckedRoute4Olp(topolLinkId, cableids, orig_ne_id_olp, orig_card_id_olp, dest_ne_id_olp, dest_card_id_olp)
                        .subscribe(baseBean -> {
                            mView.setLoadingIndicator(false, null);
                            mView.olpBindSuccess();
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
