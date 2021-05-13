package com.inspur.osp.data.bean;

import java.util.List;

public class NeRouteBean {


    /**
     * dest_site_id_val : 11中老校区1号食堂
     * cables : [{"UUID":"363480116","NAME":"晋州中兴街管道040号井-晋州中兴街管道041号井","C_LENGTH":90.89,"SHAPE":"LINESTRING  ( 115.01872704 38.02572504, 115.01769096 38.02576896)"},{"UUID":"488563844","NAME":"晋州西环管道002号井-石家庄晋州市新华路（滨河路-富强路）0002号人井","C_LENGTH":9,"SHAPE":"LINESTRING  ( 115.01669412 38.02575600, 115.01659188 38.02574700)"},{"UUID":"488810809","NAME":"晋州北环管道020号井-晋州北环管道021号井","C_LENGTH":122.27,"SHAPE":"LINESTRING  ( 115.03706400 38.03552703, 115.03566900 38.03556996)"},{"UUID":"492451108","NAME":"晋州北环管道023号井-晋州北环管道024号井","C_LENGTH":49.39,"SHAPE":"LINESTRING  ( 115.03407492 38.03560200, 115.03351116 38.03561199)"}]
     * dest_ne_id_val : (撤除)建工集团-2(4-9)建工新牌坊地产大厦项目部-集响-集响瑞斯康达虚拟机房-光收-RC512_28
     * orig_ne_id_val : null
     * ORIG_NE_ID : 123
     * dest_site_id : 393955710
     * DEST_NE_ID : 16945903
     * DEST_ROOM_ID : 1993898639
     * orig_site_id : 122490484
     * orig_room_id_val : 张家口市万全区孔家庄镇红都电讯二部用户站点机房
     * topoId : 123
     * orig_site_id_val :   中国重庆渝北机电工程学院行政楼5F楼梯间
     * ORIG_ROOM_ID : 2001387367
     * dest_room_id_val : 沧州市运河区环保局机房
     * topoName : 测试拓扑1
     */

    private String dest_site_id_val;
    private String dest_ne_id_val;
    private String orig_ne_id_val;
    private String ORIG_NE_ID;
    private String dest_site_id;
    private String DEST_NE_ID;
    private String DEST_ROOM_ID;
    private String orig_site_id;
    private String orig_room_id_val;
    private String topoId;
    private String orig_site_id_val;
    private String ORIG_ROOM_ID;
    private String dest_room_id_val;
    private String topoName;
    private List<CablesBean> cables;

    public String getDest_site_id_val() {
        return dest_site_id_val;
    }

    public void setDest_site_id_val(String dest_site_id_val) {
        this.dest_site_id_val = dest_site_id_val;
    }

    public String getDest_ne_id_val() {
        return dest_ne_id_val;
    }

    public void setDest_ne_id_val(String dest_ne_id_val) {
        this.dest_ne_id_val = dest_ne_id_val;
    }

    public String getOrig_ne_id_val() {
        return orig_ne_id_val;
    }

    public void setOrig_ne_id_val(String orig_ne_id_val) {
        this.orig_ne_id_val = orig_ne_id_val;
    }

    public String getORIG_NE_ID() {
        return ORIG_NE_ID;
    }

    public void setORIG_NE_ID(String ORIG_NE_ID) {
        this.ORIG_NE_ID = ORIG_NE_ID;
    }

    public String getDest_site_id() {
        return dest_site_id;
    }

    public void setDest_site_id(String dest_site_id) {
        this.dest_site_id = dest_site_id;
    }

    public String getDEST_NE_ID() {
        return DEST_NE_ID;
    }

    public void setDEST_NE_ID(String DEST_NE_ID) {
        this.DEST_NE_ID = DEST_NE_ID;
    }

    public String getDEST_ROOM_ID() {
        return DEST_ROOM_ID;
    }

    public void setDEST_ROOM_ID(String DEST_ROOM_ID) {
        this.DEST_ROOM_ID = DEST_ROOM_ID;
    }

    public String getOrig_site_id() {
        return orig_site_id;
    }

    public void setOrig_site_id(String orig_site_id) {
        this.orig_site_id = orig_site_id;
    }

    public String getOrig_room_id_val() {
        return orig_room_id_val;
    }

    public void setOrig_room_id_val(String orig_room_id_val) {
        this.orig_room_id_val = orig_room_id_val;
    }

    public String getTopoId() {
        return topoId;
    }

    public void setTopoId(String topoId) {
        this.topoId = topoId;
    }

    public String getOrig_site_id_val() {
        return orig_site_id_val;
    }

    public void setOrig_site_id_val(String orig_site_id_val) {
        this.orig_site_id_val = orig_site_id_val;
    }

    public String getORIG_ROOM_ID() {
        return ORIG_ROOM_ID;
    }

    public void setORIG_ROOM_ID(String ORIG_ROOM_ID) {
        this.ORIG_ROOM_ID = ORIG_ROOM_ID;
    }

    public String getDest_room_id_val() {
        return dest_room_id_val;
    }

    public void setDest_room_id_val(String dest_room_id_val) {
        this.dest_room_id_val = dest_room_id_val;
    }

    public String getTopoName() {
        return topoName;
    }

    public void setTopoName(String topoName) {
        this.topoName = topoName;
    }

    public List<CablesBean> getCables() {
        return cables;
    }

    public void setCables(List<CablesBean> cables) {
        this.cables = cables;
    }

    public static class CablesBean {
        /**
         * UUID : 363480116
         * NAME : 晋州中兴街管道040号井-晋州中兴街管道041号井
         * C_LENGTH : 90.89
         * SHAPE : LINESTRING  ( 115.01872704 38.02572504, 115.01769096 38.02576896)
         */

        private String UUID;
        private String NAME;
        private double C_LENGTH;
        private String SHAPE;

        public String getUUID() {
            return UUID;
        }

        public void setUUID(String UUID) {
            this.UUID = UUID;
        }

        public String getNAME() {
            return NAME;
        }

        public void setNAME(String NAME) {
            this.NAME = NAME;
        }

        public double getC_LENGTH() {
            return C_LENGTH;
        }

        public void setC_LENGTH(double C_LENGTH) {
            this.C_LENGTH = C_LENGTH;
        }

        public String getSHAPE() {
            return SHAPE;
        }

        public void setSHAPE(String SHAPE) {
            this.SHAPE = SHAPE;
        }
    }
}
