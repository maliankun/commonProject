package com.inspur.osp.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.inspur.osp.data.bean.TopoRouteBean;
import com.inspur.osp.ui.BaseFragment;
import com.inspur.topo.R;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by liuchen01 on 2019/10/19.
 */

public class CanUseRouteFragment extends BaseFragment {


    CommonAdapter<TopoRouteBean.LinesBean> linesBeanCommonAdapter;

    RecyclerView canUseRv;

    List<TopoRouteBean.LinesBean> linesBean = new ArrayList<>();
    CanUseRouteClickLisenter canUseRouteClickLisenter;


    public void setCanUseRouteClickLisenter(CanUseRouteClickLisenter canUseRouteClickLisenter) {
        this.canUseRouteClickLisenter = canUseRouteClickLisenter;
    }

    public static CanUseRouteFragment newInstance() {
        Bundle args = new Bundle();
        CanUseRouteFragment fragment = new CanUseRouteFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public void setTopoRouteBean(TopoRouteBean topoInfoBean) {
        linesBean.clear();
        linesBean.addAll(topoInfoBean.getLines());
    }

    @Override
    protected void initView() {
        canUseRv = rootView.findViewById(R.id.rv_can_use);

        canUseRv.setLayoutManager(new LinearLayoutManager(mContext));
        canUseRv.setHasFixedSize(true);

        linesBeanCommonAdapter = new CommonAdapter<TopoRouteBean.LinesBean>(mContext, R.layout.item_topo_list, linesBean) {
            @Override
            protected void convert(ViewHolder holder, TopoRouteBean.LinesBean linesBean, int position) {
                holder.setText(R.id.tv_topo_name, linesBean.getJump() + "跳路由");
            }
        };
        linesBeanCommonAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                if (canUseRouteClickLisenter != null) {
                    canUseRouteClickLisenter.jumperClick(linesBean.get(position));
                }
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
        canUseRv.setAdapter(linesBeanCommonAdapter);
    }

    @Override
    public void initData() {
        linesBeanCommonAdapter.notifyDataSetChanged();
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_route_can_use;
    }

    public interface CanUseRouteClickLisenter {
        void jumperClick(TopoRouteBean.LinesBean cableSegBean);
    }
}
