//package com.inspur.osp.util;
//
///**
// * Created by liuchen_ on 2017/12/14.
// */
//
//import com.amap.api.location.AMapLocationClient;
//import com.amap.api.location.AMapLocationClientOption;
//import com.amap.api.location.AMapLocationListener;
//
///**
// * Created by Administrator on 2017/3/17.
// */
//
//public class LocationUtil {
//
//    private static LocationUtil instance;
//    //声明mLocationOption对象
//    public AMapLocationClientOption mLocationOption = null;
//
//    private LocationUtil() {
//        startLocation();
//    }
//
//    public static LocationUtil getInstance() {
//        if (instance != null) {
//            return instance;
//        }
//        instance = new LocationUtil();
//        return instance;
//    }
//
//    public void setListenerOption(AMapLocationClient mLocationClient, AMapLocationListener locationListener) {
//        if (mLocationClient == null) {
//            //初始化定位
//            throw new IllegalArgumentException("mLocationClient 参数不能为null");
//        }
//        //给定位客户端对象设置定位参数
//        mLocationClient.setLocationOption(mLocationOption);
//        if (locationListener != null) {
//            mLocationClient.setLocationListener(locationListener);
//        }
//    }
//
//    public void startLocation() {
//        if (mLocationOption == null) {
//            mLocationOption = new AMapLocationClientOption();
//            mLocationOption.setNeedAddress(true);
//
//            //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
//            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
//            //设置是否返回地址信息（默认返回地址信息）
//            mLocationOption.setNeedAddress(true);
//            //设置是否只定位一次,默认为false
//            mLocationOption.setOnceLocation(false);
//            //设置是否强制刷新WIFI，默认为强制刷新
//            mLocationOption.setWifiActiveScan(true);
//            //设置是否允许模拟位置,默认为false，不允许模拟位置
//            mLocationOption.setMockEnable(false);
//            //设置定位间隔,单位毫秒,默认为2000ms
//            mLocationOption.setInterval(3 * 1000);
//        }
//    }
//
//    public void setOnceLocation() {
//        if (mLocationOption == null) {
//            mLocationOption = new AMapLocationClientOption();
//        }
//        mLocationOption.setOnceLocation(true);
//    }
//
//    public void setTimelocation(long time) {
//        if (mLocationOption == null) {
//            mLocationOption = new AMapLocationClientOption();
//        }
//        mLocationOption.setInterval(time);
//    }
//    /*
//                    if (amapLocation != null) {
//                    if (amapLocation.getErrorCode() == 0) {
//                        //可在其中解析amapLocation获取相应内容。
//                        Log.d(TAG, " getLocationType " + amapLocation.getLocationType());//获取当前定位结果来源，如网络定位结果，详见定位类型表
//                        Log.d(TAG, "getLatitude " + amapLocation.getLatitude());//获取纬度
//                        Log.d(TAG, "getLongitude " + amapLocation.getLongitude());//获取经度
//                        Log.d(TAG, "getAccuracy " + amapLocation.getAccuracy());//获取精度信息
//                        Log.d(TAG, "getAddress " + amapLocation.getAddress());//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
//                        Log.d(TAG, "getCountry " + amapLocation.getCountry());//国家信息
//                        Log.d(TAG, "getProvince " + amapLocation.getProvince());//省信息
//                        Log.d(TAG, "getCity " + amapLocation.getCity());//城市信息
//                        Log.d(TAG, " getDistrict " + amapLocation.getDistrict());//城区信息
//                        Log.d(TAG, "getStreet " + amapLocation.getStreet());//街道信息
//                        Log.d(TAG, "getStreetNum " + amapLocation.getStreetNum());//街道门牌号信息
//                        Log.d(TAG, "getCityCode " + amapLocation.getCityCode());//城市编码
//                        Log.d(TAG, "getAdCode " + amapLocation.getAdCode());//地区编码
//                        Log.d(TAG, "getAoiName " + amapLocation.getAoiName());//获取当前定位点的AOI信息
//                        Log.d(TAG, "getBuildingId " + amapLocation.getBuildingId());//获取当前室内定位的建筑物Id
//                        Log.d(TAG, "getFloor " + amapLocation.getFloor());//获取当前室内定位的楼层
//                        //获取定位时间
//                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                        Date date = new Date(amapLocation.getTime());
//                        df.format(date);
//                        Log.d(TAG, "dateTime " + date.toString());
//                    } else {
//                        //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
//                        Log.e("AmapError", "location Error, ErrCode:"
//                                + amapLocation.getErrorCode() + ", errInfo:"
//                                + amapLocation.getErrorInfo());
//                    }
//                }
//
//     */
//}
