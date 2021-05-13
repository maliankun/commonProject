package com.inspur.osp.data.bean;

import java.util.List;

/**
 * Created by liuchen01 on 2019/10/25.
 */

public class OlpByRoom {

    private List<OLPDeviceBean> orig;
    private List<OLPDeviceBean> dest;

    public List<OLPDeviceBean> getOrig() {
        return orig;
    }

    public void setOrig(List<OLPDeviceBean> orig) {
        this.orig = orig;
    }

    public List<OLPDeviceBean> getDest() {
        return dest;
    }

    public void setDest(List<OLPDeviceBean> dest) {
        this.dest = dest;
    }


    public static class OLPDeviceBean {
        /**
         * INT_ID : 1936533430
         * ZH_LABEL : 石家庄开发区干线
         */

        private String INT_ID;
        private String ZH_LABEL;

        public OLPDeviceBean(String int_id, String zh_lable) {
            INT_ID = int_id;
            ZH_LABEL = zh_lable;
        }

        public String getINT_ID() {
            return INT_ID;
        }

        public void setINT_ID(String INT_ID) {
            this.INT_ID = INT_ID;
        }

        public String getZH_LABEL() {
            return ZH_LABEL;
        }

        public void setZH_LABEL(String ZH_LABEL) {
            this.ZH_LABEL = ZH_LABEL;
        }
    }
}
