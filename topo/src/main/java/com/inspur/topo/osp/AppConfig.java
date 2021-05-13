package com.inspur.topo.osp;

/**
 * Created by liuchen_ on 2017/12/14.
 */

public class AppConfig {


    /*public static final String BASE_URL = "http://10.92.1.197:8080";
    public static final String BASE_URL_LOCAL = "http://10.92.1.197:8080";
    public static final String BASE_URL_AUTH = "http://10.92.1.197:8080";*/

    //正式
    public static String BASE_URL = "http://218.207.69.236:6105";
    public static String BASE_URL_LOCAL = "http://218.207.69.236:6105";
    public static String BASE_URL_AUTH = "http://218.207.69.236:6105";
    public static String TOPO_URL = "http://218.207.69.236:6105/devicetopo";

    //测试
//    public static String BASE_URL = "http://10.91.0.95:8080";
//    public static String BASE_URL_AUTH = "http://10.91.0.95:8080";
//    public static String BASE_URL_LOCAL = "http://10.91.0.95:8080";
//    public static String TOPO_URL = "http://10.91.0.118:8082";
    public static String TOPO_ROUTE_URL = "http://10.110.2.60:8787";


    public static String ORDER_URL = "http://10.110.2.60:7004";

    public static final String TOKEN = "Authorization";
    public static final String TAG_ERROR = "error";

    public static boolean IS_TEST = true;
    public static final String IP = "";

    //坐标类型
    public static final String COORDTYPE = "coordtype";
    public static final String GCJ02LL = "gcj02ll";

    public static final String GISSHOW_RESTYPE = "GISSHOW_RESTYPE";

    //查询类型
    public static final String INDENTIFY_TPL = "indentify_tpl";
    public static final String INDENTIFY_WX2_QUERY = "indentify_wx2_query";
    public static final String INDENTIFY_BREAKDOWN = "identify_breakdown";

    public static class Urls {
        public static final String URL_LOGIN = "/api/authenticate";//登陆
        public static final String URL_QUERY_RESOURCE_SINGLE = "/geoservice/api/v1/queryTask/execute";//单资源搜索 layId && where name like ?
        public static final String URL_INDENTIFY_EXTENT = "/geoservice/api/v1/indentifyExtent";//多资源搜索
        public static final String URL_FIND_TASK = "/geoservice/api/v1/findTask";
        public static final String URL_DEVICE_CONNECT = "/wxappserver/api/v2/app/deviceconroute";
        public static final String URL_PANEL_SHOW = "/roomdesign/rack/getRackEquipData.ilf";
        public static final String URL_CABLE_RACK = "/roomdesign/jumper/getCableByRackTermination.ilf";
        public static final String URL_RES_AUTH = "/authservice/api/v1/ossAuthController/authLogin";//获取权限需要账号密码
        public static final String URL_RES_AUTH_APP = "/authservice/api/v1/ossAuthController/authLoginApp";//获取权需要账号不需要密码
        public static final String URL_GET_VERSION = "/wxappserver/app/getversion";//获取版本号
        public static final String URL_DOWNLOAD = "/wxappserver/download";//下载app版本
        public static final String URL_GET_ALL_MODEL = "/configcenter/api/v2/configdata/models/hebei_wx2";

        public static final String URL_CITY_ENUM = "/configcenter/api/v2/configdata/models/hebei_wx2";

        public static final String URL_TASK_ADD = "/modelmanager/api/v2/modelserver/pl_task/_batchUpdate";

        public static final String URL_FILE_UPLOAD = "/fileload/api/v1/upfile";
        public static final String URL_TASK_DELETE = "/drawingedit/api/v2/route/updateGeoTask";
        public static final String URL_APP_SITE_DETAIL = "/wxappserver/api/v2/app/wirelessresource/getSiteInfo";
        public static final String URL_APP_BTS_TOTAL = "/wxappserver/api/v2/app/wirelessresource/getBtsTotalInfo";
        public static final String URL_APP_WIRELESSRESOURCE_DETAIL = "/wxappserver/api/v2/app/wirelessresource/wirelessResourceDetail";
        public static final String URL_APP_WIRELESSRESOURCE_DETAIL2 = "/wxappserver/api/v2/app/wirelessresource/getWirelessResourceDetail2";
        public static final String URL_REGION_TRANSFER = "/routeinspection/api/v1/gdregiontransfer";
        public static final String URL_TRANSDEVICELIST = "/wxappserver/api/v2/app/transelement";
        public static final String URL_DEVICETOPOLIST = "/wxappserver/api/v2/app/transelementsubnet";
        public static final String URL_BREAK_ADD = "/drawingedit/api/v2/breakdown/saveGeobreak";
        public static final String URL_GETUSERAUTH = "/authservice/api/v1/ossAuthController/getUserAuth";

        public static final String JIKE_RESOURCE = "/wxappserver/api/v1/allmajor/getjkdonghuanresourceinfo";
        public static final String URL_JIAKE_RESOURCE = "/wxappserver/api/v1/allmajor/getAllmajorJiake";
        public static final String URL_RESOURCE_DETAIL = "/wxappserver/api/v1/allmajor/getAllmajorResourceDetail";
        // public static final String URL_ALLMAJOR_FILE_UPLOAD = "/wxappserver/api/v1/routeInspect/upfile";
        public static final String URL_ALLMAJOR_FILE_UPLOAD = "/wxappserver/api/v1/allmajor/upfile";
        public static final String URL_ALLMAJOR_ORDER = "/wxappserver/api/v1/allmajor/saveorder";
        public static final String URL_FILE_DOWNLOAD = "/fileload/api/v1/download";

        public static final String URL_TASK_MODIFY_RESOURCE_QUERY = "/drawingedit/api/v2/point/getTaskModifyResource";

        public static final String URL_WORK_ORDER = "/wxappserver/api/v1/allmajor/order";
        public static final String URL_WORK_ORDER_DETAIL = "/wxappserver/api/v1/allmajor/orderDetail";
        public static final String URL_CLEAR_ORDER_CACHE = "/wxappserver/api/v1/allmajor/clearOrderCache";

        public static final String URL_POS_PORT_INFO = "/wxappserver/api/v1/allmajor/getAllmajorPosPortInfo";
        public static final String URL_POS_PORT_CUSTOMINFO = "/wxappserver/api/v1/allmajor/getAllmajorPosPortCustomInfo";
        public static final String URL_GEO_TASK_RECORD = "/drawingedit/api/v2/route/geoTaskRecord";

        public static final String URL_GET_TOPO_LIST_SUBNET = "/wx_topo/topoRouteController/queryTopoAndRouteBySubnet";//根据子网
        public static final String URL_GET_TOPO_LIST_IDS = "/wx_topo/topoRouteController/queryTopoListByIds";//根据拓扑连接id
        public static final String URL_GET_TOPO_LIST_NE = "/wx_topo/topoRouteController/queryTopoListByNe";
        public static final String URL_GET_TOPPO_ROUTE_BY_TOPO_ID = "/wx_topo/topoRouteController/topoRoute";//根据拓扑连接id，查询可用拓扑路由
        public static final String URL_GET_TOPPO_ROUTE_BIND_BY_TOPO_ID = "/wx_topo/topoRouteController/queryCheckedRoute";//根据拓扑连接id，查询已绑定的路由数据
        public static final String URL_GET_TOPO_LAY_SEG = "/wx_topo/topoRouteController/topoLayseg";//根据光缆段id查询敷设段空间数据
        public static final String URL_GET_TOPO_ROUTE_BIND_OLP_TOPO_ID = "/wx_topo/topoRouteController/queryCheckedRoute4OLP";//OLP查询拓扑路由绑定数据
        public static final String URL_GET_OLP_DEVICE_CARD = "/wx_topo/topoRouteController/queryEndInfoByOLPCard";//查询OLP板卡对端设备与板卡
        public static final String URL_GET_OLP_CARD = "/wx_topo/topoRouteController/queryOLPCardByNe";//查询OLP设备下板卡
        public static final String URL_SAVE_CHECKED_ROUTE_4OLP = "/wx_topo/topoRouteController/saveCheckedRoute4OLP";//保存OLP拓扑路由的绑定路由数据
        public static final String URL_SAVE_CHECKED_ROUTE = "/wx_topo/topoRouteController/saveCheckedRoute";//保存拓扑绑定路由数据
        public static final String URL_GET_CHECKED_ROUTE_CABLE_ID = "/wx_topo/topoRouteController/queryCheckedRouteByCableId";//根据光缆路由和拓扑查询绑定关系
        public static final String URL_GET_OLP_BY_ROOM = "/wx_topo/topoRouteController/queryOLPByAZRoom";//查询AZ两端机房 下的OLP设备
        public static final String URL_GET_OLP_CARD_BY_OLP = "/wx_topo/topoRouteController/queryOLPCardByNe";//查询OLP设备下板卡
        public static final String URL_GET_END_INFO_BY_OLP_CARD = "/wx_topo/topoRouteController/queryEndInfoByOLPCard";//查询OLP板卡对端设备与板卡
        public static final String URL_CANCEL_CHECKED_ROUTE_CABLEID = "/wx_topo/topoRouteController/cancelCheckedRouteByCableId";//根据光缆路由和拓扑解绑绑定关系
        public static final String URL_CANCEL_CHECKED_ROUTE = "/wx_topo/topoRouteController/cancelCheckedRoute";//解绑路由

        public static final String URL_SEARCH_POINT_BY_NAME = "queryResource";
        public static final String URL_SPARE_ROUTE = "spareRoute";


    }

    public static class Constants {
        public final static int TOPO_LIST_IDS = 1;
        public final static int TOPO_LIST_SUB_NET = 2;
        public final static int TOPO_LIST_NE_IDS = 3;
    }

    public static class TerminalStatus {

        public final static int IDLE = 0;//空闲
        public final static int TERMINATION = 1;//成端
        public final static int JUMPER = 2;//跳接
        public final static int OCCUPANCY = 3;//占用
        public final static int NUMER = -1;//数字
        public final static int TAG = -2;//数字
        public final static int NO_TAG = -3;//数字

    }

    public static final String JSON_SITES_SEARCH =
            "{\"success\":\"true\",\"msg\":\"\",\"num\":1,\"content\":[{\"uuid\":\"787696939\",\"name\":\"LL-冀东商贸1-A4B7光交接箱\"}]}";

    public static final String JSON_KANCHA_RESULT =
            "{\n" +
                    "\t\"success\": \"true\",\n" +
                    "\t\"content\": [{\n" +
                    "\t\t\"type\": \"first\",\n" +
                    "\t\t\"jumpCounts\": 6,\n" +
                    "\t\t\"jumpLength\": 2184,\n" +
                    "\t\t\"cableNodes\": [{\n" +
                    "\t\t\t\"sno\": 1,\n" +
                    "\t\t\t\"name\": \"秦皇岛卢龙县原县医院住宅0001号交接箱\",\n" +
                    "\t\t\t\"typecode\": \"9203\",\n" +
                    "\t\t\t\"uuid\": \"1501733427\",\n" +
                    "\t\t\t\"longitude\": \"118.86337533\",\n" +
                    "\t\t\t\"latitude\": \"39.88020786\"\n" +
                    "\t\t}, {\n" +
                    "\t\t\t\"sno\": 2,\n" +
                    "\t\t\t\"name\": \"LL-卢龙移动西1-A4B4光交箱\",\n" +
                    "\t\t\t\"typecode\": \"9203\",\n" +
                    "\t\t\t\"uuid\": \"437449416\",\n" +
                    "\t\t\t\"longitude\": \"118.86499768\",\n" +
                    "\t\t\t\"latitude\": \"39.88109356\"\n" +
                    "\t\t}, {\n" +
                    "\t\t\t\"sno\": 3,\n" +
                    "\t\t\t\"name\": \"LL-卢龙移动西1-A4光交箱\",\n" +
                    "\t\t\t\"typecode\": \"9203\",\n" +
                    "\t\t\t\"uuid\": \"488428497\",\n" +
                    "\t\t\t\"longitude\": \"118.86520359\",\n" +
                    "\t\t\t\"latitude\": \"39.88378042\"\n" +
                    "\t\t}, {\n" +
                    "\t\t\t\"sno\": 4,\n" +
                    "\t\t\t\"name\": \"秦皇岛卢龙卢龙\",\n" +
                    "\t\t\t\"typecode\": \"9503\",\n" +
                    "\t\t\t\"uuid\": \"538\",\n" +
                    "\t\t\t\"longitude\": \"118.87859538\",\n" +
                    "\t\t\t\"latitude\": \"39.89103892\"\n" +
                    "\t\t}, {\n" +
                    "\t\t\t\"sno\": 5,\n" +
                    "\t\t\t\"name\": \"秦皇岛卢龙冀东商贸1\",\n" +
                    "\t\t\t\"typecode\": \"9503\",\n" +
                    "\t\t\t\"uuid\": \"407872418\",\n" +
                    "\t\t\t\"longitude\": \"118.89416\",\n" +
                    "\t\t\t\"latitude\": \"39.90975\"\n" +
                    "\t\t}, {\n" +
                    "\t\t\t\"sno\": 6,\n" +
                    "\t\t\t\"name\": \"LL-冀东商贸1-A4光交接箱\",\n" +
                    "\t\t\t\"typecode\": \"9203\",\n" +
                    "\t\t\t\"uuid\": \"787697029\",\n" +
                    "\t\t\t\"longitude\": \"118.88760331\",\n" +
                    "\t\t\t\"latitude\": \"39.91206002\"\n" +
                    "\t\t}, {\n" +
                    "\t\t\t\"sno\": 7,\n" +
                    "\t\t\t\"name\": \"LL-冀东商贸1-A4B7光交接箱\",\n" +
                    "\t\t\t\"typecode\": \"9203\",\n" +
                    "\t\t\t\"uuid\": \"787696939\",\n" +
                    "\t\t\t\"longitude\": \"118.89773744\",\n" +
                    "\t\t\t\"latitude\": \"39.9117785\"\n" +
                    "\t\t}]\n" +
                    "\t}, {\n" +
                    "\t\t\"type\": \"second\",\n" +
                    "\t\t\"jumpCounts\": 10,\n" +
                    "\t\t\"jumpLength\": 3565,\n" +
                    "\t\t\"cableNodes\": [{\n" +
                    "\t\t\t\"sno\": 1,\n" +
                    "\t\t\t\"name\": \"秦皇岛卢龙县原县医院住宅0001号交接箱\",\n" +
                    "\t\t\t\"typecode\": \"9203\",\n" +
                    "\t\t\t\"uuid\": \"1501733427\",\n" +
                    "\t\t\t\"longitude\": \"118.86337533\",\n" +
                    "\t\t\t\"latitude\": \"39.88020786\"\n" +
                    "\t\t}, {\n" +
                    "\t\t\t\"sno\": 2,\n" +
                    "\t\t\t\"name\": \"LL-卢龙移动西1-A4B4光交箱\",\n" +
                    "\t\t\t\"typecode\": \"9203\",\n" +
                    "\t\t\t\"uuid\": \"437449416\",\n" +
                    "\t\t\t\"longitude\": \"118.86499768\",\n" +
                    "\t\t\t\"latitude\": \"39.88109356\"\n" +
                    "\t\t}, {\n" +
                    "\t\t\t\"sno\": 3,\n" +
                    "\t\t\t\"name\": \"LL-卢龙移动西1-A4光交箱\",\n" +
                    "\t\t\t\"typecode\": \"9203\",\n" +
                    "\t\t\t\"uuid\": \"488428497\",\n" +
                    "\t\t\t\"longitude\": \"118.86520359\",\n" +
                    "\t\t\t\"latitude\": \"39.88378042\"\n" +
                    "\t\t}, {\n" +
                    "\t\t\t\"sno\": 4,\n" +
                    "\t\t\t\"name\": \"秦皇岛卢龙卢龙\",\n" +
                    "\t\t\t\"typecode\": \"9503\",\n" +
                    "\t\t\t\"uuid\": \"538\",\n" +
                    "\t\t\t\"longitude\": \"118.87859538\",\n" +
                    "\t\t\t\"latitude\": \"39.89103892\"\n" +
                    "\t\t}, {\n" +
                    "\t\t\t\"sno\": 5,\n" +
                    "\t\t\t\"name\": \"LL-卢龙移动东1-A3光交箱\",\n" +
                    "\t\t\t\"typecode\": \"9203\",\n" +
                    "\t\t\t\"uuid\": \"488348631\",\n" +
                    "\t\t\t\"longitude\": \"118.87800476\",\n" +
                    "\t\t\t\"latitude\": \"39.88069527\"\n" +
                    "\t\t}, {\n" +
                    "\t\t\t\"sno\": 6,\n" +
                    "\t\t\t\"name\": \"秦皇岛卢龙卢龙妇幼医院\",\n" +
                    "\t\t\t\"typecode\": \"9503\",\n" +
                    "\t\t\t\"uuid\": \"277918981\",\n" +
                    "\t\t\t\"longitude\": \"118.877766\",\n" +
                    "\t\t\t\"latitude\": \"39.878711\"\n" +
                    "\t\t}, {\n" +
                    "\t\t\t\"sno\": 7,\n" +
                    "\t\t\t\"name\": \"秦皇岛中国人民银行卢龙分行\",\n" +
                    "\t\t\t\"typecode\": \"9503\",\n" +
                    "\t\t\t\"uuid\": \"464467694\",\n" +
                    "\t\t\t\"longitude\": \"118.885599\",\n" +
                    "\t\t\t\"latitude\": \"39.882102\"\n" +
                    "\t\t}, {\n" +
                    "\t\t\t\"sno\": 8,\n" +
                    "\t\t\t\"name\": \"秦皇岛卢龙卢龙\",\n" +
                    "\t\t\t\"typecode\": \"9503\",\n" +
                    "\t\t\t\"uuid\": \"538\",\n" +
                    "\t\t\t\"longitude\": \"118.87859538\",\n" +
                    "\t\t\t\"latitude\": \"39.89103892\"\n" +
                    "\t\t}, {\n" +
                    "\t\t\t\"sno\": 9,\n" +
                    "\t\t\t\"name\": \"秦皇岛卢龙冀东商贸1\",\n" +
                    "\t\t\t\"typecode\": \"9503\",\n" +
                    "\t\t\t\"uuid\": \"407872418\",\n" +
                    "\t\t\t\"longitude\": \"118.89416\",\n" +
                    "\t\t\t\"latitude\": \"39.90975\"\n" +
                    "\t\t}, {\n" +
                    "\t\t\t\"sno\": 10,\n" +
                    "\t\t\t\"name\": \"LL-冀东商贸1-A4光交接箱\",\n" +
                    "\t\t\t\"typecode\": \"9203\",\n" +
                    "\t\t\t\"uuid\": \"787697029\",\n" +
                    "\t\t\t\"longitude\": \"118.88760331\",\n" +
                    "\t\t\t\"latitude\": \"39.91206002\"\n" +
                    "\t\t}, {\n" +
                    "\t\t\t\"sno\": 11,\n" +
                    "\t\t\t\"name\": \"LL-冀东商贸1-A4B7光交接箱\",\n" +
                    "\t\t\t\"typecode\": \"9203\",\n" +
                    "\t\t\t\"uuid\": \"787696939\",\n" +
                    "\t\t\t\"longitude\": \"118.89773744\",\n" +
                    "\t\t\t\"latitude\": \"39.9117785\"\n" +
                    "\t\t}]\n" +
                    "\t}, {\n" +
                    "\t\t\"type\": \"third\",\n" +
                    "\t\t\"jumpCounts\": 10,\n" +
                    "\t\t\"jumpLength\": 3869,\n" +
                    "\t\t\"cableNodes\": [{\n" +
                    "\t\t\t\"sno\": 1,\n" +
                    "\t\t\t\"name\": \"秦皇岛卢龙县原县医院住宅0001号交接箱\",\n" +
                    "\t\t\t\"typecode\": \"9203\",\n" +
                    "\t\t\t\"uuid\": \"1501733427\",\n" +
                    "\t\t\t\"longitude\": \"118.86337533\",\n" +
                    "\t\t\t\"latitude\": \"39.88020786\"\n" +
                    "\t\t}, {\n" +
                    "\t\t\t\"sno\": 2,\n" +
                    "\t\t\t\"name\": \"LL-卢龙移动西1-A4B4光交箱\",\n" +
                    "\t\t\t\"typecode\": \"9203\",\n" +
                    "\t\t\t\"uuid\": \"437449416\",\n" +
                    "\t\t\t\"longitude\": \"118.86499768\",\n" +
                    "\t\t\t\"latitude\": \"39.88109356\"\n" +
                    "\t\t}, {\n" +
                    "\t\t\t\"sno\": 3,\n" +
                    "\t\t\t\"name\": \"LL-卢龙移动西1-A4光交箱\",\n" +
                    "\t\t\t\"typecode\": \"9203\",\n" +
                    "\t\t\t\"uuid\": \"488428497\",\n" +
                    "\t\t\t\"longitude\": \"118.86520359\",\n" +
                    "\t\t\t\"latitude\": \"39.88378042\"\n" +
                    "\t\t}, {\n" +
                    "\t\t\t\"sno\": 4,\n" +
                    "\t\t\t\"name\": \"秦皇岛卢龙卢龙\",\n" +
                    "\t\t\t\"typecode\": \"9503\",\n" +
                    "\t\t\t\"uuid\": \"538\",\n" +
                    "\t\t\t\"longitude\": \"118.87859538\",\n" +
                    "\t\t\t\"latitude\": \"39.89103892\"\n" +
                    "\t\t}, {\n" +
                    "\t\t\t\"sno\": 5,\n" +
                    "\t\t\t\"name\": \"秦皇岛卢龙卢龙2汇聚\",\n" +
                    "\t\t\t\"typecode\": \"9503\",\n" +
                    "\t\t\t\"uuid\": \"405384209\",\n" +
                    "\t\t\t\"longitude\": \"118.88697\",\n" +
                    "\t\t\t\"latitude\": \"39.892319\"\n" +
                    "\t\t}, {\n" +
                    "\t\t\t\"sno\": 6,\n" +
                    "\t\t\t\"name\": \"秦皇岛市卢龙县卢龙纬四路\",\n" +
                    "\t\t\t\"typecode\": \"9503\",\n" +
                    "\t\t\t\"uuid\": \"1265\",\n" +
                    "\t\t\t\"longitude\": \"118.88667247\",\n" +
                    "\t\t\t\"latitude\": \"39.89209616\"\n" +
                    "\t\t}, {\n" +
                    "\t\t\t\"sno\": 7,\n" +
                    "\t\t\t\"name\": \"秦皇岛卢龙赵庄子\",\n" +
                    "\t\t\t\"typecode\": \"9503\",\n" +
                    "\t\t\t\"uuid\": \"493863045\",\n" +
                    "\t\t\t\"longitude\": \"118.89751\",\n" +
                    "\t\t\t\"latitude\": \"39.88796\"\n" +
                    "\t\t}, {\n" +
                    "\t\t\t\"sno\": 8,\n" +
                    "\t\t\t\"name\": \"秦皇岛卢龙卢龙\",\n" +
                    "\t\t\t\"typecode\": \"9503\",\n" +
                    "\t\t\t\"uuid\": \"538\",\n" +
                    "\t\t\t\"longitude\": \"118.87859538\",\n" +
                    "\t\t\t\"latitude\": \"39.89103892\"\n" +
                    "\t\t}, {\n" +
                    "\t\t\t\"sno\": 9,\n" +
                    "\t\t\t\"name\": \"秦皇岛卢龙冀东商贸1\",\n" +
                    "\t\t\t\"typecode\": \"9503\",\n" +
                    "\t\t\t\"uuid\": \"407872418\",\n" +
                    "\t\t\t\"longitude\": \"118.89416\",\n" +
                    "\t\t\t\"latitude\": \"39.90975\"\n" +
                    "\t\t}, {\n" +
                    "\t\t\t\"sno\": 10,\n" +
                    "\t\t\t\"name\": \"LL-冀东商贸1-A4光交接箱\",\n" +
                    "\t\t\t\"typecode\": \"9203\",\n" +
                    "\t\t\t\"uuid\": \"787697029\",\n" +
                    "\t\t\t\"longitude\": \"118.88760331\",\n" +
                    "\t\t\t\"latitude\": \"39.91206002\"\n" +
                    "\t\t}, {\n" +
                    "\t\t\t\"sno\": 11,\n" +
                    "\t\t\t\"name\": \"LL-冀东商贸1-A4B7光交接箱\",\n" +
                    "\t\t\t\"typecode\": \"9203\",\n" +
                    "\t\t\t\"uuid\": \"787696939\",\n" +
                    "\t\t\t\"longitude\": \"118.89773744\",\n" +
                    "\t\t\t\"latitude\": \"39.9117785\"\n" +
                    "\t\t}]\n" +
                    "\t}]\n" +
                    "}";
//
//    public static final String ENUM_MOMDEL = "{\n" +
//            "    \"displayValue\": [\n" +
//            "        {\n" +
//            "            \"UUID\": \"2\",\n" +
//            "            \"cityUUID\": \"1\",\n" +
//            "            \"name\": \"石家庄\"\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"UUID\": \"2\",\n" +
//            "            \"cityUUID\": \"1\",\n" +
//            "            \"name\": \"石家庄\"\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"UUID\": \"2\",\n" +
//            "            \"cityUUID\": \"1\",\n" +
//            "            \"name\": \"石家庄\"\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"UUID\": \"2\",\n" +
//            "            \"cityUUID\": \"1\",\n" +
//            "            \"name\": \"石家庄\"\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"UUID\": \"2\",\n" +
//            "            \"cityUUID\": \"1\",\n" +
//            "            \"name\": \"石家庄\"\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"UUID\": \"2\",\n" +
//            "            \"cityUUID\": \"1\",\n" +
//            "            \"name\": \"石家庄\"\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"UUID\": \"2\",\n" +
//            "            \"cityUUID\": \"1\",\n" +
//            "            \"name\": \"石家庄\"\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"UUID\": \"2\",\n" +
//            "            \"cityUUID\": \"1\",\n" +
//            "            \"name\": \"石家庄\"\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"UUID\": \"2\",\n" +
//            "            \"cityUUID\": \"1\",\n" +
//            "            \"name\": \"石家庄\"\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"UUID\": \"2\",\n" +
//            "            \"cityUUID\": \"1\",\n" +
//            "            \"name\": \"石家庄\"\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"UUID\": \"2\",\n" +
//            "            \"cityUUID\": \"1\",\n" +
//            "            \"name\": \"石家庄\"\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"UUID\": \"2\",\n" +
//            "            \"cityUUID\": \"1\",\n" +
//            "            \"name\": \"石家庄\"\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"UUID\": \"2\",\n" +
//            "            \"cityUUID\": \"1\",\n" +
//            "            \"name\": \"石家庄\"\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"UUID\": \"2\",\n" +
//            "            \"cityUUID\": \"1\",\n" +
//            "            \"name\": \"石家庄\"\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"UUID\": \"2\",\n" +
//            "            \"cityUUID\": \"1\",\n" +
//            "            \"name\": \"石家庄\"\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"UUID\": \"2\",\n" +
//            "            \"cityUUID\": \"1\",\n" +
//            "            \"name\": \"石家庄\"\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"UUID\": \"2\",\n" +
//            "            \"cityUUID\": \"1\",\n" +
//            "            \"name\": \"石家庄\"\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"UUID\": \"2\",\n" +
//            "            \"cityUUID\": \"1\",\n" +
//            "            \"name\": \"石家庄\"\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"UUID\": \"2\",\n" +
//            "            \"cityUUID\": \"1\",\n" +
//            "            \"name\": \"石家庄\"\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"UUID\": \"2\",\n" +
//            "            \"cityUUID\": \"1\",\n" +
//            "            \"name\": \"石家庄\"\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"UUID\": \"2\",\n" +
//            "            \"cityUUID\": \"1\",\n" +
//            "            \"name\": \"石家庄\"\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"UUID\": \"2\",\n" +
//            "            \"cityUUID\": \"1\",\n" +
//            "            \"name\": \"石家庄\"\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"UUID\": \"2\",\n" +
//            "            \"cityUUID\": \"1\",\n" +
//            "            \"name\": \"石家庄\"\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"UUID\": \"2\",\n" +
//            "            \"cityUUID\": \"1\",\n" +
//            "            \"name\": \"石家庄\"\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"UUID\": \"2\",\n" +
//            "            \"cityUUID\": \"1\",\n" +
//            "            \"name\": \"石家庄\"\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"UUID\": \"2\",\n" +
//            "            \"cityUUID\": \"1\",\n" +
//            "            \"name\": \"石家庄\"\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"UUID\": \"2\",\n" +
//            "            \"cityUUID\": \"1\",\n" +
//            "            \"name\": \"石家庄\"\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"UUID\": \"2\",\n" +
//            "            \"cityUUID\": \"1\",\n" +
//            "            \"name\": \"石家庄\"\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"UUID\": \"2\",\n" +
//            "            \"cityUUID\": \"1\",\n" +
//            "            \"name\": \"石家庄\"\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"UUID\": \"2\",\n" +
//            "            \"cityUUID\": \"1\",\n" +
//            "            \"name\": \"石家庄\"\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"UUID\": \"2\",\n" +
//            "            \"cityUUID\": \"1\",\n" +
//            "            \"name\": \"石家庄\"\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"UUID\": \"2\",\n" +
//            "            \"cityUUID\": \"1\",\n" +
//            "            \"name\": \"石家庄\"\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"UUID\": \"2\",\n" +
//            "            \"cityUUID\": \"1\",\n" +
//            "            \"name\": \"石家庄\"\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"UUID\": \"2\",\n" +
//            "            \"cityUUID\": \"1\",\n" +
//            "            \"name\": \"石家庄\"\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"UUID\": \"2\",\n" +
//            "            \"cityUUID\": \"1\",\n" +
//            "            \"name\": \"石家庄\"\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"UUID\": \"2\",\n" +
//            "            \"cityUUID\": \"1\",\n" +
//            "            \"name\": \"石家庄\"\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"UUID\": \"2\",\n" +
//            "            \"cityUUID\": \"1\",\n" +
//            "            \"name\": \"石家庄\"\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"UUID\": \"2\",\n" +
//            "            \"cityUUID\": \"1\",\n" +
//            "            \"name\": \"石家庄\"\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"UUID\": \"2\",\n" +
//            "            \"cityUUID\": \"1\",\n" +
//            "            \"name\": \"石家庄\"\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"UUID\": \"2\",\n" +
//            "            \"cityUUID\": \"1\",\n" +
//            "            \"name\": \"石家庄\"\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"UUID\": \"2\",\n" +
//            "            \"cityUUID\": \"1\",\n" +
//            "            \"name\": \"石家庄\"\n" +
//            "        }\n" +
//            "    ]\n" +
//            "}";
//
//    public static final String RESOURCECONNECTJSON = "{\n" +
//            "    \"data\": [\n" +
//            "        {\n" +
//            "            \"dataList\": [{\n" +
//            "                \"name\": \"管道段1111\",\n" +
//            "                \"type\": \"1111\",\n" +
//            "                \"typeName\": \"管道段\",\n" +
//            "                \"uuid\": \"aaa\"\n" +
//            "            }],\n" +
//            "            \"group\": \"1111\",\n" +
//            "            \"groupName\": \"关联的管道\"\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"dataList\":[ {\n" +
//            "                \"name\": \"杆路段2222\",\n" +
//            "                \"type\": \"2222\",\n" +
//            "                \"typeName\": \"杆路段\",\n" +
//            "                \"uuid\": \"aaa\"\n" +
//            "            }],\n" +
//            "            \"group\": \"2222\",\n" +
//            "            \"groupName\": \"关联的杆路\"\n" +
//            "        }\n" +
//            "    ]\n" +
//            "}";
//
//    public static final String JSON_TASK_RESOURCE = "{\n" +
//            "    \"result\": [\n" +
//            "        {\n" +
//            "            \"uuid\": \"1273694540\",\n" +
//            "            \"name\": \"石家庄长安区棉二小区0002号标石\",\n" +
//            "            \"typecode\": \"9999\",\n" +
//            "            \"optype\": \"del\",\n" +
//            "            \"oldValue\": {\n" +
//            "                \"wkt\": \"POINT  ( 114.51232909 38.04663129)\",\n" +
//            "                \"uuid\": \"2f5cb249-51b6-437d-b01b-ef4d72cec1b8\"\n" +
//            "            },\n" +
//            "            \"newValue\": null\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"uuid\": \"46e92096-27e7-4616-a145-19863a5b3297\",\n" +
//            "            \"name\": \"2019021803测试管道-2-关键点\",\n" +
//            "            \"typecode\": \"9999\",\n" +
//            "            \"optype\": \"del\",\n" +
//            "            \"oldValue\": {\n" +
//            "                \"wkt\": \"POINT  ( 114.50948319 38.04651865)\",\n" +
//            "                \"uuid\": \"cbb1fa3d-2912-44ad-bf89-8d8d2253a80a\"\n" +
//            "            },\n" +
//            "            \"newValue\": null\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"uuid\": \"48562c96-499e-4ed1-9e9e-d76188e96180\",\n" +
//            "            \"name\": \"20180605测试管道-2-关键点\",\n" +
//            "            \"typecode\": \"9999\",\n" +
//            "            \"optype\": \"modify\",\n" +
//            "            \"oldValue\": {\n" +
//            "                \"wkt\": \"POINT  ( 114.50826035 38.04599600)\",\n" +
//            "                \"uuid\": \"d2a44491-09bf-48ca-9943-54622463b3f2\"\n" +
//            "            },\n" +
//            "            \"newValue\": {\n" +
//            "                \"wkt\": \"POINT  ( 114.50849630 38.04594267)\",\n" +
//            "                \"uuid\": \"17276828-3389-43a2-9390-7843b0043376\"\n" +
//            "            }\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"uuid\": \"ea0a4b06-3726-4063-84a8-5f9a7ab510bb\",\n" +
//            "            \"name\": \"20190807测试光交123\",\n" +
//            "            \"typecode\": \"9203\",\n" +
//            "            \"optype\": \"add\",\n" +
//            "            \"oldValue\": null,\n" +
//            "            \"newValue\": {\n" +
//            "                \"wkt\": \"POINT  ( 114.50853539 38.04653391)\",\n" +
//            "                \"uuid\": \"0ef8e855-8f5a-43be-b496-a35a346ebcc2\"\n" +
//            "            }\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"uuid\": \"769871665\",\n" +
//            "            \"name\": \"石家庄长安区棉二宿舍4号楼对过电力杆2号电杆\",\n" +
//            "            \"typecode\": \"9999\",\n" +
//            "            \"optype\": \"del\",\n" +
//            "            \"oldValue\": {\n" +
//            "                \"wkt\": \"POINT  ( 114.51097014 38.04664361)\",\n" +
//            "                \"uuid\": \"4e318d20-6915-4c0e-98f6-c32319f057f7\"\n" +
//            "            },\n" +
//            "            \"newValue\": null\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"uuid\": \"769871666\",\n" +
//            "            \"name\": \"石家庄长安区棉二宿舍4号楼对过电力杆3号电杆\",\n" +
//            "            \"typecode\": \"9999\",\n" +
//            "            \"optype\": \"del\",\n" +
//            "            \"oldValue\": {\n" +
//            "                \"wkt\": \"POINT  ( 114.51094634 38.04643897)\",\n" +
//            "                \"uuid\": \"7a610cd8-dcd6-452d-b806-8c6188a57415\"\n" +
//            "            },\n" +
//            "            \"newValue\": null\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"uuid\": \"5e1bf9fc-53e6-4f58-9461-a4122ec314a3\",\n" +
//            "            \"name\": \"20180712业务区测试任务测试光交2\",\n" +
//            "            \"typecode\": \"9203\",\n" +
//            "            \"optype\": \"modify\",\n" +
//            "            \"oldValue\": {\n" +
//            "                \"wkt\": \"POINT  ( 114.50866197 38.04594215)\",\n" +
//            "                \"uuid\": \"fa6e76e1-6b28-4b50-b345-0a963381a690\"\n" +
//            "            },\n" +
//            "            \"newValue\": {\n" +
//            "                \"wkt\": \"POINT  ( 114.50866197 38.04594215)\",\n" +
//            "                \"uuid\": \"137a7175-70ce-457e-b903-018bfb44af21\"\n" +
//            "            }\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"uuid\": \"4f26b0a9-8aa7-4763-8814-5c5f437211cc\",\n" +
//            "            \"name\": \"20190807测试光交1\",\n" +
//            "            \"typecode\": \"9203\",\n" +
//            "            \"optype\": \"add\",\n" +
//            "            \"oldValue\": null,\n" +
//            "            \"newValue\": {\n" +
//            "                \"wkt\": \"POINT  ( 114.50858962 38.04644005)\",\n" +
//            "                \"uuid\": \"ea0eddc5-7caf-4501-bea8-0b50f51f5b85\"\n" +
//            "            }\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"uuid\": \"732915265\",\n" +
//            "            \"name\": \"SJZ-建北营业厅机房1-A2B9光交箱\",\n" +
//            "            \"typecode\": \"9203\",\n" +
//            "            \"optype\": \"modify\",\n" +
//            "            \"oldValue\": {\n" +
//            "                \"wkt\": \"POINT  ( 114.50515145 38.04562870)\",\n" +
//            "                \"uuid\": \"b333525f-beff-4c6a-8f3d-f32fd6811e63\"\n" +
//            "            },\n" +
//            "            \"newValue\": {\n" +
//            "                \"wkt\": \"POINT  ( 114.50584164 38.04550284)\",\n" +
//            "                \"uuid\": \"20107053-61c2-4f8a-8957-d16a0b8b0f57\"\n" +
//            "            }\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"uuid\": \"538756261\",\n" +
//            "            \"name\": \"人民大会堂基站（拉远）\",\n" +
//            "            \"typecode\": \"9206\",\n" +
//            "            \"optype\": \"modify\",\n" +
//            "            \"oldValue\": {\n" +
//            "                \"wkt\": \"POINT  ( 114.50930000 38.04611000)\",\n" +
//            "                \"uuid\": \"abfa3a8d-03c0-461f-8400-305035426511\"\n" +
//            "            },\n" +
//            "            \"newValue\": {\n" +
//            "                \"wkt\": \"POINT  ( 114.50959071 38.04575880)\",\n" +
//            "                \"uuid\": \"2b640690-c6c6-4f3b-8387-fd2e2021c57f\"\n" +
//            "            }\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"uuid\": \"57deb2ec-d7d3-4337-af0f-f585f4f3b3b7\",\n" +
//            "            \"name\": \"20190807测试光分1\",\n" +
//            "            \"typecode\": \"9204\",\n" +
//            "            \"optype\": \"add\",\n" +
//            "            \"oldValue\": null,\n" +
//            "            \"newValue\": {\n" +
//            "                \"wkt\": \"POINT  ( 114.50985780 38.04555953)\",\n" +
//            "                \"uuid\": \"58d24ccc-2773-4d48-947a-584166bce546\"\n" +
//            "            }\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"uuid\": \"7b51a0a2-9710-40df-8d2c-e81d70599ef1\",\n" +
//            "            \"name\": \"承德丰宁满族自治县隆达建筑安装工程有限公司-丰宁三营至南辛营杆路049号杆\",\n" +
//            "            \"typecode\": \"9105\",\n" +
//            "            \"optype\": \"add\",\n" +
//            "            \"oldValue\": null,\n" +
//            "            \"newValue\": {\n" +
//            "                \"wkt\": \"MULTILINESTRING  (( 116.62174000 41.12601500, 116.61954901 41.12542681),( 116.61954901 41.12542681, 116.61727894 41.12497661),( 116.61727894 41.12497661, 116.61500763 41.12455920),( 116.61500763 41.12455920, 116.61273707 41.12407666),( 116.61273707 41.12407666, 116.61143009 41.12259397),( 116.61143009 41.12259397, 116.61135400 41.12243300))\",\n" +
//            "                \"uuid\": \"20638194\"\n" +
//            "            }\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"uuid\": \"9a18fcbd15334e829c8aba5f13127736\",\n" +
//            "            \"name\": \"20190809测试楼间缆1\",\n" +
//            "            \"typecode\": \"9202\",\n" +
//            "            \"optype\": \"del\",\n" +
//            "            \"oldValue\": {\n" +
//            "                \"wkt\": null,\n" +
//            "                \"uuid\": \"efffc899-5e59-4990-8a13-be6fcd6d829d\"\n" +
//            "            },\n" +
//            "            \"newValue\": null\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"uuid\": \"a9fe049f-60bb-4f6e-b9c8-ddc538ea7576\",\n" +
//            "            \"name\": \"2019021803测试管道\",\n" +
//            "            \"typecode\": \"9102\",\n" +
//            "            \"optype\": \"modify\",\n" +
//            "            \"oldValue\": {\n" +
//            "                \"wkt\": \"MULTILINESTRING  (( 114.50954748 38.04618476, 114.50974729 38.04616834),( 114.50926230 38.04655602, 114.50954748 38.04618476))\",\n" +
//            "                \"uuid\": \"d8b83cdc-9ca0-4e87-8195-2121820a2d02\"\n" +
//            "            },\n" +
//            "            \"newValue\": {\n" +
//            "                \"wkt\": \"MULTILINESTRING  (( 114.5094748 38.0468476, 114.5094729 38.0466834),( 114.5096230 38.0465602, 114.5094748 38.0468476))\",\n" +
//            "                \"uuid\": \"6c419ec2-1238-4781-9f44-240a3d30e44d\"\n" +
//            "            }\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"uuid\": \"42c80122064c41919b0cfefdf1648ef2\",\n" +
//            "            \"name\": \"20190809测试楼间缆1\",\n" +
//            "            \"typecode\": \"9202\",\n" +
//            "            \"optype\": \"modify\",\n" +
//            "            \"oldValue\": {\n" +
//            "                \"wkt\": null,\n" +
//            "                \"uuid\": \"0a09d187-0fa7-446a-86f0-9f7688f78d69\"\n" +
//            "            },\n" +
//            "            \"newValue\": {\n" +
//            "                \"wkt\": null,\n" +
//            "                \"uuid\": \"bb140781-9687-4b5c-bdbf-ab6172c7cebb\"\n" +
//            "            }\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"uuid\": \"8e749d2162d24ebc9c34d89d8466ee51\",\n" +
//            "            \"name\": \"20190809测试楼间缆3\",\n" +
//            "            \"typecode\": \"9202\",\n" +
//            "            \"optype\": \"modify\",\n" +
//            "            \"oldValue\": {\n" +
//            "                \"wkt\": null,\n" +
//            "                \"uuid\": \"e15750ef-acfb-4111-b65b-6bb6ebd9e6d2\"\n" +
//            "            },\n" +
//            "            \"newValue\": {\n" +
//            "                \"wkt\": null,\n" +
//            "                \"uuid\": \"1697f45a-6053-4bdb-8fec-0a72e4a478ad\"\n" +
//            "            }\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"uuid\": \"f37cd7ab-ff08-4072-9bba-424f8d71f0b9\",\n" +
//            "            \"name\": \"综区1-0828测试分纤箱\",\n" +
//            "            \"typecode\": \"9202\",\n" +
//            "            \"optype\": \"add\",\n" +
//            "            \"oldValue\": null,\n" +
//            "            \"newValue\": {\n" +
//            "                \"wkt\": \"LINESTRING  ( 114.63809279 38.02646389, 114.63949709 38.02646460)\",\n" +
//            "                \"uuid\": \"ef62486a-fbe8-42a3-9836-ee699cc2e22c\"\n" +
//            "            }\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"uuid\": \"6541fe86-505c-4dea-99a7-e9a3350457f8\",\n" +
//            "            \"name\": \"20190807测试光交1-20190807测试光分1\",\n" +
//            "            \"typecode\": \"9102\",\n" +
//            "            \"optype\": \"add\",\n" +
//            "            \"oldValue\": null,\n" +
//            "            \"newValue\": {\n" +
//            "                \"wkt\": \"MULTILINESTRING  (( 114.50858962 38.04644005, 114.50918501 38.04641477),( 114.50918501 38.04641477, 114.50945913 38.04616250),( 114.50945913 38.04616250, 114.50994409 38.04602887),( 114.50994409 38.04602887, 114.51011179 38.04575030),( 114.51011179 38.04575030, 114.50985780 38.04555953))\",\n" +
//            "                \"uuid\": \"db3ca5c4-f70f-4cde-99b6-420c3e003b97\"\n" +
//            "            }\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"uuid\": \"2aeb8862-73eb-43c2-9695-8aaba3e731e2\",\n" +
//            "            \"name\": \"0329分纤箱-0618接头\",\n" +
//            "            \"typecode\": \"9202\",\n" +
//            "            \"optype\": \"modify\",\n" +
//            "            \"oldValue\": {\n" +
//            "                \"wkt\": \"LINESTRING  ( 114.64120069 38.02698088, 114.64004399 38.02679077)\",\n" +
//            "                \"uuid\": \"14bb12d7-7f49-447b-b651-6ac53bee741c\"\n" +
//            "            },\n" +
//            "            \"newValue\": {\n" +
//            "                \"wkt\": \"LINESTRING  ( 114.64120069 38.02698088, 114.64004399 38.02679077)\",\n" +
//            "                \"uuid\": \"1364617b-5f1a-481d-a25b-389e8c4914f7\"\n" +
//            "            }\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"uuid\": \"a9c4efb0-e8ed-402b-87ea-8dde2380e57d\",\n" +
//            "            \"name\": \"0828测试分纤箱-综区1\",\n" +
//            "            \"typecode\": \"9105\",\n" +
//            "            \"optype\": \"add\",\n" +
//            "            \"oldValue\": null,\n" +
//            "            \"newValue\": {\n" +
//            "                \"wkt\": \"LINESTRING  ( 114.63809279 38.02646389, 114.63949709 38.02646460)\",\n" +
//            "                \"uuid\": \"bfc3de98-5d59-4101-b7b3-ad076e697619\"\n" +
//            "            }\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"uuid\": \"d21fb845-0e8c-4405-9f09-0b444e10fe1f\",\n" +
//            "            \"name\": \"承德丰宁南辛营村-承德丰宁满族自治县隆达建筑安装工程有限公司\",\n" +
//            "            \"typecode\": \"9202\",\n" +
//            "            \"optype\": \"add\",\n" +
//            "            \"oldValue\": null,\n" +
//            "            \"newValue\": {\n" +
//            "                \"wkt\": \"MULTILINESTRING  (( 116.62580400 41.09116900, 116.62619565 41.09110234),( 116.62572800 41.09174100, 116.62580400 41.09116900),( 116.62565300 41.09231200, 116.62572800 41.09174100),( 116.62436300 41.09468700, 116.62480700 41.09408800),( 116.62555000 41.09308600, 116.62565300 41.09231200),( 116.62527500 41.09345700, 116.62555000 41.09308600),( 116.62480700 41.09408800, 116.62527500 41.09345700),( 116.62410100 41.09504100, 116.62436300 41.09468700),( 116.62414700 41.09639500, 116.62412900 41.09586600),( 116.62417300 41.09717900, 116.62416000 41.09678100),( 116.62396800 41.09984500, 116.62416300 41.09960000),( 116.62376800 41.10009600, 116.62396800 41.09984500),( 116.62417600 41.09852800, 116.62418000 41.09820100),( 116.62416700 41.09927000, 116.62417600 41.09852800),( 116.62275700 41.10150200, 116.62300500 41.10105600),( 116.62255200 41.10186900, 116.62275700 41.10150200),( 116.62034000 41.10660500, 116.62051800 41.10602500),( 116.62157300 41.10367700, 116.62176800 41.10327700),( 116.62176800 41.10327700, 116.62205700 41.10275900),( 116.62113800 41.10456800, 116.62134600 41.10414300),( 116.62412900 41.09586600, 116.62411700 41.09552100),( 116.62418800 41.09760000, 116.62417300 41.09717900),( 116.62416000 41.09678100, 116.62414700 41.09639500),( 116.62300500 41.10105600, 116.62350900 41.10042300),( 116.62416300 41.09960000, 116.62416700 41.09927000),( 116.62350900 41.10042300, 116.62376800 41.10009600),( 116.62051800 41.10602500, 116.62067200 41.10552200),( 116.62230400 41.10231500, 116.62255200 41.10186900),( 116.62205700 41.10275900, 116.62230400 41.10231500),( 116.62134600 41.10414300, 116.62157300 41.10367700),( 116.62411700 41.09552100, 116.62410100 41.09504100),( 116.62418000 41.09820100, 116.62418800 41.09760000),( 116.62019700 41.10706700, 116.62034000 41.10660500),( 116.62019700 41.10758600, 116.62019700 41.10706700),( 116.62019600 41.10818800, 116.62019700 41.10758600),( 116.62034700 41.10857500, 116.62019600 41.10818800),( 116.62043200 41.10879300, 116.62034700 41.10857500),( 116.62016800 41.10969900, 116.62059500 41.10921100),( 116.61957200 41.11094400, 116.61975000 41.11057300),( 116.61906700 41.11153800, 116.61957200 41.11094400),( 116.61748300 41.11339800, 116.61783100 41.11299000),( 116.61724000 41.11368400, 116.61748300 41.11339800),( 116.61669500 41.11432400, 116.61724000 41.11368400),( 116.61647300 41.11458500, 116.61669500 41.11432400),( 116.61155800 41.12204200, 116.61194200 41.12130700),( 116.62088500 41.10508700, 116.62113800 41.10456800),( 116.62067200 41.10552200, 116.62088500 41.10508700),( 116.61808300 41.11269300, 116.61866600 41.11200800),( 116.61233600 41.12055500, 116.61288500 41.11983100),( 116.61783100 41.11299000, 116.61808300 41.11269300),( 116.61618700 41.11529100, 116.61647300 41.11458500),( 116.61596100 41.11550300, 116.61618700 41.11529100),( 116.61554600 41.11589200, 116.61596100 41.11550300),( 116.61522400 41.11619400, 116.61554600 41.11589200),( 116.61519900 41.11623800, 116.61522400 41.11619400),( 116.61426100 41.11788000, 116.61452100 41.11742400),( 116.61393200 41.11845500, 116.61426100 41.11788000),( 116.61347800 41.11905100, 116.61393200 41.11845500),( 116.61318500 41.11943700, 116.61347800 41.11905100),( 116.61300200 41.11967800, 116.61318500 41.11943700),( 116.61194200 41.12130700, 116.61214900 41.12091200),( 116.61288500 41.11983100, 116.61300200 41.11967800),( 116.61214900 41.12091200, 116.61233600 41.12055500),( 116.61135400 41.12243300, 116.61155800 41.12204200),( 116.61954901 41.12542681, 116.61727894 41.12497661),( 116.62059500 41.10921100, 116.62043200 41.10879300),( 116.61975000 41.11057300, 116.62016800 41.10969900),( 116.61866600 41.11200800, 116.61906700 41.11153800),( 116.61493300 41.11670300, 116.61519900 41.11623800),( 116.61452100 41.11742400, 116.61493300 41.11670300),( 116.62174000 41.12601500, 116.61954901 41.12542681),( 116.61143009 41.12259397, 116.61135400 41.12243300),( 116.61273707 41.12407666, 116.61143009 41.12259397),( 116.61500763 41.12455920, 116.61273707 41.12407666),( 116.61727894 41.12497661, 116.61500763 41.12455920))\",\n" +
//            "                \"uuid\": \"20638504\"\n" +
//            "            }\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"uuid\": \"cc5a2434872a420186088b4ec9ab1e9a\",\n" +
//            "            \"name\": \"20190809测试楼间缆6\",\n" +
//            "            \"typecode\": \"9202\",\n" +
//            "            \"optype\": \"add\",\n" +
//            "            \"oldValue\": null,\n" +
//            "            \"newValue\": {\n" +
//            "                \"wkt\": null,\n" +
//            "                \"uuid\": \"9d6b4cc3-9243-472e-b713-9c60e6945080\"\n" +
//            "            }\n" +
//            "        }\n" +
//            "    ]\n" +
//            "}";
//    public static final String JSON_PANEl = "{\n" +
//            "    \"data\": {\n" +
//            "        \"fiber\": [],\n" +
//            "        \"jumper\": [\n" +
//            "            {\n" +
//            "                \"a_device_id\": 11639,\n" +
//            "                \"a_port_id\": 11773,\n" +
//            "                \"int_id\": 11830,\n" +
//            "                \"z_device_id\": 11559,\n" +
//            "                \"z_port_id\": 11746\n" +
//            "            },\n" +
//            "            {\n" +
//            "                \"a_device_id\": 11639,\n" +
//            "                \"a_port_id\": 11774,\n" +
//            "                \"int_id\": 11831,\n" +
//            "                \"z_device_id\": 11559,\n" +
//            "                \"z_port_id\": 11747\n" +
//            "            },\n" +
//            "            {\n" +
//            "                \"a_device_id\": 11639,\n" +
//            "                \"a_port_id\": 11775,\n" +
//            "                \"int_id\": 11832,\n" +
//            "                \"z_device_id\": 11559,\n" +
//            "                \"z_port_id\": 11748\n" +
//            "            }\n" +
//            "        ],\n" +
//            "        \"odms\": [\n" +
//            "            {\n" +
//            "                \"angle\": \"180\",\n" +
//            "                \"column_no\": 12,\n" +
//            "                \"create_type\": \"0\",\n" +
//            "                \"end_u\": 21,\n" +
//            "                \"int_id\": 11836,\n" +
//            "                \"rack_id\": 11559,\n" +
//            "                \"row_no\": 4,\n" +
//            "                \"start_u\": 17,\n" +
//            "                \"task_uuid\": \"PL_WX_TASK:7576f5b6-cdbd-4746-bb8f-5e536cdebfd9\",\n" +
//            "                \"trays\": [\n" +
//            "                    {\n" +
//            "                        \"int_id\": 11837,\n" +
//            "                        \"no\": 1,\n" +
//            "                        \"odm_id\": 11836,\n" +
//            "                        \"rack_id\": 11559,\n" +
//            "                        \"remark\": \"p\",\n" +
//            "                        \"task_uuid\": \"PL_WX_TASK:7576f5b6-cdbd-4746-bb8f-5e536cdebfd9\",\n" +
//            "                        \"terminals\": [\n" +
//            "                            {\n" +
//            "                                \"column_num\": 2,\n" +
//            "                                \"int_id\": 11839,\n" +
//            "                                \"odm_id\": 11836,\n" +
//            "                                \"odm_id_value\": \"wjx测试ODM5\",\n" +
//            "                                \"rack_id\": 11559,\n" +
//            "                                \"row_num\": 1,\n" +
//            "                                \"task_uuid\": \"PL_WX_TASK:7576f5b6-cdbd-4746-bb8f-5e536cdebfd9\",\n" +
//            "                                \"tray_id\": 11837,\n" +
//            "                                \"tray_id_value\": \"wjx测试ODM5-1\",\n" +
//            "                                \"zh_label\": \"wjx测试ODM5-1-2\"\n" +
//            "                            },\n" +
//            "                            {\n" +
//            "                                \"column_num\": 3,\n" +
//            "                                \"int_id\": 11840,\n" +
//            "                                \"odm_id\": 11836,\n" +
//            "                                \"odm_id_value\": \"wjx测试ODM5\",\n" +
//            "                                \"rack_id\": 11559,\n" +
//            "                                \"row_num\": 1,\n" +
//            "                                \"task_uuid\": \"PL_WX_TASK:7576f5b6-cdbd-4746-bb8f-5e536cdebfd9\",\n" +
//            "                                \"tray_id\": 11837,\n" +
//            "                                \"tray_id_value\": \"wjx测试ODM5-1\",\n" +
//            "                                \"zh_label\": \"wjx测试ODM5-1-3\"\n" +
//            "                            },\n" +
//            "                            {\n" +
//            "                                \"column_num\": 4,\n" +
//            "                                \"int_id\": 11841,\n" +
//            "                                \"odm_id\": 11836,\n" +
//            "                                \"odm_id_value\": \"wjx测试ODM5\",\n" +
//            "                                \"rack_id\": 11559,\n" +
//            "                                \"row_num\": 1,\n" +
//            "                                \"task_uuid\": \"PL_WX_TASK:7576f5b6-cdbd-4746-bb8f-5e536cdebfd9\",\n" +
//            "                                \"tray_id\": 11837,\n" +
//            "                                \"tray_id_value\": \"wjx测试ODM5-1\",\n" +
//            "                                \"zh_label\": \"wjx测试ODM5-1-4\"\n" +
//            "                            },\n" +
//            "                            {\n" +
//            "                                \"column_num\": 5,\n" +
//            "                                \"int_id\": 11842,\n" +
//            "                                \"odm_id\": 11836,\n" +
//            "                                \"odm_id_value\": \"wjx测试ODM5\",\n" +
//            "                                \"rack_id\": 11559,\n" +
//            "                                \"row_num\": 1,\n" +
//            "                                \"task_uuid\": \"PL_WX_TASK:7576f5b6-cdbd-4746-bb8f-5e536cdebfd9\",\n" +
//            "                                \"tray_id\": 11837,\n" +
//            "                                \"tray_id_value\": \"wjx测试ODM5-1\",\n" +
//            "                                \"zh_label\": \"wjx测试ODM5-1-5\"\n" +
//            "                            },\n" +
//            "                            {\n" +
//            "                                \"column_num\": 1,\n" +
//            "                                \"int_id\": 11838,\n" +
//            "                                \"odm_id\": 11836,\n" +
//            "                                \"odm_id_value\": \"wjx测试ODM5\",\n" +
//            "                                \"rack_id\": 11559,\n" +
//            "                                \"row_num\": 1,\n" +
//            "                                \"task_uuid\": \"PL_WX_TASK:7576f5b6-cdbd-4746-bb8f-5e536cdebfd9\",\n" +
//            "                                \"tray_id\": 11837,\n" +
//            "                                \"tray_id_value\": \"wjx测试ODM5-1\",\n" +
//            "                                \"zh_label\": \"wjx测试ODM5-1-1\"\n" +
//            "                            },\n" +
//            "                            {\n" +
//            "                                \"column_num\": 6,\n" +
//            "                                \"int_id\": 11843,\n" +
//            "                                \"odm_id\": 11836,\n" +
//            "                                \"odm_id_value\": \"wjx测试ODM5\",\n" +
//            "                                \"rack_id\": 11559,\n" +
//            "                                \"row_num\": 1,\n" +
//            "                                \"task_uuid\": \"PL_WX_TASK:7576f5b6-cdbd-4746-bb8f-5e536cdebfd9\",\n" +
//            "                                \"tray_id\": 11837,\n" +
//            "                                \"tray_id_value\": \"wjx测试ODM5-1\",\n" +
//            "                                \"zh_label\": \"wjx测试ODM5-1-6\"\n" +
//            "                            },\n" +
//            "                            {\n" +
//            "                                \"column_num\": 7,\n" +
//            "                                \"int_id\": 11844,\n" +
//            "                                \"odm_id\": 11836,\n" +
//            "                                \"odm_id_value\": \"wjx测试ODM5\",\n" +
//            "                                \"rack_id\": 11559,\n" +
//            "                                \"row_num\": 1,\n" +
//            "                                \"task_uuid\": \"PL_WX_TASK:7576f5b6-cdbd-4746-bb8f-5e536cdebfd9\",\n" +
//            "                                \"tray_id\": 11837,\n" +
//            "                                \"tray_id_value\": \"wjx测试ODM5-1\",\n" +
//            "                                \"zh_label\": \"wjx测试ODM5-1-7\"\n" +
//            "                            },\n" +
//            "                            {\n" +
//            "                                \"column_num\": 8,\n" +
//            "                                \"int_id\": 11845,\n" +
//            "                                \"odm_id\": 11836,\n" +
//            "                                \"odm_id_value\": \"wjx测试ODM5\",\n" +
//            "                                \"rack_id\": 11559,\n" +
//            "                                \"row_num\": 1,\n" +
//            "                                \"task_uuid\": \"PL_WX_TASK:7576f5b6-cdbd-4746-bb8f-5e536cdebfd9\",\n" +
//            "                                \"tray_id\": 11837,\n" +
//            "                                \"tray_id_value\": \"wjx测试ODM5-1\",\n" +
//            "                                \"zh_label\": \"wjx测试ODM5-1-8\"\n" +
//            "                            },\n" +
//            "                            {\n" +
//            "                                \"column_num\": 9,\n" +
//            "                                \"int_id\": 11846,\n" +
//            "                                \"odm_id\": 11836,\n" +
//            "                                \"odm_id_value\": \"wjx测试ODM5\",\n" +
//            "                                \"rack_id\": 11559,\n" +
//            "                                \"row_num\": 1,\n" +
//            "                                \"task_uuid\": \"PL_WX_TASK:7576f5b6-cdbd-4746-bb8f-5e536cdebfd9\",\n" +
//            "                                \"tray_id\": 11837,\n" +
//            "                                \"tray_id_value\": \"wjx测试ODM5-1\",\n" +
//            "                                \"zh_label\": \"wjx测试ODM5-1-9\"\n" +
//            "                            },\n" +
//            "                            {\n" +
//            "                                \"column_num\": 10,\n" +
//            "                                \"int_id\": 11847,\n" +
//            "                                \"odm_id\": 11836,\n" +
//            "                                \"odm_id_value\": \"wjx测试ODM5\",\n" +
//            "                                \"rack_id\": 11559,\n" +
//            "                                \"row_num\": 1,\n" +
//            "                                \"task_uuid\": \"PL_WX_TASK:7576f5b6-cdbd-4746-bb8f-5e536cdebfd9\",\n" +
//            "                                \"tray_id\": 11837,\n" +
//            "                                \"tray_id_value\": \"wjx测试ODM5-1\",\n" +
//            "                                \"zh_label\": \"wjx测试ODM5-1-10\"\n" +
//            "                            },\n" +
//            "                            {\n" +
//            "                                \"column_num\": 11,\n" +
//            "                                \"int_id\": 11848,\n" +
//            "                                \"odm_id\": 11836,\n" +
//            "                                \"odm_id_value\": \"wjx测试ODM5\",\n" +
//            "                                \"rack_id\": 11559,\n" +
//            "                                \"row_num\": 1,\n" +
//            "                                \"task_uuid\": \"PL_WX_TASK:7576f5b6-cdbd-4746-bb8f-5e536cdebfd9\",\n" +
//            "                                \"tray_id\": 11837,\n" +
//            "                                \"tray_id_value\": \"wjx测试ODM5-1\",\n" +
//            "                                \"zh_label\": \"wjx测试ODM5-1-11\"\n" +
//            "                            },\n" +
//            "                            {\n" +
//            "                                \"column_num\": 12,\n" +
//            "                                \"int_id\": 11849,\n" +
//            "                                \"odm_id\": 11836,\n" +
//            "                                \"odm_id_value\": \"wjx测试ODM5\",\n" +
//            "                                \"rack_id\": 11559,\n" +
//            "                                \"row_num\": 1,\n" +
//            "                                \"task_uuid\": \"PL_WX_TASK:7576f5b6-cdbd-4746-bb8f-5e536cdebfd9\",\n" +
//            "                                \"tray_id\": 11837,\n" +
//            "                                \"tray_id_value\": \"wjx测试ODM5-1\",\n" +
//            "                                \"zh_label\": \"wjx测试ODM5-1-12\"\n" +
//            "                            }\n" +
//            "                        ],\n" +
//            "                        \"zh_label\": \"wjx测试ODM5-1\"\n" +
//            "                    },\n" +
//            "                    {\n" +
//            "                        \"int_id\": 11850,\n" +
//            "                        \"no\": 2,\n" +
//            "                        \"odm_id\": 11836,\n" +
//            "                        \"rack_id\": 11559,\n" +
//            "                        \"remark\": \"fdfs\",\n" +
//            "                        \"task_uuid\": \"PL_WX_TASK:7576f5b6-cdbd-4746-bb8f-5e536cdebfd9\",\n" +
//            "                        \"terminals\": [\n" +
//            "                            {\n" +
//            "                                \"column_num\": 1,\n" +
//            "                                \"int_id\": 11851,\n" +
//            "                                \"odm_id\": 11836,\n" +
//            "                                \"odm_id_value\": \"wjx测试ODM5\",\n" +
//            "                                \"rack_id\": 11559,\n" +
//            "                                \"row_num\": 2,\n" +
//            "                                \"task_uuid\": \"PL_WX_TASK:7576f5b6-cdbd-4746-bb8f-5e536cdebfd9\",\n" +
//            "                                \"tray_id\": 11850,\n" +
//            "                                \"tray_id_value\": \"wjx测试ODM5-2\",\n" +
//            "                                \"zh_label\": \"wjx测试ODM5-2-1\"\n" +
//            "                            },\n" +
//            "                            {\n" +
//            "                                \"column_num\": 2,\n" +
//            "                                \"int_id\": 11852,\n" +
//            "                                \"odm_id\": 11836,\n" +
//            "                                \"odm_id_value\": \"wjx测试ODM5\",\n" +
//            "                                \"rack_id\": 11559,\n" +
//            "                                \"row_num\": 2,\n" +
//            "                                \"task_uuid\": \"PL_WX_TASK:7576f5b6-cdbd-4746-bb8f-5e536cdebfd9\",\n" +
//            "                                \"tray_id\": 11850,\n" +
//            "                                \"tray_id_value\": \"wjx测试ODM5-2\",\n" +
//            "                                \"zh_label\": \"wjx测试ODM5-2-2\"\n" +
//            "                            },\n" +
//            "                            {\n" +
//            "                                \"column_num\": 3,\n" +
//            "                                \"int_id\": 11853,\n" +
//            "                                \"odm_id\": 11836,\n" +
//            "                                \"odm_id_value\": \"wjx测试ODM5\",\n" +
//            "                                \"rack_id\": 11559,\n" +
//            "                                \"row_num\": 2,\n" +
//            "                                \"task_uuid\": \"PL_WX_TASK:7576f5b6-cdbd-4746-bb8f-5e536cdebfd9\",\n" +
//            "                                \"tray_id\": 11850,\n" +
//            "                                \"tray_id_value\": \"wjx测试ODM5-2\",\n" +
//            "                                \"zh_label\": \"wjx测试ODM5-2-3\"\n" +
//            "                            },\n" +
//            "                            {\n" +
//            "                                \"column_num\": 4,\n" +
//            "                                \"int_id\": 11854,\n" +
//            "                                \"odm_id\": 11836,\n" +
//            "                                \"odm_id_value\": \"wjx测试ODM5\",\n" +
//            "                                \"rack_id\": 11559,\n" +
//            "                                \"row_num\": 2,\n" +
//            "                                \"task_uuid\": \"PL_WX_TASK:7576f5b6-cdbd-4746-bb8f-5e536cdebfd9\",\n" +
//            "                                \"tray_id\": 11850,\n" +
//            "                                \"tray_id_value\": \"wjx测试ODM5-2\",\n" +
//            "                                \"zh_label\": \"wjx测试ODM5-2-4\"\n" +
//            "                            },\n" +
//            "                            {\n" +
//            "                                \"column_num\": 5,\n" +
//            "                                \"int_id\": 11855,\n" +
//            "                                \"odm_id\": 11836,\n" +
//            "                                \"odm_id_value\": \"wjx测试ODM5\",\n" +
//            "                                \"rack_id\": 11559,\n" +
//            "                                \"row_num\": 2,\n" +
//            "                                \"task_uuid\": \"PL_WX_TASK:7576f5b6-cdbd-4746-bb8f-5e536cdebfd9\",\n" +
//            "                                \"tray_id\": 11850,\n" +
//            "                                \"tray_id_value\": \"wjx测试ODM5-2\",\n" +
//            "                                \"zh_label\": \"wjx测试ODM5-2-5\"\n" +
//            "                            },\n" +
//            "                            {\n" +
//            "                                \"column_num\": 6,\n" +
//            "                                \"int_id\": 11856,\n" +
//            "                                \"odm_id\": 11836,\n" +
//            "                                \"odm_id_value\": \"wjx测试ODM5\",\n" +
//            "                                \"rack_id\": 11559,\n" +
//            "                                \"row_num\": 2,\n" +
//            "                                \"task_uuid\": \"PL_WX_TASK:7576f5b6-cdbd-4746-bb8f-5e536cdebfd9\",\n" +
//            "                                \"tray_id\": 11850,\n" +
//            "                                \"tray_id_value\": \"wjx测试ODM5-2\",\n" +
//            "                                \"zh_label\": \"wjx测试ODM5-2-6\"\n" +
//            "                            },\n" +
//            "                            {\n" +
//            "                                \"column_num\": 7,\n" +
//            "                                \"int_id\": 11857,\n" +
//            "                                \"odm_id\": 11836,\n" +
//            "                                \"odm_id_value\": \"wjx测试ODM5\",\n" +
//            "                                \"rack_id\": 11559,\n" +
//            "                                \"row_num\": 2,\n" +
//            "                                \"task_uuid\": \"PL_WX_TASK:7576f5b6-cdbd-4746-bb8f-5e536cdebfd9\",\n" +
//            "                                \"tray_id\": 11850,\n" +
//            "                                \"tray_id_value\": \"wjx测试ODM5-2\",\n" +
//            "                                \"zh_label\": \"wjx测试ODM5-2-7\"\n" +
//            "                            },\n" +
//            "                            {\n" +
//            "                                \"column_num\": 8,\n" +
//            "                                \"int_id\": 11858,\n" +
//            "                                \"odm_id\": 11836,\n" +
//            "                                \"odm_id_value\": \"wjx测试ODM5\",\n" +
//            "                                \"rack_id\": 11559,\n" +
//            "                                \"row_num\": 2,\n" +
//            "                                \"task_uuid\": \"PL_WX_TASK:7576f5b6-cdbd-4746-bb8f-5e536cdebfd9\",\n" +
//            "                                \"tray_id\": 11850,\n" +
//            "                                \"tray_id_value\": \"wjx测试ODM5-2\",\n" +
//            "                                \"zh_label\": \"wjx测试ODM5-2-8\"\n" +
//            "                            },\n" +
//            "                            {\n" +
//            "                                \"column_num\": 9,\n" +
//            "                                \"int_id\": 11859,\n" +
//            "                                \"odm_id\": 11836,\n" +
//            "                                \"odm_id_value\": \"wjx测试ODM5\",\n" +
//            "                                \"rack_id\": 11559,\n" +
//            "                                \"row_num\": 2,\n" +
//            "                                \"task_uuid\": \"PL_WX_TASK:7576f5b6-cdbd-4746-bb8f-5e536cdebfd9\",\n" +
//            "                                \"tray_id\": 11850,\n" +
//            "                                \"tray_id_value\": \"wjx测试ODM5-2\",\n" +
//            "                                \"zh_label\": \"wjx测试ODM5-2-9\"\n" +
//            "                            },\n" +
//            "                            {\n" +
//            "                                \"column_num\": 10,\n" +
//            "                                \"int_id\": 11860,\n" +
//            "                                \"odm_id\": 11836,\n" +
//            "                                \"odm_id_value\": \"wjx测试ODM5\",\n" +
//            "                                \"rack_id\": 11559,\n" +
//            "                                \"row_num\": 2,\n" +
//            "                                \"task_uuid\": \"PL_WX_TASK:7576f5b6-cdbd-4746-bb8f-5e536cdebfd9\",\n" +
//            "                                \"tray_id\": 11850,\n" +
//            "                                \"tray_id_value\": \"wjx测试ODM5-2\",\n" +
//            "                                \"zh_label\": \"wjx测试ODM5-2-10\"\n" +
//            "                            },\n" +
//            "                            {\n" +
//            "                                \"column_num\": 11,\n" +
//            "                                \"int_id\": 11861,\n" +
//            "                                \"odm_id\": 11836,\n" +
//            "                                \"odm_id_value\": \"wjx测试ODM5\",\n" +
//            "                                \"rack_id\": 11559,\n" +
//            "                                \"row_num\": 2,\n" +
//            "                                \"task_uuid\": \"PL_WX_TASK:7576f5b6-cdbd-4746-bb8f-5e536cdebfd9\",\n" +
//            "                                \"tray_id\": 11850,\n" +
//            "                                \"tray_id_value\": \"wjx测试ODM5-2\",\n" +
//            "                                \"zh_label\": \"wjx测试ODM5-2-11\"\n" +
//            "                            },\n" +
//            "                            {\n" +
//            "                                \"column_num\": 12,\n" +
//            "                                \"int_id\": 11862,\n" +
//            "                                \"odm_id\": 11836,\n" +
//            "                                \"odm_id_value\": \"wjx测试ODM5\",\n" +
//            "                                \"rack_id\": 11559,\n" +
//            "                                \"row_num\": 2,\n" +
//            "                                \"task_uuid\": \"PL_WX_TASK:7576f5b6-cdbd-4746-bb8f-5e536cdebfd9\",\n" +
//            "                                \"tray_id\": 11850,\n" +
//            "                                \"tray_id_value\": \"wjx测试ODM5-2\",\n" +
//            "                                \"zh_label\": \"wjx测试ODM5-2-12\"\n" +
//            "                            }\n" +
//            "                        ],\n" +
//            "                        \"zh_label\": \"wjx测试ODM5-2\"\n" +
//            "                    },\n" +
//            "                    {\n" +
//            "                        \"int_id\": 11863,\n" +
//            "                        \"no\": 3,\n" +
//            "                        \"odm_id\": 11836,\n" +
//            "                        \"rack_id\": 11559,\n" +
//            "                        \"remark\": \"22222222222222\",\n" +
//            "                        \"task_uuid\": \"PL_WX_TASK:7576f5b6-cdbd-4746-bb8f-5e536cdebfd9\",\n" +
//            "                        \"terminals\": [\n" +
//            "                            {\n" +
//            "                                \"column_num\": 1,\n" +
//            "                                \"int_id\": 11864,\n" +
//            "                                \"odm_id\": 11836,\n" +
//            "                                \"odm_id_value\": \"wjx测试ODM5\",\n" +
//            "                                \"rack_id\": 11559,\n" +
//            "                                \"row_num\": 3,\n" +
//            "                                \"task_uuid\": \"PL_WX_TASK:7576f5b6-cdbd-4746-bb8f-5e536cdebfd9\",\n" +
//            "                                \"tray_id\": 11863,\n" +
//            "                                \"tray_id_value\": \"wjx测试ODM5-3\",\n" +
//            "                                \"zh_label\": \"wjx测试ODM5-3-1\"\n" +
//            "                            },\n" +
//            "                            {\n" +
//            "                                \"column_num\": 2,\n" +
//            "                                \"int_id\": 11865,\n" +
//            "                                \"odm_id\": 11836,\n" +
//            "                                \"odm_id_value\": \"wjx测试ODM5\",\n" +
//            "                                \"rack_id\": 11559,\n" +
//            "                                \"row_num\": 3,\n" +
//            "                                \"task_uuid\": \"PL_WX_TASK:7576f5b6-cdbd-4746-bb8f-5e536cdebfd9\",\n" +
//            "                                \"tray_id\": 11863,\n" +
//            "                                \"tray_id_value\": \"wjx测试ODM5-3\",\n" +
//            "                                \"zh_label\": \"wjx测试ODM5-3-2\"\n" +
//            "                            },\n" +
//            "                            {\n" +
//            "                                \"column_num\": 3,\n" +
//            "                                \"int_id\": 11866,\n" +
//            "                                \"odm_id\": 11836,\n" +
//            "                                \"odm_id_value\": \"wjx测试ODM5\",\n" +
//            "                                \"rack_id\": 11559,\n" +
//            "                                \"row_num\": 3,\n" +
//            "                                \"task_uuid\": \"PL_WX_TASK:7576f5b6-cdbd-4746-bb8f-5e536cdebfd9\",\n" +
//            "                                \"tray_id\": 11863,\n" +
//            "                                \"tray_id_value\": \"wjx测试ODM5-3\",\n" +
//            "                                \"zh_label\": \"wjx测试ODM5-3-3\"\n" +
//            "                            },\n" +
//            "                            {\n" +
//            "                                \"column_num\": 4,\n" +
//            "                                \"int_id\": 11867,\n" +
//            "                                \"odm_id\": 11836,\n" +
//            "                                \"odm_id_value\": \"wjx测试ODM5\",\n" +
//            "                                \"rack_id\": 11559,\n" +
//            "                                \"row_num\": 3,\n" +
//            "                                \"task_uuid\": \"PL_WX_TASK:7576f5b6-cdbd-4746-bb8f-5e536cdebfd9\",\n" +
//            "                                \"tray_id\": 11863,\n" +
//            "                                \"tray_id_value\": \"wjx测试ODM5-3\",\n" +
//            "                                \"zh_label\": \"wjx测试ODM5-3-4\"\n" +
//            "                            },\n" +
//            "                            {\n" +
//            "                                \"column_num\": 5,\n" +
//            "                                \"int_id\": 11868,\n" +
//            "                                \"odm_id\": 11836,\n" +
//            "                                \"odm_id_value\": \"wjx测试ODM5\",\n" +
//            "                                \"rack_id\": 11559,\n" +
//            "                                \"row_num\": 3,\n" +
//            "                                \"task_uuid\": \"PL_WX_TASK:7576f5b6-cdbd-4746-bb8f-5e536cdebfd9\",\n" +
//            "                                \"tray_id\": 11863,\n" +
//            "                                \"tray_id_value\": \"wjx测试ODM5-3\",\n" +
//            "                                \"zh_label\": \"wjx测试ODM5-3-5\"\n" +
//            "                            },\n" +
//            "                            {\n" +
//            "                                \"column_num\": 6,\n" +
//            "                                \"int_id\": 11869,\n" +
//            "                                \"odm_id\": 11836,\n" +
//            "                                \"odm_id_value\": \"wjx测试ODM5\",\n" +
//            "                                \"rack_id\": 11559,\n" +
//            "                                \"row_num\": 3,\n" +
//            "                                \"task_uuid\": \"PL_WX_TASK:7576f5b6-cdbd-4746-bb8f-5e536cdebfd9\",\n" +
//            "                                \"tray_id\": 11863,\n" +
//            "                                \"tray_id_value\": \"wjx测试ODM5-3\",\n" +
//            "                                \"zh_label\": \"wjx测试ODM5-3-6\"\n" +
//            "                            },\n" +
//            "                            {\n" +
//            "                                \"column_num\": 7,\n" +
//            "                                \"int_id\": 11870,\n" +
//            "                                \"odm_id\": 11836,\n" +
//            "                                \"odm_id_value\": \"wjx测试ODM5\",\n" +
//            "                                \"rack_id\": 11559,\n" +
//            "                                \"row_num\": 3,\n" +
//            "                                \"task_uuid\": \"PL_WX_TASK:7576f5b6-cdbd-4746-bb8f-5e536cdebfd9\",\n" +
//            "                                \"tray_id\": 11863,\n" +
//            "                                \"tray_id_value\": \"wjx测试ODM5-3\",\n" +
//            "                                \"zh_label\": \"wjx测试ODM5-3-7\"\n" +
//            "                            },\n" +
//            "                            {\n" +
//            "                                \"column_num\": 8,\n" +
//            "                                \"int_id\": 11871,\n" +
//            "                                \"odm_id\": 11836,\n" +
//            "                                \"odm_id_value\": \"wjx测试ODM5\",\n" +
//            "                                \"rack_id\": 11559,\n" +
//            "                                \"row_num\": 3,\n" +
//            "                                \"task_uuid\": \"PL_WX_TASK:7576f5b6-cdbd-4746-bb8f-5e536cdebfd9\",\n" +
//            "                                \"tray_id\": 11863,\n" +
//            "                                \"tray_id_value\": \"wjx测试ODM5-3\",\n" +
//            "                                \"zh_label\": \"wjx测试ODM5-3-8\"\n" +
//            "                            },\n" +
//            "                            {\n" +
//            "                                \"column_num\": 9,\n" +
//            "                                \"int_id\": 11872,\n" +
//            "                                \"odm_id\": 11836,\n" +
//            "                                \"odm_id_value\": \"wjx测试ODM5\",\n" +
//            "                                \"rack_id\": 11559,\n" +
//            "                                \"row_num\": 3,\n" +
//            "                                \"task_uuid\": \"PL_WX_TASK:7576f5b6-cdbd-4746-bb8f-5e536cdebfd9\",\n" +
//            "                                \"tray_id\": 11863,\n" +
//            "                                \"tray_id_value\": \"wjx测试ODM5-3\",\n" +
//            "                                \"zh_label\": \"wjx测试ODM5-3-9\"\n" +
//            "                            },\n" +
//            "                            {\n" +
//            "                                \"column_num\": 10,\n" +
//            "                                \"int_id\": 11873,\n" +
//            "                                \"odm_id\": 11836,\n" +
//            "                                \"odm_id_value\": \"wjx测试ODM5\",\n" +
//            "                                \"rack_id\": 11559,\n" +
//            "                                \"row_num\": 3,\n" +
//            "                                \"task_uuid\": \"PL_WX_TASK:7576f5b6-cdbd-4746-bb8f-5e536cdebfd9\",\n" +
//            "                                \"tray_id\": 11863,\n" +
//            "                                \"tray_id_value\": \"wjx测试ODM5-3\",\n" +
//            "                                \"zh_label\": \"wjx测试ODM5-3-10\"\n" +
//            "                            },\n" +
//            "                            {\n" +
//            "                                \"column_num\": 11,\n" +
//            "                                \"int_id\": 11874,\n" +
//            "                                \"odm_id\": 11836,\n" +
//            "                                \"odm_id_value\": \"wjx测试ODM5\",\n" +
//            "                                \"rack_id\": 11559,\n" +
//            "                                \"row_num\": 3,\n" +
//            "                                \"task_uuid\": \"PL_WX_TASK:7576f5b6-cdbd-4746-bb8f-5e536cdebfd9\",\n" +
//            "                                \"tray_id\": 11863,\n" +
//            "                                \"tray_id_value\": \"wjx测试ODM5-3\",\n" +
//            "                                \"zh_label\": \"wjx测试ODM5-3-11\"\n" +
//            "                            },\n" +
//            "                            {\n" +
//            "                                \"column_num\": 12,\n" +
//            "                                \"int_id\": 11875,\n" +
//            "                                \"odm_id\": 11836,\n" +
//            "                                \"odm_id_value\": \"wjx测试ODM5\",\n" +
//            "                                \"rack_id\": 11559,\n" +
//            "                                \"row_num\": 3,\n" +
//            "                                \"task_uuid\": \"PL_WX_TASK:7576f5b6-cdbd-4746-bb8f-5e536cdebfd9\",\n" +
//            "                                \"tray_id\": 11863,\n" +
//            "                                \"tray_id_value\": \"wjx测试ODM5-3\",\n" +
//            "                                \"zh_label\": \"wjx测试ODM5-3-12\"\n" +
//            "                            }\n" +
//            "                        ],\n" +
//            "                        \"zh_label\": \"wjx测试ODM5-3\"\n" +
//            "                    },\n" +
//            "                    {\n" +
//            "                        \"int_id\": 11876,\n" +
//            "                        \"no\": 4,\n" +
//            "                        \"odm_id\": 11836,\n" +
//            "                        \"rack_id\": 11559,\n" +
//            "                        \"remark\": \"wwwwwwwwww\",\n" +
//            "                        \"task_uuid\": \"PL_WX_TASK:7576f5b6-cdbd-4746-bb8f-5e536cdebfd9\",\n" +
//            "                        \"terminals\": [\n" +
//            "                            {\n" +
//            "                                \"column_num\": 1,\n" +
//            "                                \"int_id\": 11877,\n" +
//            "                                \"odm_id\": 11836,\n" +
//            "                                \"odm_id_value\": \"wjx测试ODM5\",\n" +
//            "                                \"rack_id\": 11559,\n" +
//            "                                \"row_num\": 4,\n" +
//            "                                \"task_uuid\": \"PL_WX_TASK:7576f5b6-cdbd-4746-bb8f-5e536cdebfd9\",\n" +
//            "                                \"tray_id\": 11876,\n" +
//            "                                \"tray_id_value\": \"wjx测试ODM5-4\",\n" +
//            "                                \"zh_label\": \"wjx测试ODM5-4-1\"\n" +
//            "                            },\n" +
//            "                            {\n" +
//            "                                \"column_num\": 2,\n" +
//            "                                \"int_id\": 11878,\n" +
//            "                                \"odm_id\": 11836,\n" +
//            "                                \"odm_id_value\": \"wjx测试ODM5\",\n" +
//            "                                \"rack_id\": 11559,\n" +
//            "                                \"row_num\": 4,\n" +
//            "                                \"task_uuid\": \"PL_WX_TASK:7576f5b6-cdbd-4746-bb8f-5e536cdebfd9\",\n" +
//            "                                \"tray_id\": 11876,\n" +
//            "                                \"tray_id_value\": \"wjx测试ODM5-4\",\n" +
//            "                                \"zh_label\": \"wjx测试ODM5-4-2\"\n" +
//            "                            },\n" +
//            "                            {\n" +
//            "                                \"column_num\": 3,\n" +
//            "                                \"int_id\": 11879,\n" +
//            "                                \"odm_id\": 11836,\n" +
//            "                                \"odm_id_value\": \"wjx测试ODM5\",\n" +
//            "                                \"rack_id\": 11559,\n" +
//            "                                \"row_num\": 4,\n" +
//            "                                \"task_uuid\": \"PL_WX_TASK:7576f5b6-cdbd-4746-bb8f-5e536cdebfd9\",\n" +
//            "                                \"tray_id\": 11876,\n" +
//            "                                \"tray_id_value\": \"wjx测试ODM5-4\",\n" +
//            "                                \"zh_label\": \"wjx测试ODM5-4-3\"\n" +
//            "                            },\n" +
//            "                            {\n" +
//            "                                \"column_num\": 4,\n" +
//            "                                \"int_id\": 11880,\n" +
//            "                                \"odm_id\": 11836,\n" +
//            "                                \"odm_id_value\": \"wjx测试ODM5\",\n" +
//            "                                \"rack_id\": 11559,\n" +
//            "                                \"row_num\": 4,\n" +
//            "                                \"task_uuid\": \"PL_WX_TASK:7576f5b6-cdbd-4746-bb8f-5e536cdebfd9\",\n" +
//            "                                \"tray_id\": 11876,\n" +
//            "                                \"tray_id_value\": \"wjx测试ODM5-4\",\n" +
//            "                                \"zh_label\": \"wjx测试ODM5-4-4\"\n" +
//            "                            },\n" +
//            "                            {\n" +
//            "                                \"column_num\": 5,\n" +
//            "                                \"int_id\": 11881,\n" +
//            "                                \"odm_id\": 11836,\n" +
//            "                                \"odm_id_value\": \"wjx测试ODM5\",\n" +
//            "                                \"rack_id\": 11559,\n" +
//            "                                \"row_num\": 4,\n" +
//            "                                \"task_uuid\": \"PL_WX_TASK:7576f5b6-cdbd-4746-bb8f-5e536cdebfd9\",\n" +
//            "                                \"tray_id\": 11876,\n" +
//            "                                \"tray_id_value\": \"wjx测试ODM5-4\",\n" +
//            "                                \"zh_label\": \"wjx测试ODM5-4-5\"\n" +
//            "                            },\n" +
//            "                            {\n" +
//            "                                \"column_num\": 6,\n" +
//            "                                \"int_id\": 11882,\n" +
//            "                                \"odm_id\": 11836,\n" +
//            "                                \"odm_id_value\": \"wjx测试ODM5\",\n" +
//            "                                \"rack_id\": 11559,\n" +
//            "                                \"row_num\": 4,\n" +
//            "                                \"task_uuid\": \"PL_WX_TASK:7576f5b6-cdbd-4746-bb8f-5e536cdebfd9\",\n" +
//            "                                \"tray_id\": 11876,\n" +
//            "                                \"tray_id_value\": \"wjx测试ODM5-4\",\n" +
//            "                                \"zh_label\": \"wjx测试ODM5-4-6\"\n" +
//            "                            },\n" +
//            "                            {\n" +
//            "                                \"column_num\": 7,\n" +
//            "                                \"int_id\": 11883,\n" +
//            "                                \"odm_id\": 11836,\n" +
//            "                                \"odm_id_value\": \"wjx测试ODM5\",\n" +
//            "                                \"rack_id\": 11559,\n" +
//            "                                \"row_num\": 4,\n" +
//            "                                \"task_uuid\": \"PL_WX_TASK:7576f5b6-cdbd-4746-bb8f-5e536cdebfd9\",\n" +
//            "                                \"tray_id\": 11876,\n" +
//            "                                \"tray_id_value\": \"wjx测试ODM5-4\",\n" +
//            "                                \"zh_label\": \"wjx测试ODM5-4-7\"\n" +
//            "                            },\n" +
//            "                            {\n" +
//            "                                \"column_num\": 8,\n" +
//            "                                \"int_id\": 11884,\n" +
//            "                                \"odm_id\": 11836,\n" +
//            "                                \"odm_id_value\": \"wjx测试ODM5\",\n" +
//            "                                \"rack_id\": 11559,\n" +
//            "                                \"row_num\": 4,\n" +
//            "                                \"task_uuid\": \"PL_WX_TASK:7576f5b6-cdbd-4746-bb8f-5e536cdebfd9\",\n" +
//            "                                \"tray_id\": 11876,\n" +
//            "                                \"tray_id_value\": \"wjx测试ODM5-4\",\n" +
//            "                                \"zh_label\": \"wjx测试ODM5-4-8\"\n" +
//            "                            },\n" +
//            "                            {\n" +
//            "                                \"column_num\": 9,\n" +
//            "                                \"int_id\": 11885,\n" +
//            "                                \"odm_id\": 11836,\n" +
//            "                                \"odm_id_value\": \"wjx测试ODM5\",\n" +
//            "                                \"rack_id\": 11559,\n" +
//            "                                \"row_num\": 4,\n" +
//            "                                \"task_uuid\": \"PL_WX_TASK:7576f5b6-cdbd-4746-bb8f-5e536cdebfd9\",\n" +
//            "                                \"tray_id\": 11876,\n" +
//            "                                \"tray_id_value\": \"wjx测试ODM5-4\",\n" +
//            "                                \"zh_label\": \"wjx测试ODM5-4-9\"\n" +
//            "                            },\n" +
//            "                            {\n" +
//            "                                \"column_num\": 10,\n" +
//            "                                \"int_id\": 11886,\n" +
//            "                                \"odm_id\": 11836,\n" +
//            "                                \"odm_id_value\": \"wjx测试ODM5\",\n" +
//            "                                \"rack_id\": 11559,\n" +
//            "                                \"row_num\": 4,\n" +
//            "                                \"task_uuid\": \"PL_WX_TASK:7576f5b6-cdbd-4746-bb8f-5e536cdebfd9\",\n" +
//            "                                \"tray_id\": 11876,\n" +
//            "                                \"tray_id_value\": \"wjx测试ODM5-4\",\n" +
//            "                                \"zh_label\": \"wjx测试ODM5-4-10\"\n" +
//            "                            },\n" +
//            "                            {\n" +
//            "                                \"column_num\": 11,\n" +
//            "                                \"int_id\": 11887,\n" +
//            "                                \"odm_id\": 11836,\n" +
//            "                                \"odm_id_value\": \"wjx测试ODM5\",\n" +
//            "                                \"rack_id\": 11559,\n" +
//            "                                \"row_num\": 4,\n" +
//            "                                \"task_uuid\": \"PL_WX_TASK:7576f5b6-cdbd-4746-bb8f-5e536cdebfd9\",\n" +
//            "                                \"tray_id\": 11876,\n" +
//            "                                \"tray_id_value\": \"wjx测试ODM5-4\",\n" +
//            "                                \"zh_label\": \"wjx测试ODM5-4-11\"\n" +
//            "                            },\n" +
//            "                            {\n" +
//            "                                \"column_num\": 12,\n" +
//            "                                \"int_id\": 11888,\n" +
//            "                                \"odm_id\": 11836,\n" +
//            "                                \"odm_id_value\": \"wjx测试ODM5\",\n" +
//            "                                \"rack_id\": 11559,\n" +
//            "                                \"row_num\": 4,\n" +
//            "                                \"task_uuid\": \"PL_WX_TASK:7576f5b6-cdbd-4746-bb8f-5e536cdebfd9\",\n" +
//            "                                \"tray_id\": 11876,\n" +
//            "                                \"tray_id_value\": \"wjx测试ODM5-4\",\n" +
//            "                                \"zh_label\": \"wjx测试ODM5-4-12\"\n" +
//            "                            }\n" +
//            "                        ],\n" +
//            "                        \"zh_label\": \"wjx测试ODM5-4\"\n" +
//            "                    }\n" +
//            "                ],\n" +
//            "                \"x\": 91.08524,\n" +
//            "                \"y\": 361.42976,\n" +
//            "                \"zh_label\": \"wjx测试ODM5\"\n" +
//            "            },\n" +
//            "            {\n" +
//            "                \"column_no\": 12,\n" +
//            "                \"create_type\": \"0\",\n" +
//            "                \"end_u\": 28,\n" +
//            "                \"int_id\": 11739,\n" +
//            "                \"rack_id\": 11559,\n" +
//            "                \"row_no\": 2,\n" +
//            "                \"start_u\": 27,\n" +
//            "                \"task_uuid\": \"PL_WX_TASK:7576f5b6-cdbd-4746-bb8f-5e536cdebfd9\",\n" +
//            "                \"trays\": [\n" +
//            "                    {\n" +
//            "                        \"int_id\": 11740,\n" +
//            "                        \"no\": 1,\n" +
//            "                        \"odm_id\": 11739,\n" +
//            "                        \"rack_id\": 11559,\n" +
//            "                        \"remark\": \"909\",\n" +
//            "                        \"task_uuid\": \"PL_WX_TASK:7576f5b6-cdbd-4746-bb8f-5e536cdebfd9\",\n" +
//            "                        \"terminals\": [\n" +
//            "                            {\n" +
//            "                                \"column_num\": 1,\n" +
//            "                                \"int_id\": 11741,\n" +
//            "                                \"odm_id\": 11739,\n" +
//            "                                \"odm_id_value\": \"ODM1\",\n" +
//            "                                \"rack_id\": 11559,\n" +
//            "                                \"row_num\": 1,\n" +
//            "                                \"task_uuid\": \"PL_WX_TASK:7576f5b6-cdbd-4746-bb8f-5e536cdebfd9\",\n" +
//            "                                \"tray_id\": 11740,\n" +
//            "                                \"tray_id_value\": \"ODM1-1\",\n" +
//            "                                \"zh_label\": \"ODM1-1-1\"\n" +
//            "                            },\n" +
//            "                            {\n" +
//            "                                \"column_num\": 2,\n" +
//            "                                \"int_id\": 11742,\n" +
//            "                                \"odm_id\": 11739,\n" +
//            "                                \"odm_id_value\": \"ODM1\",\n" +
//            "                                \"rack_id\": 11559,\n" +
//            "                                \"row_num\": 1,\n" +
//            "                                \"task_uuid\": \"PL_WX_TASK:7576f5b6-cdbd-4746-bb8f-5e536cdebfd9\",\n" +
//            "                                \"tray_id\": 11740,\n" +
//            "                                \"tray_id_value\": \"ODM1-1\",\n" +
//            "                                \"zh_label\": \"ODM1-1-2\"\n" +
//            "                            },\n" +
//            "                            {\n" +
//            "                                \"column_num\": 3,\n" +
//            "                                \"int_id\": 11743,\n" +
//            "                                \"odm_id\": 11739,\n" +
//            "                                \"odm_id_value\": \"ODM1\",\n" +
//            "                                \"rack_id\": 11559,\n" +
//            "                                \"row_num\": 1,\n" +
//            "                                \"task_uuid\": \"PL_WX_TASK:7576f5b6-cdbd-4746-bb8f-5e536cdebfd9\",\n" +
//            "                                \"tray_id\": 11740,\n" +
//            "                                \"tray_id_value\": \"ODM1-1\",\n" +
//            "                                \"zh_label\": \"ODM1-1-3\"\n" +
//            "                            },\n" +
//            "                            {\n" +
//            "                                \"column_num\": 4,\n" +
//            "                                \"int_id\": 11744,\n" +
//            "                                \"odm_id\": 11739,\n" +
//            "                                \"odm_id_value\": \"ODM1\",\n" +
//            "                                \"rack_id\": 11559,\n" +
//            "                                \"row_num\": 1,\n" +
//            "                                \"task_uuid\": \"PL_WX_TASK:7576f5b6-cdbd-4746-bb8f-5e536cdebfd9\",\n" +
//            "                                \"tray_id\": 11740,\n" +
//            "                                \"tray_id_value\": \"ODM1-1\",\n" +
//            "                                \"zh_label\": \"ODM1-1-4\"\n" +
//            "                            },\n" +
//            "                            {\n" +
//            "                                \"column_num\": 5,\n" +
//            "                                \"int_id\": 11745,\n" +
//            "                                \"odm_id\": 11739,\n" +
//            "                                \"odm_id_value\": \"ODM1\",\n" +
//            "                                \"rack_id\": 11559,\n" +
//            "                                \"row_num\": 1,\n" +
//            "                                \"task_uuid\": \"PL_WX_TASK:7576f5b6-cdbd-4746-bb8f-5e536cdebfd9\",\n" +
//            "                                \"tray_id\": 11740,\n" +
//            "                                \"tray_id_value\": \"ODM1-1\",\n" +
//            "                                \"zh_label\": \"ODM1-1-5\"\n" +
//            "                            },\n" +
//            "                            {\n" +
//            "                                \"column_num\": 6,\n" +
//            "                                \"int_id\": 11746,\n" +
//            "                                \"odm_id\": 11739,\n" +
//            "                                \"odm_id_value\": \"ODM1\",\n" +
//            "                                \"rack_id\": 11559,\n" +
//            "                                \"row_num\": 1,\n" +
//            "                                \"task_uuid\": \"PL_WX_TASK:7576f5b6-cdbd-4746-bb8f-5e536cdebfd9\",\n" +
//            "                                \"tray_id\": 11740,\n" +
//            "                                \"tray_id_value\": \"ODM1-1\",\n" +
//            "                                \"zh_label\": \"ODM1-1-6\"\n" +
//            "                            },\n" +
//            "                            {\n" +
//            "                                \"column_num\": 7,\n" +
//            "                                \"int_id\": 11747,\n" +
//            "                                \"odm_id\": 11739,\n" +
//            "                                \"odm_id_value\": \"ODM1\",\n" +
//            "                                \"rack_id\": 11559,\n" +
//            "                                \"row_num\": 1,\n" +
//            "                                \"task_uuid\": \"PL_WX_TASK:7576f5b6-cdbd-4746-bb8f-5e536cdebfd9\",\n" +
//            "                                \"tray_id\": 11740,\n" +
//            "                                \"tray_id_value\": \"ODM1-1\",\n" +
//            "                                \"zh_label\": \"ODM1-1-7\"\n" +
//            "                            },\n" +
//            "                            {\n" +
//            "                                \"column_num\": 8,\n" +
//            "                                \"int_id\": 11748,\n" +
//            "                                \"odm_id\": 11739,\n" +
//            "                                \"odm_id_value\": \"ODM1\",\n" +
//            "                                \"rack_id\": 11559,\n" +
//            "                                \"row_num\": 1,\n" +
//            "                                \"task_uuid\": \"PL_WX_TASK:7576f5b6-cdbd-4746-bb8f-5e536cdebfd9\",\n" +
//            "                                \"tray_id\": 11740,\n" +
//            "                                \"tray_id_value\": \"ODM1-1\",\n" +
//            "                                \"zh_label\": \"ODM1-1-8\"\n" +
//            "                            },\n" +
//            "                            {\n" +
//            "                                \"column_num\": 9,\n" +
//            "                                \"int_id\": 11749,\n" +
//            "                                \"odm_id\": 11739,\n" +
//            "                                \"odm_id_value\": \"ODM1\",\n" +
//            "                                \"rack_id\": 11559,\n" +
//            "                                \"row_num\": 1,\n" +
//            "                                \"task_uuid\": \"PL_WX_TASK:7576f5b6-cdbd-4746-bb8f-5e536cdebfd9\",\n" +
//            "                                \"tray_id\": 11740,\n" +
//            "                                \"tray_id_value\": \"ODM1-1\",\n" +
//            "                                \"zh_label\": \"ODM1-1-9\"\n" +
//            "                            },\n" +
//            "                            {\n" +
//            "                                \"column_num\": 10,\n" +
//            "                                \"int_id\": 11750,\n" +
//            "                                \"odm_id\": 11739,\n" +
//            "                                \"odm_id_value\": \"ODM1\",\n" +
//            "                                \"rack_id\": 11559,\n" +
//            "                                \"row_num\": 1,\n" +
//            "                                \"task_uuid\": \"PL_WX_TASK:7576f5b6-cdbd-4746-bb8f-5e536cdebfd9\",\n" +
//            "                                \"tray_id\": 11740,\n" +
//            "                                \"tray_id_value\": \"ODM1-1\",\n" +
//            "                                \"zh_label\": \"ODM1-1-10\"\n" +
//            "                            },\n" +
//            "                            {\n" +
//            "                                \"column_num\": 11,\n" +
//            "                                \"int_id\": 11751,\n" +
//            "                                \"odm_id\": 11739,\n" +
//            "                                \"odm_id_value\": \"ODM1\",\n" +
//            "                                \"rack_id\": 11559,\n" +
//            "                                \"row_num\": 1,\n" +
//            "                                \"task_uuid\": \"PL_WX_TASK:7576f5b6-cdbd-4746-bb8f-5e536cdebfd9\",\n" +
//            "                                \"tray_id\": 11740,\n" +
//            "                                \"tray_id_value\": \"ODM1-1\",\n" +
//            "                                \"zh_label\": \"ODM1-1-11\"\n" +
//            "                            },\n" +
//            "                            {\n" +
//            "                                \"column_num\": 12,\n" +
//            "                                \"int_id\": 11752,\n" +
//            "                                \"odm_id\": 11739,\n" +
//            "                                \"odm_id_value\": \"ODM1\",\n" +
//            "                                \"rack_id\": 11559,\n" +
//            "                                \"row_num\": 1,\n" +
//            "                                \"task_uuid\": \"PL_WX_TASK:7576f5b6-cdbd-4746-bb8f-5e536cdebfd9\",\n" +
//            "                                \"tray_id\": 11740,\n" +
//            "                                \"tray_id_value\": \"ODM1-1\",\n" +
//            "                                \"zh_label\": \"ODM1-1-12\"\n" +
//            "                            }\n" +
//            "                        ],\n" +
//            "                        \"zh_label\": \"ODM1-1\"\n" +
//            "                    },\n" +
//            "                    {\n" +
//            "                        \"int_id\": 11753,\n" +
//            "                        \"no\": 2,\n" +
//            "                        \"odm_id\": 11739,\n" +
//            "                        \"rack_id\": 11559,\n" +
//            "                        \"remark\": \"kkkkkkkkkkkkkkk\",\n" +
//            "                        \"task_uuid\": \"PL_WX_TASK:7576f5b6-cdbd-4746-bb8f-5e536cdebfd9\",\n" +
//            "                        \"terminals\": [\n" +
//            "                            {\n" +
//            "                                \"accountinfo\": \"fdfsfsdfds\",\n" +
//            "                                \"column_num\": 1,\n" +
//            "                                \"int_id\": 11754,\n" +
//            "                                \"odm_id\": 11739,\n" +
//            "                                \"odm_id_value\": \"ODM1\",\n" +
//            "                                \"rack_id\": 11559,\n" +
//            "                                \"row_num\": 2,\n" +
//            "                                \"task_uuid\": \"PL_WX_TASK:7576f5b6-cdbd-4746-bb8f-5e536cdebfd9\",\n" +
//            "                                \"tray_id\": 11753,\n" +
//            "                                \"tray_id_value\": \"ODM1-2\",\n" +
//            "                                \"zh_label\": \"ODM1-2-1\"\n" +
//            "                            },\n" +
//            "                            {\n" +
//            "                                \"column_num\": 2,\n" +
//            "                                \"int_id\": 11755,\n" +
//            "                                \"odm_id\": 11739,\n" +
//            "                                \"odm_id_value\": \"ODM1\",\n" +
//            "                                \"rack_id\": 11559,\n" +
//            "                                \"row_num\": 2,\n" +
//            "                                \"task_uuid\": \"PL_WX_TASK:7576f5b6-cdbd-4746-bb8f-5e536cdebfd9\",\n" +
//            "                                \"tray_id\": 11753,\n" +
//            "                                \"tray_id_value\": \"ODM1-2\",\n" +
//            "                                \"zh_label\": \"ODM1-2-2\"\n" +
//            "                            },\n" +
//            "                            {\n" +
//            "                                \"column_num\": 3,\n" +
//            "                                \"int_id\": 11756,\n" +
//            "                                \"odm_id\": 11739,\n" +
//            "                                \"odm_id_value\": \"ODM1\",\n" +
//            "                                \"rack_id\": 11559,\n" +
//            "                                \"row_num\": 2,\n" +
//            "                                \"task_uuid\": \"PL_WX_TASK:7576f5b6-cdbd-4746-bb8f-5e536cdebfd9\",\n" +
//            "                                \"tray_id\": 11753,\n" +
//            "                                \"tray_id_value\": \"ODM1-2\",\n" +
//            "                                \"zh_label\": \"ODM1-2-3\"\n" +
//            "                            },\n" +
//            "                            {\n" +
//            "                                \"column_num\": 4,\n" +
//            "                                \"int_id\": 11757,\n" +
//            "                                \"odm_id\": 11739,\n" +
//            "                                \"odm_id_value\": \"ODM1\",\n" +
//            "                                \"rack_id\": 11559,\n" +
//            "                                \"row_num\": 2,\n" +
//            "                                \"task_uuid\": \"PL_WX_TASK:7576f5b6-cdbd-4746-bb8f-5e536cdebfd9\",\n" +
//            "                                \"tray_id\": 11753,\n" +
//            "                                \"tray_id_value\": \"ODM1-2\",\n" +
//            "                                \"zh_label\": \"ODM1-2-4\"\n" +
//            "                            },\n" +
//            "                            {\n" +
//            "                                \"column_num\": 5,\n" +
//            "                                \"int_id\": 11758,\n" +
//            "                                \"odm_id\": 11739,\n" +
//            "                                \"odm_id_value\": \"ODM1\",\n" +
//            "                                \"rack_id\": 11559,\n" +
//            "                                \"row_num\": 2,\n" +
//            "                                \"task_uuid\": \"PL_WX_TASK:7576f5b6-cdbd-4746-bb8f-5e536cdebfd9\",\n" +
//            "                                \"tray_id\": 11753,\n" +
//            "                                \"tray_id_value\": \"ODM1-2\",\n" +
//            "                                \"zh_label\": \"ODM1-2-5\"\n" +
//            "                            },\n" +
//            "                            {\n" +
//            "                                \"column_num\": 6,\n" +
//            "                                \"int_id\": 11759,\n" +
//            "                                \"odm_id\": 11739,\n" +
//            "                                \"odm_id_value\": \"ODM1\",\n" +
//            "                                \"rack_id\": 11559,\n" +
//            "                                \"row_num\": 2,\n" +
//            "                                \"task_uuid\": \"PL_WX_TASK:7576f5b6-cdbd-4746-bb8f-5e536cdebfd9\",\n" +
//            "                                \"tray_id\": 11753,\n" +
//            "                                \"tray_id_value\": \"ODM1-2\",\n" +
//            "                                \"zh_label\": \"ODM1-2-6\"\n" +
//            "                            },\n" +
//            "                            {\n" +
//            "                                \"column_num\": 7,\n" +
//            "                                \"int_id\": 11760,\n" +
//            "                                \"odm_id\": 11739,\n" +
//            "                                \"odm_id_value\": \"ODM1\",\n" +
//            "                                \"rack_id\": 11559,\n" +
//            "                                \"row_num\": 2,\n" +
//            "                                \"task_uuid\": \"PL_WX_TASK:7576f5b6-cdbd-4746-bb8f-5e536cdebfd9\",\n" +
//            "                                \"tray_id\": 11753,\n" +
//            "                                \"tray_id_value\": \"ODM1-2\",\n" +
//            "                                \"zh_label\": \"ODM1-2-7\"\n" +
//            "                            },\n" +
//            "                            {\n" +
//            "                                \"column_num\": 8,\n" +
//            "                                \"int_id\": 11761,\n" +
//            "                                \"odm_id\": 11739,\n" +
//            "                                \"odm_id_value\": \"ODM1\",\n" +
//            "                                \"rack_id\": 11559,\n" +
//            "                                \"row_num\": 2,\n" +
//            "                                \"task_uuid\": \"PL_WX_TASK:7576f5b6-cdbd-4746-bb8f-5e536cdebfd9\",\n" +
//            "                                \"tray_id\": 11753,\n" +
//            "                                \"tray_id_value\": \"ODM1-2\",\n" +
//            "                                \"zh_label\": \"ODM1-2-8\"\n" +
//            "                            },\n" +
//            "                            {\n" +
//            "                                \"column_num\": 9,\n" +
//            "                                \"int_id\": 11762,\n" +
//            "                                \"odm_id\": 11739,\n" +
//            "                                \"odm_id_value\": \"ODM1\",\n" +
//            "                                \"rack_id\": 11559,\n" +
//            "                                \"row_num\": 2,\n" +
//            "                                \"task_uuid\": \"PL_WX_TASK:7576f5b6-cdbd-4746-bb8f-5e536cdebfd9\",\n" +
//            "                                \"tray_id\": 11753,\n" +
//            "                                \"tray_id_value\": \"ODM1-2\",\n" +
//            "                                \"zh_label\": \"ODM1-2-9\"\n" +
//            "                            },\n" +
//            "                            {\n" +
//            "                                \"column_num\": 10,\n" +
//            "                                \"int_id\": 11763,\n" +
//            "                                \"odm_id\": 11739,\n" +
//            "                                \"odm_id_value\": \"ODM1\",\n" +
//            "                                \"rack_id\": 11559,\n" +
//            "                                \"row_num\": 2,\n" +
//            "                                \"task_uuid\": \"PL_WX_TASK:7576f5b6-cdbd-4746-bb8f-5e536cdebfd9\",\n" +
//            "                                \"tray_id\": 11753,\n" +
//            "                                \"tray_id_value\": \"ODM1-2\",\n" +
//            "                                \"zh_label\": \"ODM1-2-10\"\n" +
//            "                            },\n" +
//            "                            {\n" +
//            "                                \"column_num\": 11,\n" +
//            "                                \"int_id\": 11764,\n" +
//            "                                \"odm_id\": 11739,\n" +
//            "                                \"odm_id_value\": \"ODM1\",\n" +
//            "                                \"rack_id\": 11559,\n" +
//            "                                \"row_num\": 2,\n" +
//            "                                \"task_uuid\": \"PL_WX_TASK:7576f5b6-cdbd-4746-bb8f-5e536cdebfd9\",\n" +
//            "                                \"tray_id\": 11753,\n" +
//            "                                \"tray_id_value\": \"ODM1-2\",\n" +
//            "                                \"zh_label\": \"ODM1-2-11\"\n" +
//            "                            },\n" +
//            "                            {\n" +
//            "                                \"column_num\": 12,\n" +
//            "                                \"int_id\": 11765,\n" +
//            "                                \"odm_id\": 11739,\n" +
//            "                                \"odm_id_value\": \"ODM1\",\n" +
//            "                                \"rack_id\": 11559,\n" +
//            "                                \"row_num\": 2,\n" +
//            "                                \"task_uuid\": \"PL_WX_TASK:7576f5b6-cdbd-4746-bb8f-5e536cdebfd9\",\n" +
//            "                                \"tray_id\": 11753,\n" +
//            "                                \"tray_id_value\": \"ODM1-2\",\n" +
//            "                                \"zh_label\": \"ODM1-2-12\"\n" +
//            "                            }\n" +
//            "                        ],\n" +
//            "                        \"zh_label\": \"ODM1-2\"\n" +
//            "                    }\n" +
//            "                ],\n" +
//            "                \"x\": 84.65,\n" +
//            "                \"y\": 123.6,\n" +
//            "                \"zh_label\": \"ODM1\"\n" +
//            "            }\n" +
//            "        ],\n" +
//            "        \"rack_attr\": {\n" +
//            "            \"angel\": 0,\n" +
//            "            \"height\": 220,\n" +
//            "            \"int_id\": 11559,\n" +
//            "            \"length\": 80,\n" +
//            "            \"room_id\": 123,\n" +
//            "            \"room_id_value\": \"209调度室\",\n" +
//            "            \"site_id\": 1715090,\n" +
//            "            \"site_id_value\": \"市区横木泾西\",\n" +
//            "            \"total_u\": 42,\n" +
//            "            \"vendor_value\": \"未知\",\n" +
//            "            \"width\": 80,\n" +
//            "            \"zh_label\": \"ODF-1\"\n" +
//            "        }\n" +
//            "    },\n" +
//            "    \"msg\": \"\",\n" +
//            "    \"success\": \"true\"\n" +
//            "}";
//
//
//    public static String JSON_MODEL = "{\n" +
//            "    \"source\": [\n" +
//            "        \"UUID\",\n" +
//            "        \"name\",\n" +
//            "        \"label\",\n" +
//            "        \"lng\",\n" +
//            "        \"lat\",\n" +
//            "        \"cityUUID\",\n" +
//            "        \"countyUUID\",\n" +
//            "        \"systemLevel\",\n" +
//            "        \"address\",\n" +
//            "        \"businessUUID\",\n" +
//            "        \"taskUUID\",\n" +
//            "        \"projectUUID\",\n" +
//            "        \"creator\",\n" +
//            "        \"creatTime\",\n" +
//            "        \"modifier\",\n" +
//            "        \"modifyTime\",\n" +
//            "        \"timeStamp\",\n" +
//            "        \"parentUUID\",\n" +
//            "        \"rmwUUID\",\n" +
//            "        \"routeUUID\",\n" +
//            "        \"createType\",\n" +
//            "        \"resType\",\n" +
//            "        \"style\",\n" +
//            "        \"dmlStatus\",\n" +
//            "        \"lcStatus\",\n" +
//            "        \"stateflag\",\n" +
//            "        \"attr01\",\n" +
//            "        \"attr02\",\n" +
//            "        \"attr03\",\n" +
//            "        \"attr04\",\n" +
//            "        \"attr05\",\n" +
//            "        \"attr06\",\n" +
//            "        \"attr07\",\n" +
//            "        \"attr08\",\n" +
//            "        \"attr09\",\n" +
//            "        \"attr10\",\n" +
//            "        \"attr11\",\n" +
//            "        \"attr12\",\n" +
//            "        \"attr13\",\n" +
//            "        \"attr14\",\n" +
//            "        \"attr15\",\n" +
//            "        \"attr16\",\n" +
//            "        \"attr17\",\n" +
//            "        \"attr18\",\n" +
//            "        \"attr19\",\n" +
//            "        \"attr20\",\n" +
//            "        \"amapProvince\",\n" +
//            "        \"amapCity\",\n" +
//            "        \"amapCitycode\",\n" +
//            "        \"amapDistrict\",\n" +
//            "        \"amapAdcode\",\n" +
//            "        \"amapTownship\",\n" +
//            "        \"amapStreet\",\n" +
//            "        \"amapStreetnumber\",\n" +
//            "        \"amapAddress\"\n" +
//            "    ],\n" +
//            "    \"rawValue\": [\n" +
//            "        {\n" +
//            "            \"amapStreet\": null,\n" +
//            "            \"modifier\": null,\n" +
//            "            \"amapDistrict\": null,\n" +
//            "            \"parentUUID\": null,\n" +
//            "            \"modifyTime\": null,\n" +
//            "            \"UUID\": \"2b640690-c6c6-4f3b-8387-fd2e2021c57f\",\n" +
//            "            \"businessUUID\": null,\n" +
//            "            \"lat\": 38.0457588,\n" +
//            "            \"lng\": 114.50959071,\n" +
//            "            \"attr09\": null,\n" +
//            "            \"resType\": \"9206\",\n" +
//            "            \"attr08\": null,\n" +
//            "            \"attr07\": null,\n" +
//            "            \"countyUUID\": \"136\",\n" +
//            "            \"attr06\": null,\n" +
//            "            \"attr05\": null,\n" +
//            "            \"attr04\": null,\n" +
//            "            \"attr03\": null,\n" +
//            "            \"attr02\": null,\n" +
//            "            \"attr01\": null,\n" +
//            "            \"projectUUID\": \"67D7855216E137A9E0506E0A3D0275EC\",\n" +
//            "            \"_link\": {\n" +
//            "                \"_self\": \"/modelmanager/api/modelserver/guangzhongduanhe_his/2b640690-c6c6-4f3b-8387-fd2e2021c57f\"\n" +
//            "            },\n" +
//            "            \"amapTownship\": null,\n" +
//            "            \"amapAddress\": null,\n" +
//            "            \"name\": \"人民大会堂基站（拉远）\",\n" +
//            "            \"style\": null,\n" +
//            "            \"dmlStatus\": 2,\n" +
//            "            \"attr19\": null,\n" +
//            "            \"attr18\": \"0\",\n" +
//            "            \"attr17\": null,\n" +
//            "            \"amapCitycode\": null,\n" +
//            "            \"stateflag\": 0,\n" +
//            "            \"attr16\": null,\n" +
//            "            \"createType\": null,\n" +
//            "            \"attr15\": null,\n" +
//            "            \"attr14\": null,\n" +
//            "            \"attr13\": null,\n" +
//            "            \"taskUUID\": \"a3ca6a45626b4254887813431aabce4e\",\n" +
//            "            \"attr12\": null,\n" +
//            "            \"attr11\": \"9206\",\n" +
//            "            \"attr10\": \"1\",\n" +
//            "            \"amapCity\": null,\n" +
//            "            \"creatTime\": \"2019-08-07 17:05:03\",\n" +
//            "            \"amapAdcode\": null,\n" +
//            "            \"amapProvince\": null,\n" +
//            "            \"creator\": \"admin\",\n" +
//            "            \"cityUUID\": \"33\",\n" +
//            "            \"address\": null,\n" +
//            "            \"label\": null,\n" +
//            "            \"rmwUUID\": null,\n" +
//            "            \"timeStamp\": \"2019-08-07 17:05:03\",\n" +
//            "            \"attr20\": \"538756098\",\n" +
//            "            \"lcStatus\": 8,\n" +
//            "            \"systemLevel\": null,\n" +
//            "            \"routeUUID\": null,\n" +
//            "            \"amapStreetnumber\": null\n" +
//            "        }\n" +
//            "    ],\n" +
//            "    \"displayValue\": [\n" +
//            "        {\n" +
//            "            \"amapStreet\": null,\n" +
//            "            \"modifier\": null,\n" +
//            "            \"amapDistrict\": null,\n" +
//            "            \"parentUUID\": null,\n" +
//            "            \"modifyTime\": null,\n" +
//            "            \"UUID\": \"2b640690-c6c6-4f3b-8387-fd2e2021c57f\",\n" +
//            "            \"businessUUID\": \"\",\n" +
//            "            \"lat\": 38.0457588,\n" +
//            "            \"lng\": 114.50959071,\n" +
//            "            \"attr09\": null,\n" +
//            "            \"resType\": \"9206\",\n" +
//            "            \"attr08\": null,\n" +
//            "            \"attr07\": null,\n" +
//            "            \"countyUUID\": \"山海关区\",\n" +
//            "            \"attr06\": null,\n" +
//            "            \"attr05\": null,\n" +
//            "            \"attr04\": null,\n" +
//            "            \"attr03\": null,\n" +
//            "            \"attr02\": null,\n" +
//            "            \"attr01\": null,\n" +
//            "            \"projectUUID\": \"国美电器数据专线项目\",\n" +
//            "            \"amapTownship\": null,\n" +
//            "            \"amapAddress\": null,\n" +
//            "            \"name\": \"人民大会堂基站（拉远）\",\n" +
//            "            \"style\": null,\n" +
//            "            \"dmlStatus\": 2,\n" +
//            "            \"attr19\": null,\n" +
//            "            \"attr18\": \"0\",\n" +
//            "            \"attr17\": null,\n" +
//            "            \"amapCitycode\": null,\n" +
//            "            \"stateflag\": 0,\n" +
//            "            \"attr16\": null,\n" +
//            "            \"createType\": null,\n" +
//            "            \"attr15\": null,\n" +
//            "            \"attr14\": null,\n" +
//            "            \"attr13\": null,\n" +
//            "            \"taskUUID\": \"20190807测试任务\",\n" +
//            "            \"attr12\": null,\n" +
//            "            \"attr11\": \"9206\",\n" +
//            "            \"attr10\": \"1\",\n" +
//            "            \"amapCity\": null,\n" +
//            "            \"creatTime\": \"2019-08-07 17:05:03\",\n" +
//            "            \"amapAdcode\": null,\n" +
//            "            \"amapProvince\": null,\n" +
//            "            \"creator\": \"admin\",\n" +
//            "            \"cityUUID\": \"秦皇岛\",\n" +
//            "            \"address\": null,\n" +
//            "            \"label\": null,\n" +
//            "            \"rmwUUID\": null,\n" +
//            "            \"timeStamp\": \"2019-08-07 17:05:03\",\n" +
//            "            \"attr20\": \"538756098\",\n" +
//            "            \"lcStatus\": \"工程\",\n" +
//            "            \"systemLevel\": null,\n" +
//            "            \"routeUUID\": null,\n" +
//            "            \"amapStreetnumber\": null\n" +
//            "        }\n" +
//            "    ],\n" +
//            "    \"sourceName\": [\n" +
//            "        \"UUID\",\n" +
//            "        \"名称\",\n" +
//            "        \"地图标签\",\n" +
//            "        \"经度\",\n" +
//            "        \"纬度\",\n" +
//            "        \"所属地市\",\n" +
//            "        \"所属区县\",\n" +
//            "        \"级别\",\n" +
//            "        \"地址\",\n" +
//            "        \"所属综合业务区\",\n" +
//            "        \"所属任务\",\n" +
//            "        \"所属工程\",\n" +
//            "        \"创建人\",\n" +
//            "        \"创建时间\",\n" +
//            "        \"修改人\",\n" +
//            "        \"修改时间\",\n" +
//            "        \"时间戳\",\n" +
//            "        \"所属承载设施\",\n" +
//            "        \"rmwUUID\",\n" +
//            "        \"所属路由\",\n" +
//            "        \"创建类型\",\n" +
//            "        \"资源类型\",\n" +
//            "        \"图形样式\",\n" +
//            "        \"dmlStatus\",\n" +
//            "        \"生命周期状态\",\n" +
//            "        \"stateflag\",\n" +
//            "        null,\n" +
//            "        null,\n" +
//            "        null,\n" +
//            "        null,\n" +
//            "        null,\n" +
//            "        null,\n" +
//            "        null,\n" +
//            "        null,\n" +
//            "        null,\n" +
//            "        null,\n" +
//            "        null,\n" +
//            "        null,\n" +
//            "        null,\n" +
//            "        null,\n" +
//            "        null,\n" +
//            "        null,\n" +
//            "        null,\n" +
//            "        null,\n" +
//            "        null,\n" +
//            "        null,\n" +
//            "        null,\n" +
//            "        null,\n" +
//            "        null,\n" +
//            "        null,\n" +
//            "        null,\n" +
//            "        null,\n" +
//            "        null,\n" +
//            "        null,\n" +
//            "        null\n" +
//            "    ]\n" +
//            "}";
}
