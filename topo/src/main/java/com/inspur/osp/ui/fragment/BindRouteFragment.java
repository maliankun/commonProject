package com.inspur.osp.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.inspur.osp.contract.BindRouteContract;
import com.inspur.osp.data.bean.CableSegBean;
import com.inspur.osp.data.bean.TopoCheckedRouteBean;
import com.inspur.osp.data.bean.TopoRouteBean;
import com.inspur.osp.presenter.BindRoutePresenter;
import com.inspur.osp.ui.BaseFragment;
import com.inspur.osp.ui.inter.RouterBeanClickListener;
import com.inspur.osp.ui.widgets.TopoRouteDetailViewHolder;
import com.inspur.osp.util.DialogHelp;
import com.inspur.topo.R;

import java.util.List;

//import butterknife.BindView;
//import butterknife.ButterKnife;

/**
 * Created by liuchen01 on 2019/10/19.
 */

public class BindRouteFragment extends BaseFragment<BindRouteContract.Presenter> implements BindRouteContract.View {


//    List<CableSegBean> cableSegBeanList = new ArrayList<>();
//    @BindView(R.id.layout_start)
//    LinearLayout startLayout;
//    @BindView(R.id.layout_end)
//    LinearLayout endLayout;


    //    TopoRouteDetailViewholder

    LinearLayout routeLayout1;

    TextView firtRouteTitleTv;

    TopoRouteDetailViewHolder<CableSegBean> firstTopoRouteVh;
    Button cancelRouteBtn1;

    LinearLayout routeLayout2;
    TextView secondRouteTitleTv;
    TopoRouteDetailViewHolder<CableSegBean> secondTopoRouteVh;
    Button cancelRouteBtn2;

    TopoCheckedRouteBean topoCheckedRouteBean;
    TopoCheckedRouteBean topoCheckedRouteBean2;
    TopoRouteBean topoRouteBean;
    RouterBeanClickListener<CableSegBean> mBindRouteClickLisenter;

//    CommonAdapter<CableSegBean> segBeanCommonAdapter;


    public static BindRouteFragment newInstance() {
        Bundle args = new Bundle();
//        args.putSerializable("topoRoute", topoRouteBean);
//        args.putStringArrayList("topoChecked", topoCheckedRouteBeans);
        BindRouteFragment fragment = new BindRouteFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public void setmBindRouteClickLisenter(RouterBeanClickListener<CableSegBean> mBindRouteClickLisenter) {
        this.mBindRouteClickLisenter = mBindRouteClickLisenter;
    }


    public void bindTopoRouteList(TopoRouteBean topoRouteBean, List<TopoCheckedRouteBean> topoCheckedRouteBeans) {
        this.topoRouteBean = topoRouteBean;

        if (topoCheckedRouteBeans.size() == 1) {
            topoCheckedRouteBean = topoCheckedRouteBeans.get(0);
        } else if (topoCheckedRouteBeans.size() == 2) {
            topoCheckedRouteBean = topoCheckedRouteBeans.get(0);
            topoCheckedRouteBean2 = topoCheckedRouteBeans.get(1);
        }
    }

    public void bindTopoCableSegList(List<CableSegBean> cableSegBeans) {
//        cableSegBeanList.clear();
//        cableSegBeanList.addAll(cableSegBeans);
//        segBeanCommonAdapter.notifyDataSetChanged();
    }

    @Override
    protected void initView() {
        routeLayout1 = rootView.findViewById(R.id.layout_1);
        firtRouteTitleTv = rootView.findViewById(R.id.title_route_first);
        cancelRouteBtn1 = rootView.findViewById(R.id.btn_cancel_route1);
        routeLayout2 = rootView.findViewById(R.id.layout_2);
        secondRouteTitleTv = rootView.findViewById(R.id.title_route_second);
        cancelRouteBtn2 = rootView.findViewById(R.id.btn_cancel_route2);

        new BindRoutePresenter(mContext, this);
        firstTopoRouteVh = new TopoRouteDetailViewHolder(rootView.findViewById(R.id.layout_bind_route1));
        firstTopoRouteVh.setmBindRouteClickLisenter(mBindRouteClickLisenter);
        secondTopoRouteVh = new TopoRouteDetailViewHolder(rootView.findViewById(R.id.layout_bind_route2));
        secondTopoRouteVh.setmBindRouteClickLisenter(mBindRouteClickLisenter);


//        firstTopoRouteVh.setView();

//        cableSegBindRecycleview.setHasFixedSize(true);
//        cableSegBindRecycleview.setLayoutManager(new LinearLayoutManager(mContext));
//        segBeanCommonAdapter = new CommonAdapter<CableSegBean>(mContext, R.layout.item_topo_list, cableSegBeanList) {
//            @Override
//            protected void convert(ViewHolder holder, CableSegBean cableSegBean, int position) {
//                holder.setText(R.id.tv_topo_name, cableSegBean.getNAME());
//            }
//        };
//        segBeanCommonAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
//                CableSegBean cableSegBean = cableSegBeanList.get(position);
//                if (mBindRouteClickLisenter != null) {
//                    mBindRouteClickLisenter.cableSegClick(cableSegBean);
//                }
//            }
//
//            @Override
//            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
//                return false;
//            }
//        });
//        cableSegBindRecycleview.setAdapter(segBeanCommonAdapter);
//
//        startPointVh = new PointViewholder(startLayout);
//        endPointVh = new PointViewholder(endLayout);
    }

    @Override
    public void initData() {
        if (this.topoCheckedRouteBean2 == null) {
            this.routeLayout2.setVisibility(View.GONE);
            return;
        }
        if (this.topoCheckedRouteBean2 != null && this.topoCheckedRouteBean != null) {
            this.routeLayout2.setVisibility(View.VISIBLE);
            return;
        }
    }

    private String getCableIds(TopoCheckedRouteBean paramTopoCheckedRouteBean) {
        if (paramTopoCheckedRouteBean == null)
            return "";
        StringBuilder stringBuilder = new StringBuilder();
        if (stringBuilder.length() > 0)
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }


    @Override
    protected void initListener() {
        cancelRouteBtn1.setOnClickListener(v ->
                DialogHelp.getConfirmDialog(mContext, "是否要解绑",
                        "系统提示", "取消", "确定", (dialog, which) ->
                                mPresenter.cancelCheckedRoute(topoCheckedRouteBean.getID())
                ).show());
        cancelRouteBtn2.setOnClickListener(v ->
                DialogHelp.getConfirmDialog(mContext, "是否要解绑",
                        "系统提示", "取消", "确定", (dialog, which) ->
                                mPresenter.cancelCheckedRoute(topoCheckedRouteBean2.getID())
                ).show());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_bind_topo_route_list;
    }

    @Override
    public void setPresenter(BindRouteContract.Presenter presenter) {
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
    public void finishView() {

    }


    public static class PointViewholder {

        TextView siteNameLeftTv;
        TextView siteNameTv;

        TextView roomNameLeftTv;
        TextView roomNameTv;

        TextView netNameLeftTv;
        TextView netNameTv;
        TextView portNameLeftTv;
        TextView portNameTv;

        TextView olpDeviceLeftTv;
        TextView olpDeviceTv;

        TextView olpDeviceCardLeftTv;
        TextView olpDeviceCardTv;

        TextView olpDevicePortLeftTv;
        TextView oldDevicePortTv;

        LinearLayout olpCardLayout;

        public PointViewholder(View view) {
            siteNameLeftTv = view.findViewById(R.id.tv_site_name_left);
            siteNameTv = view.findViewById(R.id.tv_site_name);
            roomNameLeftTv = view.findViewById(R.id.tv_room_name_left);
            roomNameTv = view.findViewById(R.id.tv_room_name);
            netNameLeftTv = view.findViewById(R.id.tv_net_name_left);
            netNameTv = view.findViewById(R.id.tv_net_name);
            portNameLeftTv = view.findViewById(R.id.tv_port_name_left);
            portNameTv = view.findViewById(R.id.tv_port_name);
            olpDeviceLeftTv = view.findViewById(R.id.tv_olp_device_left);
            olpDeviceTv = view.findViewById(R.id.tv_olp_device);
            olpDeviceCardLeftTv = view.findViewById(R.id.tv_olp_device_card_left);
            olpDeviceCardTv = view.findViewById(R.id.tv_olp_device_card);
            olpDevicePortLeftTv = view.findViewById(R.id.tv_olp_device_port_left);
            oldDevicePortTv = view.findViewById(R.id.tv_olp_device_port);
            olpCardLayout = view.findViewById(R.id.layout_card_olp);

        }


        public void bindData(int type, TopoRouteBean topoRouteBean, TopoCheckedRouteBean topoCheckedRouteBean) {

            if (type == 0) {
                siteNameLeftTv.setText("A端站点：");
                siteNameTv.setText(topoRouteBean.getTopoInfo().getOrigSiteId());

                netNameLeftTv.setText("Ad端网元：");
                netNameTv.setText(topoRouteBean.getTopoInfo().getOrigNeId());
                portNameLeftTv.setText("A端网元端口：");
                portNameTv.setText(topoRouteBean.getTopoInfo().getOrigPointId());

                roomNameLeftTv.setText("A端机房：");
                roomNameTv.setText(topoRouteBean.getTopoInfo().getOrigRoomId());

                if (topoCheckedRouteBean == null ) {
                    olpCardLayout.setVisibility(View.GONE);
                } else {
//                    olpDeviceLeftTv.setText("A端OLP设备：");
//                    olpDeviceTv.setText(topoCheckedRouteBean == null ? "" : topoCheckedRouteBean.getORIG_NE_ID_OLP_VALUE());
//                    olpDeviceCardLeftTv.setText("A端OLP设备板卡：");
//                    olpDeviceCardTv.setText(topoCheckedRouteBean == null ? "" : topoCheckedRouteBean.getORIG_CARD_ID_OLP_VALUE());
//                    olpDevicePortLeftTv.setText("A端OLP设备端口:");
//                    oldDevicePortTv.setText(topoCheckedRouteBean == null ? "" : topoCheckedRouteBean.getCOUNTER() == 0 ? "R1T1" : "R2T2");

                    olpDeviceLeftTv.setText("A端OLP设备：");
//                    olpDeviceTv.setText(topoCheckedRouteBean == null ? "" : topoCheckedRouteBean.());
                    olpDeviceCardLeftTv.setText("A端OLP设备板卡：");
//                    olpDeviceCardTv.setText(topoCheckedRouteBean == null ? "" : topoCheckedRouteBean.getORIG_CARD_ID_OLP_VALUE());
                    olpDevicePortLeftTv.setText("A端OLP设备端口:");
//                    oldDevicePortTv.setText(topoCheckedRouteBean == null ? "" : topoCheckedRouteBean.getCOUNTER() == 1 ? "R1T1" : "R2T2");
                }

            } else {
                siteNameLeftTv.setText("Z端站点：");
                siteNameTv.setText(topoRouteBean.getTopoInfo().getDestSiteName());
                netNameLeftTv.setText("Z端网元：");
                netNameTv.setText(topoRouteBean.getTopoInfo().getDestNeName());
                portNameLeftTv.setText("Z端网元端口：");
                portNameTv.setText(topoRouteBean.getTopoInfo().getDestPointName());

                roomNameLeftTv.setText("Z端机房：");
                roomNameTv.setText(topoRouteBean.getTopoInfo().getDestRoomName());

                if (topoCheckedRouteBean == null) {
                    olpCardLayout.setVisibility(View.GONE);
                } else {
                    olpDeviceLeftTv.setText("Z端OLP设备：");
//                    olpDeviceTv.setText(topoCheckedRouteBean == null ? "" : topoCheckedRouteBean.get());
                    olpDeviceCardLeftTv.setText("Z端OLP设备板卡：");
//                    olpDeviceCardTv.setText(topoCheckedRouteBean == null ? "" : topoCheckedRouteBean.getDEST_CARD_ID_OLP_VALUE());
                    olpDevicePortLeftTv.setText("Z端OLP设备端口:");
//                    oldDevicePortTv.setText(topoCheckedRouteBean == null ? "" : topoCheckedRouteBean.getCOUNTER() == 0 ? "R1T1" : "R2T2");
                }
            }
        }
    }
}
