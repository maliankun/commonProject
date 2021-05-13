package com.inspur.osp.contract;

import com.inspur.osp.BasePresenter;
import com.inspur.osp.BaseView;
import com.inspur.osp.data.bean.OlpByRoom;
import com.inspur.osp.data.bean.OlpCardEndInfo;

import java.util.List;

/**
 * Created by liuchen01 on 2019/9/24.
 */

public class OlpBindConstract {

    public interface View extends BaseView<Presenter> {
        void fillAzDeviceByRoom(OlpByRoom olpByRoom);

        void fillOlpCardByDevice(int type, List<OlpByRoom.OLPDeviceBean> olpDeviceBean);

        void fillEndInfoByOlpCard(int type, OlpCardEndInfo olpCardEndInfo);

        void olpBindSuccess();
    }

    public interface Presenter extends BasePresenter {

        void getAzOlpDeviceByRoom(String aRoomId, String zRoomId);

        void getOlpCardByOlpDevice(String neId, int type);

        void getEndInfoByOlpCard(String cardId, int type);

        void saveOlpDeviceBind(String topolLinkId, String cableids, String orig_ne_id_olp, String orig_card_id_olp, String dest_ne_id_olp, String dest_card_id_olp);
    }
}
