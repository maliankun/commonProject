package com.inspur.osp.data.source.remote;

import android.util.Log;

import com.cocoahero.android.geojson.GeoJSON;
import com.cocoahero.android.geojson.GeoJSONObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.inspur.osp.AppConfig;
import com.inspur.osp.data.bean.ACodeBean;
import com.inspur.osp.data.bean.AcodePost;
import com.inspur.osp.data.bean.BaseBean;
import com.inspur.osp.data.bean.BaseObjBean;
import com.inspur.osp.data.bean.BaseSingleBean;
import com.inspur.osp.data.bean.CableSegBean;
import com.inspur.osp.data.bean.CheckRouteStateBean;
import com.inspur.osp.data.bean.ElementSubnetInfo;
import com.inspur.osp.data.bean.LaySegBean;
import com.inspur.osp.data.bean.NeRouteBean;
import com.inspur.osp.data.bean.OlpByRoom;
import com.inspur.osp.data.bean.OlpCardEndInfo;
import com.inspur.osp.data.bean.TaskBean;
import com.inspur.osp.data.bean.TopoBean;
import com.inspur.osp.data.bean.TopoCheckedRouteBean;
import com.inspur.osp.data.bean.TopoNePortBean;
import com.inspur.osp.data.bean.TopoRouteBean;
import com.inspur.osp.data.source.okclient.OkClientInstance;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by liuchen_ on 2017/12/14.
 */

public class RemoteDataSource implements IRemoteDataSource {

    private static RemoteDataSource INSTANCE;

    private RestfulApi mRestfulApi;
    private RestfulApi mRestfulApiTest;
    private RestfulApi mRetrofitApiOrder;
    private Gson mGson;

    Retrofit mRetrofit;
    Retrofit mRetrofitTest;
    Retrofit mRetrofitOrder;


    public static RemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RemoteDataSource();
        }
        return INSTANCE;
    }

    public Retrofit getmRetrofit() {
        return mRetrofit;
    }

    public void setmRetrofit(Retrofit retrofit) {
        this.mRetrofit = retrofit;
        mRestfulApi = mRetrofit.create(RestfulApi.class);
    }

    public Retrofit getmRetrofitTest() {
        return mRetrofitTest;
    }

    public void setmRetrofitTest(Retrofit mRetrofitTest) {
        this.mRetrofitTest = mRetrofitTest;
        mRestfulApiTest = mRetrofitTest.create(RestfulApi.class);
    }

    public Retrofit getmRetrofitOrder() {
        return mRetrofitOrder;
    }

    public void setmRetrofitOrder(Retrofit mRetrofitOrder) {
        this.mRetrofitOrder = mRetrofitOrder;
        mRetrofitApiOrder = mRetrofitOrder.create(RestfulApi.class);
    }

    private RemoteDataSource() {
        mGson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .enableComplexMapKeySerialization() //支持Map的key为复杂对象的形式
                .serializeNulls()
                .create();

        mRetrofit = new Retrofit.Builder()
                .client(OkClientInstance.getClient())
                .baseUrl(AppConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(mGson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        mRestfulApi = mRetrofit.create(RestfulApi.class);

        mRetrofitTest = new Retrofit.Builder()
                .client(OkClientInstance.getClient())
                //.baseUrl("http://192.168.1.106:8080")
                .baseUrl(AppConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(mGson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        mRestfulApiTest = mRetrofitTest.create(RestfulApi.class);

        mRetrofitOrder = new Retrofit.Builder()
                .client(OkClientInstance.getClient())
                //.baseUrl("http://192.168.1.106:8080")
                .baseUrl(AppConfig.ORDER_URL)
                .addConverterFactory(GsonConverterFactory.create(mGson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        mRetrofitApiOrder = mRetrofitOrder.create(RestfulApi.class);
    }

    public RestfulApi getRmoteDataService() {
        return mRestfulApi;
    }



    @Override
    public Flowable<TaskBean> indentifyExtend(Map<String, Object> param) {
        return mRestfulApi.indentifyExtend(param)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(taskBean -> {
                    if (null != taskBean.getResults()) {
                        for (TaskBean.ResultsBean resultsBean : taskBean.getResults()) {
                            String jsonG = resultsBean.getGeoJson().toString();
//                        Timber.d(jsonG);
                            JSONObject jsonObject = new JSONObject(jsonG);
                            GeoJSONObject geoJSON = GeoJSON.parse(jsonObject);
                            resultsBean.setmGeoJsonObject(geoJSON);
                        }
                    }

                });
    }

    @Override
    public Flowable<BaseBean<NeRouteBean>> singleNeCheckedRoute(String uuid) {
        return mRestfulApi.singleNeCheckedRoute(uuid)
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    @Override
    public Flowable<BaseBean<TopoBean>> getTopoByIds(String topoIds) {
        return mRestfulApi.getTopoByIds(topoIds)
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Flowable<BaseBean<TopoBean>> getTopoBySubnet(String subnetId) {
        return mRestfulApi.getTopoBySubnet(subnetId)
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Flowable<BaseObjBean<TopoRouteBean>> getTopoRouteByTopoids(String topoids, String serviceLevel) {
        return mRestfulApi.getTopoRouterByTopoId(topoids, serviceLevel)
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Flowable<List<CableSegBean>> getTopoRouteBindByTopoId(String topoid) {
        return mRestfulApi.getTopoRouteBindByTopoId(topoid)
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Flowable<BaseBean<TopoCheckedRouteBean>> getTopoRouteBindByTopoIdOlp(String topoid) {
        return mRestfulApi.getTopoRouteBindByTopoIdOlp(topoid)
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Flowable<BaseObjBean<List<LaySegBean>>> getTopoLaySeg(String cableId) {
        return mRestfulApi.getTopoLaySegs(cableId)
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Flowable<CheckRouteStateBean> getCheckedRouteByCableId(String topoLinkId, String cableIds) {
        return mRestfulApi.getCheckedRouteByCableId(topoLinkId, cableIds)
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Flowable<BaseBean> saveCheckedRoute(String topolLinkId, String cableids) {
        return mRestfulApi.saveCheckedRoute(topolLinkId, cableids)
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Flowable<BaseBean> saveCheckedRoute4Olp(String topolLinkId, String cableids, String orig_ne_id_olp, String orig_card_id_olp, String dest_ne_id_olp, String dest_card_id_olp) {
        return mRestfulApi.saveCheckedRoute4Olp(topolLinkId, cableids, orig_ne_id_olp, orig_card_id_olp, dest_ne_id_olp, dest_card_id_olp)
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Flowable<BaseSingleBean<OlpByRoom>> getOlpDeviceByRoom(String orig_room_id, String dest_room_id) {
        return mRestfulApi.getOlpDeviceByRoom(orig_room_id, dest_room_id)
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Flowable<BaseBean<OlpByRoom.OLPDeviceBean>> getOlpCardByOlp(String neId) {
        return mRestfulApi.getOlpCardByOlp(neId)
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Flowable<BaseSingleBean<OlpCardEndInfo>> getEndInfoByOlpCard(String cardId) {
        return mRestfulApi.getEndInfoByOlpCard(cardId)
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Flowable<BaseBean> cancelCheckRouteCableId(String topoLinkId, String cableids) {
        return mRestfulApi.cancelCheckRouteCableId(topoLinkId, cableids)
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Flowable<BaseBean> cancelCheckedRoute(String uuid) {
        return mRestfulApi.cancelCheckedRoute(uuid)
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    @Override
    public Flowable<BaseBean<TopoBean>> getTopoByNe(String neIds) {
        return mRestfulApi.getTopoByNe(neIds)
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Flowable<ElementSubnetInfo> getsubnetElement(String aNeId, String aPortId, String zNeId, String zPortId) {
        return mRestfulApi.getSubnetInfo(aNeId,aPortId,zNeId,zPortId)
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Flowable<TopoNePortBean> getNePortData(String netId, String portId) {
        return mRestfulApi.getNePortData(netId,portId)
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Flowable<ResponseBody> upLoad(RequestBody Body) {
        return mRestfulApi.upLoad(Body)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io());
    }

    @Override
    public Flowable<ACodeBean> regionTransfer(AcodePost acodePost) {
        return mRestfulApi.regionTransfer(acodePost)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
