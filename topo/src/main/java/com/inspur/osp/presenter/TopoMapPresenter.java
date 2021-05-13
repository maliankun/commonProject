package com.inspur.osp.presenter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.amap.api.maps.model.LatLng;
import com.cocoahero.android.geojson.GeoJSONObject;
import com.cocoahero.android.geojson.LineString;
import com.cocoahero.android.geojson.Position;
import com.inspur.osp.AppConfig;
import com.inspur.osp.contract.CsTopoMapConstact;
import com.inspur.osp.data.bean.LaySegBean;
import com.inspur.osp.data.bean.LayerItemRequestbean;
import com.inspur.osp.data.source.remote.RemoteDataSource;
import com.inspur.osp.util.L;
import com.inspur.osp.util.ResourceUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.disposables.Disposable;

/**
 * Created by liuchen01 on 2019/10/18.
 */

public class TopoMapPresenter extends FragmentPresenter<CsTopoMapConstact.View> implements CsTopoMapConstact.Presenter {


    public TopoMapPresenter(Context mContext, CsTopoMapConstact.View view) {
        this.mContext = mContext;
        this.mView = view;
        this.mView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void desctroyView() {

    }

    @Override
    public void queryCheckedRouteByOnlyNe(String neId) {

        Disposable disposable = RemoteDataSource.getInstance().singleNeCheckedRoute(neId)
                .subscribe(neRouteBeanBaseBean -> {
                    mView.fillSingleNeRoute(neRouteBeanBaseBean.data);
                }, throwable -> selfExceptionDeal(throwable));

        compositeDisposable.add(disposable);
    }

    @Override
    public void queryTopoNetPort(String neId, String portId) {
        mView.setLoadingIndicator(true, "数据加载中");
        RemoteDataSource.getInstance().getNePortData(neId,portId)
                .subscribe(topoNePortBean -> {
                    mView.fillTopoNePortId(topoNePortBean);
                },throwable -> selfExceptionDeal(throwable));
    }

    @Override
    public void muiltyResQuery(List<LayerItemRequestbean> dataList, double xMin, double xMax, double yMin, double yMax) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put(AppConfig.COORDTYPE, AppConfig.GCJ02LL);
        dataMap.put(AppConfig.INDENTIFY_TPL, AppConfig.INDENTIFY_WX2_QUERY);
        dataMap.put("imageDisplay", "1910,941,96");
        dataMap.put("layerDef", dataList);
        dataMap.put("tolerance", "2");
        dataMap.put("mapExtent", xMin + "," + yMin + "," + xMax + "," + yMax);
        dataMap.put("xmax", xMax);
        dataMap.put("xmin", xMin);
        dataMap.put("ymax", yMax);
        dataMap.put("ymin", yMin);
//                    dataMap.put("where", "COUNTY_UUID in (" + user.authCountyIds + ")");
        Log.v("地图服务请求", dataList.toString());
        Disposable mutiDisposble = RemoteDataSource.getInstance().indentifyExtend(dataMap)
                .subscribe(taskBean -> {
                            //mView.setLoadingIndicator(false, null);
                            String layerId = dataList.get(0).layerId;
                            if (taskBean != null) {
                                if (null != taskBean.getResults()) {
                                    int size = taskBean.getResults().size();
                                    L.D("请求到的数据条数为" + size);
                                    if (size >= 1000) {
                                        L.D("-----------------超长了------------- ");
                                    } else {
                                        L.D("-----------------没有超长------------- ");
                                    }
                                    if (taskBean.getResults() != null && taskBean.getResults().size() > 0) {
                                        mView.packageData(taskBean, layerId);
//                                        mView.muiltyFillMap(taskBean);
                                    } else {
                                        mView.packageData(taskBean, layerId);
//                                        mView.muiltyFillMap(taskBean);
//                                        mView.showHint(mContext.getString(R.string.error_data_empty));
                                    }
                                } else {
                                    mView.packageData(taskBean, layerId);
                                }
                            } else {
                                mView.packageData(taskBean, layerId);
                                //mView.muiltyFillMap(taskBean);
                                //mView.showHint(mContext.getString(R.string.error_data_empty));
                            }
                        },
                        throwable -> {
                            mView.packageData(null, "");
                            selfExceptionDeal(throwable);
                        });
        compositeDisposable.add(mutiDisposble);


//        Disposable mutiDisposble = UsersRepository.getInstance(mContext)
//                .userLocalDataSource.getLastestUser()
//                .flatMap(user -> {
//                    for (LayerItemRequestbean layerItemRequestbean : dataList) {
//                        if (!TextUtils.isEmpty(layerItemRequestbean.filter)
//                                && !TextUtils.isEmpty(user.authCountyIds)
//                                && !"null".equals(user.authCountyIds)
//                                ) {
//                            layerItemRequestbean.filter =
//                                    new StringBuilder(" ( ")
//                                            .append(layerItemRequestbean.filter)
//                                            .append(" ) ")
////                                            .append(" and COUNTY_UUID in ( " + "'" + user.authCountyIds.replace(",", "','") + "'" + ")")
//                                            .toString();
//                        }
//                    }
//                    Map<String, Object> dataMap = new HashMap<>();
//                    dataMap.put(AppConfig.COORDTYPE, AppConfig.GCJ02LL);
//                    dataMap.put(AppConfig.INDENTIFY_TPL, AppConfig.INDENTIFY_WX2_QUERY);
//                    dataMap.put("imageDisplay", "1910,941,96");
//                    dataMap.put("layerDef", dataList);
//                    dataMap.put("tolerance", "2");
//                    dataMap.put("mapExtent", xMin + "," + yMin + "," + xMax + "," + yMax);
//                    dataMap.put("xmax", xMax);
//                    dataMap.put("xmin", xMin);
//                    dataMap.put("ymax", yMax);
//                    dataMap.put("ymin", yMin);
////                    dataMap.put("where", "COUNTY_UUID in (" + user.authCountyIds + ")");
//                    Log.v("地图服务请求", dataList.toString());
//                    return RemoteDataSource.getInstance().indentifyExtend(dataMap);
//                })

    }

    @Override
    public void queryTopoListBySubnet(String subnetId) {
        mView.setLoadingIndicator(true, "数据加载中");
        compositeDisposable.add(RemoteDataSource.getInstance().getTopoBySubnet(subnetId)
                .subscribe(topoBaseBean -> {
                    mView.setLoadingIndicator(false, null);
                    if (topoBaseBean.code == 0) {
                        mView.fillTopoList(topoBaseBean.data);
                    } else {
                        mView.showHint(topoBaseBean.msg);
                    }
                }, throwable -> selfExceptionDeal(throwable))
        );

    }

    @Override
    public void queryTopoListByIds(String ids) {
        mView.setLoadingIndicator(true, "数据加载中");
        compositeDisposable.add(RemoteDataSource.getInstance().getTopoByIds(ids)
                .subscribe(topoBaseBean -> {
                    mView.setLoadingIndicator(false, null);
                    if (topoBaseBean.code == 0) {
                        mView.fillTopoList(topoBaseBean.data);
                    } else {
                        mView.showHint(topoBaseBean.msg);
                    }
                }, throwable -> selfExceptionDeal(throwable))
        );
    }

    @Override
    public void queryTopoListByNe(String neIds) {
        mView.setLoadingIndicator(true, "数据加载中");
        compositeDisposable.add(RemoteDataSource.getInstance().getTopoByNe(neIds)
                .subscribe(topoBaseBean -> {
                    mView.setLoadingIndicator(false, null);
                    if (topoBaseBean.code == 0) {
                        mView.fillTopoList(topoBaseBean.data);
                    } else {
                        mView.showHint(topoBaseBean.msg);
                    }
                }, throwable -> selfExceptionDeal(throwable))
        );
    }

    @Override
    public void queryTopoRouteByTopoIds(String topoIds, String systemlevel) {
        mView.setLoadingIndicator(true, "数据请求中..");
        compositeDisposable.add(
                RemoteDataSource.getInstance().getTopoRouteByTopoids(topoIds, systemlevel)
                        .subscribe(topoRouteBean -> {
                            mView.setLoadingIndicator(false, null);
                            mView.fillTopoRouteList(topoRouteBean.data);
                        }, throwable -> selfExceptionDeal(throwable))
        );
    }

    @Override
    public void queryTopoRouteBindByTopoId(String topoid) {
        mView.setLoadingIndicator(true, "数据请求中...");
        compositeDisposable.add(
                RemoteDataSource.getInstance().getTopoRouteBindByTopoIdOlp(topoid)
                        .subscribe(cableSegBeans -> {
                            mView.setLoadingIndicator(false, null);
                            mView.fillTopoRouteBindList(cableSegBeans.data);

                        }, throwable -> selfExceptionDeal(throwable))
        );
    }

    @Override
    public void qeurySingleCableLaySeg(String ids) {
        mView.setLoadingIndicator(true, "数据请求中...");
        compositeDisposable.add(
                RemoteDataSource.getInstance().getTopoLaySeg(ids)
                        .doOnNext(laySegBeans -> {
                            if (laySegBeans.data != null && laySegBeans.data.size() > 0) {
                                for (LaySegBean laySegBean : laySegBeans.data) {
                                    GeoJSONObject geoJSONObject =
                                            ResourceUtil.parseWkt(laySegBean.getShape());
                                    if (geoJSONObject instanceof LineString) {
                                        List<LatLng> latLngs = new ArrayList();
                                        for (Position position : ((LineString) geoJSONObject).getPositions()) {
                                            latLngs.add(ResourceUtil.coordConverter(mContext, new LatLng(position.getLatitude(), position.getLongitude())));
                                        }
                                        laySegBean.setLatLngs(latLngs);
                                    }
                                }
                            }
                        })
                        .subscribe(laySegBeans -> {
                            mView.setLoadingIndicator(false, null);
                            mView.fillSingleCableLaySeg(laySegBeans.data);
                        }, throwable -> selfExceptionDeal(throwable))
        );
    }

    @Override
    public void queryAllCableLaySeg(String ids) {
        mView.setLoadingIndicator(true, "数据请求中...");
        compositeDisposable.add(
                RemoteDataSource.getInstance().getTopoLaySeg(ids)
                        .doOnNext(laySegBeans -> {
                            if (laySegBeans.data != null && laySegBeans.data.size() > 0) {
                                for (LaySegBean laySegBean : laySegBeans.data) {
                                    GeoJSONObject geoJSONObject =
                                            ResourceUtil.parseWkt(laySegBean.getShape());
                                    if (geoJSONObject instanceof LineString) {
                                        List<LatLng> latLngs = new ArrayList();
                                        for (Position position : ((LineString) geoJSONObject).getPositions()) {
                                            latLngs.add(ResourceUtil.coordConverter(mContext, new LatLng(position.getLatitude(), position.getLongitude())));
                                        }
                                        laySegBean.setLatLngs(latLngs);
                                    }
                                }
                            }
                        })
                        .subscribe(laySegBeans -> {
                            mView.setLoadingIndicator(false, null);
                            mView.fillAllCableLaySeg(laySegBeans.data);
                        }, throwable -> selfExceptionDeal(throwable))
        );
    }

}
