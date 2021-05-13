package com.inspur.topo;

import android.Manifest;
import android.animation.Animator;
import android.animation.FloatEvaluator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.BounceInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.amap.api.maps.AMap;
import com.amap.api.maps.AMapOptions;
import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.Projection;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.PolygonOptions;
import com.amap.api.maps.model.Polyline;
import com.amap.api.maps.model.PolylineOptions;
import com.cocoahero.android.geojson.GeoJSONObject;
import com.cocoahero.android.geojson.LineString;
import com.cocoahero.android.geojson.Polygon;
import com.cocoahero.android.geojson.Position;
import com.cocoahero.android.geojson.Ring;
import com.inspur.osp.AppConfig;
import com.inspur.osp.contract.CsTopoMapConstact;
import com.inspur.osp.data.bean.CableSegBean;
import com.inspur.osp.data.bean.LaySegBean;
import com.inspur.osp.data.bean.LayerItemRequestbean;
import com.inspur.osp.data.bean.NeRouteBean;
import com.inspur.osp.data.bean.PolylineObj;
import com.inspur.osp.data.bean.TaskBean;
import com.inspur.osp.data.bean.TopoBean;
import com.inspur.osp.data.bean.TopoCheckedRouteBean;
import com.inspur.osp.data.bean.TopoNePortBean;
import com.inspur.osp.data.bean.TopoRouteBean;
import com.inspur.osp.presenter.TopoMapPresenter;
import com.inspur.osp.ui.BaseActivity;
import com.inspur.osp.ui.widgets.RightTopoRouteContainer;
import com.inspur.osp.ui.widgets.StartEndPointContainer;
import com.inspur.osp.ui.widgets.TopoRouteDetailContainer;
import com.inspur.osp.util.L;
import com.inspur.osp.util.ResourceUtil;
import com.inspur.osp.util.RxBus;
import com.inspur.osp.util.Utility;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import io.reactivex.disposables.Disposable;

//import com.facebook.stetho.Stetho;
//import com.amap.api.location.AMapLocation;
//import com.amap.api.location.AMapLocationClient;
//import com.amap.api.location.AMapLocationListener;
//import com.inspur.osp.util.LocationUtil;
//import butterknife.BindView;

/**
 * Created by liuchen_ on 2017/12/13.
 * <p>
 * isGetAuth =false 获取权限过滤掉没有的权限
 * RightResource isEnable默认为false
 */

public class TopoConnectActivity extends BaseActivity
        implements CsTopoMapConstact.View,
        LocationSource,
//        AMapLocationListener,
        AMap.OnPolylineClickListener,
        AMap.OnMarkerClickListener,
        View.OnClickListener {

    public static Context mStaticContext;

    private static final int CODE_REQUEST_RESOURCE_CONNECT = 1000;

    public static TopoConnectActivity mapActivity;

    CsTopoMapConstact.Presenter topoMapPresenter;

    /*
     *  控件相关
     * */
    // 抽屉菜单对象
    private ActionBarDrawerToggle drawerbar;

    DrawerLayout drawerLayout;

    //private TestFragment testFragment;


    LinearLayout leftTopoListLayout;

    RecyclerView topoRecyclewView;
    CommonAdapter<TopoBean> topoCommonAdapter;
    List<TopoBean> topoList = new ArrayList<>();

    List<NeRouteBean> neRouteBeanList = new ArrayList<>();

    boolean isSingleNeSearch = false;


    //定位
    CardView locationCard;

    CardView topoCardview;

    //图层
    CardView routerCardview;

    private LatLng currentPosition;

    /**
     * 高德地图 Api
     */
    AMap mMap;

    MapView mapView;

    private String topoid;
    private TopoRouteBean topoRouteBean;

    private TopoRouteBean.TopoInfoBean topoInfoBean;

    //projection
    Projection mProjection;

    private UiSettings mUiSettings;

    //权限集合
    private String[] permisstions = {
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE
    };

//    public AMapLocationClient mLocationClient = null;

    private OnLocationChangedListener mListener;

    private List<Marker> searchNamePointList = new ArrayList<>();
    private List<PolylineObj> searchNameLineList = new ArrayList<>();

    //搜索到的点
    private List<Marker> searchPointList = new ArrayList<>();
    //搜索到的线
    private List<PolylineObj> searchLineList = new ArrayList<>();

    private float zoomLevel;

    private boolean isGetAuth = false;
    private boolean isCameraChangeFinish = false;

    private List<ResourceUtil.RightResource> sitesList = null;
    private List<ResourceUtil.RightResource> deviceTypeList = null;
    private List<ResourceUtil.RightResource> bearerList = null;
    private List<ResourceUtil.RightResource> opticalList = null;
//    private List<ResourceUtil.RightResource> polygonList = null;


    List<LayerItemRequestbean> requestLayerItemList = new ArrayList<>();

    private double xMin, xMax, yMin, yMax;
    private AlertDialog selectResourceDialog;
    private AlertDialog nearbyResourceDialog;
    //    private RecyclerView resourceRecyclerView;
    private RelativeLayout selectContainer;


    private static final int lineWidth = 8;


    private String adCounty;

    private String[] layerIds = new String[]{"zhandian", "guangjiaojiexiang", "guangfenxianxiang", "guangzhongduanhe",
            "guandaoduan", "ganluduan", "zhimaiduan", "guaqiangduan", "guanglanduan", "allpolygon"};  //资源的layerId

    private HashMap<String, List> resourceMap = new HashMap<>();

    private int topoSearchType = 1;
    private String dataIds = "1,489847209";

    private String zNeId;
    private String aNeId;
    private String aPortId;
    private String zPortId;
    private RightTopoRouteContainer mRightTopoRouteContainer;

    private List<PolylineObj> polylineObjs = new ArrayList<>();
    private CableSegBean currentCableSegBean;
    private List<PolylineObj> currentCableLaySegs = new ArrayList<>();

    private TopoRouteDetailContainer topoRouteDetailContainer;

    Disposable mDisposable;

    Marker forkMarker = null;

    Marker startMarker = null;
    Marker endMarker = null;

    StartEndPointContainer startEndPointContainer;

    LatLng startLl = null;
    LatLng endLl = null;

    //点相关
//    private BottomSelectDialog slideFromBottomPopup;
//    private MapChooseBottomPopup mapChooseBottomPopup;

    /**
     * @param context
     * @param topoSearchType 1：根据拓扑连接id，多个逗号隔开
     *                       2：根据子网Id
     *                       3：根据两端网元id，多个逗号隔开 至少两个网元数据
     * @param data           具体的数据
     */
    public static void startAct(Context context, int topoSearchType, String data) {
        Intent mIntent = new Intent(context, TopoConnectActivity.class);
        mIntent.putExtra("topoSearchType", topoSearchType);
        mIntent.putExtra("data", data);
        context.startActivity(mIntent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mStaticContext = this;
//        Stetho.initializeWithDefaults(this);
        super.onCreate(savedInstanceState);
        mapActivity = this;
//        User user = a.blockingFirst();
//        String roleIds = user.roleIds;
//        String allRoleList = user.allFunctions;
    }

    @Override
    protected void getIntentData() {
        super.getIntentData();
        topoSearchType = getIntent().getIntExtra("topoSearchType", 1);
        dataIds = getIntent().getStringExtra("data");

        aNeId = getIntent().getStringExtra("aNeId");
        zNeId = getIntent().getStringExtra("zNeId");

        this.aPortId = getIntent().getStringExtra("aPortId");
        this.zPortId = getIntent().getStringExtra("zPortId");


    }

    @Override
    protected void afterSetView(@Nullable Bundle savedInstanceState) {
        super.afterSetView(savedInstanceState);
        mapView = findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
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
    protected int getLayoutId() {
        return R.layout.activity_topo_connect;
    }

    private boolean isKeyDown = false;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (backChooseOlpFragmet()) {
                return true;
            }
//            Timber.d("backStackEntryCount=" + backStackEntryCount);
//            if (mSearchResultContainer.getSheetState() == BottomSheetBehavior.STATE_EXPANDED) {
//                mSearchResultContainer.collapsed();
//                return true;
//            }
//            if (mSearchResultContainer.getSheetState() == BottomSheetBehavior.STATE_COLLAPSED) {
//                mSearchResultContainer.hide();
//                return true;
//            }
//            if (isKeyDown) {
//                finish();
////                System.exit(0);
//            } else {
//                if (drawerLayout.isDrawerOpen(coordRouterList)) {
//                    drawerLayout.closeDrawer(coordRouterList);
//                    return true;
//                }
            if (drawerLayout.isDrawerOpen(leftTopoListLayout)) {
                drawerLayout.closeDrawer(leftTopoListLayout);
                return true;
            }
            if (topoRouteDetailContainer.getSheetState() != BottomSheetBehavior.STATE_HIDDEN) {
                topoRouteDetailContainer.hide();
                return true;
            }
            if (mRightTopoRouteContainer.getSheetState() != BottomSheetBehavior.STATE_HIDDEN) {
                mRightTopoRouteContainer.hide();
                return true;
            }
            finish();
            //再按一次推出程序
//                isKeyDown = true;
//                ToastUtil.showToast(mContext, R.string.hint_exit);
//                Observable.timer(2, TimeUnit.SECONDS)
//                        .subscribe(aLong -> isKeyDown = false);
//            }
            //finish();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void back() {
        if (drawerLayout.isDrawerOpen(leftTopoListLayout)) {
            drawerLayout.closeDrawer(leftTopoListLayout);
            return;
        }
        if (topoRouteDetailContainer.getSheetState() != BottomSheetBehavior.STATE_HIDDEN) {
            topoRouteDetailContainer.hide();
            return;
        }
        if (mRightTopoRouteContainer.getSheetState() != BottomSheetBehavior.STATE_HIDDEN) {
            mRightTopoRouteContainer.hide();
            return;
        }
        finish();
    }

    @Override
    protected void initData() {

        for (int i = 0; i < layerIds.length; i++) {
            List list = new ArrayList();
            resourceMap.put(layerIds[i], list);

        }
        if (!TextUtils.isEmpty(this.aNeId) && !TextUtils.isEmpty(this.aPortId) && TextUtils.isEmpty(this.zNeId)) {

            this.topoMapPresenter.queryTopoNetPort(this.aNeId, this.aPortId);

        } else if (aNeId != null && zNeId != null) {
            topoMapPresenter.queryTopoListByNe(aNeId + "," + zNeId);
        } else if (aNeId != null) {
            isSingleNeSearch = true;
            topoMapPresenter.queryCheckedRouteByOnlyNe(aNeId);
        } else {
            if (topoSearchType == AppConfig.Constants.TOPO_LIST_IDS) {
                topoMapPresenter.queryTopoListByIds(dataIds);
            } else if (topoSearchType == AppConfig.Constants.TOPO_LIST_SUB_NET) {
                topoMapPresenter.queryTopoListBySubnet(dataIds);
            } else if (topoSearchType == AppConfig.Constants.TOPO_LIST_NE_IDS) {
                topoMapPresenter.queryTopoListByNe(dataIds);
            }
        }

//        mapPresenter.getUserAuth();
        //定位蓝点
//        locationStyle = new MyLocationStyle();
//        locationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_FOLLOW);//定位一次，且将视角移动到地图中心点。
//        locationStyle.showMyLocation(true);
//        mMap.setMyLocationStyle(locationStyle);
//        mMap.setMyLocationEnabled(false);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。


        drawerbar = new ActionBarDrawerToggle(this, drawerLayout, R.drawable.ic_launcher_background, R.string.app_name) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        drawerLayout.setDrawerListener(drawerbar);

    }


    // 右边菜单开关事件
    public void openRightLayout() {
//        if (drawerLayout.isDrawerOpen(coordRouterList)) {
//            drawerLayout.closeDrawer(coordRouterList);
//        } else {
//            drawerLayout.openDrawer(coordRouterList);
//        }
    }

    public void openLeftlayout() {
        if (drawerLayout.isDrawerOpen(leftTopoListLayout)) {
            drawerLayout.closeDrawer(leftTopoListLayout);
        } else {
            drawerLayout.openDrawer(leftTopoListLayout);
        }
    }


    @Override
    protected void initView() {
        super.initView();
        this.startEndPointContainer = new StartEndPointContainer(findViewById(R.id.route_start_end));
        this.startEndPointContainer.setiStartEndPointListener(new StartEndPointContainer.IStartEndPointListener() {
            public void dataChanged(List<TaskBean.ResultsBean> param1List) {
            }

            public void inputDistance(int param1Int) {
                TopoConnectActivity.this.showInputDistanceDialog(param1Int);
            }

            public void stateChanged(int param1Int) {
            }
        });

        drawerLayout = findViewById(R.id.main_drawer_layout);
        leftTopoListLayout = findViewById(R.id.main_left_drawer_layout);
        topoRecyclewView = findViewById(R.id.recyclew_topo);
        locationCard = findViewById(R.id.location);
        topoCardview = findViewById(R.id.card_topo);
        routerCardview = findViewById(R.id.card_router);
        setTitleMsg("拓扑路由");
//        setBackIcon();
        sitesList = ResourceUtil.getRight(mContext, ResourceUtil.TYPE_SITE);
        deviceTypeList = ResourceUtil.getRight(mContext, ResourceUtil.TYPE_DEVICE);
        bearerList = ResourceUtil.getRight(mContext, ResourceUtil.TYPE_BEARER);
        opticalList = ResourceUtil.getRight(mContext, ResourceUtil.TYPE_OPTICAl);
//        polygonList = ResourceUtil.getRight(mContext, ResourceUtil.TYPE_POLYGON);

        mDisposable = RxBus.getInstance().toObservable().subscribe(
                obj -> {
                    if ("router_refresh".equals(obj)) {
                        topoMapPresenter.queryTopoRouteBindByTopoId(topoid);//查询在用的
                    }
                }
        );
//        mSearchResultContainer = new SearchResultContainer(findViewById(R.id.bottom_sheet));
//        mSearchResultContainer.setmSheepHiddenLisenter(new SearchResultContainer.SheetHiddenLisnter() {
//            @Override
//            public void stateChanged(int newState) {
//                if (newState == BottomSheetBehavior.STATE_HIDDEN) {
//                    //隐藏
//                    isSearchDataByName = false;
//                    clearMapLayerData();
//                    clearMapNameLayerDate();
//                    muiltResQuery();
//                } else {
//
//                }
//            }
//
//            @Override
//            public void dataChanged(List<TaskBean.ResultsBean> beans) {
//                //重新加载数据到地图
//                isSearchedData = false;
//                clearMapNameLayerDate();
//                fillMapMuiltyResource(beans, searchNamePointList, searchNameLineList);
//                markerFitMap();
//            }
//        });
//        mSearchResultContainer.setSearchResultClickCallback(mSearchResultItemCallback);
        new TopoMapPresenter(mContext, this);

        mMap = mapView.getMap();
        //projection
        mProjection = mMap.getProjection();
        mUiSettings = mMap.getUiSettings();

        mMap.setLocationSource(this);
        mMap.setMyLocationType(AMap.LOCATION_TYPE_LOCATE);


        mUiSettings.setZoomControlsEnabled(false);// 通过手势缩放地图
        mUiSettings.setScrollGesturesEnabled(true);// 通过手势移动地图
        mUiSettings.setLogoPosition(AMapOptions.LOGO_POSITION_BOTTOM_CENTER);
        mUiSettings.setCompassEnabled(true);
        mUiSettings.setScaleControlsEnabled(true);//控制比
        mUiSettings.setTiltGesturesEnabled(true);//不可倾斜
        mUiSettings.setRotateGesturesEnabled(true);

        //UiSettings
//        mUiSettings = mMap.getUiSettings();//实例化UiSettings类对象

        //位置改变监听
        mMap.setOnMyLocationChangeListener(location -> {


        });


        mMap.setOnMarkerClickListener(this);
        mMap.setOnMapClickListener(this::mapLatlngClick);

        //地图状态发生变化的监听接口。
        mMap.setOnCameraChangeListener(new AMap.OnCameraChangeListener() {
            @Override
            public void onCameraChange(CameraPosition cameraPosition) {
                isCameraChangeFinish = false;
                //缩放级别
//                Timber.d("onCameraChange  " + cameraPosition.zoom);
//                Timber.d("onCameraChange 经度 %1$f 维度 %2$f  ", cameraPosition.target.longitude, cameraPosition.target.latitude);
            }

            @Override
            public void onCameraChangeFinish(CameraPosition cameraPosition) {

//                if (isFirstLocation)
//                    return;
                zoomLevel = cameraPosition.zoom;
                isCameraChangeFinish = true;
                //缩放级别

                LatLng leftTopLl = mProjection.fromScreenLocation(new Point(0, 0));
                LatLng rightBottomLl = mProjection.fromScreenLocation(
                        new Point(Utility.getScreenWidth(mContext), Utility.getScreenHeight(mContext)));


                //查询当前屏幕的
//                double xMinT = Math.min(leftTopLl.longitude, rightBottomLl.longitude);
//                double xMaxT = Math.max(leftTopLl.longitude, rightBottomLl.longitude);
//                double yMinT = Math.min(leftTopLl.latitude, rightBottomLl.latitude);
//                double yMaxT = Math.max(leftTopLl.latitude, rightBottomLl.latitude);

//                if (Math.abs(xMin - xMinT) < 0.01
//                        && Math.abs(xMax - xMaxT) < 0.01
//                        && Math.abs(yMin - yMinT) < 0.01
//                        && Math.abs(yMax - yMaxT) < 0.01
//                        ) {
//                    return;
//                }
                xMin = Math.min(leftTopLl.longitude, rightBottomLl.longitude);
                xMax = Math.max(leftTopLl.longitude, rightBottomLl.longitude);
                yMin = Math.min(leftTopLl.latitude, rightBottomLl.latitude);
                yMax = Math.max(leftTopLl.latitude, rightBottomLl.latitude);

//                muiltResQuery();

//                if ((isGetAuth && !isSearchDataByName) || (isGetAuth && isSearchedData)) {
//                    muiltResQuery();
//                }
            }
        });
        //地图加载完毕 监听
        mMap.setOnMapLoadedListener(() -> {
            toCurrentView(new LatLng(38.041954, 114.507828), null);
        });

        //UI Setting
//        location();
        initRightLayout();
        initLeftLayout();
//        nearByPopup = new NearByFunctionPopup(mContext);
    }

    private void selectRefResource() {

    }

    public void selectNearbyResource(LatLng centerPoint) {
        /*if (nearbyResourceDialog == null) {
            View nearbyResView = LayoutInflater.from(mContext).inflate(R.layout.dialog_nearby_select, null);
            nearbyResourceDialog = new AlertDialog.Builder(mContext)
                    .setView(nearbyResView)
                    .create();
            nearbyResourceDialog.getWindow().setContentView(nearbyResView);
            rl_nearby_site = (LinearLayout) nearbyResourceDialog.getWindow().findViewById(R.id.rl_res_site);
            rl_nearby_fiber_cab = (LinearLayout) nearbyResourceDialog.getWindow().findViewById(R.id.rl_res_fiber_cab);
            rl_nearby_fiber_dp = (LinearLayout) nearbyResourceDialog.getWindow().findViewById(R.id.rl_res_fiber_dp);
            rl_nearby_fiber_box = (LinearLayout) nearbyResourceDialog.getWindow().findViewById(R.id.rl_res_fiber_box);
            rl_nearby_segment = (LinearLayout) nearbyResourceDialog.getWindow().findViewById(R.id.rl_res_segment);
            rl_nearby_wire_seg = (LinearLayout) nearbyResourceDialog.getWindow().findViewById(R.id.rl_res_wire_seg);
        }

        rl_nearby_site.setOnClickListener(view -> {
            startNearbyResActivity(GeometryServerUtil.site_layerId, centerPoint);
        });
        rl_nearby_fiber_cab.setOnClickListener(view -> {
            startNearbyResActivity(GeometryServerUtil.gj_layerId, centerPoint);
        });
        rl_nearby_fiber_dp.setOnClickListener(view -> {
            startNearbyResActivity(GeometryServerUtil.gf_layerId, centerPoint);
        });
        rl_nearby_fiber_box.setOnClickListener(view -> {
            startNearbyResActivity(GeometryServerUtil.gz_layerId, centerPoint);
        });
        rl_nearby_segment.setOnClickListener(view -> {
            startNearbyResActivity(GeometryServerUtil.segment_layerId, centerPoint);
        });
        rl_nearby_wire_seg.setOnClickListener(view -> {
            startNearbyResActivity(GeometryServerUtil.gl_layerId, centerPoint);
        });

        nearbyResourceDialog.getWindow().setGravity(Gravity.LEFT);
        nearbyResourceDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        nearbyResourceDialog.show();*/  // modify by liyak 修改显示效果 由对话框变为Popupwindow
//        nearByPopup.setClickListener(new NearByFunctionPopup.OnItemClickListener() {
//            @Override
//            public void onSiteClick() {
//                startNearbyResActivity(GeometryServerUtil.site_layerId, centerPoint);
//            }
//
//            @Override
//            public void onFiberCabClick() {
//                startNearbyResActivity(GeometryServerUtil.gj_layerId, centerPoint);
//            }
//
//            @Override
//            public void onFiberDpClick() {
//                startNearbyResActivity(GeometryServerUtil.gf_layerId, centerPoint);
//            }
//
//            @Override
//            public void onFiberBoxClick() {
//                startNearbyResActivity(GeometryServerUtil.gz_layerId, centerPoint);
//            }
//
//            @Override
//            public void onSegmentClick() {
//                startNearbyResActivity(GeometryServerUtil.segment_layerId, centerPoint);
//            }
//
//            @Override
//            public void onWireClick() {
//                startNearbyResActivity(GeometryServerUtil.gl_layerId, centerPoint);
//            }
//        });
//        nearByPopup.showPopupWindow(cvNearby);
    }


    private void mapLatlngClick(LatLng clickLl) {
//        viewSign(clickLl);
//        List<Object> objectList =
//                Utility.MapUtil.isExsitOtherRes(mProjection, clickLl, searchPointList,
//                        null, searchLineList, null, null, mMap, 15);
//        summaryResource(objectList);
//        if (!mapOfLineItemClick(clickLl, searchLine) && searchLineList != null & searchLineList.size() > 0) {
//            for (PolylineObj polyline : searchLineList) {
//                if (mapOfLineItemClick(clickLl, polyline)) {
//                    return;
//                }
//            }
//        }
    }

    private boolean mapOfLineItemClick(LatLng clickLl, PolylineObj polyline) {
//        if (polyline != null) {
//            for (LatLng linePoint : polyline.getmPolyline().getPoints()) {
//                if (Math.abs(linePoint.latitude - clickLl.latitude) < 0.001
//                        && Math.abs(linePoint.longitude - clickLl.longitude) < 0.001) {
//                    onMapClick(null, polyline);
//                    return true;
//                }
//            }
//        }
        return false;
    }

    private void refreshTopoRouteBindList(String topoids) {
        topoMapPresenter.queryTopoRouteBindByTopoId(topoids);//查询可用的
    }

    private void initLeftLayout() {

        topoRecyclewView.setHasFixedSize(true);
        topoRecyclewView.setLayoutManager(new LinearLayoutManager(mContext));

        topoCommonAdapter = new CommonAdapter<TopoBean>(mContext, R.layout.item_topo_list, topoList) {
            @Override
            protected void convert(ViewHolder holder, TopoBean topoBean, int position) {
                holder.setText(R.id.tv_topo_name, topoBean.getZh_label());
            }
        };
        topoCommonAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {

                if (isSingleNeSearch) {
                    NeRouteBean neRouteBean = neRouteBeanList.get(position);
//                    mRightTopoRouteContainer.expandResult();
                    openLeftlayout();
                    drawAllRouter(neRouteBean);
                    return;
                }
                TopoBean topoBean = topoList.get(position);

                if (topoBean != null) {
                    topoid = topoBean.getIntId() + "";
//                    mRightTopoRouteContainer.expandResult();

                    topoMapPresenter.queryTopoRouteByTopoIds(topoBean.getIntId() + "", "1");//查询可用的
                    openLeftlayout();
//                    topoMapPresenter.queryTopoRouteBindByTopoId(topoBean.getINT_ID());//查询在用的
                }
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
        topoRecyclewView.setAdapter(topoCommonAdapter);
//        LeftTagAdapter stationAdapter = new LeftTagAdapter(sitesList, mContext);
//        stationFlowLeft.setAdapter(stationAdapter);
//        LeftTagAdapter deviceAdapter = new LeftTagAdapter(deviceTypeList, mContext);
//        deviceFlowLeft.setAdapter(deviceAdapter);
//        LeftTagAdapter bearerAdapter = new LeftTagAdapter(bearerList, mContext);
//        bearerFlowLeft.setAdapter(bearerAdapter);
//        LeftTagAdapter opticalAdapter = new LeftTagAdapter(opticalList, mContext);
//        opticalFlowLeft.setAdapter(opticalAdapter);
        //LeftTagAdapter polygonAdapter = new LeftTagAdapter(polygonList, mContext);
//        List<Map<String, String>> mapList = new ArrayList<>();
//        Map<String, String> polygon = new HashMap<>();
//        polygon.put("name", "业务区");
//        polygon.put("resType", "10100");
//        polygon.put("attr16", "0");
//        Map<String, String> weige = new HashMap<>();
//        weige.put("name", "微格");
//        weige.put("resType", "20003");
//        weige.put("attr16", "0");
//        Map<String, String> weige1 = new HashMap<>();
//        weige1.put("name", "点亮微格");
//        weige1.put("resType", "20003");
//        weige1.put("attr16", "1");
//        Map<String, String> weige2 = new HashMap<>();
//        weige2.put("name", "业务能力完整");
//        weige2.put("resType", "20003");
//        weige2.put("attr16", "2");
//        mapList.add(polygon);
//        mapList.add(weige);
//        mapList.add(weige1);
//        mapList.add(weige2);
//        PolygonAdapter polygonAdapter = new PolygonAdapter(mapList, mContext);
//        polygonFlowLeft.setAdapter(polygonAdapter);

    }

//    RightTagAdapter stationAdapter;
//    RightTagAdapter deviceAdapter;
//    RightTagAdapter bearerAdapter;
//    RightTagAdapter opticalAdapter;
//    RightTagAdapter polygonAdapter;
//
//
//    private String getSelectedStr(TagFlowLayout tagFlowLayout) {
//        Iterator<Integer> integers = tagFlowLayout.getSelectedList().iterator();
//        StringBuilder stringBuilder = new StringBuilder();
//        while (integers.hasNext()) {
//            stringBuilder.append(integers.next() + ",");
//        }
//        if (stringBuilder.length() > 0) {
//            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
//        }
//        Timber.d(stringBuilder.toString());
//        return stringBuilder.toString();
//    }


    private void drawAllRouter(NeRouteBean neRouteBean) {
        clearLineList(polylineObjs);

        if (isSingleNeSearch && neRouteBean != null && neRouteBean.getCables() != null) {

            for (NeRouteBean.CablesBean cablesBean : neRouteBean.getCables()) {
                String shape = cablesBean.getSHAPE();
                GeoJSONObject geoJSONObject = ResourceUtil.parseWkt(shape);
                if (geoJSONObject instanceof LineString) {
                    List<LatLng> latLngs = new ArrayList();
                    for (Position position : ((LineString) geoJSONObject).getPositions()) {
                        latLngs.add(ResourceUtil.coordConverter(mContext, new LatLng(position.getLatitude(), position.getLongitude())));
                    }
                    Polyline polyline = drawLine(latLngs, R.color.green_normal);
                    polylineObjs.add(new PolylineObj(cablesBean, polyline));
                }
            }
        }
        markerFitMap(polylineObjs);

        drawAzPoint();

    }

    private boolean backChooseOlpFragmet() {
        int backStackEntryCount = getSupportFragmentManager().getBackStackEntryCount();
        if (backStackEntryCount > 0) {
            getSupportFragmentManager().popBackStackImmediate();
            return true;
        } else {
            return false;
        }
    }

    private void initRightLayout() {

        topoRouteDetailContainer = new TopoRouteDetailContainer(findViewById(R.id.route_detail_sheet));

        topoRouteDetailContainer.setRouterBeanClickListener(routesBean -> {
            topoRouteDetailContainer.collapsed();
            topoMapPresenter.qeurySingleCableLaySeg("'" + routesBean.getId() + "'");
        });
        topoRouteDetailContainer.setmSheepHiddenLisenter(new TopoRouteDetailContainer.SheetHiddenLisnter() {
            @Override
            public void stateChanged(int newState) {
                if (BottomSheetBehavior.STATE_HIDDEN == newState) {
                    mRightTopoRouteContainer.expandResult();
                }
                backChooseOlpFragmet();
            }

            @Override
            public void dataChanged(List<TaskBean.ResultsBean> beans) {

            }

            @Override
            public void cableSegClickLisenter(CableSegBean cableSegBean) {

            }

            @Override
            public void pageChangedListener(int position) {

            }
        });

        mRightTopoRouteContainer = new RightTopoRouteContainer(findViewById(R.id.bottom_sheet));

        mRightTopoRouteContainer.setRouterBeanClickListener(routesBean -> {
            mRightTopoRouteContainer.collapsed();
//            topoMapPresenter.qeurySingleCableLaySeg("'" + routesBean.getId() + "'");
        });

        mRightTopoRouteContainer.setmSheepHiddenLisenter(new RightTopoRouteContainer.SheetHiddenLisnter() {
            @Override
            public void stateChanged(int newState) {

            }

            @Override
            public void dataChanged(List<TaskBean.ResultsBean> beans) {

            }

            @Override
            public void cableSegClickLisenter(CableSegBean cableSegBean) {
                //请求
                currentCableSegBean = cableSegBean;
                topoMapPresenter.qeurySingleCableLaySeg("'" + cableSegBean.getID() + "'");
                mRightTopoRouteContainer.collapsed();
            }

            @Override
            public void canUseJumperClickListener(TopoRouteBean.LinesBean linesBean) {
                mRightTopoRouteContainer.collapsed();
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        topoRouteDetailContainer.expandResult();
                    }
                }, 200);
                topoRouteDetailContainer.setTopoRouteBean(topoRouteBean, topoRouteBean.getLines().indexOf(linesBean));
            }

            @Override
            public void pageChangedListener(int position) {
                if (position == 1) {
                    //清空
                }
            }
        });
//        rightTitleTv.setText("路由列表");
//        fragments.add(RecommendRouteFragment.newInstance());
//        fragments.add(CanUseRouteFragment.newInstance());
//
//        titleList.add("推荐");
//        titleList.add("可用");
//
//        routerTabLayout.setupWithViewPager(rightViewPager);
//        rightViewPager.setCurrentItem(0);
//        rightViewPager.setOffscreenPageLimit(3);
//        rightViewPager.setAdapter(mPageAdapter);


//        stationAdapter = new RightTagAdapter(sitesList, mContext);
//        stationFlow.setAdapter(stationAdapter);
//        stationFlow.setOnTagClickListener((view, position, parent) -> {
//            PreferenceUtil.putString(mContext, ResourceUtil.MAP_LAYER_TYPE.ZHANDIAN, getSelectedStr(stationFlow));
//            return false;
//        });
//
//        deviceAdapter = new RightTagAdapter(deviceTypeList, mContext);
//        deviceFlow.setAdapter(deviceAdapter);
//        deviceFlow.setOnTagClickListener((view, position, parent) -> {
//            PreferenceUtil.putString(mContext, ResourceUtil.MAP_LAYER_TYPE.DEVICE, getSelectedStr(deviceFlow));
//            return false;
//        });
//
//        bearerAdapter = new RightTagAdapter(bearerList, mContext);
//        bearerFlow.setAdapter(bearerAdapter);
//        bearerFlow.setOnTagClickListener((view, position, parent) -> {
//            PreferenceUtil.putString(mContext, ResourceUtil.MAP_LAYER_TYPE.BEAR, getSelectedStr(bearerFlow));
//            return false;
//        });
//
//        opticalAdapter = new RightTagAdapter(opticalList, mContext);
//        opticalFlow.setAdapter(opticalAdapter);
//        opticalFlow.setOnTagClickListener((view, position, parent) -> {
//            PreferenceUtil.putString(mContext, ResourceUtil.MAP_LAYER_TYPE.OPTICAL, getSelectedStr(opticalFlow));
//            return false;
//        });
//
//        polygonAdapter = new RightTagAdapter(polygonList, mContext);
//        polygonFlow.setAdapter(polygonAdapter);
//        polygonFlow.setOnTagClickListener((view, position, parent) -> {
//            PreferenceUtil.putString(mContext, ResourceUtil.MAP_LAYER_TYPE.POLYGON, getSelectedStr(polygonFlow));
//            return false;
//        });

//        setLayerSelected(stationFlow, PreferenceUtil.getString(mContext, ResourceUtil.MAP_LAYER_TYPE.ZHANDIAN));
//        setLayerSelected(deviceFlow, PreferenceUtil.getString(mContext, ResourceUtil.MAP_LAYER_TYPE.DEVICE));
//        setLayerSelected(bearerFlow, PreferenceUtil.getString(mContext, ResourceUtil.MAP_LAYER_TYPE.BEAR));
//        setLayerSelected(opticalFlow, PreferenceUtil.getString(mContext, ResourceUtil.MAP_LAYER_TYPE.OPTICAL));
//        setLayerSelected(polygonFlow, PreferenceUtil.getString(mContext, ResourceUtil.MAP_LAYER_TYPE.POLYGON));

//        setLayerSelected(polygonFlow, PreferenceUtil.getString(mContext, ResourceUtil.MAP_LAYER_TYPE.POLYGON));

//        defaultSelected(stationFlow);
//        defaultSelected(deviceFlow);
//        defaultSelected(bearerFlow);
//        defaultSelected(opticalFlow);
        //defaultSelected(polygonFlow);

//        stationFlow.setAdapter(new TagAdapter<String>(sitesList) {
//            @Override
//            public View getView(FlowLayout parent, int position, String o) {
//
//                LinearLayout itemView = (LinearLayout) LayoutInflater.from(mContext)
//                        .inflate(R.layout.item_seleted, stationFlow, false);
//                TextView titleView = itemView.findViewById(R.id.title);
//                titleView.setText(o);
//                return itemView;
//            }
//

//            @Override
//            public void onSelected(int position, View view) {
//                super.onSelected(position, view);
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//                    view.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bg_ractangle_border_red));
//                } else {
//                    view.setBackgroundResource(R.drawable.bg_ractangle_border_red);
//                }
//                view.findViewById(R.id.checked).setVisibility(View.VISIBLE);
//
//            }
//
//            @Override
//            public void unSelected(int position, View view) {
//                super.unSelected(position, view);
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//                    view.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bg_check_default));
//                } else {
//                    view.setBackgroundResource(R.drawable.bg_check_default);
//                }
//                view.findViewById(R.id.checked).setVisibility(View.GONE);
//            }
//        });
    }

//    private void setLayerSelected(TagFlowLayout flowLayout, String flagStr) {
//        if (flagStr == null) {
//            if (flowLayout != polygonFlow) {
//                defaultSelected(flowLayout);
//            }
//        } else {
//            String[] checkedArray = flagStr.split(",");
//            Set<Integer> set = new HashSet();
//            for (int i = 0; i < checkedArray.length; i++) {
//                if (!TextUtils.isEmpty(checkedArray[i])) {
//
//                    set.add(Integer.parseInt(checkedArray[i]));
//                }
//            }
//            flowLayout.getAdapter().setSelectedList(set);
//        }
//    }

//    private void defaultSelected(TagFlowLayout flowLayout) {
//        int stationCount = flowLayout.getAdapter().getCount();
//        Set<Integer> set = new HashSet();
//        for (int i = 0; i < stationCount; i++) {
//            set.add(i);
//        }
//        flowLayout.getAdapter().setSelectedList(set);
//    }

//    private void toTargetResView(LatLng position, Object obj) {
//        if (position != null) {
//            CameraUpdate mCameraUpdate =
//                    CameraUpdateFactory.newCameraPosition(new CameraPosition(position,
//                            zoomLevel > 18 ? zoomLevel : 18, 0, 0));
//            mMap.animateCamera(mCameraUpdate, 1000, new AMap.CancelableCallback() {
//                @Override
//                public void onFinish() {
////                    if (isFirstLocation) {
////                        isFirstLocation = false;
////                    }
//                    Timber.d("移动完成");
//                    if (obj instanceof Marker) {
//                        jumpPoint((Marker) obj);
//                    }
//                    if (obj instanceof PolylineObj) {
//                        lineAnimate(((PolylineObj) obj).getmPolyline());
//                    }
//                }
//
//                @Override
//                public void onCancel() {
//                    Timber.d("取消移动");
//                }
//            });
//        }
//
//    }

//    private void markerFitMap() {
//        LatLngBounds.Builder builder = new LatLngBounds.Builder();
//        for (int i = 0; i < searchNamePointList.size(); i++) {
//            builder.include(searchNamePointList.get(i).getPosition());
//        }
//
//        for (int i = 0; i < searchNameLineList.size(); i++) {
//            List<LatLng> latLngs = searchNameLineList.get(i).getmPolyline().getPoints();
//            for (int j = 0; j < latLngs.size(); j++) {
//                builder.include(latLngs.get(j));
//            }
//        }
//        LatLngBounds latLngBounds = builder.build();
//        mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds, 20));
//    }

    private void toCurrentView(LatLng position, Object obj) {
        L.D("toCurrentView2");

        if (position != null) {
            CameraUpdate mCameraUpdate =
                    CameraUpdateFactory.newCameraPosition(new CameraPosition(position,
                            zoomLevel > 18 ? zoomLevel : 18, 0, 0));
            mMap.animateCamera(mCameraUpdate, 1000, new AMap.CancelableCallback() {
                @Override
                public void onFinish() {

                    if (obj instanceof Marker) {
                        jumpPoint((Marker) obj);
//                        if (obj != currentPositionMarker) {
//                            onMapClick((Marker) obj, null);
//                            viewSign(position);
//                        }
                    }
                    if (obj instanceof PolylineObj) {
                        lineAnimate(((PolylineObj) obj).getmPolyline());
//                        if (obj != currentPositionMarker) {
//                            onMapClick(null, (PolylineObj) obj);
////                            viewSign(position);
//                        }
                    }
//                    if (isGetAuth && isSearchedData) {
//                    muiltResQuery();
//                    }
                }

                @Override
                public void onCancel() {
                }
            });
        }

    }

    private void viewSign(LatLng position) {
//        locationNearbyPosition = position;
//        isLocationNearby = true;
//        if (viewMarker == null) {
//            viewMarker = mMap.addMarker(
//                    new MarkerOptions().icon(BitmapDescriptorFactory
//                            .defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
//                            .draggable(false)
//                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_location_sign))
//                            .position(position)
//            );
//            viewMarker.setAnchor(0.5f, 1f);
//        } else {
//            viewMarker.setPosition(position);
//        }
//        viewMarker.setToTop();
//        realTimeLatlng = position;

        showRealTime();
    }

    @Override
    protected void initListener() {
        super.initListener();
        mMap.setOnPolylineClickListener(this);

//        arrowDownIv.setOnClickListener(view -> {
//            showSearchType();
//        });
//        selectType.setOnClickListener(view -> {
//            showSearchType();
//        });
//        layerLayout.setOnClickListener(view -> {
//            openRightLayout();
//        });
//        moreButton.setOnClickListener(view -> {
//
//            //showMore dialog
//            showMoreFunc();
//
//        });
//        legendLayout.setOnClickListener(view -> {
//            openLeftlayout();
//        });
//        surveyTask.setOnClickListener(view -> {
//
//            Intent mIntent = new Intent(mContext, TaskQueryActivity.class);
//            //Intent mIntent = new Intent(mContext, TaskNewActivity.class);
//            startActivity(mIntent);
//        });
//
//        cvInspect.setOnClickListener(view -> {
//
//                    Flowable<User> a = usersRepository.userLocalDataSource.getLastestUser();
//                    User user = a.blockingFirst();
//                    Log.d("MapAcitivty", user.login + user.authCountyIds);
//                    Intent mIntent = new Intent(mContext, LXMainActivity.class);
//                    mIntent.putExtra("adCounty", adCounty);
//                    startActivity(mIntent);
//                }
//        );

        //附近资源查询
//        cvNearby.setOnClickListener(view -> {
//            LatLng centerPoint = null;
//            if (isLocationNearby && locationNearbyPosition != null) {
//                centerPoint = locationNearbyPosition;
//            } else {
//                centerPoint = currentPosition;
//            }
//            selectNearbyResource(centerPoint);
//
//
//        });
//
//        cvOrder.setOnClickListener(v -> {
//            Intent orderIntent = new Intent(mContext, WorkOrderListActivity.class);
//            orderIntent.putExtra("orderMajor", "8");
//            startActivity(orderIntent);
//        });
//
//        cvGuzhang.setOnClickListener(view -> {
//            Intent gIntent = new Intent(mContext, GuzhangActivity.class);
//            startActivity(gIntent);
//
//        });

        locationCard.setOnClickListener(view -> {
            toCurrentView(currentPosition, null);
        });
        routerCardview.setOnClickListener(view -> openRightLayout());
        topoCardview.setOnClickListener(view -> openLeftlayout());

//        this.searchEdt.setOnEditorActionListener((v, actionId, event) -> {
//            if (EditorInfo.IME_ACTION_SEARCH == actionId) {
//                hideSoftInputMethod();
//                searchEvent();
//                return true;
//            }
//            return false;
//        });
//
//        resetBtn.setOnClickListener(view -> {
//
//            defaultSelected(stationFlow);
//            defaultSelected(deviceFlow);
//            defaultSelected(bearerFlow);
//            defaultSelected(opticalFlow);
//        });
//        okBtn.setOnClickListener(view -> {
//            if (isSearchDataByName) {
//                return;
//            }
//            muiltResQuery();
//            openRightLayout();
//        });
//        //拓扑点击事件
//        llTopo.setOnClickListener(view -> {
//            //ToastUtil.showToast(MapActivity.this,"拓扑点击");
//            Intent intent = new Intent(this, TopoWebviewActivity.class);
//            intent.putExtra("type", "provincetopo");
//            startActivity(intent);
//        });
//        //逻辑拓扑点击事件
//        llLogictopo.setOnClickListener(view -> {
//            //Intent intent = new Intent(this, TopoWebviewActivity.class);
//            Intent intent = new Intent(this, LocalWebViewActivity.class);
//            intent.putExtra("type", "provincetopo");
//            startActivity(intent);
//        });
    }


    private void muiltResQuery() {
        requestLayerItemList.clear();
        requestLayerItemList.addAll(getQuerySql(ResourceUtil.TYPE_SITE));
        requestLayerItemList.addAll(getQuerySql(ResourceUtil.TYPE_DEVICE));
        requestLayerItemList.addAll(getQuerySql(ResourceUtil.TYPE_BEARER));
        requestLayerItemList.addAll(getQuerySql(ResourceUtil.TYPE_OPTICAl));
        requestLayerItemList.addAll(getQuerySql(ResourceUtil.TYPE_POLYGON));
        if (requestLayerItemList.size() == 0) {
            //将数据清空
            for (Marker marker : searchPointList) {
                marker.remove();
            }
            searchPointList.clear();

            for (PolylineObj polyline : searchLineList) {
                polyline.getmPolyline().remove();
            }
            searchLineList.clear();
            return;
        }

//        mapPresenter.muiltyResQuery(requestLayerItemList, xMin, xMax, yMin, yMax);
        requestSize = requestLayerItemList.size();
        /**
         * liyakun 优化加载速度，修改为同时多个请求加载资源
         */
        for (LayerItemRequestbean layerItemRequestbean : requestLayerItemList) {
            List<LayerItemRequestbean> newrequestLayerItemList = new ArrayList<>();
            newrequestLayerItemList.add(layerItemRequestbean);
            topoMapPresenter.muiltyResQuery(newrequestLayerItemList, xMin, xMax, yMin, yMax);
        }
        //setLoadingIndicator(true,"");
        sucessSize = 0;

    }

    public List<LayerItemRequestbean> getFilterSql(String type) {
        List<LayerItemRequestbean> resultList = new ArrayList<>();
        String layerId = type;
        String filter = "stateflag=0";
        resultList.add(new LayerItemRequestbean(layerId, filter));
        return resultList;
    }

    public List<LayerItemRequestbean> getQuerySql(int type) {
        List<LayerItemRequestbean> targetList = new ArrayList<>();
        String layoutId = "";
        String resType = "";
        switch (type) {
            case ResourceUtil.TYPE_SITE:

                StringBuilder siteSys = new StringBuilder();
                for (int i = 0; i < sitesList.size(); i++) {
                    ResourceUtil.RightResource site = sitesList.get(i);
                    layoutId = site.layId;
                    resType = site.resType;
                    //判断该级别在此缩放级别下面是否可见
                    if (!Utility.MapUtil.getLayerIdByZoomLevel(resType, site.systemLevel, zoomLevel)) {
                        continue;
                    }
                    if (i == 0) {
                        siteSys.append(" system_level = ");
                    } else {
                        siteSys.append(" or  system_level = ");
                    }
                    siteSys.append("'" + site.systemLevel + "'");
                }
                if (!TextUtils.isEmpty(layoutId) && !TextUtils.isEmpty(siteSys.toString())) {
                    targetList.add(new LayerItemRequestbean(layoutId, siteSys.toString()));
                }
                break;
            case ResourceUtil.TYPE_DEVICE:

                StringBuilder deviceBuilder = new StringBuilder();
                boolean isFirstInto = true;
                for (int integer = 0; integer < deviceTypeList.size(); integer++) {
                    ResourceUtil.RightResource device = deviceTypeList.get(integer);
                    layoutId = device.layId;
                    resType = device.resType;
                    int siteLevel = device.systemLevel;
                    if (!Utility.MapUtil.getLayerIdByZoomLevel(resType, siteLevel, zoomLevel)) {
                        continue;
                    }
                    if (integer < 4) {
                        //0 ,1,2,3
                        if (isFirstInto) {
                            isFirstInto = false;
                            deviceBuilder.append(" system_level = ");
                        } else {
                            deviceBuilder.append(" or  system_level = ");
                        }
                        deviceBuilder.append("'" + device.systemLevel + "'");
                    } else {
                        layoutId = device.layId;
                        targetList.add(new LayerItemRequestbean(layoutId, "1=1"));
                    }

                }
                break;
            case ResourceUtil.TYPE_BEARER:

                for (int m = 0; m < bearerList.size(); m++) {
                    ResourceUtil.RightResource bearer = bearerList.get(m);

                    resType = bearer.resType;
                    if (!Utility.MapUtil.getLayerIdByZoomLevel(resType, bearer.systemLevel, zoomLevel)) {
                        continue;
                    }
                    targetList.add(new LayerItemRequestbean(bearer.layId, "1=1"));
                }
                break;
            case ResourceUtil.TYPE_OPTICAl:

                StringBuilder opticalSb = new StringBuilder();

                for (int m = 0; m < opticalList.size(); m++) {
                    ResourceUtil.RightResource optical = opticalList.get(m);
                    resType = optical.resType;
                    if (!Utility.MapUtil.getLayerIdByZoomLevel(resType, optical.systemLevel, zoomLevel)) {
                        continue;
                    }
                    if (m == 0) {
                        opticalSb.append(" system_level = ");
                    } else {
                        opticalSb.append(" or system_level = ");
                    }
                    opticalSb.append("'" + optical.systemLevel + "'");
                    layoutId = optical.layId;
                }
                if (!TextUtils.isEmpty(layoutId) && !TextUtils.isEmpty(opticalSb.toString())) {
                    targetList.add(new LayerItemRequestbean(layoutId, opticalSb.toString()));
                }

                break;
        }
        return targetList;
    }
    /**
     * 搜搜事件
     */
//    private void searchEvent() {
//        Timber.d("search");
//
////        if(bottomBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
////            bottomBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
////        }else {
////            bottomBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
////        }
////        return;
//        String inputContent = searchEdt.getText().toString();
//
//        if (TextUtils.equals(key_default, ResourceUtil.searchTypeKeyArray[ResourceUtil.searchTypeKeyArray.length - 1])) {
//            //查找 目的地
//            doSearchQuery(inputContent);
//        } else {
//            mapPresenter.singleResQuery(key_default, inputContent);
//            mSearchResultContainer.clearData();
//        }
//    }


    /**
     * 开始进行poi搜索
     */
//    private void doSearchQuery(String keyWord) {
//
//        showProgressLoading(mContext.getString(R.string.common_searching));
//        currentPage = 0;
//        query = new PoiSearch.Query(keyWord, "", "河北");
//        // 第一个参数表示搜索字符串，第二个参数表示poi搜索类型，第三个参数表示poi搜索区域（空字符串代表全国）
//        query.setPageSize(10);// 设置每页最多返回多少条poiitem
//        query.setPageNum(currentPage);// 设置查第一页
//        query.setCityLimit(true);
//
//        poiSearch = new PoiSearch(this, query);
//        poiSearch.setOnPoiSearchListener(this);
//        poiSearch.searchPOIAsyn();
//
//
//    }

//    private void showMoreFunc() {
//        hideSoftInputMethod();
//
//        if (moreFunctionPopup == null) {
//            moreFunctionPopup = new MoreFunctionPopup(mContext);
//        }
//        moreFunctionPopup.setOnListPopupItemClickListener((func) -> {
//            searchEdt.requestFocus();
//            moreFunctionPopup.dismiss();
//            switch (func) {
//                case street:
//                    if (streetMarker == null) {
//                        streetMarker = mMap.addMarker(
//                                new MarkerOptions()
//                                        .icon(BitmapDescriptorFactory
//                                                .fromResource(R.drawable.ic_street_view))
//                                        .title("点击查看街景")
//                        );
//                        streetMarker.setPositionByPixels(Utility.getScreenWidth(mContext) / 2, Utility.getScreenHeight(mContext) / 2);
//                        streetMarker.setVisible(false);
//                        streetMarker.setAnchor(0.5f, 0.5f);
//                        streetMarker.setObject("street");
//                    }
//                    if (streetMarker.isVisible()) {
//                        streetMarker.setVisible(false);
//                    } else {
//                        streetMarker.setVisible(true);
//                    }
//                    break;
//                case traffic:
//                    isTraffic = !isTraffic;
//                    mMap.setTrafficEnabled(isTraffic);//显示实时路况图层，aMap是地图控制器对象。
//                    break;
//                case satelite:
//                    isSatellite = !isSatellite;
//                    if (isSatellite) {
//                        mMap.setMapType(AMap.MAP_TYPE_SATELLITE);
//                    } else {
//                        mMap.setMapType(AMap.MAP_TYPE_NORMAL);
//                    }
//                    break;
//                case cd_about:
//                    Intent intent = new Intent(this, AboutActivity.class);
//                    startActivity(intent);
//                    break;
//                case cdmajor:
//                    Intent funcintent = new Intent(this, FunctionSelectActivity.class);
//                    funcintent.putExtra("flag", "mainpage");
//                    startActivity(funcintent);
//                    finish();
//                    break;
//            }
//        });
//        moreFunctionPopup.changeButtonStatus(streetMarker != null && streetMarker.isVisible(),
//                isSatellite, isTraffic);
//        moreFunctionPopup.showPopupWindow(moreButton);
//        moreFunctionPopup.setDismissWhenTouchOutside(true);
//
//    }

//    private void showSearchType() {
//        hideSoftInputMethod();
//        if (searchTypePopup == null) {
//            searchTypePopup = new SearchTypePopup(mContext);
//        }
//        searchTypePopup.setOnListPopupItemClickListener((what, text, key) -> {
//            Timber.d(key);
//            selectType.setText(text);
//            key_default = key;
//            searchEdt.requestFocus();
//            searchTypePopup.dismiss();
//
//        });
//
//        searchTypePopup.showPopupWindow(arrowDownIv);
//        searchTypePopup.setDismissWhenTouchOutside(true);
//
//    }
    @Override
    protected void onResume() {
        super.onResume();
        if (mapView != null)
            mapView.onResume();
        L.D("onResume");
        location();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mapView != null)
            mapView.onPause();
//        deactivate();
    }

    @Override
    protected void onStop() {
        super.onStop();
        deactivate();
//        if (mapView != null)
//            mapView.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mapView != null)
            mapView.onDestroy();
        //退出界面的时候停止定位
//        if (mLocationClient != null) {
//            mLocationClient.stopLocation();
//        }

        if (topoMapPresenter != null) {
            topoMapPresenter.desctroyView();
        }
        if (mDisposable != null)
            mDisposable.dispose();
        mContext = null;
    }


    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {
        L.D("active");
        location();
    }

    @Override
    public void deactivate() {
        L.D("deactivate");
//        if (mLocationClient != null) {
//            mLocationClient.stopLocation();
//            mLocationClient.onDestroy();
//        }
//        mLocationClient = null;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        if (requestCode == 100) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                location();
            } else {
                // Permission Denied
                Toast.makeText(mContext, "请允许定位和文件权限，否则无法使用", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void location() {
        if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            //米有申请通过
            Log.d("1111", "no permisstion");

            ActivityCompat.requestPermissions(this, permisstions, 100);
        } else {
            Log.d("1111", "yes permisstion");

            //location
//            mLocationClient = new AMapLocationClient(mContext);
//            LocationUtil.getInstance().setListenerOption(mLocationClient, this);
//            if (mLocationClient != null)
//                mLocationClient.startLocation();
        }
    }

//    @Override
//    public void onLocationChanged(AMapLocation amapLocation) {
//        if (amapLocation != null && amapLocation.getErrorCode() == 0) {
//            L.D(amapLocation.toStr());
//
////            if (viewMarker == null) {
////                realTimeLatlng = new LatLng(amapLocation.getLatitude(), amapLocation.getLongitude());
////                showRealTime();
////            }
////            Timber.d(amapLocation.toStr());
//            currentPosition = new LatLng(amapLocation.getLatitude(), amapLocation.getLongitude());
////            addMarkersToMap(amapLocation,
////                    currentPosition
////                    , amapLocation.getAddress()
////
////            );
////            adCounty = amapLocation.getAdCode();
////            //Toast.makeText(mContext,"首页city="+adCounty,Toast.LENGTH_SHORT).show();
////            if (isFirstLocation) {
////                toCurrentView(currentPosition, currentPositionMarker);
////
////            }
//        } else {
//        }
//
//
////        if (mListener != null && amapLocation != null) {
////            if (amapLocation != null
////                    && amapLocation.getErrorCode() == 0) {
////                mListener.onLocationChanged(amapLocation);// 显示系统小蓝点
////            } else {
////                String errText = "定位失败," + amapLocation.getErrorCode()+ ": " + amapLocation.getErrorInfo();
////                Log.e("AmapErr",errText);
////            }
////        }
//    }


//    private void addMarkerToMapByPosition() {
//        if (currentPositionMarker == null) {
//            currentPositionMarker = mMap.addMarker(
//                    new MarkerOptions().icon(BitmapDescriptorFactory
//                            .defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
//                            .position(searchPosition)
//            );
//        } else {
//            currentPositionMarker.setPosition(searchPosition);
//        }
//        toCurrentView(searchPosition);
////        List<LatLng> latLngList = new ArrayList<>();
////        if (resultsBean.getGeoJson() != null
////                && resultsBean.getGeoJson().getCoordinates() != null
////                && resultsBean.getGeoJson().getCoordinates().size() > 0
////                ) {
////
//////            double lat
////            for (int i = 0; i < resultsBean.getGeoJson().getCoordinates().size(); i++) {
////                resultsBean.getGeoJson().getCoordinates().get(i);
////                resultsBean.getGeoJson().getCoordinates().get(i);
////
////
////            }
////            if (resultsBean.getGeoJson().getCoordinates().size() > 2) {
////                //划线
////            } else {
////                //画点
////
////
////            }
////
////        }
//
////
////        if (currentPositionMarker == null) {
////            currentPositionMarker = mMap.addMarker(
////                    new MarkerOptions().icon(BitmapDescriptorFactory
////                            .defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
////                            .position(searchPosition)
////            );
////        } else {
////            currentPositionMarker.setPosition(searchPosition);
////        }
//    }

//    private void addMarkersToMap(AMapLocation amapLocation, LatLng latLng, String address) {
//        if (currentPositionMarker == null) {
//            currentPositionMarker = mMap.addMarker(
//                    new MarkerOptions().icon(BitmapDescriptorFactory
//                            .defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
////                            .title(address)
//                            .draggable(false)
//                            .position(latLng)
//
//            );
//            currentPositionMarker.setAnchor(0.5f, 0.5f);
//        } else {
//            currentPositionMarker.setPosition(latLng);
//        }
//        currentPositionMarker.hideInfoWindow();
//        currentPositionMarker.setObject(amapLocation);
//    }

    @Override
    public void setPresenter(CsTopoMapConstact.Presenter presenter) {
        topoMapPresenter = presenter;
    }

    @Override
    public void showHint(String message) {
//        ToastUtil.showToast(mContext, message);
        showSnackMessage(message);

    }

    private void showRealTime() {
//        if (realTimeLatlng != null) {
//
//            Double[] newlatlng = TransCoords.GCJ02ToWGS84(realTimeLatlng.longitude, realTimeLatlng.latitude);
//
//            lngPosition.setText(mContext.getResources().getString(R.string.position_longitude, newlatlng[0]));
//            latPosition.setText(mContext.getResources().getString(R.string.position_latitude, newlatlng[1]));
//        }
    }

    @Override
    public LatLng getCurrentPosition() {
//        return currentPosition;
        return null;
    }

//    @Override
//    public void fillMap(List<TaskBean.ResultsBean> resultsBeanList) {
//        dismissProgressLoading();

//        if (  linePositionSign!=null){
//            linePositionSign.setVisible(false);
//        }

    //1 计算距离，按照距离由远及近的顺序排列
    //2 分页，查看更多
    //3 进入到地图范模糊搜索功能，禁用图层加载功能
    // bottomSheet
//        for (TaskBean.ResultsBean resultsBean : resultsBeanList) {
//            if (resultsBean.getmGeoJsonObject() instanceof com.cocoahero.android.geojson.Point) {
//                //画点
//                com.cocoahero.android.geojson.Point point = (com.cocoahero.android.geojson.Point) resultsBean.getmGeoJsonObject();
//                int resId = ResourceUtil.getResourceKeySystemLevel(resultsBean.getLayerId(), Utility.getResType(resultsBean),
//                        resultsBean.getAttributes().getSYSTEM_LEVEL());
//                if (resId == 0)
//                    resId = R.drawable.location_marker;
//                LatLng searchPoint = new LatLng(point.getPosition().getLatitude(), point.getPosition().getLongitude());
//                double distance = AMapUtils.calculateLineDistance(currentPosition, searchPoint);
//                resultsBean.getAttributes().setDISTANCE(MathUtil.getDistanceDesc(distance));
//                resultsBean.getAttributes().setMETER(MathUtil.getFromatDistance(distance));
//
////                searchPointList.add(getMarker(resultsBean,
////                        new LatLng(point.getPosition().getLatitude(), point.getPosition().getLongitude()),
////                        resultsBean.getAttributes().getNAME(),
////                        resId));
//            } else if (resultsBean.getmGeoJsonObject() instanceof LineString) {
//                LineString lineString =
//                        (LineString) resultsBean.getmGeoJsonObject();
//
//                List<LatLng> latLngs = new ArrayList<LatLng>();
//
//                if (lineString != null && lineString.getPositions() != null) {
//                    for (int i = 0; i < lineString.getPositions().size(); i++) {
//                        Position position = lineString.getPositions().get(i);
//                        if (position != null) {
//                            LatLng latLng = new LatLng(position.getLatitude(), position.getLongitude());
//                            latLngs.add(latLng);
//                        }
//                    }
//                }
//                double distance = GeometryServerUtil.getCloseToLine(currentPosition, latLngs);
//                resultsBean.getAttributes().setDISTANCE(MathUtil.getDistanceDesc(distance));
//                resultsBean.getAttributes().setMETER(MathUtil.getFromatDistance(distance));
////                int color = ResourceUtil.getResourceKeySystemLevel(resultsBean.getLayerId(), Utility.getResType(resultsBean),
////                        resultsBean.getAttributes().getSYSTEM_LEVEL());
////                if(color == 0)
////                    color = R.color.cable_one;
////                searchLineList.add(new PolylineObj(resultsBean, getPolyLine(resultsBean, latLngs, color)));
//            }
//        }
    //按照距离由近到远排序
//        Collections.sort(resultsBeanList, (o1, o2) -> {
//            double meter1 = o1.getAttributes().getMETER();
//            double meter2 = o2.getAttributes().getMETER();
//            if (meter1 > meter2) {
//                return 1;
//            } else {
//                return -1;
//            }
//        });
//        this.targetSearchList = resultsBeanList;
//        bindSearchResultDialog();
//        dismissProgressLoading();
//        if (searchResultPopup == null) {
//            searchResultPopup = new SearchResultPopup.Builder(mContext)
//                    .addItems(resultsBeanList)
//                    .build();
//            searchResultPopup.setDismissWhenTouchOutside(true);
//        }
//        searchResultPopup.notifyData(resultsBeanList);
//        searchResultPopup.showPopupWindow(searchEdt);
//
//        searchResultPopup.setOnListPopupItemClickListener(resultsBean -> {
//            //绘制到图上
//            searchResultPopup.dismiss();
//            mSearchResultBean = resultsBean;
//            searchResultShow(mSearchResultBean, false);
//        });
//    }

//    private List<TaskBean.ResultsBean> getDataByPage() {
//        if (searchPageIndex > searchPageNum) {
//            return null;
//        } else {
//            int from = (searchPageIndex - 1) * searchPageSize;
//            int end = searchPageIndex == searchPageNum ? this.targetSearchList.size() : searchPageIndex * searchPageSize;
//            return targetSearchList.subList(from, end);
//        }
//    }

    public void bindSearchResultDialog() {
//        if (mSearchResultDialog != null) {
//        } else {
//            mSearchResultDialog = new SearchResultDialog.Builder(mContext).show();
//        }
//        searchPageIndex = 1;
//        searchPageNum = targetSearchList.size() / searchPageSize;
//        int mod = targetSearchList.size() % searchPageSize;
//        if (mod > 0) {
//            searchPageNum++;
//        }
//        //清除数据
//        isSearchDataByName = true;
//        clearMapLayerData();
//        clearMapNameLayerDate();
//        List<TaskBean.ResultsBean> tempData = getDataByPage();
////        mSearchResultDialog.addData(tempData);
//        mSearchResultContainer.addData(tempData);
//        mSearchResultContainer.expandResult();
    }

//    public void searchResultShow(TaskBean.ResultsBean resultsBean, boolean isJustMove) {
//        if (resultsBean.getmGeoJsonObject() != null) {
//            if (resultsBean.getmGeoJsonObject() instanceof com.cocoahero.android.geojson.Point) {
//                Timber.d("point");
//                //画点
//                com.cocoahero.android.geojson.Point point = (com.cocoahero.android.geojson.Point) resultsBean.getmGeoJsonObject();
//
//                searchPosition = new LatLng(point.getPosition().getLatitude(), point.getPosition().getLongitude());
//
//                String resType = Utility.getResType(resultsBean);
//                int resId = ResourceUtil.getResourceKeySystemLevel(resultsBean.getLayerId(), resType,
//                        resultsBean.getAttributes().getSYSTEM_LEVEL());
//                if (resId == 0)
//                    resId = R.drawable.location_marker;
//                addMarkersSearch(resultsBean, searchPosition, resultsBean.getAttributes().getNAME(), resId);
//                if (isJustMove) {
//                    toTargetResView(searchPosition, locationMarker);
//                } else {
//                    toCurrentView(searchPosition, locationMarker);
//                }
////                    addMarkersToMap(searchPosition);
//            } else if (resultsBean.getmGeoJsonObject() instanceof LineString) {
//                LineString lineString =
//                        (LineString) resultsBean.getmGeoJsonObject();
//
//                List<LatLng> latLngs = new ArrayList<LatLng>();
//
//                if (lineString != null && lineString.getPositions() != null) {
//                    for (int i = 0; i < lineString.getPositions().size(); i++) {
//                        Position position = lineString.getPositions().get(i);
//                        if (position != null) {
//                            LatLng latLng = new LatLng(position.getLatitude(), position.getLongitude());
//                            latLngs.add(latLng);
//                        }
//                    }
//                }
//
//                int color = ResourceUtil.getResourceKeySystemLevel(resultsBean.getLayerId(), Utility.getResType(resultsBean),
//                        resultsBean.getAttributes().getSYSTEM_LEVEL());
//
//                if (searchLine != null) {
//                    searchLine.getmPolyline().remove();
//                    searchLine = null;
//                }
////                if (searchLine == null) {
//                if (color == 0) {
//                    color = R.color.cable_one;
//                }
//
//                Polyline polyline = mMap.addPolyline(new PolylineOptions()
//                        .addAll(latLngs).width(lineWidth)
//                        .zIndex(20)
//                        .color(getResources().getColor(color))
//                );
//
//                searchLine = new PolylineObj(resultsBean, polyline);
////                }
//                searchLine.getmPolyline().setPoints(latLngs);
//
//                if (isJustMove) {
//                    toTargetResView(latLngs.get(0), searchLine);
//                } else {
//                    toCurrentView(latLngs.get(0), searchLine);
//                }
//                Timber.d("not point");
//            }
//        }
//    }

//    @Override
//    public void accurateQueryResourcePosition(TaskBean taskBean) {
//
//        if (taskBean.getResults().size() == 1) {
//            TaskBean.ResultsBean resultsBean = taskBean.getResults().get(0);
//            searchResultShow(resultsBean, false);
////            slideFromBottomPopup.applyData(resultsBean, currentPosition);
//        } else {
//            //搜索到多个
//            Timber.d("搜索到多个");
//        }
//
//    }

    private TaskBean alltaskBean = null;
    private int requestSize = 0;
    int sucessSize = 0;

    @Override
    public void packageData(TaskBean taskBean, String layerId) {
        Log.v("request 返回", (sucessSize) + "-------" + layerId);
        if (null == alltaskBean) {
            if (null == taskBean) {
                alltaskBean = new TaskBean();
            } else {
                alltaskBean = taskBean;
            }
        } else {
            if (null != taskBean && null != taskBean.getResults()) {
                if (null == alltaskBean.getResults()) {
                    alltaskBean.setResults(new ArrayList<>());
                    alltaskBean.getResults().addAll(taskBean.getResults());
                } else {
                    alltaskBean.getResults().addAll(taskBean.getResults());
                }

            }

        }
        sucessSize++;
        if (sucessSize == requestSize) {
            sucessSize = 0;
            muiltyFillMap(alltaskBean);
            setLoadingIndicator(false, "");
        }
    }

    @Override
    public void fillTopoList(List<TopoBean> topoBeanList) {
        if (topoBeanList.size() == 0) {
            showHint("暂无拓扑");
            return;
        }
        topoList.addAll(topoBeanList);
        topoCommonAdapter.notifyDataSetChanged();
        openLeftlayout();


    }

    @Override
    public void fillTopoRouteList(TopoRouteBean paramTopoRouteBean) {

        if (paramTopoRouteBean == null)
            return;
        this.topoMapPresenter.queryTopoRouteBindByTopoId(this.topoid);
        this.topoRouteBean = paramTopoRouteBean;
        if (paramTopoRouteBean != null) {
            TopoRouteBean.TopoInfoBean topoInfoBean = paramTopoRouteBean.getTopoInfo();
        } else {
            paramTopoRouteBean = null;
        }
        this.topoInfoBean = paramTopoRouteBean.getTopoInfo();

        //绘制推荐路由
        //绘制可用路由
        //绘制在用路由
    }

    @Override
    public void fillTopoRouteBindList(List<TopoCheckedRouteBean> paramList) {

        if (paramList.size() == 0) {
            showHint("暂无绑定路由");
            return;
        }
        if (paramList != null && paramList.size() > 0) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < paramList.size(); i++) {
                TopoCheckedRouteBean topoCheckedRouteBean = paramList.get(i);
                stringBuilder.append("'" + topoCheckedRouteBean.getID() + "'");
                stringBuilder.append(",");
            }
            if (stringBuilder.length() != 0) {
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                this.topoMapPresenter.queryAllCableLaySeg(stringBuilder.toString());
                return;
            }
        }
    }


    public void clearLineList(List<PolylineObj> polylineObjs) {
        for (PolylineObj polyline : polylineObjs) {
            polyline.getmPolyline().remove();
        }
        polylineObjs.clear();
    }

    @Override
    public void fillSingleCableLaySeg(List<LaySegBean> laySegBeans) {

        clearLineList(currentCableLaySegs);
        //绘制所有的线
        if (laySegBeans != null && laySegBeans.size() > 0) {
            for (LaySegBean laySegBean : laySegBeans) {
                Polyline polyline = drawLine(laySegBean.getLatLngs(), R.color.red);
                currentCableLaySegs.add(new PolylineObj(laySegBean, polyline));

//                GeoJSONObject geoJSONObject =
//                        ResourceUtil.parseWkt(laySegBean.getSHAPE());
//                if (geoJSONObject instanceof LineString) {
//                    List<LatLng> latLngs = new ArrayList();
//                    for (Position position : ((LineString) geoJSONObject).getPositions()) {
//                        latLngs.add(ResourceUtil.coordConverter(mContext, new LatLng(position.getLatitude(), position.getLongitude())));
//                    }
//                    Polyline polyline = drawLine(latLngs, R.color.red);
//                    currentCableLaySegs.add(new PolylineObj(laySegBean, polyline));
//                }
            }
            markerFitMap(currentCableLaySegs);
        }
    }

    private void findNextCable(List<LaySegBean> laySegBeans, String id, String name, List<LaySegBean> newList) {
        LaySegBean targetBean = null;
        for (LaySegBean laySegBean : laySegBeans) {
//            List<LatLng> latLngs = laySegBean.getLatLngs();
            if (
                    TextUtils.equals(laySegBean.getA_point(), id)
                            && !TextUtils.equals(laySegBean.getName(), name)

            ) {
                //找到A站点
                targetBean = laySegBean;
                newList.add(targetBean);
                laySegBeans.remove(targetBean);
                findNextCable(laySegBeans, targetBean.getZ_point(), targetBean.getName(), newList);
                break;
            } else if (TextUtils.equals(laySegBean.getZ_point(), id)
                    && !TextUtils.equals(laySegBean.getName(), name)
            ) {
                targetBean = laySegBean;
                newList.add(targetBean);
                laySegBeans.remove(targetBean);
                findNextCable(laySegBeans, targetBean.getA_point(), targetBean.getName(), newList);
                break;
            }
        }
    }

    @Override
    public void fillAllCableLaySeg(List<LaySegBean> laySegBeans) {

        polylineObjs.clear();
        //绘制所有的线
        if (laySegBeans != null && laySegBeans.size() > 0) {
//
//            LatLng startLl = laySegBeans.get(0).getLatLngList().get(0);
//            L.D("startLl:" + startLl.toString());
//            List<LatLng> latLngs1 = laySegBeans.get(laySegBeans.size() - 1).getLatLngList();
//            LatLng endLL = latLngs1.get(latLngs1.size() - 1);
//            L.D("endLLl:" + endLL.toString());


//            for (LaySegBean laySegBean : laySegBeans) {
//                List<LatLng> latLngs = laySegBean.getLatLngs();
//                if (
//                        TextUtils.equals(topoInfoBean.getOrigRoomId(), laySegBean.getA_point()) ||
//                                TextUtils.equals(topoInfoBean.getOrigRoomId(), laySegBean.getZ_point())
//                ) {
//                    //找到A站点
//                    startLl = latLngs.get(0);
//                    break;
//                }
//
//            }
//            for (LaySegBean laySegBean : laySegBeans) {
//                List<LatLng> latLngs = laySegBean.getLatLngs();
//                Polyline polyline = drawLine(latLngs, R.color.green_normal);
//                polylineObjs.add(new PolylineObj(laySegBean, polyline));
//                if (
//                        TextUtils.equals(topoInfoBean.getOrigRoomId(), laySegBean.getA_point()) ||
//                                TextUtils.equals(topoInfoBean.getOrigRoomId(), laySegBean.getZ_point())
//                ) {
//                    //找到A站点
//                    startLl = latLngs.get(0);
//                }
//                if (
//                        TextUtils.equals(laySegBean.getZ_point(), laySegBean.getZ_point()) ||
//                                TextUtils.equals(laySegBean.getZ_point(), laySegBean.getA_point())
//                ) {
//                    //找到A站点
//                    endLl = latLngs.get(0);
//                }
//            }

            List<LaySegBean> newLsb = new ArrayList<>();
            findNextCable(laySegBeans, topoInfoBean.getOrigSiteId(), "", newLsb);

            for (LaySegBean laySegBean : newLsb) {
                List<LatLng> latLngs = laySegBean.getLatLngs();
                Polyline polyline = drawLine(latLngs, R.color.green_normal);
                polylineObjs.add(new PolylineObj(laySegBean, polyline));
            }

            markerFitMap(polylineObjs);
        }
        drawAzPoint();
    }

    private void drawAzPoint() {

//        if (startLl == null) {
//            if (startMarker != null) {
//                startMarker.remove();
//                startMarker = null;
//            }
//        } else {
//            if (startMarker != null) {
//                startMarker.setPosition(startLl);
//            } else {
//                startMarker = drawMarker(startLl, R.drawable.ic_point_start);
//            }
//            startMarker.setObject("start");
//        }
//
//        if (endLl == null) {
//            if (endMarker != null) {
//                endMarker.remove();
//                endMarker = null;
//            }
//        } else {
//            if (endMarker != null) {
//                endMarker.setPosition(endLl);
//            } else {
//                endMarker = drawMarker(endLl, R.drawable.ic_point_start);
//            }
//            endMarker.setObject("end");
//        }

        if (polylineObjs.size() > 0) {
            PolylineObj startLine = polylineObjs.get(0);
            LatLng startLl = startLine.getmPolyline().getPoints().get(0);

//            L.D("startll:" + startLl.toString());

            if (startMarker != null) {
                startMarker.setPosition(startLl);
            } else {
                startMarker = drawMarker(startLl, R.drawable.ic_point_start);
            }
            startMarker.setObject("start");

            PolylineObj endLine = polylineObjs.get(polylineObjs.size() - 1);
            List<LatLng> latLngs = endLine.getmPolyline().getPoints();

            LatLng endll = latLngs.get(latLngs.size() - 1);
            L.D("endll:" + endll.toString());

            if (endMarker != null) {
                endMarker.setPosition(endll);
            } else {
                endMarker = drawMarker(endll, R.drawable.ic_point_end);
            }
            endMarker.setObject("end");
        }


    }

    @Override
    public void fillSingleNeRoute(List<NeRouteBean> neRouteBeans) {

        if (neRouteBeans == null || neRouteBeans.size() == 0) {
            showHint("暂无绑定路由");
            return;
        }
        neRouteBeanList = neRouteBeans;
        for (NeRouteBean neRouteItem : neRouteBeans) {
            TopoBean topoBean = new TopoBean();
            topoBean.setZh_label(neRouteItem.getTopoName());
            topoList.add(topoBean);
        }
        topoCommonAdapter.notifyDataSetChanged();
    }

    @Override
    public void fillTopoNePortId(TopoNePortBean paramTopoNePortBean) {

        if (TextUtils.isEmpty(paramTopoNePortBean.getANeId()) || TextUtils.isEmpty(paramTopoNePortBean.getZNeid())) {
            showHint("根据一端网元和端口查询数据为空");
            return;
        }
        this.topoMapPresenter.queryTopoListByNe(paramTopoNePortBean.getANeId() + "," + paramTopoNePortBean.getZNeid());
    }


    private Marker drawMarker(LatLng position, int icon) {
//        icon = R.drawable.fork;
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), icon);
//        BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromResource(icon);
        L.D("hegith=" + bitmap.getHeight());
        L.D("Width=" + bitmap.getWidth());
        MarkerOptions markerOptions = new MarkerOptions()
//                        .anchor(bitmap.getHeight() / 2, bitmap.getWidth() / 2)
                .icon(BitmapDescriptorFactory.fromBitmap(bitmap))
//                        .anchor(-24, -24)
                .position(position);

        if (icon == R.drawable.ic_fork) {
            markerOptions.anchor(0.5f, 0.5f);
        }
        Marker marker = mMap.addMarker(markerOptions);
        return marker;
    }

    @Override
    public AMap getAmap() {
        return mMap;
    }

    private Polyline drawLine(List<LatLng> latLngList, int color) {
        Polyline polyline = mMap.addPolyline(new PolylineOptions()
                .addAll(latLngList).width(20)
                .zIndex(30)
                .color(getResources().getColor(color)));
        return polyline;
    }

    private void markerFitMap(List<PolylineObj> polylineObjList) {
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
//        for (int i = 0; i < allMarkerList.size(); i++) {
//            builder.include(allMarkerList.get(i).getPosition());
//        }

        for (int i = 0; i < polylineObjList.size(); i++) {
            List<LatLng> latLngs = polylineObjList.get(i).getmPolyline().getPoints();
            for (int j = 0; j < latLngs.size(); j++) {
                builder.include(latLngs.get(j));
            }
        }
        LatLngBounds latLngBounds = builder.build();
        mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds, 20));
    }


    private void clearMapNameLayerDate() {

        for (Marker marker : searchNamePointList) {
            marker.remove();
        }
        searchNamePointList.clear();
        for (PolylineObj polyline : searchNameLineList) {
            polyline.getmPolyline().remove();
        }
        searchNameLineList.clear();
    }

    private void clearMapLayerData() {
        for (Marker marker : searchPointList) {
            marker.remove();
        }
        searchPointList.clear();

        for (PolylineObj polyline : searchLineList) {
            polyline.getmPolyline().remove();
        }
        searchLineList.clear();

//        for (PolygonObj polygon : searchpolygonList) {
//            polygon.getmPolyline().remove();
//        }
//        searchpolygonList.clear();
//

    }

    public void fillMapMuiltyResource(List<TaskBean.ResultsBean> resultsBeanList, List<Marker> pointList, List<PolylineObj> polyLineList) {
        clearMapLayerData();
        if (resultsBeanList == null || resultsBeanList.size() == 0) {
            return;
        }
        for (TaskBean.ResultsBean resultsBean : resultsBeanList) {
//            if (locationMarker != null && locationMarker.getObject() instanceof TaskBean.ResultsBean) {
//                if (TextUtils.equals(resultsBean.getAttributes().getUUID(),
//                        ((TaskBean.ResultsBean) locationMarker.getObject()).getAttributes().getUUID())
//                        ) {
//                    continue;
//                }
//            }
//            if (searchLine != null && searchLine.getDataObj() instanceof TaskBean.ResultsBean) {
//                if (TextUtils.equals(resultsBean.getAttributes().getUUID(),
//                        ((TaskBean.ResultsBean) searchLine.getDataObj()).getAttributes().getUUID())
//                        ) {
////                    searchLineList.add(searchLine);
//                    continue;
//                }
//            }
            Boolean isContains = false;
            if (resultsBean.getmGeoJsonObject() instanceof com.cocoahero.android.geojson.Point) {
                //画点
                com.cocoahero.android.geojson.Point point = (com.cocoahero.android.geojson.Point) resultsBean.getmGeoJsonObject();
                int resId = ResourceUtil.getResourceKeySystemLevel(resultsBean.getLayerId(), Utility.getResType(resultsBean),
                        resultsBean.getAttributes().getSYSTEM_LEVEL());
                if (resId == 0)
                    resId = R.drawable.location_marker;

                for (Marker marker : pointList) {
                    TaskBean.ResultsBean bean = (TaskBean.ResultsBean) marker.getObject();
                    if (bean.getAttributes().getUUID().equals(resultsBean.getAttributes().getUUID())) {
                        isContains = true;
                    }
                }
                if (!isContains) {
                    pointList.add(getMarker(resultsBean,
                            new LatLng(point.getPosition().getLatitude(), point.getPosition().getLongitude()),
                            resultsBean.getAttributes().getNAME(),
                            resId));
                }
                /*searchPointList.add(getMarker(resultsBean,
                        new LatLng(point.getPosition().getLatitude(), point.getPosition().getLongitude()),
                        resultsBean.getAttributes().getNAME(),
                        resId));*/
            } else if (resultsBean.getmGeoJsonObject() instanceof LineString) {
                LineString lineString =
                        (LineString) resultsBean.getmGeoJsonObject();
                List<LatLng> latLngs = new ArrayList<LatLng>();
                if (lineString != null && lineString.getPositions() != null) {
                    for (int i = 0; i < lineString.getPositions().size(); i++) {
                        Position position = lineString.getPositions().get(i);
                        if (position != null) {
                            LatLng latLng = new LatLng(position.getLatitude(), position.getLongitude());
                            latLngs.add(latLng);
                        }
                    }
                }
                int color = ResourceUtil.getResourceKeySystemLevel(resultsBean.getLayerId(), Utility.getResType(resultsBean),
                        resultsBean.getAttributes().getSYSTEM_LEVEL());
                //searchLineList.add(new PolylineObj(resultsBean, getPolyLine(resultsBean, latLngs, color)));
                for (PolylineObj polylineObj : polyLineList) {
                    TaskBean.ResultsBean bean = (TaskBean.ResultsBean) polylineObj.getDataObj();
                    if (bean.getAttributes().getUUID().equals(resultsBean.getAttributes().getUUID())) {
                        isContains = true;
                    }
                }
                if (!isContains) {
                    polyLineList.add(new PolylineObj(resultsBean, getPolyLine(resultsBean, latLngs, color)));
                }
            } else if (resultsBean.getmGeoJsonObject() instanceof Polygon) {
                Polygon polygon = (Polygon) resultsBean.getmGeoJsonObject();
                List<Ring> ringList = polygon.getRings();
                Ring ring = ringList.get(0);
                List<LatLng> latLngs = new ArrayList<LatLng>();
                if (ring != null && ring.getPositions() != null) {
                    for (int i = 0; i < ring.getPositions().size(); i++) {
                        Position position = ring.getPositions().get(i);
                        if (position != null) {
                            LatLng latLng = new LatLng(position.getLatitude(), position.getLongitude());
                            latLngs.add(latLng);
                        }
                    }
                }
                //searchpolygonList.add(new PolygonObj(resultsBean,getPolygon(resultsBean,latLngs)));
//                for (PolygonObj polygonObj : searchpolygonList) {
//                    TaskBean.ResultsBean bean = (TaskBean.ResultsBean) polygonObj.getDataObj();
//                    if (bean.getAttributes().getUUID().equals(resultsBean.getAttributes().getUUID())) {
//                        isContains = true;
//                    }
//                }
                if (resultsBean.getAttributes().getRES_TYPE().equals("20003")) {
                    Log.v("MapActivity", "加载微格" + isContains);
                }

//                if (!isContains) {
//                    searchpolygonList.add(new PolygonObj(resultsBean, getPolygon(resultsBean, latLngs)));
//                }
            }
        }

    }

    @Override
    public void muiltyFillMap(TaskBean taskBean) {
        //画多个点
        fillMapMuiltyResource(taskBean.getResults(), searchPointList, searchLineList);
        alltaskBean = null;
    }

//    @Override
//    public void filterUserAuth(List<String> authList) {
//        Timber.d(authList.toString());
//        Flowable.fromIterable(sitesList)
//                .doOnNext(site -> {
//                    if (authList.contains(site.layName)) {
//                        site.setEnable(true);
//                    } else {
//                        Timber.d("site false");
//                        site.setEnable(false);
//                    }
//                })
//                .toList()
//                .toFlowable()
//                .flatMap(list -> Flowable.fromIterable(deviceTypeList))
//                .doOnNext(device -> {
//                    Timber.d("device");
//                    if (authList.contains(device.layName)) {
//                        device.setEnable(true);
//                    } else {
//                        device.setEnable(false);
//                    }
//                })
//                .toList()
//                .toFlowable()
//                .flatMap(list -> Flowable.fromIterable(bearerList))
//                .doOnNext(bearer -> {
//                    Timber.d("bearer");
//                    if (authList.contains(bearer.layName)) {
//                        bearer.setEnable(true);
//                    } else {
//                        bearer.setEnable(false);
//                    }
//                })
//                .toList()
//                .toFlowable().flatMap(list -> Flowable.fromIterable(opticalList))
//                .doOnNext(optical -> {
//                    Timber.d("optical");
//                    if (authList.contains(optical.layName)) {
//                        optical.setEnable(true);
//                    } else {
//                        optical.setEnable(false);
//                    }
//                })
//                .toList()
//                .toFlowable().flatMap(list -> Flowable.fromIterable(polygonList))
//                .doOnNext(polygon -> {
//                    Timber.d("poly");
//                    if (authList.contains(polygon.layName)) {
//                        polygon.setEnable(true);
//                    } else {
//                        polygon.setEnable(false);
//                    }
//                })
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .toList()
//                .subscribe(rightResource -> {
//                    stationFlow.getAdapter().notifyDataChanged();
//                    deviceFlow.getAdapter().notifyDataChanged();
//                    bearerFlow.getAdapter().notifyDataChanged();
//                    opticalFlow.getAdapter().notifyDataChanged();
//                    polygonFlow.getAdapter().notifyDataChanged();
//                    isGetAuth = true;
//                    if (isCameraChangeFinish && !isSearchDataByName) {
//                        muiltResQuery();
//                    }
//                });
//
//    }

//    @Override
//    public List<Object> getNearByResourceList() {
//        return getCurrentPosResource();
//    }

//    @Override
//    public void fillCells(AllMajorResourceItem allMajorResourceItem) {
//
//    }

//    @Override
//    public void muiltyFillCellMap(AllMajorResourceItem allMajorResourceItem) {
//
//    }

//    public LatLng getCurrentPos() {
//        if (currentPositionMarker != null) {
//            return currentPositionMarker.getPosition();
//        } else if (viewMarker != null) {
//            return viewMarker.getPosition();
//        }
//        return null;
//    }


    public String getCurrentCounty() {
        if (adCounty != null) {
            return adCounty;
        }
        return null;
    }

    private Marker getMarker(Object resultsBean, LatLng searchPosition, String searchName, int resId) {
        Marker marker = mMap.addMarker(
                new MarkerOptions()
                        .position(searchPosition)
//                            .zIndex(20)
//                        .title(searchName)
                        .draggable(false)
                        .icon(BitmapDescriptorFactory.fromResource(resId))

        );
        marker.setAnchor(0.5f, 0.5f);
        marker.hideInfoWindow();
        marker.setObject(resultsBean);
        return marker;
    }

    private Polyline getPolyLine(Object resultsBean, List<LatLng> latLngs, int color) {

        return mMap.addPolyline(new PolylineOptions()
                .addAll(latLngs).width(lineWidth)
                .zIndex(20)
                .color(getResources().getColor(color)));
    }


    private com.amap.api.maps.model.Polygon getPolygon(TaskBean.ResultsBean resultsBean, List<LatLng> latLngs) {
        if (resultsBean.getAttributes().getRES_TYPE().equals("10100")) {
            return mMap.addPolygon(new PolygonOptions().addAll(latLngs).fillColor(Color.argb(150, 178, 223, 238)).strokeColor(Color.argb(180, 178, 223, 238)).zIndex(10));
        } else if (resultsBean.getAttributes().getRES_TYPE().equals("20003")) {
            if (resultsBean.getAttributes().getATTR16().equals("0")) {
                return mMap.addPolygon(new PolygonOptions().addAll(latLngs).fillColor(Color.argb(170, 198, 113, 113)).strokeColor(Color.argb(200, 198, 113, 113)).zIndex(15));
            } else if (resultsBean.getAttributes().getATTR16().equals("1")) {
                return mMap.addPolygon(new PolygonOptions().addAll(latLngs).fillColor(Color.argb(170, 255, 255, 0)).strokeColor(Color.argb(255, 255, 255, 0)).zIndex(15));
            } else if (resultsBean.getAttributes().getATTR16().equals("2")) {
                return mMap.addPolygon(new PolygonOptions().addAll(latLngs).fillColor(Color.argb(170, 198, 180, 150)).strokeColor(Color.argb(200, 198, 180, 150)).zIndex(15));
            } else {
                return mMap.addPolygon(new PolygonOptions().addAll(latLngs).fillColor(Color.argb(170, 198, 113, 113)).strokeColor(Color.argb(200, 198, 113, 113)).zIndex(15));
            }
        } else {
            return null;
        }
    }

//    private void addMarkersSearch(Object resultsBean, LatLng searchPosition, String searchName, int resId) {
//        if (locationMarker == null) {
//            locationMarker = mMap.addMarker(
//                    new MarkerOptions()
//                            .position(searchPosition)
////                            .zIndex(20)
//                            .title(searchName)
//                            .icon(BitmapDescriptorFactory.fromResource(resId))
//
//            );
//            locationMarker.setAnchor(0.5f, 0.5f);
//        } else {
//            locationMarker.setIcon(BitmapDescriptorFactory.fromResource(resId));
//            locationMarker.setTitle(searchName);
//            locationMarker.setPosition(searchPosition);
//        }
//        locationMarker.setObject(resultsBean);
//    }

//    @Override
//    public void setPresenter(MapConstact.Presenter presenter) {
//        mapPresenter = presenter;
//    }

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

    private LatLng calculateTarget(int leaveLength, LatLng firstLl, LatLng lastLl) {
        float rate = leaveLength / AMapUtils.calculateLineDistance(firstLl, lastLl);
        double newlng = (lastLl.longitude - firstLl.longitude) * rate + firstLl.longitude;
        double newlat = (lastLl.latitude - firstLl.latitude) * rate + firstLl.latitude;
        return new LatLng(newlat, newlng);
    }

    private void caculateDiatance(boolean isStart, int distance) {

        LatLng forkPosition = null;
        if (polylineObjs != null && polylineObjs.size() > 0) {
            int polySize = polylineObjs.size();
            int sumLen = 0;
            if (isStart) {
                for (int i = 0; i < polySize; i++) {
//                    int j = i + 1;
                    PolylineObj polylineObj = polylineObjs.get(i);
                    List<LatLng> latLngs = polylineObj.getmPolyline().getPoints();

                    sumLen += AMapUtils.calculateLineDistance(latLngs.get(0), latLngs.get(1));
                    if (sumLen > distance) {
//                        sumLen-distance;
                        forkPosition = calculateTarget(sumLen - distance, latLngs.get(0), latLngs.get(1));
//                        forkPosition = getCenterLl(latLngs.get(0), latLngs.get(1));
                    }
//                    polylineObj.getmPolyline().setColor(getResources().getColor(R.color.red));
                    if (forkPosition != null) {
                        break;
                    }
//                    if (polySize > j) {
//                        PolylineObj nextPolylineObj = polylineObjs.get(j);
//                        List<LatLng> nextLatlngList = nextPolylineObj.getmPolyline().getPoints();
//                        LatLng firstLl = nextLatlngList.get(0);
//                        sumLen += AMapUtils.calculateLineDistance(latLngs.get(1), firstLl);
//                        if (sumLen > distance) {
//                            forkPosition = getCenterLl(latLngs.get(0), latLngs.get(1));
//                        }
//                        if (forkPosition != null) {
//                            break;
//                        }
//                    }
                }
            } else {
                for (int i = polySize - 1; i > 0; i--) {
                    int j = i - 1;
                    PolylineObj polylineObj = polylineObjs.get(i);
                    List<LatLng> latLngs = polylineObj.getmPolyline().getPoints();
                    sumLen += AMapUtils.calculateLineDistance(latLngs.get(1), latLngs.get(0));
//                    polylineObj.getmPolyline().setColor(getResources().getColor(R.color.red));

                    if (sumLen > distance) {
//                        forkPosition = getCenterLl(latLngs.get(1), latLngs.get(0));

                        forkPosition = calculateTarget(sumLen - distance, latLngs.get(1), latLngs.get(0));

                        break;
                    }
//                    if (forkPosition != null) {
//                        break;
//                    }
//                    if (j > -1) {
//                        PolylineObj nextPolylineObj = polylineObjs.get(j);
//                        List<LatLng> nextLatlngList = nextPolylineObj.getmPolyline().getPoints();
//                        LatLng firstLl = nextLatlngList.get(1);
//                        sumLen += AMapUtils.calculateLineDistance(latLngs.get(0), firstLl);
//                        if (sumLen > distance) {
//                            forkPosition = getCenterLl(latLngs.get(1), latLngs.get(0));
//                        }
//                        if (forkPosition != null) {
//                            break;
//                        }
//                    }
                }
            }
        }

        if (forkPosition == null) {
            showHint("长度大于路由长度");
            return;
        }
        L.D("forkPosition" + forkPosition.toString());
        if (forkMarker != null) {
            forkMarker.setPosition(forkPosition);
            return;
        }
        forkMarker = drawMarker(forkPosition, R.drawable.ic_fork);
    }

    private LatLng getCenterLl(LatLng latLng1, LatLng latLng2) {
        double centerLat = (latLng1.latitude + latLng2.latitude) / 2;
        double centerLng = (latLng1.longitude + latLng2.longitude) / 2;
        return new LatLng(centerLat, centerLng);
    }

    private void showInputDistanceDialog(int flag) {
        EditText distanceEt = new EditText(mContext);
        distanceEt.setInputType(InputType.TYPE_CLASS_NUMBER);
        ;
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setView(distanceEt);
        builder.setTitle("请输入故障距离（米）");
        builder.setPositiveButton("确定", (dialog, which) -> {
            String number = distanceEt.getText().toString();
            try {
                int distance = Integer.parseInt(number);
                L.D("distance" + distance);
                caculateDiatance(flag == 0 ? true : false, distance);
            } catch (Exception e) {
                showHint("请输入正确的数字格式");
                e.printStackTrace();
            }

        });
        builder.setNegativeButton("取消", null);
        builder.create().show();
    }

    @Override
    public boolean onMarkerClick(Marker paramMarker) {

        Object object = paramMarker.getObject();
        if (object != null) {
            if ("start".equals(object)) {
                this.startEndPointContainer.expandResult();
                this.startEndPointContainer.refreshInfo(0, this.topoInfoBean);
                return true;
            }
        } else {
            return true;
        }
        if ("end".equals(object)) {
            this.startEndPointContainer.expandResult();
            this.startEndPointContainer.refreshInfo(1, this.topoInfoBean);
            return true;
        }
        return true;
//        onMapClick(marker, null);

//        if (currentPositionMarker != null && marker.getId().equals(currentPositionMarker.getId())) {
//            //点击了自己所在位置的点
//            onMapClick(marker, null);
//            return true;
//        } else if (streetMarker != null && marker.getId().equals(streetMarker.getId())) {
//            onMapClick(marker, null);
//            return true;
//        }
//        viewSign(marker.getPosition());
//        List<Object> objectList =
//                Utility.MapUtil.isExsitOtherRes(mProjection, marker.getPosition(),
//                        searchPointList, null, searchLineList, null, null,
//                        mMap, 30);
//        summaryResource(objectList);
//        return true;
    }


//    private void summaryResource(List<Object> objectList) {
//        resourceSummaryList.clear();
//        BaseApplication.lastObj.clear();
//        if (objectList.size() > 1) {
//            for (int i = 0, objecSize = objectList.size();
//                 i < objecSize; i++) {
//                Object object = objectList.get(i);
//                TaskBean.ResultsBean resultsBean = Utility.MapUtil.getResultBeanByObject(object);
//
//                boolean isSafe = false;
//                if (resultsBean == null)
//                    break;
//                for (int j = 0, summarySize = resourceSummaryList.size();
//                     j < summarySize;
//                     j++
//                        ) {
//                    ResourceSummary summary = resourceSummaryList.get(j);
//                    if (summary.getResType().equals(Utility.getResType(resultsBean))) {
//                        isSafe = true;
//                        summary.getObjectList().add(object);
//                        break;
//                    }
//                }
//                if (!isSafe) {
//                    List<Object> newObjList = new ArrayList<>();
//                    newObjList.add(object);
//                    ResourceUtil.Resource resource = ResourceUtil.resourceBeanMap.get(
//                            Utility.getResType(resultsBean)
//                    );
//
//                    String layerName = "";
//                    if (Utility.getResType(resultsBean).equals("20003")) {
//                        layerName = "微格";
//                    } else if (Utility.getResType(resultsBean).equals("10100")) {
//                        layerName = "业务区";
//                    } else {
//                        layerName = (resource == null ? "" : resource.type);
//                    }
//
//                    resourceSummaryList.add(new ResourceSummary(resultsBean.getLayerId(), Utility.getResType(resultsBean),
//                            layerName,
//                            newObjList));
//                }
//            }
////            resourceObjList.clear();
////            resourceObjList.addAll(objectList);
//            selectRefResource();
//        } else if (objectList.size() == 1) {
//            Object obj = objectList.get(0);
//            if (obj instanceof Marker) {
////                onMapClick((Marker) obj, null);
//            } else if (obj instanceof PolylineObj) {
//                lineAnimate(((PolylineObj) obj).getmPolyline());
////                onMapClick(null, (PolylineObj) obj);
//            }
//        }
//    }

//    private void openBottomDialog(TaskBean.ResultsBean targetObject) {
//        slideFromBottomPopup = new BottomSelectDialog.Builder(mContext)
//                .setItemSelectedListener(new BottomSelectDialog.IResOperateListener() {
//                    @Override
//                    public void guide(double lat, double lng) {
//                        List<Utility.MapUtil.MapPackge> pageList = Utility.MapUtil.getAvailableMap(mContext);
//                        if (pageList != null && pageList.size() > 0) {
//                            if (mapChooseBottomPopup == null) {
//                                mapChooseBottomPopup = new MapChooseBottomPopup(mContext, pageList);
//                            }
//                            mapChooseBottomPopup.setLng(lng);
//                            mapChooseBottomPopup.setLat(lat);
//                            mapChooseBottomPopup.showPopupWindow();
//                        } else {
//                            showSnackMessage(mContext.getString(R.string.error_map_no));
//                        }
//                    }
//
//                    @Override
//                    public void resourceConnect(String uuid, String name, String type) {
//                        Intent connectIntent = new Intent(mContext, ResourceConnectActivity.class);
//                        connectIntent.putExtra("uuid", uuid);
//                        connectIntent.putExtra("name", name);
//                        connectIntent.putExtra("type", type);
//                        startActivityForResult(connectIntent, CODE_REQUEST_RESOURCE_CONNECT);
//                    }
//                }).show();
//        slideFromBottomPopup.applyData(targetObject, currentPosition);
//    }


//    private void onMapClick(Marker marker, PolylineObj polylineObj) {
//
//        //if (slideFromBottomPopup == null) {
//        slideFromBottomPopup = new BottomSelectDialog.Builder(mContext)
//                .setIsFromConnect(true)
//                .setItemSelectedListener(new BottomSelectDialog.IResOperateListener() {
//                    @Override
//                    public void guide(double lat, double lng) {
//                        List<Utility.MapUtil.MapPackge> pageList = Utility.MapUtil.getAvailableMap(mContext);
//                        if (pageList != null && pageList.size() > 0) {
//                            if (mapChooseBottomPopup == null) {
//                                mapChooseBottomPopup = new MapChooseBottomPopup(mContext, pageList);
//                            }
//                            mapChooseBottomPopup.setLng(lng);
//                            mapChooseBottomPopup.setLat(lat);
//                            mapChooseBottomPopup.showPopupWindow();
//                        } else {
//                            showSnackMessage(mContext.getString(R.string.error_map_no));
//                        }
//                    }
//
//                    @Override
//                    public void resourceConnect(String uuid, String name, String type) {
//                        Intent connectIntent = new Intent(mContext, ResourceConnectActivity.class);
//                        connectIntent.putExtra("uuid", uuid);
//                        connectIntent.putExtra("name", name);
//                        connectIntent.putExtra("type", type);
//                        startActivityForResult(connectIntent, CODE_REQUEST_RESOURCE_CONNECT);
//                    }
//                }).show();
////        slideFromBottomPopup.
//        //}
//        Object targetObject = null;
//        if (marker != null) {
//            targetObject = marker.getObject();
//
//            //跳动的效果
//            jumpPoint(marker);
//
//        } else if (polylineObj != null) {
//            targetObject = polylineObj.getDataObj();
//
////            jumpLine(polylineObj.getmPolyline());
//
//
////            polylineObj.getmPolyline().setWidth();
//
//        }
//        if (targetObject == null) {
//            return;
//        }
//        if (targetObject instanceof TaskBean.ResultsBean) {
//            slideFromBottomPopup.applyData(targetObject, currentPosition);
//        } else if (targetObject instanceof AMapLocation) {
//            slideFromBottomPopup.applyData(targetObject, currentPosition);
//        }
//    }

//    @Override
//    public void onPoiSearched(PoiResult result, int rCode) {
//        dismissProgressLoading();
//        if (rCode == AMapException.CODE_AMAP_SUCCESS) {
//            if (result != null && result.getQuery() != null) {// 搜索poi的结果
//                if (result.getQuery().equals(query)) {// 是否是同一条
//                    poiResult = result;
//                    // 取得搜索到的poiitems有多少页
//                    List<PoiItem> poiItems = poiResult.getPois();// 取得第一页的poiitem数据，页数从数字0开始
//                    List<SuggestionCity> suggestionCities = poiResult
//                            .getSearchSuggestionCitys();// 当搜索不到poiitem数据时，会返回含有搜索关键字的城市信息
//
//                    if (poiItems != null && poiItems.size() > 0) {
//
//                        showPlaceNameChoose(poiItems);
////                        aMap.clear();// 清理之前的图标
////                        PoiOverlay poiOverlay = new PoiOverlay(aMap, poiItems);
////                        poiOverlay.removeFromMap();
////                        poiOverlay.addToMap();
////                        poiOverlay.zoomToSpan();
////                    } else if (suggestionCities != null
////                            && suggestionCities.size() > 0) {
//////                        showSuggestCity(suggestionCities);
////                        showSnackMessage(
////                                getResources().getString(R.string.error_data_empty)
////                        );
//                    } else {
//                        showSnackMessage(
//                                getResources().getString(R.string.error_data_empty)
//                        );
//                    }
//                }
//            } else {
//                showSnackMessage(
//                        getResources().getString(R.string.error_data_empty)
//                );
//            }
//        }
//    }

    public void jumpLine(Polyline polyline) {
        final Handler handler = new Handler();
        final long start = SystemClock.uptimeMillis();
//        polyline.setZIndex(80);

        List<LatLng> oldllList = polyline.getPoints();
        List<LatLng> startllList = new ArrayList<>();
        for (LatLng ll : oldllList) {
            Point startPoint = mProjection.toScreenLocation(ll);
            startPoint.offset(0, -100);
            LatLng startLatLng = mProjection.fromScreenLocation(startPoint);
            startllList.add(startLatLng);
        }
        final long duration = 800;
        final Interpolator interpolator = new BounceInterpolator();
        handler.post(new Runnable() {
            @Override
            public void run() {
                long elapsed = SystemClock.uptimeMillis() - start;
                float t = interpolator.getInterpolation((float) elapsed
                        / duration);
                List<LatLng> newLlList = new ArrayList<>();
                for (int i = 0; i < oldllList.size(); i++) {
                    LatLng oldLl = oldllList.get(i);
                    LatLng startLl = startllList.get(i);
                    double lng = t * oldLl.longitude + (1 - t)
                            * startLl.longitude;
                    double lat = t * oldLl.latitude + (1 - t)
                            * startLl.latitude;
                    newLlList.add(new LatLng(lat, lng));

                }
                polyline.setPoints(newLlList);
//                polyline.getOptions().do
//                polyline.getOptions().setPoints(newLlList);
                if (t < 1.0) {
                    handler.postDelayed(this, 16);
                }
            }
        });
    }

    public void jumpPoint(final Marker marker) {

//        if (linePositionSign != null) {
//            linePositionSign.setVisible(false);
//        }
        marker.setToTop();
        final LatLng oldLl = marker.getPosition();
        final Handler handler = new Handler();
        final long start = SystemClock.uptimeMillis();

        Point startPoint = mProjection.toScreenLocation(marker.getPosition());
        startPoint.offset(0, -100);
        final LatLng startLatLng = mProjection.fromScreenLocation(startPoint);
        final long duration = 800;

        final Interpolator interpolator = new BounceInterpolator();
        handler.post(new Runnable() {
            @Override
            public void run() {
                long elapsed = SystemClock.uptimeMillis() - start;
                float t = interpolator.getInterpolation((float) elapsed
                        / duration);
                double lng = t * oldLl.longitude + (1 - t)
                        * startLatLng.longitude;
                double lat = t * oldLl.latitude + (1 - t)
                        * startLatLng.latitude;
                marker.setPosition(new LatLng(lat, lng));
                if (t < 1.0) {
                    handler.postDelayed(this, 16);
                }
            }
        });
    }

//    private void showPlaceNameChoose(List<PoiItem> dataList) {
//        if (placeNameSearchPopup == null) {
//            placeNameSearchPopup =
//                    new PlaceNameSearchResultPopup.Builder(mContext)
//                            .addItems(dataList).build();
//            placeNameSearchPopup.setDismissWhenTouchOutside(true);
//        }
//
//        placeNameSearchPopup.setOnListPopupItemClickListener(poiItem -> {
//            placeNameSearchPopup.dismiss();
//
//            searchPosition = new LatLng(poiItem.getLatLonPoint().getLatitude(), poiItem.getLatLonPoint().getLongitude());
//
//            addMarkersSearch(poiItem, searchPosition, poiItem.getTitle(), R.drawable.location_marker);
//            toCurrentView(searchPosition, locationMarker);
//
//        });
//        placeNameSearchPopup.notifyData(dataList);
//
//        placeNameSearchPopup.showPopupWindow(searchEdt);
//    }

//    @Override
//    public void onPoiItemSearched(PoiItem poiItem, int i) {
//
//    }

    /**
     * 逆地理编码回调
     */
//    @Override
//    public void onRegeocodeSearched(RegeocodeResult result, int rCode) {
//
//        Timber.d("onRegeocodeSearched ");
//        dismissProgressLoading();
//        if (rCode == AMapException.CODE_AMAP_SUCCESS) {
//            if (result != null && result.getRegeocodeAddress() != null
//                    && result.getRegeocodeAddress().getFormatAddress() != null) {
//                String addressName = result.getRegeocodeAddress().getFormatAddress()
//                        + "附近";
//
//                Intent addressIntent = new Intent(PanoramaDemoActivityMain.ACTION_ADDRESS);
//                addressIntent.putExtra(PanoramaDemoActivityMain.ACTION_ADDRESS, addressName);
//                LocalBroadcastManager.getInstance(mContext).sendBroadcast(addressIntent);
//
//            } else {
//                showSnackMessage(mContext.getString(R.string.error_data_empty));
//            }
//        } else {
//            showSnackMessage(mContext.getString(R.string.error_data_empty));
//        }
//
//
//    }

//    private List<Object> getCurrentPosResource() {
//        if (currentPositionMarker != null) {
//            List<Object> objectList =
//                    Utility.MapUtil.isExsitOtherRes(mProjection,
//                            new LatLng(currentPositionMarker.getPosition().latitude,
//                                    currentPositionMarker.getPosition().longitude),
//                            searchPointList, locationMarker, searchLineList, searchLine, mMap, 200);
//            return objectList;
//        }
//        return null;
//    }


//    private void sendNearByList() {
//        ArrayList<PanoramaTransBean> nearbyList = new ArrayList<>();
//        Flowable.empty()
//                .delay(3000, TimeUnit.MILLISECONDS)
//                .subscribeOn(Schedulers.io())
//                .doFinally(() -> {
//                    List<Object> objectList =
//                            Utility.MapUtil.isExsitOtherRes(mProjection,
//                                    new LatLng(streetLat,
//                                            streetLng),
//                                    searchPointList, locationMarker, null, null, mMap, 60);
//                    for (Object object : objectList) {
//                        if (object instanceof Marker
//                                && ((Marker) object).getObject() instanceof TaskBean.ResultsBean) {
//                            Marker marker = (Marker) object;
//                            TaskBean.ResultsBean resultsBean = (TaskBean.ResultsBean) marker.getObject();
//                            nearbyList.add(new PanoramaTransBean(
//                                    marker.getPosition().latitude,
//                                    marker.getPosition().longitude,
//                                    resultsBean.getLayerId(),
//                                    resultsBean.getAttributes().getSYSTEM_LEVEL(),
//                                    Utility.getResType(resultsBean),
//                                    resultsBean.getAttributes().getNAME()
//                            ));
//                        }
//                    }
//                    Timber.d("nearList" + nearbyList.size());
//                    Intent addressIntent = new Intent(PanoramaDemoActivityMain.ACTION_ADDRESS_LIST);
//                    addressIntent.putExtra(PanoramaDemoActivityMain.ACTION_ADDRESS_LIST, nearbyList);
//                    LocalBroadcastManager.getInstance(mContext).sendBroadcast(addressIntent);
//                })
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe();
////        if (searchPointList != null && searchPointList.size() > 0) {
////
////            for (Marker searchItem : searchPointList) {
////
////            }
////        }
////        if (locationMarker != null && locationMarker.getPosition() != null
////                && Math.abs(locationMarker.getPosition().latitude - streetMarker.getPosition().latitude) < 0.01
////                && Math.abs(locationMarker.getPosition().longitude - streetMarker.getPosition().longitude) < 0.01
////                && locationMarker.getObject() instanceof TaskBean.ResultsBean
////                ) {
////            TaskBean.ResultsBean resultsBean = (TaskBean.ResultsBean) locationMarker.getObject();
////            nearbyList.add(new PanoramaTransBean(
////                    locationMarker.getPosition().latitude,
////                    locationMarker.getPosition().longitude,
////                    resultsBean.getLayerId(),
////                    resultsBean.getAttributes().getSYSTEM_LEVEL()
////            ));
////        }
////        //街景点击
////        Timber.d(streetMarker.getPosition().longitude + "");
////        Timber.d(streetMarker.getPosition().latitude + "");
////        Timber.d(streetMarker.getTitle() + "");
////        Timber.d(streetMarker.getSnippet() + "");
//    }

    /**
     * 响应逆地理编码
     */
//    public void getAddress(final LatLonPoint latLonPoint) {
////        showProgressLoading(mContext.getString(R.string.common_searching));
//        RegeocodeQuery query = new RegeocodeQuery(latLonPoint, 200,
//                GeocodeSearch.AMAP);// 第一个参数表示一个Latlng，第二参数表示范围多少米，第三个参数表示是火系坐标系还是GPS原生坐标系
//        geocoderSearch.getFromLocationAsyn(query);// 设置异步逆地理编码请求
//    }


    /**
     * 地理编码查询回调
     */
//    @Override
//    public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {
//
//    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == CODE_REQUEST_RESOURCE_CONNECT) {
                //关联资源的点击回调

                if (data != null) {
                    String name = data.getStringExtra("name");
                    String type = data.getStringExtra("type");
                    String typeName = data.getStringExtra("typeName");
                    String uuid = data.getStringExtra("uuid");

                    List<String> list = Arrays.asList(ResourceUtil.ResourceTypeRequest);
                    if (list.contains(type)) {
                        //某种情况下需要重新获取经纬度
                        //重新定位 uuid
//                        mapPresenter.accurateQueryResource(uuid);
                    } else {
                        //无需在地图上显示的或不需在再获取经纬度了
//                        slideFromBottomPopup.applyData(new ResourceConnectBean.ResouceTypeBean(uuid,
//                                name, type, typeName), currentPosition);
                    }

                }
            }

        }
    }


    private void lineAnimate(Polyline polyline) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(polyline, "width",
                30, 20, lineWidth);
        animator.setEvaluator(new FloatEvaluator());
        animator.setInterpolator(new LinearInterpolator());
        animator.setDuration(1000);
        animator.addListener(new Animator.AnimatorListener() {

            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

//                if (linePositionSign == null) {
//                    linePositionSign = mMap.addPolyline(new PolylineOptions()
//                            .addAll(polyline.getPoints()).width(lineWidth + 10)
//                            .zIndex(30)
//                            .color(getResources().getColor(R.color.color_red)));
//                } else {
//                    linePositionSign.setVisible(true);
//                    linePositionSign.setPoints(polyline.getPoints());
//                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animator.start();
    }

    @Override
    public void onPolylineClick(Polyline polyline) {

//        Timber.d("onPolylineClick");
//        lineAnimate(polyline);
//        if (searchLine != null) {
//            Timber.d(searchLine.getmPolyline().getId());
//            if (polyline.getId().equals(searchLine.getmPolyline().getId())) {
//
//                onMapClick(null, searchLine);
//
//                return;
//            }
//        }
//        if (searchLineList != null && searchLineList.size() > 0) {
//            for (PolylineObj polylineObj : searchLineList) {
//
//                if (polyline.getId().equals(polylineObj.getmPolyline().getId())) {
//                    onMapClick(null, polylineObj);
//                    return;
//                }
//            }
//        }


//        if (searchLine != null && searchLine.getmPolyline() == polyline) {
//            onMapClick(null, searchLine);
//            return;
//        }
//
//        if (searchLineList != null && searchLineList.size() > 0) {
//
//            polyline.getPoints()
//            for (PolylineObj polylineObj : searchLineList) {
//                if (polyline == polylineObj.getmPolyline()) {
//                    onMapClick(null, polylineObj);
//                    return;
//                }
//            }
//        }
    }

    @Override
    public void onClick(View v) {

    }


}
