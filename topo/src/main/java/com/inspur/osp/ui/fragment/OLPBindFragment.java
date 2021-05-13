package com.inspur.osp.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.inspur.osp.contract.OlpBindConstract;
import com.inspur.osp.data.bean.OlpByRoom;
import com.inspur.osp.data.bean.OlpCardEndInfo;
import com.inspur.osp.data.bean.TopoRouteBean;
import com.inspur.osp.presenter.OlpBindPresenter;
import com.inspur.osp.ui.BaseFragment;
import com.inspur.osp.ui.widgets.ItemSelectWidget;
import com.inspur.osp.util.RxBus;
import com.inspur.topo.R;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

//import butterknife.BindView;
//import butterknife.ButterKnife;

/**
 * Created by liuchen01 on 2019/10/22.
 */

public class OLPBindFragment extends BaseFragment<OlpBindConstract.Presenter> implements OlpBindConstract.View {

    ItemSelectWidget aDeviceVh;
    ItemSelectWidget aCardVh;
    ItemSelectWidget zDeviceVh;
    ItemSelectWidget zCardVh;

    TopoRouteBean topoRouteBean;

    private List<OlpByRoom.OLPDeviceBean> aAllOlpDeviceList;
    private List<OlpByRoom.OLPDeviceBean> zAllOlpDeviceList;

    private OlpByRoom.OLPDeviceBean chooseAOlpDevice;
    private OlpByRoom.OLPDeviceBean chooseAOlpCard;

    private OlpByRoom.OLPDeviceBean chooseZOlpDevice;
    private OlpByRoom.OLPDeviceBean chooseZOlpCard;

    private List<OlpByRoom.OLPDeviceBean> aAllOlpCardList;
    private List<OlpByRoom.OLPDeviceBean> zAllOlpCardList;

    private AlertDialog olpDevieDialog;
    private SelectOlpCardViewHoder selectOlpCardViewHoder;

    private static int type = 1;//选择olp设备

    private static final int TYPE_A_OLP_CHOOSE = 1;
    private static final int TYPE_A_CARD_CHOOSE = 2;
    private static final int TYPE_Z_OLP_CHOOSE = 3;
    private static final int TYPE_Z_CARD_CHOOSE = 4;

    Button chooseSureBtn;
    int position = 0;
    String cableids;

    public static OLPBindFragment newInstance() {

        Bundle args = new Bundle();

        OLPBindFragment fragment = new OLPBindFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public void setTopoRouteBean(TopoRouteBean topoRouteBean, String cableids) {
        this.topoRouteBean = topoRouteBean;
        this.cableids = cableids;
    }

    @Override
    protected void initView() {
        chooseSureBtn = rootView.findViewById(R.id.btn_choose_sure);
        new OlpBindPresenter(mContext, this);

        aDeviceVh = new ItemSelectWidget(rootView.findViewById(R.id.layout_a_device));
        aCardVh = new ItemSelectWidget(rootView.findViewById(R.id.layout_a_card));
        aDeviceVh.contentDescTv.setText(mContext.getResources().getString(R.string.room_olp_device, "A"));
        aCardVh.contentDescTv.setText(mContext.getResources().getString(R.string.room_olp_card, "A"));

        zDeviceVh = new ItemSelectWidget(rootView.findViewById(R.id.layout_z_device));
        zCardVh = new ItemSelectWidget(rootView.findViewById(R.id.layout_z_card));
        zDeviceVh.contentDescTv.setText(mContext.getResources().getString(R.string.room_olp_device, "Z"));
        zCardVh.contentDescTv.setText(mContext.getResources().getString(R.string.room_olp_card, "Z"));


    }

    private List<OlpByRoom.OLPDeviceBean> getOlpDeviceByType() {

        if (type == TYPE_A_OLP_CHOOSE) {
            return aAllOlpDeviceList;
        } else if (type == TYPE_A_CARD_CHOOSE) {
            return aAllOlpCardList;
        } else if (type == TYPE_Z_OLP_CHOOSE) {
            return zAllOlpDeviceList;
        } else {
            return zAllOlpCardList;
        }
    }

    private void showDialog() {
        if (olpDevieDialog == null) {
            View container = LayoutInflater.from(mContext).inflate(R.layout.dialog_olp_device_select, null);
            selectOlpCardViewHoder = new SelectOlpCardViewHoder(container);

            selectOlpCardViewHoder.setmOlpDeviceCardClick((olpDeviceBean, type) -> {
                if (olpDeviceBean == null) return;
                if (type == TYPE_A_OLP_CHOOSE) {
                    if (chooseAOlpDevice != null
                            && !TextUtils.equals(olpDeviceBean.getINT_ID(), chooseAOlpDevice.getINT_ID())) {
                        //不一样且不相等，清空下面数据
                        chooseAOlpCard = null;
                        aCardVh.contentTv.setText(null);
                    }
                    chooseAOlpDevice = olpDeviceBean;
                    aDeviceVh.contentTv.setText(chooseAOlpDevice.getZH_LABEL());

                } else if (type == TYPE_Z_OLP_CHOOSE) {
                    if (chooseZOlpDevice != null
                            && !TextUtils.equals(olpDeviceBean.getINT_ID(), chooseZOlpDevice.getINT_ID())) {
                        //不一样且不相等，清空下面数据
                        chooseZOlpCard = null;
                        zCardVh.contentTv.setText(null);
                    }
                    chooseZOlpDevice = olpDeviceBean;
                    zDeviceVh.contentTv.setText(chooseZOlpDevice.getZH_LABEL());
                } else if (type == TYPE_A_CARD_CHOOSE) {

                    chooseAOlpCard = olpDeviceBean;
                    aCardVh.contentTv.setText(chooseAOlpCard.getZH_LABEL());
                    //然后再去查询对端设备
                    mPresenter.getEndInfoByOlpCard(chooseAOlpCard.getINT_ID(), TYPE_A_CARD_CHOOSE);
                } else {
                    chooseZOlpCard = olpDeviceBean;
                    zCardVh.contentTv.setText(chooseZOlpCard.getZH_LABEL());
                    mPresenter.getEndInfoByOlpCard(chooseZOlpCard.getINT_ID(), TYPE_Z_CARD_CHOOSE);
                }
                olpDevieDialog.dismiss();
            });
            olpDevieDialog = new AlertDialog.Builder(mContext).setView(container).create();
        }
        selectOlpCardViewHoder.setmDataList(getOlpDeviceByType());
        olpDevieDialog.show();
    }

    @Override
    public void initData() {
//        mPresenter.getAzOlpDeviceByRoom(topoRouteBean.getTopoInfo().getORIG_ROOM_ID(), topoRouteBean.getTopoInfo().getDEST_ROOM_ID());
        mPresenter.getAzOlpDeviceByRoom("1", "2");
    }


    @Override
    protected void initListener() {

        aDeviceVh.contentFl.setOnClickListener(view -> {
            //choose olp device
            type = TYPE_A_OLP_CHOOSE;
            showDialog();

        });
        aCardVh.contentFl.setOnClickListener(view -> {
            type = TYPE_A_CARD_CHOOSE;
            if (chooseAOlpDevice == null) return;
            mPresenter.getOlpCardByOlpDevice(chooseAOlpDevice.getINT_ID(), type);
        });
        zDeviceVh.contentFl.setOnClickListener(view -> {
            type = TYPE_Z_OLP_CHOOSE;
            showDialog();
        });
        zCardVh.contentFl.setOnClickListener(view -> {
            type = TYPE_Z_CARD_CHOOSE;
            if (chooseZOlpDevice == null) return;
            mPresenter.getOlpCardByOlpDevice(chooseZOlpDevice.getINT_ID(), type);
        });
        chooseSureBtn.setOnClickListener(view -> {
            if (chooseAOlpDevice == null) {
                showHint("A端OLP设备不能为空");
                return;
            }
            if (chooseZOlpDevice == null) {
                showHint("Z端OLP设备不能为空");
                return;
            }
            if (chooseAOlpCard == null) {
                showHint("A端OLP设备板卡不能为空");
                return;
            }
            if (chooseZOlpCard == null) {
                showHint("Z端OLP设备板卡不能为空");
                return;
            }
            //提交
            mPresenter.saveOlpDeviceBind(topoRouteBean.getTopoInfo().getIntId(), cableids, chooseAOlpDevice.getINT_ID(),
                    chooseAOlpCard.getINT_ID(), chooseZOlpDevice.getINT_ID(), chooseZOlpCard.getINT_ID());
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_olp_select;
    }

    @Override
    public void fillAzDeviceByRoom(OlpByRoom olpByRoom) {
        aAllOlpDeviceList = olpByRoom.getOrig();
        zAllOlpDeviceList = olpByRoom.getDest();

        if (aAllOlpDeviceList != null && aAllOlpDeviceList.size() > 0) {
            chooseAOlpDevice = aAllOlpDeviceList.get(0);
            aDeviceVh.contentTv.setText(chooseAOlpDevice.getZH_LABEL());
        }
        if (zAllOlpDeviceList != null && zAllOlpDeviceList.size() > 0) {
            chooseZOlpDevice = zAllOlpDeviceList.get(0);
            zDeviceVh.contentTv.setText(chooseZOlpDevice.getZH_LABEL());
        }
    }

    @Override
    public void fillOlpCardByDevice(int type, List<OlpByRoom.OLPDeviceBean> olpDeviceBean) {

        if (type == TYPE_A_CARD_CHOOSE) {
            aAllOlpCardList = olpDeviceBean;
            showDialog();
        } else if (type == TYPE_Z_CARD_CHOOSE) {
            zAllOlpCardList = olpDeviceBean;
            showDialog();
        }
    }

    @Override
    public void fillEndInfoByOlpCard(int type, OlpCardEndInfo olpCardEndInfo) {


        if (type == TYPE_A_CARD_CHOOSE) {
            chooseZOlpDevice = new OlpByRoom.OLPDeviceBean(olpCardEndInfo.getINT_ID(), olpCardEndInfo.getZH_LABEL());
            chooseZOlpCard = new OlpByRoom.OLPDeviceBean(olpCardEndInfo.getCard().getINT_ID(), olpCardEndInfo.getCard().getZH_LABEL());
            zDeviceVh.contentTv.setText(chooseZOlpDevice.getZH_LABEL());
            zCardVh.contentTv.setText(chooseZOlpCard.getZH_LABEL());
        } else if (type == TYPE_Z_CARD_CHOOSE) {
            chooseAOlpDevice = new OlpByRoom.OLPDeviceBean(olpCardEndInfo.getINT_ID(), olpCardEndInfo.getZH_LABEL());
            chooseAOlpCard = new OlpByRoom.OLPDeviceBean(olpCardEndInfo.getCard().getINT_ID(), olpCardEndInfo.getCard().getZH_LABEL());
            aDeviceVh.contentTv.setText(chooseAOlpDevice.getZH_LABEL());
            aCardVh.contentTv.setText(chooseAOlpCard.getZH_LABEL());
        }
    }

    @Override
    public void olpBindSuccess() {
        //刷新
        RxBus.getInstance().send("router_refresh");
        ((AppCompatActivity) mContext).getSupportFragmentManager().popBackStackImmediate();

    }


    @Override
    public void setPresenter(OlpBindConstract.Presenter presenter) {
        this.mPresenter = presenter;
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

    public static class SelectOlpCardViewHoder {
        RecyclerView dataListRv;
        List<OlpByRoom.OLPDeviceBean> mDataList = new ArrayList<>();
        Context mContext;
        CommonAdapter<OlpByRoom.OLPDeviceBean> commonAdapter;

        OlpDeviceCardClick mOlpDeviceCardClick;


        public void setmOlpDeviceCardClick(OlpDeviceCardClick olpDeviceCardClick) {
            mOlpDeviceCardClick = olpDeviceCardClick;
        }

        SelectOlpCardViewHoder(View view) {
            dataListRv = view.findViewById(R.id.data_list);
            mContext = view.getContext();
            dataListRv.setLayoutManager(new LinearLayoutManager(mContext));
            dataListRv.setHasFixedSize(true);
            commonAdapter = new CommonAdapter<OlpByRoom.OLPDeviceBean>(mContext, R.layout.item_topo_list, mDataList) {
                @Override
                protected void convert(ViewHolder holder, OlpByRoom.OLPDeviceBean olpDeviceBean, int position) {
                    holder.setText(R.id.tv_topo_name, olpDeviceBean.getZH_LABEL());

                }

            };
            commonAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                    if (mOlpDeviceCardClick != null) {
                        mOlpDeviceCardClick.itemClickListener(mDataList.get(position), type);
                    }
                }

                @Override
                public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                    return false;
                }
            });
            dataListRv.setAdapter(commonAdapter);
        }

        public void setmDataList(List<OlpByRoom.OLPDeviceBean> olpDeviceBeanList) {
            mDataList.clear();
            mDataList.addAll(olpDeviceBeanList);
            commonAdapter.notifyDataSetChanged();
        }
    }

    public interface OlpDeviceCardClick {
        public void itemClickListener(OlpByRoom.OLPDeviceBean olpDeviceBean, int type);
    }
}
