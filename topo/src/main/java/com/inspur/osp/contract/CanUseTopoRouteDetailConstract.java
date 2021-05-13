package com.inspur.osp.contract;

import com.inspur.osp.BasePresenter;
import com.inspur.osp.BaseView;
import com.inspur.osp.data.bean.CheckRouteStateBean;

/**
 * Created by liuchen01 on 2019/9/24.
 */

public class CanUseTopoRouteDetailConstract {

    public interface View extends BaseView<Presenter> {
        void fillBindRelation(CheckRouteStateBean checkRouteStateBean);

        void bindRouteSuccess();

        void bindRouteOlpSuccess();
    }

    public interface Presenter extends BasePresenter {

        void getBindRelations(String topolinkId, String cableids);

        void saveCheckedRoute(String topolinkId, String cableids);

        void saveCheckedRoute4OLP(String topolinkId, String cableids);

        void cancelBind(String topolinkId, String cableids);

        void cancelBindByUuid(String uuid);
    }
}
