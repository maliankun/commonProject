package com.inspur.osp.data.source.okclient;

import android.text.TextUtils;

import com.inspur.osp.AppConfig;
import com.inspur.osp.BaseApplication;
import com.inspur.osp.data.source.SelfException;
import com.inspur.osp.util.L;

import java.io.EOFException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;

import static com.inspur.osp.data.source.SelfException.CODE_DEAULT;
import static com.inspur.osp.data.source.SelfException.TIME_OUT;

/**
 * Project: OnesgoWMS
 * Author: liyi
 * Create Date: 2017/1/12.
 */
class DominInterceptor implements Interceptor {
    private static final Charset UTF8 = Charset.forName("UTF-8");

    @Override
    public Response intercept(Chain chain) {

        SelfException selfException;
        Map<String, String> uploadMap = new HashMap<>();

        Request oldRequest = chain.request();
        //更改Ip
        //获取老的url
        HttpUrl oldUrl = oldRequest.url();
        List<String> urlnameList = oldRequest.headers("urlname");
        //获取originalRequest的创建者builder
        Request.Builder builder = oldRequest.newBuilder();
        boolean isLogin = false;
        boolean isDownload = false;
        if (urlnameList != null && urlnameList.size() > 0) {
            builder.removeHeader("urlname");
            String urlname = urlnameList.get(0);
            HttpUrl baseURL = null;
            if ("manage".equals(urlname)) {
                baseURL = HttpUrl.parse(AppConfig.BASE_URL_LOCAL);
            } else if ("authUrl".equals(urlname)) {
                baseURL = HttpUrl.parse(AppConfig.BASE_URL_AUTH);
            } else if ("login".equals(urlname)) {
                isLogin = true;
            } else if ("download".equalsIgnoreCase(urlname)) {
                isDownload = true;
            } else if ("topo".equalsIgnoreCase(urlname)) {
                baseURL = HttpUrl.parse(AppConfig.TOPO_ROUTE_URL);
            }
            if (baseURL != null) {
                //重建新的HttpUrl，需要重新设置的url部分
                HttpUrl newHttpUrl = oldUrl.newBuilder()
                        .scheme(baseURL.scheme())//http协议如：http或者https
                        .host(baseURL.host())//主机地址
                        .port(baseURL.port())//端口
                        .build();
                oldRequest = builder.url(newHttpUrl).build();
            }
        }

        Request newRequst = oldRequest ;
//        if (!isLogin) {
//            //添加token
//            String token = PreferenceUtil.getString(BaseApplication.getInstance(), AppConfig.TOKEN);
//            if (!TextUtils.isEmpty(token)) {
//                newRequst = oldRequest.newBuilder()
//                        .addHeader(AppConfig.TOKEN, "Bearer " + PreferenceUtil.getString(BaseApplication.getInstance(), AppConfig.TOKEN))
//                        .method(oldRequest.method(), oldRequest.body())
//                        .build();
//            } else {
//                newRequst = oldRequest;
//            }
//        } else {
//            newRequst = oldRequest;
//        }

        //打印请求信息
        L.D("url:" + newRequst.url());
        L.D("method:" + newRequst.method());

        Buffer buffer = new Buffer();
        RequestBody requestBody = newRequst.body();
        if (requestBody != null) {
            try {
                requestBody.writeTo(buffer);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Charset charset = Charset.forName("UTF-8");
            MediaType contentType = requestBody.contentType();
            if (contentType != null) {
                charset = contentType.charset(UTF8);
            }
            String requestContent = buffer.readString(charset);
            uploadMap
                    .put("requestContent", requestContent);
        }
        uploadMap
                .put("requestUrl", newRequst.url().toString());
        Response response = null;
        try {
            response = chain.proceed(newRequst);
        } catch (IOException e) {
            e.printStackTrace();
            selfException = new SelfException(TIME_OUT, "time out");
            selfException.setExtraMap(uploadMap);
            throw selfException;
        }
        L.D("response.code() " + response.code());
        L.D("response.message() " + response.message());
        L.D("response.toString() " + response.toString());

        ResponseBody responseBody = response.body();//获取请求body
        if (response.code() != 200) {
            byte[] resp = new byte[0];
            try {
                resp = responseBody.bytes();
            } catch (IOException e) {
                e.printStackTrace();
            }
            selfException = new SelfException(response.code(), new String(resp));
            selfException.setExtraMap(uploadMap);
            throw selfException;
        }
        byte[] resp = new byte[0];
        try {
            resp = responseBody.bytes();
        } catch (IOException e) {
            e.printStackTrace();
            selfException = new SelfException(CODE_DEAULT, e.getMessage());
            selfException.setExtraMap(uploadMap);
            throw selfException;
        }
        if (resp.length == 0) {
            resp = "{}".getBytes();
//            selfException = new SelfException(DATA_EMPTY, "无数据返回，请重试！");
//            selfException.setExtraMap(uploadMap);
//            throw selfException;
        }
//        Timber.d(new String(resp));

        return response.newBuilder()
                .body(ResponseBody.create(null, resp))
                .build();
    }

    static boolean isPlaintext(Buffer buffer) throws EOFException {
        try {
            Buffer prefix = new Buffer();
            long byteCount = buffer.size() < 64 ? buffer.size() : 64;
            buffer.copyTo(prefix, 0, byteCount);
            for (int i = 0; i < 16; i++) {
                if (prefix.exhausted()) {
                    break;
                }
                int codePoint = prefix.readUtf8CodePoint();
                if (Character.isISOControl(codePoint) && !Character.isWhitespace(codePoint)) {
                    return false;
                }
            }
            return true;
        } catch (EOFException e) {
            return false; // Truncated UTF-8 sequence.
        }
    }

    /**
     * 添加公共参数 * * @param oldRequest * @return
     */
    private Request addDomainParam(Request oldRequest) {

        HttpUrl.Builder builder = oldRequest.url()
                .newBuilder()
                .setEncodedQueryParameter("domainId", "wms")
                .setEncodedQueryParameter("domain_", "wms");
//
//        String whseId = PreferencesUtils.getString(WMSApplication.getAppContext(), "whseId");
//        if (!TextUtils.isEmpty(whseId)) {
//            builder.setEncodedQueryParameter("whseId", whseId);
//        }
        return oldRequest.newBuilder()
                .method(oldRequest.method(), oldRequest.body())
                .url(builder.build())
                .build();
    }
}
