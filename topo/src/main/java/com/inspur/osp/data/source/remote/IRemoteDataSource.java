package com.inspur.osp.data.source.remote;

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
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.Query;

/**
 * Created by liuchen_ on 2017/12/14.
 */

public interface IRemoteDataSource {


    Flowable<BaseBean<TopoBean>> getTopoByIds(String topoIds);

    Flowable<BaseBean<TopoBean>> getTopoBySubnet(String subnetId);

    Flowable<BaseBean<TopoBean>> getTopoByNe(String neIds);

    Flowable<BaseObjBean<TopoRouteBean>> getTopoRouteByTopoids(String topoids, String serviceLevel);

    Flowable<List<CableSegBean>> getTopoRouteBindByTopoId(String topoid);

    Flowable<BaseBean<TopoCheckedRouteBean>> getTopoRouteBindByTopoIdOlp(String paramString);

    Flowable<BaseObjBean<List<LaySegBean>>> getTopoLaySeg(String cableId);

    Flowable<CheckRouteStateBean> getCheckedRouteByCableId(String topoLinkId, String cableIds);


    Flowable<BaseBean> saveCheckedRoute(String topolLinkId, String cableids);

    Flowable<BaseBean> saveCheckedRoute4Olp(String topolLinkId,
                                            String cableids,
                                            String orig_ne_id_olp,
                                            String orig_card_id_olp,
                                            String dest_ne_id_olp,
                                            String dest_card_id_olp);

    Flowable<BaseSingleBean<OlpByRoom>> getOlpDeviceByRoom(String orig_room_id, String dest_room_id);

    Flowable<BaseBean<OlpByRoom.OLPDeviceBean>> getOlpCardByOlp(String neId);

    Flowable<BaseSingleBean<OlpCardEndInfo>> getEndInfoByOlpCard(String cardId);

    Flowable<BaseBean> cancelCheckRouteCableId(String topoLinkId, String cableids);

    Flowable<BaseBean> cancelCheckedRoute(String uuid);

    //组合条件查找
    Flowable<TaskBean> indentifyExtend(@Body Map<String, Object> param);

    Flowable<BaseBean<NeRouteBean>> singleNeCheckedRoute(@Query("neId") String uuid);

    Flowable<ElementSubnetInfo> getsubnetElement(String aNeId, String aPortId, String zNeId, String zPortId);

    Flowable<TopoNePortBean> getNePortData(String netId, String portId);

    Flowable<ResponseBody> upLoad(RequestBody Body);

    //获得高德地图转换后的地域信息
    Flowable<ACodeBean> regionTransfer(@Body AcodePost acodePost);


}
