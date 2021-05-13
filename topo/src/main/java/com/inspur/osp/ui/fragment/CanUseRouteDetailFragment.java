package com.inspur.osp.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.inspur.osp.contract.CanUseTopoRouteDetailConstract;
import com.inspur.osp.data.bean.CheckRouteStateBean;
import com.inspur.osp.data.bean.TopoRouteBean;
import com.inspur.osp.presenter.CanUseTopoRouteDetailPresenter;
import com.inspur.osp.ui.BaseFragment;
import com.inspur.osp.ui.inter.RouterBeanClickListener;
import com.inspur.osp.ui.widgets.TopoRouteDetailViewHolder;
import com.inspur.osp.util.DialogHelp;
import com.inspur.osp.util.RxBus;
import com.inspur.topo.R;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * Created by liuchen01 on 2019/10/19.
 */

public class CanUseRouteDetailFragment extends BaseFragment<CanUseTopoRouteDetailConstract.Presenter> implements CanUseTopoRouteDetailConstract.View {


    TopoRouteDetailViewHolder<TopoRouteBean.LinesBean.RoutesBean> topoRouteDetailViewHolder;

    //    @BindView(R.id.jumper_can_bind)
//    RecyclerView canUseJumperRv;
//    @BindView(R.id.floating_choose)
//    FloatingActionButton floatingActionButton;
//
//    CommonAdapter<TopoRouteBean.LinesBean.RoutesBean> routerBeanCommonAdapter;
//    TopoRouteBean topoRouteBean;
//    List<TopoRouteBean.LinesBean.RoutesBean> routesBeans = new ArrayList<>();
//
    RouterBeanClickListener<TopoRouteBean.LinesBean.RoutesBean> routerBeanClickListener;
//    //
//    BindRouteFragment.PointViewholder startPointVh;
//    BindRouteFragment.PointViewholder endPointVh;
//
//    @BindView(R.id.layout_start)
//    LinearLayout startLayout;
//    @BindView(R.id.layout_end)
//    LinearLayout endLayout;

    Button bindBtn;
    Button canCancelBtn;
    Button olpBindBtn;
    LinearLayout operateLayout;

    String cableids;
    TopoRouteBean topoRouteBean;
    private int type = 0;//0 :可用；1 :推荐
    private int position = 0;
    Disposable refreshDisposable;
    private String cancelId;

    public void setRouterBeanClickListener(RouterBeanClickListener<TopoRouteBean.LinesBean.RoutesBean> routerBeanClickListener) {
        this.routerBeanClickListener = routerBeanClickListener;
    }

    public static CanUseRouteDetailFragment newInstance(TopoRouteBean topoRouteBean, int position, int type) {
        Bundle args = new Bundle();
        args.putInt("type", type);
        args.putInt("position", position);
        args.putSerializable("topoRoute", topoRouteBean);
        CanUseRouteDetailFragment fragment = new CanUseRouteDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView() {


        bindBtn = rootView.findViewById(R.id.btn_bind);
        canCancelBtn = rootView.findViewById(R.id.btn_bind_cancel);
        olpBindBtn = rootView.findViewById(R.id.btn_bind_olp);
        operateLayout = rootView.findViewById(R.id.layout_operate);

        refreshDisposable = RxBus.getInstance().toObservable().subscribe(object -> {
            if ("router_refresh".equals(object)) {
                mPresenter.getBindRelations(topoRouteBean.getTopoInfo().getIntId(), cableids);
            }
        });
    }

    @Override
    public void initData() {

        new CanUseTopoRouteDetailPresenter(mContext, this);

        this.type = getArguments().getInt("type");
        this.topoRouteBean = (TopoRouteBean) getArguments().getSerializable("topoRoute");
        this.position = getArguments().getInt("position");

        if (this.type == 1) {
            operateLayout.setVisibility(View.GONE);
        } else {
            operateLayout.setVisibility(View.VISIBLE);
        }

        List<TopoRouteBean.LinesBean.RoutesBean> routesBeans = topoRouteBean.getLines().get(position).getRoutes();
        topoRouteDetailViewHolder = new TopoRouteDetailViewHolder<>(rootView.findViewById(R.id.layout_detail));
        topoRouteDetailViewHolder.setmBindRouteClickLisenter(routesBean -> {
            if (routerBeanClickListener != null) {
                routerBeanClickListener.routerBeanClick(routesBean);
            }
        });
        topoRouteDetailViewHolder.bindData(routesBeans, topoRouteBean, null);

        StringBuilder sb = new StringBuilder();
        for (TopoRouteBean.LinesBean.RoutesBean routesBean : routesBeans) {
            sb.append(routesBean.getId());
            sb.append(",");
        }

        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        cableids = sb.toString();
        mPresenter.getBindRelations(topoRouteBean.getTopoInfo().getIntId(), cableids);
    }

    @Override
    protected void initListener() {

        bindBtn.setOnClickListener(view -> {
            if (topoRouteBean != null) {
                mPresenter.saveCheckedRoute(topoRouteBean.getTopoInfo().getIntId(), cableids);
            }
        });
        canCancelBtn.setOnClickListener(view ->
                DialogHelp.getConfirmDialog(mContext, "是否要解绑",
                        "系统提示", "取消", "确定", (dialog, which) -> {
                            mPresenter.cancelBindByUuid(cancelId);
                        }).show());
        olpBindBtn.setOnClickListener(view -> {
            OLPBindFragment olpBindFragment = OLPBindFragment.newInstance();
            olpBindFragment.setTopoRouteBean(topoRouteBean, cableids);
            FragmentManager supportFragmentManager =
                    ((AppCompatActivity) mContext).getSupportFragmentManager();
            FragmentTransaction transaction = supportFragmentManager.beginTransaction();
            transaction.replace(R.id.framelayout_container, olpBindFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        });
//        floatingActionButton.setOnClickListener(v -> {
//
//        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_topo_route_detail;
    }

    @Override
    public void fillBindRelation(CheckRouteStateBean checkRouteStateBean) {

        if (checkRouteStateBean.getData().size() > 0) {
            canCancelBtn.setEnabled(true);
            cancelId = checkRouteStateBean.getData().get(0).getUUID();
        } else {
            canCancelBtn.setEnabled(false);
        }
        if (checkRouteStateBean.getRoutedata().size() > 1) {
            olpBindBtn.setEnabled(false);
            bindBtn.setEnabled(false);
        } else if (checkRouteStateBean.getRoutedata().size() > 0) {
            bindBtn.setEnabled(false);
            CheckRouteStateBean.RoutedataBean routedataBean = checkRouteStateBean.getRoutedata().get(0);
            if (!TextUtils.isEmpty(routedataBean.getORIG_NE_ID_OLP())) {
                olpBindBtn.setEnabled(true);
            } else {
                olpBindBtn.setEnabled(false);
            }
        } else {
            bindBtn.setEnabled(true);
            olpBindBtn.setEnabled(true);
        }
    }

    @Override
    public void bindRouteSuccess() {
//        mPresenter.getBindRelations(topoRouteBean.getTopoInfo().getINT_ID(), cableids);
        RxBus.getInstance().send("router_refresh");
    }

    @Override
    public void bindRouteOlpSuccess() {
        mPresenter.getBindRelations(topoRouteBean.getTopoInfo().getIntId(), cableids);
    }

    @Override
    public void setPresenter(CanUseTopoRouteDetailConstract.Presenter presenter) {
        mPresenter = presenter;
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
    public void onDestroyView() {
        super.onDestroyView();
        if (refreshDisposable != null) {
            refreshDisposable.dispose();
        }
    }

    @Override
    public void finishView() {

    }
}
