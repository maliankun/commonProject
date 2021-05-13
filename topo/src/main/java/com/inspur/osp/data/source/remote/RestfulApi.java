package com.inspur.osp.data.source.remote;

import com.inspur.osp.AppConfig.Urls;
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

import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by liuchen_ on 2017/12/14.
 */

public interface RestfulApi {

    @Headers({"urlname:login", "Content-Type:application/json"})
    @POST(Urls.URL_LOGIN)
    Flowable<Map<String, String>> login(
            @Body Map<String, Object> params
    );


    @Headers({"Content-Type:application/json"})
    @POST(Urls.URL_QUERY_RESOURCE_SINGLE)
    Flowable<TaskBean> singleResQuery(@Body Map<String, String> param);

    @Headers({"Content-Type:application/json"})
    @POST(Urls.URL_INDENTIFY_EXTENT)
    Flowable<TaskBean> indentifyExtend(@Body Map<String, Object> param);

    @Headers({"urlname:topo"})
    @POST(Urls.URL_GET_TOPO_LIST_IDS)
    Flowable<BaseBean<TopoBean>> getTopoByIds(@Query("topoIds") String topoIds);

    @Headers({"urlname:topo"})
    @POST(Urls.URL_GET_TOPO_LIST_SUBNET)
    Flowable<BaseBean<TopoBean>> getTopoBySubnet(@Query("subnetId") String subnetId);

    @Headers({"urlname:topo"})
    @POST(Urls.URL_GET_TOPO_LIST_NE)
    Flowable<BaseBean<TopoBean>> getTopoByNe(@Query("neIds") String neIds);

    @Headers({"urlname:topo"})
    @POST(Urls.URL_GET_TOPPO_ROUTE_BY_TOPO_ID)
    Flowable<BaseObjBean<TopoRouteBean>> getTopoRouterByTopoId(@Query("topoLinkId") String topoLinkId,
                                                @Query("serviceLevel") String serviceLevel);

    @Headers({"urlname:topo"})
    @POST(Urls.URL_GET_TOPPO_ROUTE_BIND_BY_TOPO_ID)
    Flowable<List<CableSegBean>> getTopoRouteBindByTopoId(@Query("topoLinkId") String topoLinkId);

    @Headers({"urlname:topo"})
    @POST(Urls.URL_GET_TOPO_LAY_SEG)
    Flowable<BaseObjBean<List<LaySegBean>>> getTopoLaySegs(@Query("ids") String id);

    @GET("/devicetopo/api/v1/topoManager/getTopoNeByNeId")
    Flowable<TopoNePortBean> getNePortData(@Query("neId") String paramString1, @Query("portId") String paramString2);



    @Headers({"urlname:topo"})
    @POST("/wx_topo/newTopoRouteController/queryCheckedRoute4OLP")
    Flowable<BaseBean<TopoCheckedRouteBean>> getTopoRouteBindByTopoIdOlp(String paramString);

    @Headers({"urlname:topo"})
    @POST(Urls.URL_GET_OLP_DEVICE_CARD)
    Flowable<List<LaySegBean>> getOlpDeviceCard(@Query("cardId") String cardId);

    @Headers({"urlname:topo"})
    @POST(Urls.URL_GET_OLP_CARD)
    Flowable<List<LaySegBean>> getOlpCard(@Query("neId") String neId);

    @Headers({"urlname:topo"})
    @POST(Urls.URL_SAVE_CHECKED_ROUTE)
    Flowable<BaseBean> saveCheckedRoute(@Query("topolLinkId") String topolLinkId, @Query("cableids") String cableids);

    @Headers({"urlname:topo"})
    @POST(Urls.URL_SAVE_CHECKED_ROUTE_4OLP)
    Flowable<BaseBean> saveCheckedRoute4Olp(@Query("topolLinkId") String topolLinkId,
                                            @Query("cableids") String cableids,
                                            @Query("orig_ne_id_olp") String orig_ne_id_olp,
                                            @Query("orig_card_id_olp") String orig_card_id_olp,
                                            @Query("dest_ne_id_olp") String dest_ne_id_olp,
                                            @Query("dest_card_id_olp") String dest_card_id_olp);

    @Headers({"urlname:topo"})
    @POST(Urls.URL_GET_CHECKED_ROUTE_CABLE_ID)
    Flowable<CheckRouteStateBean> getCheckedRouteByCableId(@Query("topoLinkId") String topoLinkId, @Query("cableIds") String cableIds);

    @Headers({"urlname:topo"})
    @POST(Urls.URL_GET_OLP_BY_ROOM)
    Flowable<BaseSingleBean<OlpByRoom>> getOlpDeviceByRoom(@Query("orig_room_id") String orig_room_id, @Query("dest_room_id") String dest_room_id);

    @Headers({"urlname:topo"})
    @POST(Urls.URL_GET_OLP_CARD_BY_OLP)
    Flowable<BaseBean<OlpByRoom.OLPDeviceBean>> getOlpCardByOlp(@Query("neId") String neId);

    @Headers({"urlname:topo"})
    @POST(Urls.URL_GET_END_INFO_BY_OLP_CARD)
    Flowable<BaseSingleBean<OlpCardEndInfo>> getEndInfoByOlpCard(@Query("cardId") String cardId);

    @Headers({"urlname:topo"})
    @POST(Urls.URL_CANCEL_CHECKED_ROUTE_CABLEID)
    Flowable<BaseBean> cancelCheckRouteCableId(@Query("topoLinkId") String topoLinkId, @Query("cableIds") String cableids);

    @Headers({"urlname:topo"})
    @POST(Urls.URL_CANCEL_CHECKED_ROUTE)
    Flowable<BaseBean> cancelCheckedRoute(@Query("uuid") String uuid);

    @Headers({"urlname:topo"})
    @POST(Urls.URL_ONLY_ROUTE_SEARCH)
    Flowable<BaseBean<NeRouteBean>> singleNeCheckedRoute(@Query("neId") String uuid);



    @GET(Urls.URL_GET_SUBNET_BY_ELEMENT)
    Flowable<ElementSubnetInfo> getSubnetInfo(@Query("aNeId") String aNeId,@Query("aPortId") String aPortId,@Query("zNeId") String zNeId,@Query("zPortId") String zPortId);


    @POST(Urls.URL_FILE_UPLOAD)
    Flowable<ResponseBody> upLoad(@Body RequestBody Body);

    @Headers({"Content-Type:application/json"})
    @POST(Urls.URL_REGION_TRANSFER)
    Flowable<ACodeBean> regionTransfer(@Body AcodePost adcode);

}
