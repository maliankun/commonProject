package com.inspur.osp.util;

import android.content.Context;
import android.text.TextUtils;

import com.amap.api.maps.CoordinateConverter;
import com.amap.api.maps.model.LatLng;
import com.cocoahero.android.geojson.GeoJSONObject;
import com.cocoahero.android.geojson.LineString;
import com.cocoahero.android.geojson.MultiLineString;
import com.cocoahero.android.geojson.Point;
import com.cocoahero.android.geojson.Position;
import com.inspur.osp.BaseApplication;
import com.inspur.topo.R;
import com.inspur.topo.TopoConnectActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by liuchen_ on 2017/12/16.
 * <p>
 * <p>
 * 加资源 要改的地方
 * <p>
 * 1.数组searchTypeKeyArray
 * 2.数组searchNameArray
 * 3.getSystemlevelByLayId 设置其图片资源 ，及其显示的缩放级别
 * 4.getRight 设置右侧过滤条件
 * 5.资源文件 strings.xml描述
 */

public class ResourceUtil {

    public static List<Resource> resourceList;

//    public static List<String> PingYinList;
//    public static List<String> ResCodeList;

    public static List<Resource> LEVEL_ONE_LIST;
    public static List<Resource> LEVEL_TWO_LIST;
    public static List<Resource> LEVEL_THREE_LIST;
    //    public static Map<String, String> resourceMap;
    public static Map<String, Resource> resourceBeanMap;


    public static String resName = "";
    public static final String[] lineArray = new String[]{
            "guanglanduan",
            "guandaoduan",
            "ganluduan",
            "zhimaiduan",
            "guaqiangduan",
            "allpolygon"
    };


    public static class MAP_LAYER_TYPE {

        public static final String ZHANDIAN = "zhandian";
        public static final String DEVICE = "device";
        public static final String BEAR = "bear";
        public static final String OPTICAL = "optical";
        public static final String POLYGON = "polygon";
        public static final String BREAKDOWN = "breakdown";
    }

    public static class PING_YIN_CONST {
        public static final String KEY_SITE = "zhandian";
        public static final String KEY_GUANGJIAOJIEXIANG = "guangjiaojiexiang";
        public static final String KEY_GUANGFENXIANXIANG = "guangfenxianxiang";
        public static final String KEY_GUANGZHONGDUANHE = "guangzhongduanhe";
        public static final String KEY_GUANGLANDUAN = "guanglanduan";
        public static final String KEY_GUANDAODUAN = "guandaoduan";
        public static final String KEY_GANLUDUAN = "ganluduan";
        public static final String KEY_ZHIMAIDUAN = "zhimaiduan";
        public static final String KEY_GUAQIANGDUAN = "guaqiangduan";
        public static final String KEY_GJTH = "guangjietouhe";
        public static final String KEY_YEWUQU = "yewuqu";
        public static final String KEY_WEIGE = "weige";
        public static final String KEY_CELL = "cell";
        public static final String KEY_GUZHANG = "breakdown";

        //未在地图上显示的资源
        public static final String KEY_POINT_KEY = "guanjiandian";
        public static final String KEY_REN_SHOU_JING = "renshoujing";
        public static final String KEY_DIAN_GAN = "diangan";
        public static final String KEY_BIAO_SHI = "biaoshi";
        public static final String KEY_CHENG_DIAN = "chengdian";
        public static final String KEY_NET_POINT = "wangluoziyuandian";
        public static final String KEY_FU_SHE = "fusheduan";
        public static final String KEY_GUANG_LAN_YU_LIU = "guanglanyuliu";
        public static final String KEY_RACK_DEVICE = "shebeijijia";
        public static final String KEY_ZONG_HE_JI_JIA = "zonghejijia";
        public static final String KEY_ODF = "odf";
        public static final String KEY_GUANGSHEBEI = "guangshebei";
        public static final String KEY_GUANGLANPANLIU = "guanglanpanliu";
        public static final String KEY_GUANXIANXITONG = "guanxianxitong";
        public static final String KEY_GUANGLANXITONG = "guanglanxitong";//光缆系统
        public static final String KEY_ODM = "odm";
        public static final String KEY_JIFANG = "jifang";

    }


    public static String[][] ARRAY_PY_CODE = new String[][]{
            {
                    PING_YIN_CONST.KEY_POINT_KEY
                    , RES_CODE_CONST.RES_POINT_KEY,
                    "关键点"
            },
            {
                    PING_YIN_CONST.KEY_SITE,
                    RES_CODE_CONST.RES_TYPE_SITE,
                    "站点"
            },
            {
                    PING_YIN_CONST.KEY_GUANGJIAOJIEXIANG,
                    RES_CODE_CONST.RES_TYPE_GUANGJIAOJIEXIANG
                    , "光交接箱"
            },
            {
                    PING_YIN_CONST.KEY_GUANGFENXIANXIANG,
                    RES_CODE_CONST.RES_TYPE_GUANGFENXIANXIANG
                    , "光分纤箱"
            },
            {
                    PING_YIN_CONST.KEY_GUANGZHONGDUANHE,
                    RES_CODE_CONST.RES_TYPE_GUANGZHONGDUANHE
                    , "光终端盒"
            },
            {
                    PING_YIN_CONST.KEY_GUANGLANDUAN
                    , RES_CODE_CONST.RES_TYPE_GUANGLANDUAN,
                    "光缆段"
            },
            {
                    PING_YIN_CONST.KEY_GUANDAODUAN,
                    RES_CODE_CONST.RES_TYPE_GUANDAODUAN,
                    "管道段"
            },
            {
                    PING_YIN_CONST.KEY_GANLUDUAN
                    , RES_CODE_CONST.RES_TYPE_GANLUDUAN,
                    "杆路段"
            },
            {
                    PING_YIN_CONST.KEY_ZHIMAIDUAN
                    , RES_CODE_CONST.RES_TYPE_ZHIMAIDUAN,
                    "直埋段"
            },
            {
                    PING_YIN_CONST.KEY_GUAQIANGDUAN
                    , RES_CODE_CONST.RES_TYPE_GUAQIANGDUAN,
                    "挂墙段"
            },
            {
                    PING_YIN_CONST.KEY_GJTH
                    , RES_CODE_CONST.RES_GJTH,
                    "光接头盒"
            },
            {
                    PING_YIN_CONST.KEY_REN_SHOU_JING
                    , RES_CODE_CONST.RES_REN_SHOU_JING,
                    "人手井"
            },
            {
                    PING_YIN_CONST.KEY_DIAN_GAN
                    , RES_CODE_CONST.RES_DIAN_GAN,
                    "电杆"
            },
            {
                    PING_YIN_CONST.KEY_BIAO_SHI
                    , RES_CODE_CONST.RES_BIAO_SHI,
                    "标石"
            },
            {
                    PING_YIN_CONST.KEY_CHENG_DIAN
                    , RES_CODE_CONST.RES_CHENG_DIAN,
                    "撑点"
            },
            {
                    PING_YIN_CONST.KEY_NET_POINT
                    , RES_CODE_CONST.RES_NET_POINT,
                    "网络资源点"
            },
            {
                    PING_YIN_CONST.KEY_FU_SHE
                    , RES_CODE_CONST.RES_FUSHE,
                    "敷设段"
            },
            {
                    PING_YIN_CONST.KEY_GUANG_LAN_YU_LIU
                    , RES_CODE_CONST.RES_TYPE_GUANG_LAN_YU_LIU,
                    "光缆预留"
            },
            {
                    PING_YIN_CONST.KEY_RACK_DEVICE
                    , RES_CODE_CONST.RES_RACK_DEVICE,
                    "设备机架"
            },
            {
                    PING_YIN_CONST.KEY_ZONG_HE_JI_JIA
                    , RES_CODE_CONST.RES_TYPE_JIJIA,
                    "综合机架"
            },
            {
                    PING_YIN_CONST.KEY_ODF
                    , RES_CODE_CONST.RES_TYPE_ODF,
                    "ODF"
            },
            {
                    PING_YIN_CONST.KEY_GUANGSHEBEI
                    , RES_CODE_CONST.RES_GUANGSHEBEI,
                    "光设备"
            },
            {
                    PING_YIN_CONST.KEY_GUANGLANPANLIU
                    , RES_CODE_CONST.RES_GUANGLANPANLIU,
                    "光缆盘留"
            },
            {
                    PING_YIN_CONST.KEY_GUANXIANXITONG
                    , RES_CODE_CONST.RES_GUANXIANXITONG,
                    "光纤系统"
            },
            {
                    PING_YIN_CONST.KEY_GUANGLANXITONG
                    , RES_CODE_CONST.RES_GUANGLANXITONG,
                    "光缆系统"
            },
            {
                    PING_YIN_CONST.KEY_ODM
                    , RES_CODE_CONST.RES_TYPE_ODM,
                    "ODM"
            },
            {
                    PING_YIN_CONST.KEY_JIFANG
                    , RES_CODE_CONST.RES_TYPE_JIFANG,
                    "机房"
            }
    };

    public static String[] KEY_POINT_ARRAY = {
            RES_CODE_CONST.RES_REN_SHOU_JING,
            RES_CODE_CONST.RES_DIAN_GAN,
            RES_CODE_CONST.RES_BIAO_SHI,
            RES_CODE_CONST.RES_CHENG_DIAN,
            RES_CODE_CONST.RES_POINT_KEY
    };

    public static class RES_CODE_CONST {
        public static final String RES_TYPE_SITE = "9503";
        public static final String RES_TYPE_GUANGJIAOJIEXIANG = "9203";
        public static final String RES_TYPE_GUANGFENXIANXIANG = "9204";
        public static final String RES_TYPE_GUANGZHONGDUANHE = "9206";
        public static final String RES_TYPE_GUANGLANDUAN = "9202";
        public static final String RES_TYPE_GUANDAODUAN = "9102";
        public static final String RES_TYPE_GANLUDUAN = "9105";
        public static final String RES_TYPE_ZHIMAIDUAN = "9111";
        public static final String RES_TYPE_GUAQIANGDUAN = "9109";
        public static final String RES_GJTH = "9205";//接头盒
        public static final String RES_TYPE_YEWUQU = "10100";
        public static final String RES_TYPE_WEIGE = "20003";
        public static final String RES_TYPE_BREAKDOWN = "20005";

        //未在地图上显示的资源
        public static final String RES_POINT_KEY = "9999";
        public static final String RES_REN_SHOU_JING = "9101";
        public static final String RES_DIAN_GAN = "9104";
        public static final String RES_BIAO_SHI = "9110";
        public static final String RES_CHENG_DIAN = "9107";
        public static final String RES_NET_POINT = "9505";
        public static final String RES_FUSHE = "9120";
        public static final String RES_TYPE_GUANG_LAN_YU_LIU = "9208";
        public static final String RES_RACK_DEVICE = "9310";
        public static final String RES_TYPE_JIJIA = "9611";
        public static final String RES_TYPE_ODF = "9212";//odf
        public static final String RES_GUANGSHEBEI = "9300";//光设备
        public static final String RES_GUANGLANPANLIU = "9210";//光缆盘留
        public static final String RES_GUANXIANXITONG = "9100";//管线系统
        public static final String RES_GUANGLANXITONG = "9201";//光缆系统
        public static final String RES_TYPE_ODM = "9213";//odm
        public static final String RES_TYPE_JIFANG = "9504";//odm

    }


    public static final String[] panelArray = new String[]{
            RES_CODE_CONST.RES_TYPE_GUANGJIAOJIEXIANG, RES_CODE_CONST.RES_TYPE_GUANGFENXIANXIANG, RES_CODE_CONST.RES_TYPE_GUANGZHONGDUANHE,
            RES_CODE_CONST.RES_TYPE_ODF, RES_CODE_CONST.RES_TYPE_JIJIA, RES_CODE_CONST.RES_TYPE_ODM, RES_CODE_CONST.RES_GJTH
    };


    public static final String[] searchTypeKeyArray = new String[]{
            PING_YIN_CONST.KEY_SITE,
            PING_YIN_CONST.KEY_GUANGJIAOJIEXIANG,
            PING_YIN_CONST.KEY_GUANGFENXIANXIANG,
            PING_YIN_CONST.KEY_GUANGZHONGDUANHE,
            PING_YIN_CONST.KEY_GJTH,
            PING_YIN_CONST.KEY_GUANGLANDUAN,
            PING_YIN_CONST.KEY_GUANDAODUAN,
            PING_YIN_CONST.KEY_GANLUDUAN,
            PING_YIN_CONST.KEY_ZHIMAIDUAN,
            PING_YIN_CONST.KEY_GUAQIANGDUAN,
            PING_YIN_CONST.KEY_GUZHANG,
            "place_name",//9
    };

    public static final String[] ResourceTypeRequest = new String[]{
            RES_CODE_CONST.RES_TYPE_SITE,
            RES_CODE_CONST.RES_TYPE_GUANGJIAOJIEXIANG,
            RES_CODE_CONST.RES_TYPE_GUANGFENXIANXIANG,
            RES_CODE_CONST.RES_TYPE_GUANGZHONGDUANHE,
            RES_CODE_CONST.RES_GJTH,
            RES_CODE_CONST.RES_TYPE_GUANGLANDUAN,
            RES_CODE_CONST.RES_TYPE_GUANDAODUAN,
            RES_CODE_CONST.RES_TYPE_GANLUDUAN,
            RES_CODE_CONST.RES_TYPE_ZHIMAIDUAN,
            RES_CODE_CONST.RES_TYPE_GUAQIANGDUAN,
            RES_CODE_CONST.RES_TYPE_BREAKDOWN,
            "0"
    };
    public static final String[] searchNameArray = new String[]{
            "站点",//0
            "光交",//1
            "光分纤箱",//2
            "光终端盒",//3
            "分歧接头",
            "光缆段",//4
            "管道段",//5
            "杆路段",//6
            "直埋段",//7
            "挂墙段",//8
            "故障",//9
            "地名",//10
    };


    public static int getResourceKeySystemLevel(String layId, String resType, String level) {
        Integer levelInt = -1;
        try {
            levelInt = Integer.parseInt(level);
        } catch (Exception e) {
            levelInt = -1;
        }
        for (int i = 0; i < resourceList.size(); i++) {
            Resource resource = resourceList.get(i);
            if (TextUtils.equals(resource.resType, resType)) {
                resName = resource.type;
                for (SystemLevel systemLevel : resource.systemLevel) {
                    if (levelInt == systemLevel.level) {
                        return systemLevel.resId;
                    }
                }
            }
        }
        return 0;
    }

    public static String[] getResourceEnum(String typeCode) {

        for (String code : KEY_POINT_ARRAY) {
            if (code.equals(typeCode)) {
                return ARRAY_PY_CODE[0];
            }
        }
        if (!TextUtils.isEmpty(typeCode)) {
            for (String[] array : ARRAY_PY_CODE) {
                if (TextUtils.equals(array[1], typeCode)) {
                    return array;
                }
            }
        }
        return new String[]{
                "weizhi",
                "000",
                "未知"
        };
    }

    public static String getLayoutIdByResType(String resType) {

        if (!TextUtils.isEmpty(resType)) {
            for (String[] array : ARRAY_PY_CODE) {
                String py = array[0];
                String type = array[1];
                if (TextUtils.equals(resType, type)) {
                    return py;
                }
            }
        }
        return "";
    }

    static {

        resourceList = new ArrayList<>();

        for (int i = 0; i < searchTypeKeyArray.length; i++) {
            String layId = searchTypeKeyArray[i];
            String name = searchNameArray[i];
            String resType = "";
            if (ResourceTypeRequest.length > i) {
                resType = ResourceTypeRequest[i];
            }
            resourceList.add(new Resource(layId, resType, name, getSystemlevelByLayId(i)));
        }
//        resourceMap = new HashMap<>();
        resourceBeanMap = new HashMap<>();
        for (int i = 0; i < ResourceTypeRequest.length; i++) {
            String resType = ResourceTypeRequest[i];
            String name = searchNameArray[i];
            resourceBeanMap.put(resType, resourceList.get(i));
        }
    }

    public static boolean isCell(String layId) {
        return "cell".equals(layId);
    }

    public static List<SystemLevel> getSystemlevelByLayId(int id) {

        List<SystemLevel> systemLevels = new ArrayList<>();
        switch (id) {
            case 0:
                SystemLevel siteLevelCore = new SystemLevel(1, R.drawable.ic_site_core, 6);
                SystemLevel siteLevelUnion = new SystemLevel(2, R.drawable.ic_site_union, 12);
                SystemLevel siteLevelAccess = new SystemLevel(3, R.drawable.ic_site_access, 14);
                SystemLevel siteLevelUser = new SystemLevel(4, R.drawable.ic_site_user, 14);
                systemLevels.add(siteLevelCore);
                systemLevels.add(siteLevelUnion);
                systemLevels.add(siteLevelAccess);
                systemLevels.add(siteLevelUser);
                break;
            case 1:
                //光交
                SystemLevel gjTrunk = new SystemLevel(1, R.drawable.ic_device_gj_trunk, 11);
                SystemLevel gjWiring = new SystemLevel(2, R.drawable.ic_device_gj_wiring, 15);
                SystemLevel gjCommnity = new SystemLevel(3, R.drawable.ic_device_gj_community, 13);
                SystemLevel gjCountry = new SystemLevel(4, R.drawable.ic_device_gj_country, 13);
                systemLevels.add(gjTrunk);
                systemLevels.add(gjWiring);
                systemLevels.add(gjCommnity);
                systemLevels.add(gjCountry);
                break;
            case 2:
                //光分纤箱 没有下一级
                systemLevels.add(new SystemLevel(-1, R.drawable.ic_optical_box, 16));

                break;
            case 3:
                //光终端盒 无下一级
                systemLevels.add(new SystemLevel(-1, R.drawable.ic_optical_terminal, 16));
                break;
            case 4:
                //分歧接头
                systemLevels.add(new SystemLevel(-1, R.drawable.ic_gjth, 15));
                break;
            case 5:
                //光缆
                systemLevels.add(new SystemLevel(1, R.color.cable_one, 9));
                systemLevels.add(new SystemLevel(2, R.color.cable_two, 9));
                systemLevels.add(new SystemLevel(3, R.color.cable_city_trunk, 9));
                systemLevels.add(new SystemLevel(4, R.color.cable_city_union, 14));
                systemLevels.add(new SystemLevel(5, R.color.cable_city_access, 16));
                systemLevels.add(new SystemLevel(6, R.color.cable_city_access_special, 16));
                break;
            case 6:
                //管道
                systemLevels.add(new SystemLevel(-1, R.color.segment_pipe, 15));
                break;
            case 7:
                //杆路段
                systemLevels.add(new SystemLevel(-1, R.color.segment_ganlu, 16));
                break;
            case 8:
                //直埋段
                systemLevels.add(new SystemLevel(-1, R.color.segment_buried, 15));
                break;
            case 9:
                //挂墙段
                systemLevels.add(new SystemLevel(-1, R.color.segment_wall, 16));
                break;
            case 10:
                //光交
//                SystemLevel yiganBreak = new SystemLevel(1, R.drawable.ic_breakdown_yg, 11);
//                SystemLevel erganBreak = new SystemLevel(2, R.drawable.ic_breakdown_eg, 15);
//                SystemLevel cyzgBreak = new SystemLevel(3, R.drawable.ic_breakdown_zg, 13);
//                SystemLevel cyhjBreak = new SystemLevel(4, R.drawable.ic_breakdown_jr, 13);
//                SystemLevel cyjrBreak = new SystemLevel(5, R.drawable.ic_breakdown_hj, 13);
//                systemLevels.add(yiganBreak);
//                systemLevels.add(erganBreak);
//                systemLevels.add(cyzgBreak);
//                systemLevels.add(cyhjBreak);
//                systemLevels.add(cyjrBreak);
                break;
            case 11:
//                systemLevels.add(new SystemLevel(-1, R.drawable.ic_place_name));
                break;
        }
//
//
//
//        if (TextUtils.equals(id, searchTypeKeyArray[0])) {//站点
//
//        } else if (TextUtils.equals(id, searchTypeKeyArray[1])) {
//
//
//        } else if (TextUtils.equals(id, searchTypeKeyArray[2])) {
//            //光分纤箱 没有下一级
//            systemLevels.add(new SystemLevel(-1, R.drawable.ic_device_gfxx));
//        } else if (TextUtils.equals(id, searchTypeKeyArray[3])) {
//            //光终端盒 无下一级
//            systemLevels.add(new SystemLevel(-1, R.drawable.ic_device_gzdh));
//        } else if (TextUtils.equals(id, searchTypeKeyArray[4])) {
//            //光缆
//            systemLevels.add(new SystemLevel(1, R.color.cable_one));
//            systemLevels.add(new SystemLevel(2, R.color.cable_two));
//            systemLevels.add(new SystemLevel(3, R.color.cable_city_trunk));
//            systemLevels.add(new SystemLevel(4, R.color.cable_city_union));
//            systemLevels.add(new SystemLevel(5, R.color.cable_city_access));
//            systemLevels.add(new SystemLevel(6, R.color.cable_city_access_special));
//        } else if (TextUtils.equals(id, searchTypeKeyArray[5])) {
//            systemLevels.add(new SystemLevel(-1, R.color.segment_pipe));
//        } else if (TextUtils.equals(id, searchTypeKeyArray[6])) {
//            systemLevels.add(new SystemLevel(-1, R.color.segment_ganlu));
//        } else if (TextUtils.equals(id, searchTypeKeyArray[7])) {
//            systemLevels.add(new SystemLevel(-1, R.color.segment_buried));
//        } else if (TextUtils.equals(id, searchTypeKeyArray[8])) {
//            systemLevels.add(new SystemLevel(-1, R.color.segment_wall));
//        } else if (TextUtils.equals(id, searchTypeKeyArray[9])) {
//            systemLevels.add(new SystemLevel(-1, R.drawable.ic_place_name));
//        }
        return systemLevels;
    }

    public static final int TYPE_SITE = 0;
    public static final int TYPE_DEVICE = 1;
    public static final int TYPE_BEARER = 2;
    public static final int TYPE_OPTICAl = 3;
    public static final int TYPE_POLYGON = 4;
    public static final int TYPE_CELL = 5;
    public static final int TYPE_BREAKDOWN = 6;

//
    public static List<RightResource> getRight(Context mContext, int type) {
        List<String> deviceTypeList;
        int layIdIndex = 0;
        switch (type) {
            case TYPE_SITE:
                deviceTypeList = Arrays.asList(mContext.getResources().getStringArray(R.array.array_site_type));
                break;
            case TYPE_DEVICE:

                deviceTypeList = Arrays.asList(mContext.getResources().getStringArray(R.array.array_device_type));
                break;
            case TYPE_BEARER:
                deviceTypeList = Arrays.asList(mContext.getResources().getStringArray(R.array.array_bearer_type));
                break;
            case TYPE_OPTICAl:
                deviceTypeList = Arrays.asList(mContext.getResources().getStringArray(R.array.array_optical_type));
                break;
//            case TYPE_POLYGON:
//                deviceTypeList = Arrays.asList(mContext.getResources().getStringArray(R.array.array_polygon_type));
//                break;
//            case TYPE_CELL:
//                deviceTypeList = Arrays.asList(mContext.getResources().getStringArray(R.array.array_cell_type));
//                break;
//            case TYPE_BREAKDOWN:
//                deviceTypeList = Arrays.asList(mContext.getResources().getStringArray(R.array.array_breakdown_type));
//                break;
            default:
                deviceTypeList = Arrays.asList(mContext.getResources().getStringArray(R.array.array_site_type));
                break;
        }
        String layoutId = "";
        String res_type = "";
        List<RightResource> rightResourceList = new ArrayList<>();
        for (int i = 0; i < deviceTypeList.size(); i++) {
            int systemLevel = i + 1;
            switch (type) {
                case TYPE_SITE:
                    res_type = RES_CODE_CONST.RES_TYPE_SITE;
                    layoutId = PING_YIN_CONST.KEY_SITE;
                    break;
                case TYPE_DEVICE:
                    if (i < 4) {
                        layoutId = PING_YIN_CONST.KEY_GUANGJIAOJIEXIANG;
                        res_type = RES_CODE_CONST.RES_TYPE_GUANGJIAOJIEXIANG;
                    }
                    if (i == 4) {
                        systemLevel = -1;
                        layoutId = PING_YIN_CONST.KEY_GUANGFENXIANXIANG;
                        res_type = RES_CODE_CONST.RES_TYPE_GUANGFENXIANXIANG;
                    }
                    if (i == 5) {
                        systemLevel = -1;
                        layoutId = PING_YIN_CONST.KEY_GUANGZHONGDUANHE;
                        res_type = RES_CODE_CONST.RES_TYPE_GUANGZHONGDUANHE;
                    }
                    if (i == 6) {
                        systemLevel = -1;
                        layoutId = PING_YIN_CONST.KEY_GJTH;
                        res_type = RES_CODE_CONST.RES_GJTH;
                    }
                    break;
                case TYPE_BEARER:
                    systemLevel = -1;
                    if (i == 0) {
                        layoutId = PING_YIN_CONST.KEY_GUANDAODUAN;
                        res_type = RES_CODE_CONST.RES_TYPE_GUANDAODUAN;
                    }
                    if (i == 1) {
                        layoutId = PING_YIN_CONST.KEY_GANLUDUAN;
                        res_type = RES_CODE_CONST.RES_TYPE_GANLUDUAN;

                    }
                    if (i == 2) {
                        layoutId = PING_YIN_CONST.KEY_ZHIMAIDUAN;
                        res_type = RES_CODE_CONST.RES_TYPE_ZHIMAIDUAN;
                    }
                    if (i == 3) {
                        layoutId = PING_YIN_CONST.KEY_GUAQIANGDUAN;
                        res_type = RES_CODE_CONST.RES_TYPE_GUAQIANGDUAN;
                    }
                    break;
                case TYPE_OPTICAl:
                    layoutId = PING_YIN_CONST.KEY_GUANGLANDUAN;
                    res_type = RES_CODE_CONST.RES_TYPE_GUANGLANDUAN;
                    break;
                case TYPE_POLYGON:
                    systemLevel = -1;
                    if (i == 0) {
                        //layoutId = PING_YIN_CONST.KEY_POLYGON;
                        layoutId = PING_YIN_CONST.KEY_YEWUQU;
                        res_type = RES_CODE_CONST.RES_TYPE_YEWUQU;
                    } else {
                        //layoutId = PING_YIN_CONST.KEY_POLYGON;
                        layoutId = PING_YIN_CONST.KEY_WEIGE;
                        res_type = RES_CODE_CONST.RES_TYPE_WEIGE;
                    }

                    break;
                case TYPE_CELL:
                    layoutId = "cell";
                    res_type = "0000";
                    break;
                case TYPE_BREAKDOWN:
                    layoutId = PING_YIN_CONST.KEY_GUZHANG;
                    res_type = RES_CODE_CONST.RES_TYPE_BREAKDOWN;
                    break;
            }
            rightResourceList.add(new RightResource(layoutId, res_type, deviceTypeList.get(i), systemLevel));
        }

        return rightResourceList;
    }

    public static class RightResource {
        public String layId = "";
        public String layName = "";
        public String resType = "";
        public boolean isEnable = false;//默认为false
        public int systemLevel = -1;
//        public boolean isChecked = true;

        public boolean isEnable() {
            return isEnable;
        }

        public void setEnable(boolean enable) {
            isEnable = enable;
        }

        public RightResource(String layId, String resType, String layName, int systemLevel) {
            this.layId = layId;
            this.layName = layName;
            this.resType = resType;
            this.systemLevel = systemLevel;
        }
    }

    public static class Resource {
        public String layId = "";
        public String type = "";//point line
        public String resType;//point line
        public List<SystemLevel> systemLevel;

        public Resource(String layId, String resType, String type, List<SystemLevel> systemLevel) {
            this.layId = layId;
            this.type = type;
            this.resType = resType;
            this.systemLevel = systemLevel;
        }
    }

    public static class SystemLevel {
        public int level = 1;
        public int resId = 1;
        public float minZoomVsible = 15;

        public SystemLevel(int level, int resId) {
            this.level = level;
            this.resId = resId;
        }

        public SystemLevel(int level, int resId, float minZoomVsible) {
            this(level, resId);
            this.minZoomVsible = minZoomVsible;
        }
    }

    public static GeoJSONObject parseWkt(String wktStr) {

        GeoJSONObject geoJSONObject = null;
        if (wktStr == null) return null;

        if (wktStr.contains("POINT")) {
            //点
            String splitString = wktStr.substring(wktStr.indexOf("(") + 1, wktStr.indexOf(")")).trim();
            String[] array = splitString.split(" ");
            geoJSONObject = new Point(Double.parseDouble(array[1]), Double.parseDouble(array[0]));
        } else if (wktStr.contains("MULTILINESTRING")) {

            MultiLineString multiLineString = new MultiLineString();

            String multline = wktStr.substring(wktStr.indexOf("((") + 2, wktStr.lastIndexOf("))")).trim();
            String[] lineArray = multline.split("\\)," +
                    "\\(");
            for (int i = 0; i < lineArray.length; i++) {
                LineString lineString = new LineString();
                String line = lineArray[i];
                String[] pointArray = line.split(",");
                String point1 = pointArray[0].trim();
                String[] point1Array = point1.split(" ");
                Position position1 =
                        new Position(Double.parseDouble(point1Array[1]), Double.parseDouble(point1Array[0]));
                String point2 = pointArray[1].trim();
                String[] point2Array = point2.split(" ");
                Position position2 =
                        new Position(Double.parseDouble(point2Array[1]), Double.parseDouble(point2Array[0]));
                lineString.addPosition(position1);
                lineString.addPosition(position2);
                multiLineString.addLineString(lineString);
            }
            geoJSONObject = multiLineString;
        } else if (wktStr.contains("LINESTRING")) {
            String lineSplit = wktStr.substring(wktStr.indexOf("(") + 1, wktStr.lastIndexOf(")")).trim();
            String[] lineSplitArray = lineSplit.split(",");
            String[] lnglatArray1 = lineSplitArray[0].trim().split(" ");
            Position position1 = new Position(Double.parseDouble(lnglatArray1[1]), Double.parseDouble(lnglatArray1[0]));

            String[] lnglatArray2 = lineSplitArray[01].trim().split(" ");
            Position position2 = new Position(Double.parseDouble(lnglatArray2[1]), Double.parseDouble(lnglatArray2[0]));
            LineString lineString = new LineString();
            lineString.addPosition(position1);
            lineString.addPosition(position2);
            geoJSONObject = lineString;
        }
        return geoJSONObject;
    }

//    public static String kanwuResourceEnname(String typecode) {
//        if (RES_CODE_CONST.RES_TYPE_GUANGJIAOJIEXIANG.equals(typecode)) {
//            //光交箱
//            return GeometryServerUtil.gj_layerId;
//        } else if (RES_CODE_CONST.RES_TYPE_GUANGFENXIANXIANG.equals(typecode)) {
//            return GeometryServerUtil.gf_layerId;
//        } else if (RES_CODE_CONST.RES_TYPE_GUANGZHONGDUANHE.equals(typecode)) {
//            return GeometryServerUtil.gz_layerId;
//        } else if (RES_CODE_CONST.RES_GJTH.equals(typecode)) {
//            return "guangjietouhe";
//        } else if (RES_CODE_CONST.RES_TYPE_GUANGLANDUAN.equals(typecode)) {
//            return GeometryServerUtil.gl_layerId;
//        } else if (RES_CODE_CONST.RES_TYPE_GUANDAODUAN.equals(typecode)) {
//            return "guandaoduan";
//        } else if (RES_CODE_CONST.RES_TYPE_GANLUDUAN.equals(typecode)) {
//            return "ganluduan";
//        } else if (RES_CODE_CONST.RES_TYPE_ZHIMAIDUAN.equals(typecode)) {
//            return "zhimaiduan";
//        } else if (RES_CODE_CONST.RES_TYPE_GUAQIANGDUAN.equals(typecode)) {
//            return "guaqiangduan";
//        } else {
//            return "guanjiandian";
//        }
//    }

    public static String kanwuResourceName(String typeCode) {
        if (RES_CODE_CONST.RES_TYPE_GUANGJIAOJIEXIANG.equals(typeCode)) {
            //光交箱
            return "光交接箱";
        } else if (RES_CODE_CONST.RES_TYPE_GUANGFENXIANXIANG.equals(typeCode)) {
            return "光分纤箱";
        } else if (RES_CODE_CONST.RES_TYPE_GUANGZHONGDUANHE.equals(typeCode)) {
            return "光终端盒";
        } else if (RES_CODE_CONST.RES_GJTH.equals(typeCode)) {
            return "光接头盒";
        }
        {
            return "关键点";
        }
//        if (RES_CODE_CONST.RES_POINT_KEY.equals(typeCode)
//                ||RES_CODE_CONST.RES_BIAO_SHI
//                ||
//
//                ) {
//            //关键点
//        }
    }

//    public static int kanwuIcon(String typeCode, String optType) {
//
//        int drawableRes = 0;
//        if (RES_CODE_CONST.RES_TYPE_GUANGJIAOJIEXIANG.equals(typeCode)) {
//            //光交箱
//            if ("add".equals(optType)) {
//                drawableRes = R.drawable.guangjiaojiexiang;
//            } else {
//                drawableRes = R.drawable.guangjiaojiexiang_hlight;
//            }
//        } else if (RES_CODE_CONST.RES_TYPE_GUANGFENXIANXIANG.equals(typeCode)) {
//            if ("add".equals(optType)) {
//                drawableRes = R.drawable.guangfenxianxiang;
//            } else {
//                drawableRes = R.drawable.guangfenxianxiang_hlight;
//            }
//        } else if (RES_CODE_CONST.RES_TYPE_GUANGZHONGDUANHE.equals(typeCode)) {
//            if ("add".equals(optType)) {
//                drawableRes = R.drawable.guangzhongduanhe;
//            } else {
//                drawableRes = R.drawable.guangzhongduanhe_hlight;
//            }
//        } else if (RES_CODE_CONST.RES_GJTH.equals(typeCode)) {
//            if ("add".equals(optType)) {
//                drawableRes = R.drawable.guangjietouhe;
//            } else {
//                drawableRes = R.drawable.guangjietouhe_hlight;
//            }
//        } else {
//            if ("add".equals(optType)) {
//                drawableRes = R.drawable.guanjiandian_hlight;
//            } else {
//                drawableRes = R.drawable.guanjiandian_hlight;
//            }
//        }
//        return drawableRes;
////        if (RES_CODE_CONST.RES_POINT_KEY.equals(typeCode)
////                ||RES_CODE_CONST.RES_BIAO_SHI
////                ||
////
////                ) {
////            //关键点
////        }
//    }

    public static LatLng coordConverter(Context mContext, LatLng latLng) {

        return new CoordinateConverter(mContext).from(CoordinateConverter.CoordType.GPS)
                .coord(latLng)
                .convert();
    }

    public static List<LatLng> parseLineStringToListLatlng(LineString lineString) {
        List<LatLng> latLngs = new ArrayList<LatLng>();
        for (Position position : lineString.getPositions()) {
            latLngs.add(coordConverter(TopoConnectActivity.mStaticContext, new LatLng(position.getLatitude(), position.getLongitude())));
        }
        return latLngs;
    }

    public static List<List<LatLng>> parseMuiltyLineStringToListLatlng(MultiLineString multiLineString) {
        List<List<LatLng>> muiltLines = new ArrayList<>();
        for (LineString lineString : multiLineString.getLineStrings()) {
            List<LatLng> singleLines = new ArrayList<>();
            for (Position position : lineString.getPositions()) {
                singleLines.add(coordConverter(TopoConnectActivity.mStaticContext, new LatLng(position.getLatitude(), position.getLongitude())));
            }
            muiltLines.add(singleLines);
        }
        return muiltLines;
    }
}
