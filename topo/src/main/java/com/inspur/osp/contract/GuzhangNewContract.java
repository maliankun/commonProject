package com.inspur.osp.contract;

import com.inspur.osp.BasePresenter;
import com.inspur.osp.BaseView;
import com.inspur.osp.data.bean.ACodeBean;
import com.inspur.osp.data.bean.GeoBreakDownEntity;
import com.inspur.osp.data.bean.LayerItemRequestbean;
import com.inspur.osp.data.bean.ModelEnumBean;
import com.inspur.osp.data.bean.TaskBean;

import java.io.File;

/**
 * @author liyakun
 *
 */
public class GuzhangNewContract {

    public interface View extends BaseView<Presenter> {

        public void enumFillView(String searchType, ModelEnumBean modelEnumBean);

        public void resourceWithCity(ModelEnumBean modelEnumBean);

        public void resourceWithCountry(ModelEnumBean modelEnumBean);

        public void fileUploadSuccess(String uuid, String filePath);

        public void setCityAndCounty(ACodeBean modelEnumBean);

        public void addSuccess();

        public void loadWireSegData(TaskBean taskBean, String layerId);

        public void deleteDateSucessInfo();

    }


    public interface Presenter extends BasePresenter {
        public void requestModelEnum(String type, String jsonParam);

        public void getCityByResource(String type, String jsonParam);

        public void getCountryByResource(String type, String jsonParam);

        public void addGuzhang(GeoBreakDownEntity taskCommitBean);

        public void uploadFile(File file, String longitude, String latitude);

        public void regionTranfer(String adCountyCode);

        public void muiltyBreakdownQuery(LayerItemRequestbean dataItem, String systemLevelIds, double xMin, double xMax, double yMin, double yMax, String layerId);

        public void deleteModelInfo(String modelName, String[] uuidList);
    }
}
