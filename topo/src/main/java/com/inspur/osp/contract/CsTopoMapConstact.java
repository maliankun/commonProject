package com.inspur.osp.contract;

import com.amap.api.maps.AMap;
import com.amap.api.maps.model.LatLng;
import com.inspur.osp.BasePresenter;
import com.inspur.osp.BaseView;
import com.inspur.osp.data.bean.LaySegBean;
import com.inspur.osp.data.bean.LayerItemRequestbean;
import com.inspur.osp.data.bean.NeRouteBean;
import com.inspur.osp.data.bean.TaskBean;
import com.inspur.osp.data.bean.TopoBean;
import com.inspur.osp.data.bean.TopoCheckedRouteBean;
import com.inspur.osp.data.bean.TopoNePortBean;
import com.inspur.osp.data.bean.TopoRouteBean;

import java.util.List;

/**
 * Created by liuchen_ on 2017/12/15.
 */

public class CsTopoMapConstact {

    public interface View extends BaseView<CsTopoMapConstact.Presenter> {


        LatLng getCurrentPosition();

        void muiltyFillMap(TaskBean taskBean);

        void packageData(TaskBean taskBean, String packageData);

        void fillTopoList(List<TopoBean> topoBeanList);

        void fillTopoRouteList(TopoRouteBean topoRouteBean);

        void fillTopoRouteBindList(List<TopoCheckedRouteBean> cableSegBeans);

        void fillSingleCableLaySeg(List<LaySegBean> laySegBeans);

        void fillAllCableLaySeg(List<LaySegBean> laySegBeans);

        void fillSingleNeRoute(List<NeRouteBean> neRouteBean );

        void fillTopoNePortId(TopoNePortBean param1TopoNePortBean);

        AMap getAmap();


//        void filterUserAuth(List<String> authList);

//        List<Object> getNearByResourceList();

//        void fillCells(AllMajorResourceItem allMajorResourceItem);

//        void muiltyFillCellMap(AllMajorResourceItem allMajorResourceItem);

    }

    public interface Presenter extends BasePresenter {

//        public void singleResQuery(String key, String inputContent);

//        void accurateQueryResource(String uuid);

        void muiltyResQuery(List<LayerItemRequestbean> dataList, double xMin, double xMax, double yMin, double yMax);

        void queryTopoListBySubnet(String subnetId);//根据子网

        void queryTopoListByIds(String ids);//根据topo连接的id拼接

        void queryTopoListByNe(String neIds);//根据两端网元

        void queryTopoRouteByTopoIds(String topoIds, String systemlevel);

        void queryTopoRouteBindByTopoId(String topoid);

        void qeurySingleCableLaySeg(String ids);

        void queryAllCableLaySeg(String ids);


        void queryCheckedRouteByOnlyNe(String neId);

        void queryTopoNetPort(String neId, String portId);

//        void resourceResQuery(LayerItemRequestbean dataItem, double xMin, double xMax, double yMin, double yMax);

//        void getUserAuth();

//        void singleCellQuery(String type, String paramId, String seaCon, int start, int limit);

//        void muiltyCellQuery(String type, String paramId, String seaCon, int start, int limit);

    }
}
