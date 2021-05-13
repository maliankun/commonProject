package com.inspur.osp.data.source.okclient;


//import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.inspur.osp.AppConfig;
import com.inspur.osp.BaseApplication;
import com.inspur.topo.TopoConnectActivity;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
//import okhttp3.logging.HttpLoggingInterceptor;
//import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Author: liyi
 * Create Date: 16/8/28.
 */
public class OkClientInstance {
    private static volatile OkHttpClient _client;

    public static synchronized OkHttpClient getClient() {
        if (_client == null) {
            synchronized (OkClientInstance.class) {
                if (_client == null) {
//                    if (cn.onesgo.wms.BuildConfig.DEBUG) {
//                    HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
//                    logging.setLevel(HttpLoggingInterceptor.Level.BODY);
                    if (AppConfig.IS_TEST) {
                        _client = new OkHttpClient.Builder()
                                .cookieJar(new CookiesManager(TopoConnectActivity.mStaticContext))
//                                .addInterceptor(logging)
                                .addInterceptor(new DominInterceptor())
//                                .addNetworkInterceptor(new StethoInterceptor())
                                .connectTimeout(20, TimeUnit.SECONDS)
                                .writeTimeout(30, TimeUnit.SECONDS)
                                .readTimeout(40, TimeUnit.SECONDS)
                                .build();
                    } else {
//                        HttpLoggingInterceptor logging2 = new HttpLoggingInterceptor();
//                        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
                        _client = new OkHttpClient.Builder()
                                .cookieJar(new CookiesManager(TopoConnectActivity.mStaticContext))
                                .addInterceptor(new DominInterceptor())
//                                .addNetworkInterceptor(new StethoInterceptor())

//                                .addInterceptor(logging)
                                .connectTimeout(20, TimeUnit.SECONDS)
                                .writeTimeout(30, TimeUnit.SECONDS)
                                .readTimeout(40, TimeUnit.SECONDS)
                                .build();
                    }
                }
            }
        }
        return _client;
    }
}
