package com.inspur.osp;

import android.app.Application;
import android.content.Context;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

import com.amap.api.maps.MapsInitializer;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by liuchen_ on 2017/12/13.
 */

public class BaseApplication extends Application {

    private static BaseApplication INSTANCE;

    public static String lastRackId = null;
    public static String lastRackType = null;

    public static List<Map<String, Object>> lastObj = null;

    public static BaseApplication getInstance() {
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        INSTANCE = this;
        lastObj = new ArrayList<>();

//        try {
//            MapsInitializer.initialize(INSTANCE);
//        } catch (RemoteException e) {
//            e.printStackTrace();
//        }


        //LogUtil.configLog();

        //获取Application Log
        //Logger log = Logger.getLogger(BaseApplication.class);

        //输出MyApplication的信息
        //log.info("Log4j Is Ready and My Application Was Created Successfully! ");
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }
}
