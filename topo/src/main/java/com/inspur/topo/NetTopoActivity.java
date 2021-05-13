package com.inspur.topo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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
import android.view.Window;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.inspur.osp.AppConfig;
import com.inspur.osp.adapter.NewCellDetailAdapter;
import com.inspur.osp.contract.NetTopoContract;
import com.inspur.osp.data.bean.CellDetailInfo;
import com.inspur.osp.data.bean.ElementSubnetInfo;
import com.inspur.osp.presenter.NetTopoPresenter;
import com.inspur.osp.presenter.TopoMapPresenter;
import com.inspur.osp.ui.BaseActivity;
import com.inspur.osp.util.GsonUtil;
import com.inspur.osp.util.Utility;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 逻辑拓扑（供泰岳emos使用）
 */
public class NetTopoActivity extends BaseActivity implements NetTopoContract.View {

    NetTopoContract.Presenter netPresenter;
    WebView wvCon;
    ProgressBar progressBar1;
    WebSettings settings;
    TextView tv_noSubnet;

    private String city_id;
    private String ems_id;
    private String county_id;
    private String subnet_id;
    private String subnet_type;

    private String aNeId;
    private String aPortId;
    private String zNeId;
    private String zPortId;

    private String type;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        TopoConnectActivity.mStaticContext = this;
//        Stetho.initializeWithDefaults(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_net_topo;
    }

    @Override
    protected void getIntentData() {
        super.getIntentData();
        if(getIntent().hasExtra("type")){
            type = getIntent().getStringExtra("type");
        }else{
            type="outer";
        }

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


        if(getIntent().hasExtra("aNeId")){
            aNeId = getIntent().getStringExtra("aNeId");
        }else{
            aNeId="";
        }
        if(getIntent().hasExtra("aPortId")){
            aPortId = getIntent().getStringExtra("aPortId");
        }else{
            aPortId="";
        }
        if(getIntent().hasExtra("zNeId")){
            zNeId = getIntent().getStringExtra("zNeId");
        }else{
            zNeId="";
        }
        if(getIntent().hasExtra("zPortId")){
            zPortId = getIntent().getStringExtra("zPortId");
        }else{
            zPortId="";
        }

    }

    @Override
    protected void afterSetView(@Nullable Bundle savedInstanceState) {
        super.afterSetView(savedInstanceState);
        wvCon = findViewById(R.id.wvCon);
        progressBar1 = findViewById(R.id.progressBar1);
        tv_noSubnet = findViewById(R.id.tv_noSubnet);
    }



    @Override
    protected void beforeSetView(@Nullable Bundle savedInstanceState) {
        super.beforeSetView(savedInstanceState);
        setupStatusBar();
    }

    private void setupStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.WHITE);
//            StatusBarLightModeUtils.StatusBarLightMode(this);
        }
    }

    @Override
    protected void initView() {
        super.initView();
        setTitleMsg("逻辑路由");
        setBackIcon();
        new NetTopoPresenter(mContext, this);


    }

    protected void initwebview(String aNeId,String zNeId) {






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
        wvCon.addJavascriptInterface(new NetTopoActivity.WebAppInterface(this),
                "android");

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


        wvCon.addJavascriptInterface(new NetTopoActivity.TopoData(this,city_id,ems_id,county_id,subnet_id,subnet_type,aNeId,zNeId),
                "toPodata");
        wvCon.loadUrl("file:///android_asset/CommonSubnetwork.html");
    }


    @Override
    protected void initData() {
        if(!type.equals("outer")){
            initwebview("","");
        }else{
            netPresenter.queryElemnetSubnet(aNeId,aPortId,zNeId,zPortId);
        }
    }

    @Override
    public void showTopo(ElementSubnetInfo elementSubnetInfo) {
       Log.v("NetTopoActivity",GsonUtil.gsonString(elementSubnetInfo));
       if(null == elementSubnetInfo.getSubnetId()){
           showHint("没有找到对应的子网信息，请检查网元是否正确");
           tv_noSubnet.setVisibility(View.VISIBLE);
           wvCon.setVisibility(View.GONE);
       }else{
           city_id = elementSubnetInfo.getCityId();
           ems_id = elementSubnetInfo.getEmsId();
           county_id = "";
           subnet_id = elementSubnetInfo.getSubnetId();
           subnet_type ="common_subnet";
           if(null!=elementSubnetInfo.getaNeId()){
               initwebview(elementSubnetInfo.getaNeId(),elementSubnetInfo.getzNeid());
           }else{
               initwebview(elementSubnetInfo.getzNeid(),elementSubnetInfo.getaNeId());
           }

       }



    }

    @Override
    public void setPresenter(NetTopoContract.Presenter presenter) {
        netPresenter = presenter;
    }

    @Override
    public void setLoadingIndicator(boolean active, String msg) {
        if (active) {
            showProgressLoading(msg);
        } else {
            dismissProgressLoading();
        }
    }

    @Override
    public void finishView() {
       finish();
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

        private String aNeId;
        private String zNeid;
        public TopoData(Context c,String city_id,String ems_id,String county_id,String subnet_id,String subnet_type,String a,String z){
            mContext = c;
            this.city_id = city_id;
            this.ems_id = ems_id;
            this.county_id = county_id;
            this.subnet_id = subnet_id;
            this.subnet_type = subnet_type;
            this.aNeId = a;
            this.zNeid = z;
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
        public String getaNeId() {
            return aNeId;
        }
        @JavascriptInterface
        public String getzNeid() {
            return zNeid;
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
            newintent.putExtra("type","inner");
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
