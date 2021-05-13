package com.inspur.osp.contract;

import com.inspur.osp.BasePresenter;
import com.inspur.osp.BaseView;

/**
 * Created by liuchen_ on 2018/1/13.
 */

public class BindRouteContract {

    public interface View extends BaseView<Presenter> {


    }

    public interface Presenter extends BasePresenter {
        void cancelBindRoute(String topolinkid, String cableids);

        void cancelCheckedRoute(String uuid);
    }
}
