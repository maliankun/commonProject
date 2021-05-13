package com.inspur.osp.util;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.location.LocationManager;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.AMapException;
import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.Projection;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.NaviPara;
import com.amap.api.maps.model.Polygon;
import com.amap.api.maps.model.Polyline;
import com.cocoahero.android.geojson.LineString;
import com.cocoahero.android.geojson.Position;
import com.inspur.osp.data.bean.TaskBean;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;



/**
 * Created by liuchen_ on 2017/12/13.
 */

public class Utility {


    private static LocationManager locManager;

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) { // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0); // 计算子项View 的宽高
            totalHeight += listItem.getMeasuredHeight(); // 统计所有子项的总高度
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        listView.setLayoutParams(params);
    }

    /***
     * 判断用户是否打开GPS
     *
     * @param context
     * @return
     */
    public static Boolean gpsStatus(Context context) {
        locManager = (LocationManager) context
                .getSystemService(Context.LOCATION_SERVICE);
        if (!(locManager
                .isProviderEnabled(LocationManager.GPS_PROVIDER))) {

            return false;
        }
        return true;
    }

    public static int getAppVersionCode(Context context) {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packageInfo;
        int versionCode = 1;
        try {
            packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            versionCode = packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    /**
     * get App versionName
     *
     * @param context
     * @return
     */
    public static String getVersionName(Context context) {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packageInfo;

        try {
            packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }


    /**
     * 获取设备厂商型号
     */
    public static String getDeviceBrand() {
        return android.os.Build.BRAND;
    }

    /**
     * 得到设备屏幕的宽度
     */
    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 得到设备屏幕的高度
     */
    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * 得到设备的密度
     */
    public static float getScreenDensity(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }

    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public static int dp2px(float dipValue) {
        return (int) (Resources.getSystem().getDisplayMetrics().density * dipValue + 0.5f);
    }

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /***
     * 读取文件
     * @param context
     * @param filename
     * @return
     */
    public static String readAssetsFile(Context context, String filename) {
        String text = "";
        try {
            // Return an AssetManager instance for your application's package
            InputStream is = context.getAssets().open(filename);
            int size = is.available();

            // Read the entire asset into a local byte buffer.
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            // Convert the buffer into a string.
            text = new String(buffer, "GB2312");

            // Finally stick the string into the text view.

        } catch (IOException e) {
            // Should never happen!
            throw new RuntimeException(e);
        }
        return text;
    }

    public static String getResType(TaskBean.ResultsBean resultsBean) {
        if (resultsBean != null && resultsBean.getAttributes() != null
                ) {
            if (resultsBean.getAttributes().getRES_TYPE() != null) {
                if ("NULL".toLowerCase().equals(resultsBean.getAttributes().getRES_TYPE().toLowerCase())
                        || TextUtils.isEmpty(resultsBean.getAttributes().getRES_TYPE())) {

                    return resultsBean.getAttributes().getROUTE_TYPE();

                } else {
                    return resultsBean.getAttributes().getRES_TYPE();
                }
            } else {
                return resultsBean.getAttributes().getROUTE_TYPE();
            }
        } else {
            return "0";
        }
    }


    public static class StringUtil {
        public static String filterNullValue(String nullValue) {
            if (TextUtils.isEmpty(nullValue) || "null".equalsIgnoreCase(nullValue)) {
                return "-";
            } else {
                return nullValue;
            }
        }


    }

    public static class MapUtil {

        public static List<MapPackge> packges = new ArrayList<>();
        public static final String MAP_BAIDU = "com.baidu.BaiduMap";
        public static final String MAP_GAODE = "com.autonavi.minimap";
        public static final String MAP_GOOGLE = "com.google.android.apps.maps";
        public static final String MAP_TENCENT = "com.tencent.map";

        public static final String MAP_BAIDU_NAME = "百度地图";
        public static final String MAP_GAODE_NAME = "高德地图";
        public static final String MAP_GOOGLE_NAME = "谷歌地图";
        public static final String MAP_TENCENT_NAME = "腾讯地图";


        public static boolean getLayerIdByZoomLevel(String layerId, int siteLevel, float zoomLevel) {
            boolean isSiteVisible = true;
            ResourceUtil.Resource resource = ResourceUtil.resourceBeanMap.get(layerId);

            if (resource.systemLevel.size() == 1) {
                return resource.systemLevel.get(0).minZoomVsible <= zoomLevel;
            }
            for (ResourceUtil.SystemLevel level : resource.systemLevel) {
                if (siteLevel == level.level) {
                    if (level.minZoomVsible > zoomLevel) {
                        //不可见
                        isSiteVisible = false;
                        break;
                    }
                }
            }
            return isSiteVisible;
        }




        public static int isNextAvailable(List<Integer> numList, List<ResourceUtil.RightResource> deviceList, int nextIndex, float zoomLevel) {
            if (numList.size() > nextIndex && numList.get(nextIndex) < 4) {
                Integer nextNum = numList.get(nextIndex);
                ResourceUtil.RightResource nextResource = deviceList.get(nextNum);
                if (nextResource.isEnable() &&
                        Utility.MapUtil.getLayerIdByZoomLevel(nextResource.resType, nextResource.systemLevel, zoomLevel)
                        ) {
                    //存在下一个 ，并且下一个属于这个范围  有且可用返回1，继续下次查找 不封口
                    return 1;
                } else {
                    return 3;//有且不可用返回3   继续下次查找 不封口
                }
            } else {
                //没有返回0  不再找，封口
                return 0;
            }
        }
//
//        //判断点击的经纬度附近是否还有其他资源
//
//        public static List<Object> isExsitOtherRes(Projection mProjection,
//                                                   LatLng clickLatlng,
//                                                   List<Marker> searchPointList,
//                                                   Marker searchedPoint,
//                                                   List<PolylineObj> searchPolyList,
//                                                   PolylineObj searchedPolyline,
//                                                   AMap aMap,
//                                                   int rectangLength
//        ) {
//            //点击附近的经纬度转坐标
//            android.graphics.Point centerPoint = mProjection.toScreenLocation(clickLatlng);
//
//            int xMin = centerPoint.x - rectangLength;
//            int xMax = centerPoint.x + rectangLength;
//
//            int yMin = centerPoint.y - rectangLength;
//            int yMax = centerPoint.y + rectangLength;
//
//            android.graphics.Point minPoint = new android.graphics.Point(xMin, yMin);
//            android.graphics.Point maxPoint = new android.graphics.Point(xMax, yMax);
//
//
//            List<Object> nearbyList = new ArrayList<>();
//            if (searchPointList != null) {
//                //点begin
//                for (Marker searchItemMarker : searchPointList) {
//                    if (searchItemMarker != null && searchItemMarker.getPosition() != null) {
//                        android.graphics.Point searchPoint = mProjection.toScreenLocation(searchItemMarker.getPosition());
//                        if (isPointInRectagle(searchPoint, minPoint, maxPoint)) {
//                            nearbyList.add(searchItemMarker);
//                        }
//                    }
//                }
//            }
//
//            if (searchedPoint != null) {
//                if (isPointInRectagle(mProjection.toScreenLocation(searchedPoint.getPosition()), minPoint, maxPoint)) {
//                    nearbyList.add(searchedPoint);
//                }
//            }
//            //点end
//            //线begin
//            //矩形四条线段
//            android.graphics.Point leftTopPoint = new android.graphics.Point(xMin, yMax);
//            android.graphics.Point leftBottomPoint = new android.graphics.Point(xMin, yMin);
//            android.graphics.Point rightTopPoint = new android.graphics.Point(xMax, yMax);
//            android.graphics.Point rightBottomPoint = new android.graphics.Point(xMax, yMin);
//
////            aMap.addPolygon(new PolygonOptions().add(
////                    mProjection.fromScreenLocation(leftBottomPoint),
////                    mProjection.fromScreenLocation(leftTopPoint),
////                    mProjection.fromScreenLocation(rightTopPoint),
////                    mProjection.fromScreenLocation(rightBottomPoint),
////                    mProjection.fromScreenLocation(rightBottomPoint)
////                    )
////                            .fillColor(BaseApplication.getInstance().getResources().getColor(R.color.color_red))
////                            .strokeColor(BaseApplication.getInstance().getResources().getColor(R.color.colorAccent))
////                            .strokeWidth(5)
////            );
//
//
//            android.graphics.Point[][] arrayLine = {
//                    {leftTopPoint, rightTopPoint},
//                    {leftTopPoint, leftBottomPoint},
//                    {rightTopPoint, rightBottomPoint},
//                    {leftBottomPoint, rightBottomPoint},
//            };
//
//            if (searchPolyList != null && searchPolyList.size()
//                    > 0) {
//                for (PolylineObj seachItemLine : searchPolyList) {
//                    PolylineObj obj = isLineInRectagle(mProjection, seachItemLine, minPoint, maxPoint, arrayLine);
//                    if (obj != null) {
//                        nearbyList.add(obj);
//                    }
//                }
//            }
//            if (searchedPolyline != null) {
//                PolylineObj obj = isLineInRectagle(mProjection, searchedPolyline, minPoint, maxPoint, arrayLine);
//                if (obj != null) {//判断UUid一样不需再次添加
//                    nearbyList.add(obj);
//                }
//            }
//            return nearbyList;
//        }

//
//        /**
//         * add by liyk
//         *
//         * @param mProjection
//         * @param clickLatlng
//         * @param searchPointList
//         * @param searchedPoint
//         * @param searchPolyList
//         * @param searchedPolyline
//         * @param aMap
//         * @param rectangLength
//         * @return
//         */
//        public static List<Object> isExsitOtherRes(Projection mProjection,
//                                                   LatLng clickLatlng,
//                                                   List<Marker> searchPointList,
//                                                   Marker searchedPoint,
//                                                   List<PolylineObj> searchPolyList,
//                                                   List<PolygonObj> searchPolygonList,
//                                                   PolylineObj searchedPolyline,
//                                                   AMap aMap,
//                                                   int rectangLength
//        ) {
//            //点击附近的经纬度转坐标
//            android.graphics.Point centerPoint = mProjection.toScreenLocation(clickLatlng);
//
//            int xMin = centerPoint.x - rectangLength;
//            int xMax = centerPoint.x + rectangLength;
//
//            int yMin = centerPoint.y - rectangLength;
//            int yMax = centerPoint.y + rectangLength;
//
//            android.graphics.Point minPoint = new android.graphics.Point(xMin, yMin);
//            android.graphics.Point maxPoint = new android.graphics.Point(xMax, yMax);
//
//
//            List<Object> nearbyList = new ArrayList<>();
//            if (searchPointList != null) {
//                //点begin
//                for (Marker searchItemMarker : searchPointList) {
//                    if (searchItemMarker != null && searchItemMarker.getPosition() != null) {
//                        android.graphics.Point searchPoint = mProjection.toScreenLocation(searchItemMarker.getPosition());
//                        if (isPointInRectagle(searchPoint, minPoint, maxPoint)) {
//                            nearbyList.add(searchItemMarker);
//                        }
//                    }
//                }
//            }
//
//            if (searchedPoint != null) {
//                if (isPointInRectagle(mProjection.toScreenLocation(searchedPoint.getPosition()), minPoint, maxPoint)) {
//                    nearbyList.add(searchedPoint);
//                }
//            }
//            //点end
//            //线begin
//            //矩形四条线段
//            android.graphics.Point leftTopPoint = new android.graphics.Point(xMin, yMax);
//            android.graphics.Point leftBottomPoint = new android.graphics.Point(xMin, yMin);
//            android.graphics.Point rightTopPoint = new android.graphics.Point(xMax, yMax);
//            android.graphics.Point rightBottomPoint = new android.graphics.Point(xMax, yMin);
//
////            aMap.addPolygon(new PolygonOptions().add(
////                    mProjection.fromScreenLocation(leftBottomPoint),
////                    mProjection.fromScreenLocation(leftTopPoint),
////                    mProjection.fromScreenLocation(rightTopPoint),
////                    mProjection.fromScreenLocation(rightBottomPoint),
////                    mProjection.fromScreenLocation(rightBottomPoint)
////                    )
////                            .fillColor(BaseApplication.getInstance().getResources().getColor(R.color.color_red))
////                            .strokeColor(BaseApplication.getInstance().getResources().getColor(R.color.colorAccent))
////                            .strokeWidth(5)
////            );
//
//
//            android.graphics.Point[][] arrayLine = {
//                    {leftTopPoint, rightTopPoint},
//                    {leftTopPoint, leftBottomPoint},
//                    {rightTopPoint, rightBottomPoint},
//                    {leftBottomPoint, rightBottomPoint},
//            };
//
//            if (searchPolyList != null && searchPolyList.size()
//                    > 0) {
//                for (PolylineObj seachItemLine : searchPolyList) {
//                    PolylineObj obj = isLineInRectagle(mProjection, seachItemLine, minPoint, maxPoint, arrayLine);
//                    if (obj != null) {
//                        nearbyList.add(obj);
//                    }
//                }
//            }
//            if (searchedPolyline != null) {
//                PolylineObj obj = isLineInRectagle(mProjection, searchedPolyline, minPoint, maxPoint, arrayLine);
//                if (obj != null) {//判断UUid一样不需再次添加
//                    nearbyList.add(obj);
//                }
//            }
//            if (searchPolygonList != null) {
//                for (PolygonObj polygonObj : searchPolygonList) {
//                    Polygon polygon = polygonObj.getmPolyline();
//                    if (polygon.contains(clickLatlng)) {
//                        nearbyList.add(polygonObj);
//                    }
//                }
//            }
//
//            return nearbyList;
//        }
//
//        public static PolylineObj isLineInRectagle(Projection mProjection, PolylineObj seachItemLine,
//                                                   android.graphics.Point minPoint,
//                                                   android.graphics.Point maxPoint,
//                                                   android.graphics.Point[][] arrayLine) {
//            //最开始的for循环
//            if (seachItemLine.getDataObj() instanceof TaskBean.ResultsBean) {
//                TaskBean.ResultsBean resultsBean = (TaskBean.ResultsBean) seachItemLine.getDataObj();
//                if (resultsBean.getmGeoJsonObject() instanceof LineString) {
//                    LineString lineString = (LineString) resultsBean.getmGeoJsonObject();
//                    int linePointSize = 0;
//                    if (lineString != null && lineString.getPositions() != null && lineString.getPositions().size() > 0) {
//                        linePointSize = lineString.getPositions().size();
//                        if (lineString.getPositions().size() == 0) {
//                            if (isPointInRectagle(
//                                    mProjection.toScreenLocation(
//                                            new LatLng(lineString.getPositions().get(0).getAltitude(),
//                                                    lineString.getPositions().get(0).getLongitude())
//                                    ), minPoint, maxPoint
//                            )) {
//                                //线怎么会就一个点呢 。。。
////                                nearbyList.add(seachItemLine);
//                                return seachItemLine;
//                            }
//                        } else {
//                            boolean isIntersect = false;
//                            //多个点，1 和2 当做线段，2和3当做线段 。。。
//                            for (int i = 0; i < linePointSize; i++) {
//                                int j = i + 1;
//                                if (linePointSize > j) {
//                                    //说明 j存在
//                                    Position lastPosition = lineString.getPositions().get(i);
//                                    Position nextPosition = lineString.getPositions().get(j);
//                                    android.graphics.Point lastPoint =
//                                            mProjection.toScreenLocation(new LatLng(lastPosition.getLatitude(), lastPosition.getLongitude()));
//                                    android.graphics.Point nextPoint =
//                                            mProjection.toScreenLocation(new LatLng(nextPosition.getLatitude(), nextPosition.getLongitude()));
//
//                                    int pointXmin = Math.min(lastPoint.x, nextPoint.x);
//                                    int pointXmax = Math.max(lastPoint.x, nextPoint.x);
//                                    int pointYmin = Math.min(lastPoint.y, nextPoint.y);
//                                    int pointYmax = Math.max(lastPoint.y, nextPoint.y);
//
//                                    //快速排斥实验，根据以线段的对角线形成的矩形 边界比较左右边界和上下边界
//                                    if (pointXmax < minPoint.x
//                                            || pointXmin > maxPoint.x
//                                            || pointYmax < minPoint.y
//                                            || pointYmin > maxPoint.y
//                                            ) {
//                                        //这种情况下，不会重合
//                                    } else {
//                                        //在判断其他情况，跨立试验
//                                        //判断P1P2跨立Q1Q2的依据是：( P1 - Q1 ) × ( Q2 - Q1 ) * ( Q2 - Q1 ) × ( P2 - Q1 ) >= 0
//                                        //(P1.x -Q1.x ,P1.y -Q1.y)×(Q2.x-Q1.x,Q2.y-Q1.y) *(Q2.x-Q1.x,Q2.y-Q1.y)×(P2.x-Q1.x,P2.y-Q1.y)>=0
//                                        //判断P1P2跨立Q1Q2的依据是：( Q1 - P1 ) × ( P2 - P1 ) * ( P2 - P1 ) × ( Q2 - P1 ) >= 0
//                                        //(Q1.x -P1.x ,Q1.y -P1.y)×(P2.x-P1.x,P2.y-P1.y) *(P2.x-P1.x,P2.y-P1.y)×(Q2.x-P1.x,Q2.y-P1.y)>=0
//
//                                        android.graphics.Point P1 = lastPoint;
//                                        android.graphics.Point P2 = nextPoint;
//
//                                        for (int m = 0; m < arrayLine.length; m++) {
//                                            android.graphics.Point[] points = arrayLine[m];
//                                            android.graphics.Point Q1 = points[0];
//                                            android.graphics.Point Q2 = points[1];
//
//                                            //P × Q = x1*y2 - x2*y1
//
//                                            boolean left = (((P1.x - Q1.x) * (Q2.y - Q1.y) - (Q2.x - Q1.x) * (P1.y - Q1.y))
//                                                    * ((Q2.x - Q1.x) * (P2.y - Q1.y) - (P2.x - Q1.x) * (Q2.y - Q1.y))) >= 0;
//                                            boolean right = (((Q1.x - P1.x) * (P2.y - P1.y) - (P2.x - P1.x) * (Q1.y - P1.y))
//                                                    * ((P2.x - P1.x) * (Q2.y - P1.y) - (Q2.x - P1.x) * (P2.y - P1.y))) >= 0;
//                                            Timber.d(" left " + left + " right " + right);
//
//                                            if (left && right) {
//                                                //所以线段与矩形任意一条边相交
//                                                isIntersect = true;
//                                                return seachItemLine;
////                                                break;
//                                            }
//                                        }
//                                        if (isIntersect) {
//                                            break;
//                                        }
//                                    }
//                                    if (isIntersect) {
////                                        seachItemLine
////                                        nearbyList.add(seachItemLine);
//                                        break;
//                                    }
//                                } else {
//                                    //已经没有下一个点了
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//            return null;
//        }

        public static boolean isPointInRectagle(android.graphics.Point point,
                                                android.graphics.Point minPoint,
                                                android.graphics.Point maxPoint) {
            if (point.x < minPoint.x
                    || point.x > maxPoint.x
                    || point.y < minPoint.y
                    || point.y > maxPoint.y
                    ) {
                //满足此条件，不在矩形范围内
                return false;
            } else {
                //在矩形范围内
                return true;
            }
        }


        public static String[] pageckageArray = new String[]{
                MAP_BAIDU, MAP_GAODE, MAP_GOOGLE, MAP_TENCENT
        };
        public static String[] pageckDescArray = new String[]{
                MAP_BAIDU_NAME, MAP_GAODE_NAME, MAP_GOOGLE_NAME, MAP_TENCENT_NAME
        };

        static {
            for (int i = 0; i < pageckageArray.length; i++) {
                packges.add(new MapPackge(pageckageArray[i], pageckDescArray[i]));
            }

        }

        /**
         * 获取可用的地图应用列表
         *
         * @param context
         * @return
         */
        public static List<MapPackge> getAvailableMap(Context context) {
            List<MapPackge> packgeList = new ArrayList<>();
            for (int i = 0; i < packges.size(); i++) {
                if (isAvilible(context, packges.get(i).packageName)) {
                    packgeList.add(packges.get(i));
                }
            }
            return packgeList;
        }

        public static void mapGuide(Context mContext, String packName, double lat, double lng) {

            switch (packName) {
                case MAP_BAIDU:
                    // 百度地图

//                    Point sourcePoint = new Point(lng, lat);//x lng y lat
//                    Point targetPoint = CoordinateConverter.converter(CoordinateConverter.COOR_TYPE.COOR_TYPE_GCJ02, sourcePoint);
//
//                    Intent naviIntent = new Intent("android.intent.action.VIEW",
//                            Uri.parse("baidumap://map/geocoder?location=" + targetPoint.y + "," + targetPoint.x));
//                    mContext.startActivity(naviIntent);
                    break;
                case MAP_GAODE:
                    // 高德地图

//                    Intent intent = new Intent("android.intent.action.VIEW",
//                            android.net.Uri.parse("androidamap://navi?sourceApplication=外线地图&lat=" + lat + "&lon=" + lng + "&dev=0&style=2"));
//                    intent.setPackage(packName);
//                    mContext.startActivity(intent); // 启动调用


                    // 构造导航参数
                    NaviPara naviPara = new NaviPara();
                    // 设置终点位置
                    naviPara.setTargetPoint(new LatLng(lat, lng));
                    // 设置导航策略，这里是避免拥堵
                    naviPara.setNaviStyle(AMapUtils.DRIVING_AVOID_CONGESTION);
                    // 调起高德地图导航
                    try {
                        AMapUtils.openAMapNavi(naviPara, mContext);
                    } catch (AMapException e) {
                        // 如果没安装会进入异常，调起下载页面
                        AMapUtils.getLatestAMapApp(mContext);
                    }
                    break;
                case MAP_GOOGLE:

//                    double[] latLngArray = GPSUtil.gcj02_To_Bd09(lat, lng);
//
//                    Timber.d(latLngArray[0] + "");
//                    Timber.d(latLngArray[1] + "");
//                    Uri gmmIntentUri = Uri.parse("google.navigation:q=" + latLngArray[0] + "," +
//                            latLngArray[1] + ", + Sydney +Australia");
//                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
//                    mapIntent.setPackage("com.google.android.apps.maps");
//                    mContext.startActivity(mapIntent);
                    break;
                case MAP_TENCENT:

                    // 腾讯地图
                    Intent tIntent = new Intent("android.intent.action.VIEW",
                            Uri.parse("qqmap://map/routeplan?type=drive&from=&fromcoord=&to=目的地&tocoord=" + lat + "," + lng + "&policy=0&referer=appName"));
                    mContext.startActivity(tIntent);
                    break;
            }
        }

        public static class MapPackge {
            public final String packageName;
            public final String packageDesc;

            public MapPackge(String packageName, String packageDesc) {
                this.packageName = packageName;
                this.packageDesc = packageDesc;
            }
        }
    }

    public static boolean isAvilible(Context context, String packageName) {
        //获取packagemanager
        final PackageManager packageManager = context.getPackageManager();
        //获取所有已安装程序的包信息
        List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
        //用于存储所有已安装程序的包名
        List<String> packageNames = new ArrayList<String>();
        //从pinfo中将包名字逐一取出，压入pName list中
        if (packageInfos != null) {
            for (int i = 0; i < packageInfos.size(); i++) {
                String packName = packageInfos.get(i).packageName;
                packageNames.add(packName);
            }
        }
        //判断packageNames中是否有目标程序的包名，有TRUE，没有FALSE
        return packageNames.contains(packageName);
    }


    public static boolean isPolylineEquals(Polyline onePoly, Polyline twoPoly) {

        if (onePoly != null && twoPoly != null) {
            if (onePoly.getPoints().size() == twoPoly.getPoints().size()) {
                int pointSize = onePoly.getPoints().size();
                for (int i = 0; i < pointSize; i++) {
                    LatLng latLng = onePoly.getPoints().get(i);
//                   if ()
                }
            }
        }
        return false;
    }

    public static String valueOf(Object obj) {
        if (obj == null) {
            return "";
        }
        return obj.toString();
    }


    public static void calculateDistance(LatLng currentPosition, List<TaskBean.ResultsBean> resultsBeans) {
    }

    public static void copyToClipboar(Context mContext, String content) {
        //获取剪贴板管理器：
        ClipboardManager cm = (ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
// 创建普通字符型ClipData
        ClipData mClipData = ClipData.newPlainText("Label", content);
// 将ClipData内容放到系统剪贴板里。
        cm.setPrimaryClip(mClipData);
    }

}
