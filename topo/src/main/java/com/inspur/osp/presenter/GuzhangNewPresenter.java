package com.inspur.osp.presenter;

import android.content.Context;

import com.inspur.osp.contract.GuzhangNewContract;
import com.inspur.osp.data.bean.GeoBreakDownEntity;
import com.inspur.osp.data.bean.LayerItemRequestbean;

import java.io.File;

import io.reactivex.disposables.Disposable;

public class GuzhangNewPresenter extends FragmentPresenter<GuzhangNewContract.View> implements GuzhangNewContract.Presenter {

    Disposable mutiDisposble;

    public GuzhangNewPresenter(Context mContext, GuzhangNewContract.View view) {
        this.mContext = mContext;
        this.mView = view;
        this.mView.setPresenter(this);
    }


    @Override
    public void requestModelEnum(String type, String jsonParam) {

    }

    @Override
    public void getCityByResource(String type, String jsonParam) {

    }

    @Override
    public void getCountryByResource(String type, String jsonParam) {

    }

    @Override
    public void addGuzhang(GeoBreakDownEntity taskCommitBean) {

    }

    @Override
    public void uploadFile(File file, String longitude, String latitude) {

    }

    @Override
    public void regionTranfer(String adCountyCode) {

    }

    @Override
    public void muiltyBreakdownQuery(LayerItemRequestbean dataItem, String systemLevelIds, double xMin, double xMax, double yMin, double yMax, String layerId) {

    }

    @Override
    public void deleteModelInfo(String modelName, String[] uuidList) {

    }

    @Override
    public void start() {

    }

    @Override
    public void desctroyView() {

    }
}
