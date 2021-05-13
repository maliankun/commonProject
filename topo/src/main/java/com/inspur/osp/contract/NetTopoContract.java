package com.inspur.osp.contract;

import com.inspur.osp.BasePresenter;
import com.inspur.osp.BaseView;
import com.inspur.osp.data.bean.ElementSubnetInfo;

/**
 *
 */
public class NetTopoContract {

    public interface View extends BaseView<NetTopoContract.Presenter> {

       void showTopo(ElementSubnetInfo elementSubnetInfo);
    }

    public interface Presenter extends BasePresenter {
        void queryElemnetSubnet(String aElementId,String aPortId,String zElementId, String zPortId);
    }

}
