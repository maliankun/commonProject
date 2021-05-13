package com.inspur.osp.ui.widgets;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.inspur.osp.data.bean.CableSegBean;
import com.inspur.osp.data.bean.TopoCheckedRouteBean;
import com.inspur.osp.data.bean.TopoRouteBean;
import com.inspur.osp.ui.fragment.BindRouteFragment;
import com.inspur.osp.ui.inter.RouterBeanClickListener;
import com.inspur.topo.R;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

//import butterknife.BindView;
//import butterknife.ButterKnife;

/**
 * Created by liuchen01 on 2019/10/23.
 */

public class TopoRouteDetailViewHolder<T> {


    BindRouteFragment.PointViewholder startPointVh;
    BindRouteFragment.PointViewholder endPointVh;

    RecyclerView cableListRv;

//    @BindView(R.id.layout_cable)
//    LinearLayout cableLayout;

    Context mContext;
    List<T> datas;
    RouterBeanClickListener<T> mBindRouteClickLisenter;
    CommonAdapter mCommonAdapter;

    public TopoRouteDetailViewHolder(View view) {
        cableListRv = view.findViewById(R.id.rv_cable_list);
        mContext = view.getContext();
        startPointVh = new BindRouteFragment.PointViewholder(view.findViewById(R.id.layout_start));
        endPointVh = new BindRouteFragment.PointViewholder(view.findViewById(R.id.layout_end));

    }

    public void setmBindRouteClickLisenter(RouterBeanClickListener<T> mBindRouteClickLisenter) {
        this.mBindRouteClickLisenter = mBindRouteClickLisenter;
    }

    public void bindData(List<T> data, TopoRouteBean topoRouteBean, TopoCheckedRouteBean topoCheckedRouteBean) {

        startPointVh.bindData(0, topoRouteBean, topoCheckedRouteBean);
        endPointVh.bindData(1, topoRouteBean, topoCheckedRouteBean);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setSmoothScrollbarEnabled(true);
        layoutManager.setAutoMeasureEnabled(true);
        cableListRv.setLayoutManager(layoutManager);
        cableListRv.setNestedScrollingEnabled(false);
        datas = data;

//        for (T t : datas) {
//            View view = LayoutInflater.from(mContext).inflate(R.layout.item_topo_list, null);
//            if (t instanceof CableSegBean) {
//                ((TextView) view.findViewById(R.id.tv_topo_name)).setText(((CableSegBean) t).getNAME());
//            } else if (t instanceof TopoRouteBean.LinesBean.RoutesBean) {
//                ((TextView) view.findViewById(R.id.tv_topo_name)).setText(((TopoRouteBean.LinesBean.RoutesBean) t).getName());
//            }
//            view.setOnClickListener(v -> {
//                if (mBindRouteClickLisenter != null) {
//                    mBindRouteClickLisenter.routerBeanClick(t);
//                }
//            });
//            cableLayout.addView(view);
//        }
        mCommonAdapter = new CommonAdapter<T>(mContext, R.layout.item_topo_list, datas) {
            @Override
            protected void convert(ViewHolder holder, T t, int position) {

                if (t instanceof CableSegBean) {
                    holder.setText(R.id.tv_topo_name, ((CableSegBean) t).getNAME());
                    holder.setVisible(R.id.layout_center_site, true);
//                    holder.setText(R.id.tv_site_name,t.get)
                    holder.setText(R.id.tv_site_name, ((CableSegBean) t).getZ_SITE_ID_VAL());
                    holder.setText(R.id.tv_room_name, ((CableSegBean) t).getZ_ROOM_ID_VAL());
                    holder.setText(R.id.tv_device_name, ((CableSegBean) t).getZ_RACK_ID_VAL());
                } else if (t instanceof TopoRouteBean.LinesBean.RoutesBean) {
                    holder.setText(R.id.tv_topo_name, ((TopoRouteBean.LinesBean.RoutesBean) t).getName());
                    holder.setVisible(R.id.layout_center_site, true);
                    holder.setText(R.id.tv_site_name, ((TopoRouteBean.LinesBean.RoutesBean) t).getEndNode());
                    holder.setText(R.id.tv_room_name, ((TopoRouteBean.LinesBean.RoutesBean) t).getZ_room_id_val());
                    holder.setText(R.id.tv_device_name, ((TopoRouteBean.LinesBean.RoutesBean) t).getZ_rack_id_val());

                }
                if (position == datas.size() - 1) {
                    holder.setVisible(R.id.layout_center_site, false);
                }
            }
        };
        mCommonAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                if (mBindRouteClickLisenter != null) {
                    mBindRouteClickLisenter.routerBeanClick(datas.get(position));
                }
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
        cableListRv.setAdapter(mCommonAdapter);
    }
}
