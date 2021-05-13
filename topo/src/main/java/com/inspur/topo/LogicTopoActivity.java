package com.inspur.topo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.inspur.osp.AppConfig;
import com.inspur.osp.adapter.NewCellDetailAdapter;
import com.inspur.osp.data.bean.CellDetailInfo;
import com.inspur.osp.util.GsonUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 *
 */
public class LogicTopoActivity extends Activity {

    RelativeLayout rl_back;
    TextView title;
    ProgressBar progressBar1;
    WebView wvCon;



    private String city_id;
    private String ems_id;
    private String county_id;
    private String subnet_id;
    private String subnet_type;


    WebSettings settings;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_web_view);
        TopoConnectActivity.mStaticContext = this;
        rl_back = findViewById(R.id.rl_back);
        progressBar1 = findViewById(R.id.progressBar1);
        wvCon = findViewById(R.id.wvCon);
        rl_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initData();
    }




    protected void initData() {
        if(getIntent().hasExtra("city_id")){
            city_id = getIntent().getStringExtra("city_id");
        }else{
            city_id="";
        }
        if(getIntent().hasExtra("ems_id")){
            ems_id = getIntent().getStringExtra("ems_id");
        }else{
            ems_id="";
        }
        if(getIntent().hasExtra("county_id")){
            county_id = getIntent().getStringExtra("county_id");
        }else{
            county_id="";
        }
        if(getIntent().hasExtra("subnet_id")){
            subnet_id = getIntent().getStringExtra("subnet_id");
        }else{
            subnet_id="";
        }
        if(getIntent().hasExtra("subnet_type")){
            subnet_type = getIntent().getStringExtra("subnet_type");
        }else{
            subnet_type="province_subnet";
        }
        settings = wvCon.getSettings();
        //设置可自由缩放网页
        //出现net::ERR_CACHE_MISS错误提示
        //使用缓存的方式是基于导航类型。正常页面加载的情况下将缓存内容。当导航返回,
        //内容不会恢复（重新加载生成）,而只是从缓存中取回内容
//        if (Build.VERSION.SDK_INT >= 19) {
//            settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
//        }
        settings.setSupportZoom(true);
        settings.setJavaScriptEnabled(true);//有JavaScript功能的一定要实现
        settings.setBuiltInZoomControls(true);//支持缩放
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);//bushi
        settings.setAllowContentAccess(true);//
        settings.setAppCacheEnabled(true);//是否使用缓存
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        settings.setDomStorageEnabled(true);
        settings.setJavaScriptEnabled(true);
        wvCon.addJavascriptInterface(new WebAppInterface(this),
                "android");
        wvCon.addJavascriptInterface(new TopoData(this,city_id,ems_id,county_id,subnet_id,subnet_type),
                "toPodata");
        wvCon.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                //progressBar1.setProgress(newProgress);
                super.onProgressChanged(view, newProgress);
                if(newProgress ==100){
                    progressBar1.setVisibility(View.GONE);
                    //progressBar.setProgress(newProgress);
                }else {
                    progressBar1.setVisibility(View.VISIBLE);
                    progressBar1.setProgress(newProgress);//设置加载进度
                }

            }

        });
        try {
            if (Build.VERSION.SDK_INT >= 16) {
                Class<?> clazz = wvCon.getSettings().getClass();
                Method method = clazz.getMethod(
                        "setAllowUniversalAccessFromFileURLs", boolean.class);
                if (method != null) {
                    method.invoke(wvCon.getSettings(), true);
                }
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        wvCon.loadUrl("file:///android_asset/CommonSubnetwork.html");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (data != null) {
                if (requestCode == 1000) {
                    String name = data.getStringExtra("name");
                    String type = data.getStringExtra("type");
                    String typeName = data.getStringExtra("typeName");
                    String uuid = data.getStringExtra("uuid");
                    Intent intent = new Intent();
                    intent.putExtra("name", name);
                    intent.putExtra("type", type);
                    intent.putExtra("typeName", typeName);
                    intent.putExtra("uuid", uuid);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        }
    }

    //接口名称自定义

    public class WebAppInterface   {

        Context mContext;

        WebAppInterface(Context   c) {

            mContext   = c;

        }

        //参数名这个自己定义，和网页js传参对应即可

        /**
         * 与js交互时用到的方法，在js里直接调用的
         */
        @JavascriptInterface
        public void showToast(String ssss) {

            ssss ="abnn";
        }

    }

    public class NeObject {
        private Context mContext;
        private String neId;
        public NeObject(Context c,String neId){
            this.neId = neId;
            mContext = c;
        }
        @JavascriptInterface
        public String getNeId() {
            return neId;
        }
    }


    public class TopoData {
        private Context mContext;
        private String city_id;
        private String ems_id;
        private String county_id;
        private String subnet_id;
        private String subnet_type;
        public TopoData(Context c,String city_id,String ems_id,String county_id,String subnet_id,String subnet_type){
            mContext = c;
            this.city_id = city_id;
            this.ems_id = ems_id;
            this.county_id = county_id;
            this.subnet_id = subnet_id;
            this.subnet_type = subnet_type;
        }

        @JavascriptInterface
        public String getCity_id() {
            return city_id;
        }
        @JavascriptInterface
        public String getEms_id() {
            return ems_id;
        }
        @JavascriptInterface
        public String getCounty_id() {
            return county_id;
        }
        @JavascriptInterface
        public String getSubnet_id() {
            return subnet_id;
        }
        @JavascriptInterface
        public String getSubnet_type() {
            return subnet_type;
        }

        @JavascriptInterface
        public void toNePage(String neId){
//            Intent i = new Intent(LocalWebViewActivity.this,NePageActivity.class);
//            i.putExtra("neId",neId);
//            startActivity(i);
        }

        @JavascriptInterface
        public void showDetail(String listData){
            Log.v("LocalWebViewActivity",listData+"");
            List<CellDetailInfo> detailList = GsonUtil.GsonToList(listData, CellDetailInfo[].class);
            Iterator<CellDetailInfo> it = detailList.iterator();
            List<CellDetailInfo> showList = new ArrayList<>();
            for(CellDetailInfo cellDetailInfo : detailList){
                if(cellDetailInfo.getShow()){
                    showList.add(cellDetailInfo);
                }
            }
            View view = View.inflate(mContext, R.layout.celldetail_dialog, null);
            RecyclerView reSubnet = (RecyclerView) view.findViewById(R.id.rv_subnet);
            TextView tv_panel = view.findViewById(R.id.tv_panel);
            tv_panel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    String uuid ="";
//                    for(CellDetailInfo cellDetailInfo :detailList){
//                        if(cellDetailInfo.getEnName().equals("int_id")){
//                            uuid = cellDetailInfo.getValue();
//                        }
//                    }
//                    Intent i = new Intent(LocalWebViewActivity.this,NePageActivity.class);
//                    i.putExtra("neId",uuid);
//                    startActivity(i);
                }
            });

            TextView tv_relate_site = view.findViewById(R.id.tv_relate_site);



            NewCellDetailAdapter cellDetailAdapter = new NewCellDetailAdapter(showList);
            reSubnet.setHasFixedSize(true);
            reSubnet.setLayoutManager(new LinearLayoutManager(mContext));
            reSubnet.setItemAnimator(new DefaultItemAnimator());
            reSubnet.setAdapter(cellDetailAdapter);
            reSubnet.addItemDecoration(new DividerItemDecoration(mContext,DividerItemDecoration.VERTICAL));
            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(mContext);
            bottomSheetDialog.setContentView(view);
            bottomSheetDialog.show();
        }


        @JavascriptInterface
        public void showNextTopo(String node_city_id,String node_ems_id,String node_subnet_id,String node_subnet_type,String node_county_id){
            Intent newintent = new Intent(mContext,LogicTopoActivity.class);
            newintent.putExtra("city_id",node_city_id);
            newintent.putExtra("ems_id",node_ems_id);
            newintent.putExtra("county_id",node_county_id);
            newintent.putExtra("subnet_id",node_subnet_id);
            newintent.putExtra("subnet_type",node_subnet_type);
            startActivityForResult(newintent,1000);

        }

        @JavascriptInterface
        public void showInsideTOpo(String listdata){
            Log.v("LocalWebViewActivity",listdata+"");
            List<CellDetailInfo> detailList = GsonUtil.GsonToList(listdata,CellDetailInfo[].class);
            String uuid ="";
            for(CellDetailInfo cellDetailInfo :detailList){
                if(cellDetailInfo.getEnName().equals("int_id")){
                    uuid = cellDetailInfo.getValue();
                }
            }
            TopoConnectActivity.startAct(mContext, AppConfig.Constants.TOPO_LIST_IDS, uuid);
        }

    }



}
