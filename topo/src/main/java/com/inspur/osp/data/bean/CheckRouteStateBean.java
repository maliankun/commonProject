package com.inspur.osp.data.bean;

import java.util.List;

/**
 * Created by liuchen01 on 2019/10/24.
 */

public class CheckRouteStateBean {

    /**
     * code : 0
     * data : [{"UUID":"1571903912029","TOPO_ROUTE_UUID":"489847411","CABLE_UUID":"1718787321,1479647733","ORIG_NE_ID_OLP":1936533432,"ORIG_CARD_ID_OLP":1954167407,"DEST_NE_ID_OLP":1936533457,"DEST_CARD_ID_OLP":1954166694,"COUNTER":1}]
     * routedata : [{"UUID":"1571903912029","TOPO_ROUTE_UUID":"489847411","CABLE_UUID":"1718787321,1479647733","ORIG_NE_ID_OLP":1936533432,"ORIG_CARD_ID_OLP":1954167407,"DEST_NE_ID_OLP":1936533457,"DEST_CARD_ID_OLP":1954166694,"COUNTER":1}]
     */

    private int code;
    private List<DataBean> data;
    private List<RoutedataBean> routedata;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public List<RoutedataBean> getRoutedata() {
        return routedata;
    }

    public void setRoutedata(List<RoutedataBean> routedata) {
        this.routedata = routedata;
    }

    public static class DataBean {
        /**
         * UUID : 1571903912029
         * TOPO_ROUTE_UUID : 489847411
         * CABLE_UUID : 1718787321,1479647733
         * ORIG_NE_ID_OLP : 1936533432
         * ORIG_CARD_ID_OLP : 1954167407
         * DEST_NE_ID_OLP : 1936533457
         * DEST_CARD_ID_OLP : 1954166694
         * COUNTER : 1
         */

        private String UUID;
        private String TOPO_ROUTE_UUID;
        private String CABLE_UUID;
        private int ORIG_NE_ID_OLP;
        private int ORIG_CARD_ID_OLP;
        private int DEST_NE_ID_OLP;
        private int DEST_CARD_ID_OLP;
        private int COUNTER;

        public String getUUID() {
            return UUID;
        }

        public void setUUID(String UUID) {
            this.UUID = UUID;
        }

        public String getTOPO_ROUTE_UUID() {
            return TOPO_ROUTE_UUID;
        }

        public void setTOPO_ROUTE_UUID(String TOPO_ROUTE_UUID) {
            this.TOPO_ROUTE_UUID = TOPO_ROUTE_UUID;
        }

        public String getCABLE_UUID() {
            return CABLE_UUID;
        }

        public void setCABLE_UUID(String CABLE_UUID) {
            this.CABLE_UUID = CABLE_UUID;
        }

        public int getORIG_NE_ID_OLP() {
            return ORIG_NE_ID_OLP;
        }

        public void setORIG_NE_ID_OLP(int ORIG_NE_ID_OLP) {
            this.ORIG_NE_ID_OLP = ORIG_NE_ID_OLP;
        }

        public int getORIG_CARD_ID_OLP() {
            return ORIG_CARD_ID_OLP;
        }

        public void setORIG_CARD_ID_OLP(int ORIG_CARD_ID_OLP) {
            this.ORIG_CARD_ID_OLP = ORIG_CARD_ID_OLP;
        }

        public int getDEST_NE_ID_OLP() {
            return DEST_NE_ID_OLP;
        }

        public void setDEST_NE_ID_OLP(int DEST_NE_ID_OLP) {
            this.DEST_NE_ID_OLP = DEST_NE_ID_OLP;
        }

        public int getDEST_CARD_ID_OLP() {
            return DEST_CARD_ID_OLP;
        }

        public void setDEST_CARD_ID_OLP(int DEST_CARD_ID_OLP) {
            this.DEST_CARD_ID_OLP = DEST_CARD_ID_OLP;
        }

        public int getCOUNTER() {
            return COUNTER;
        }

        public void setCOUNTER(int COUNTER) {
            this.COUNTER = COUNTER;
        }
    }

    public static class RoutedataBean {
        /**
         * UUID : 1571903912029
         * TOPO_ROUTE_UUID : 489847411
         * CABLE_UUID : 1718787321,1479647733
         * ORIG_NE_ID_OLP : 1936533432
         * ORIG_CARD_ID_OLP : 1954167407
         * DEST_NE_ID_OLP : 1936533457
         * DEST_CARD_ID_OLP : 1954166694
         * COUNTER : 1
         */

        private String UUID;
        private String TOPO_ROUTE_UUID;
        private String CABLE_UUID;
        private String ORIG_NE_ID_OLP;
        private String ORIG_CARD_ID_OLP;
        private String DEST_NE_ID_OLP;
        private String DEST_CARD_ID_OLP;
        private int COUNTER;

        public String getUUID() {
            return UUID;
        }

        public void setUUID(String UUID) {
            this.UUID = UUID;
        }

        public String getTOPO_ROUTE_UUID() {
            return TOPO_ROUTE_UUID;
        }

        public void setTOPO_ROUTE_UUID(String TOPO_ROUTE_UUID) {
            this.TOPO_ROUTE_UUID = TOPO_ROUTE_UUID;
        }

        public String getCABLE_UUID() {
            return CABLE_UUID;
        }

        public void setCABLE_UUID(String CABLE_UUID) {
            this.CABLE_UUID = CABLE_UUID;
        }

        public String getORIG_NE_ID_OLP() {
            return ORIG_NE_ID_OLP;
        }

        public void setORIG_NE_ID_OLP(String ORIG_NE_ID_OLP) {
            this.ORIG_NE_ID_OLP = ORIG_NE_ID_OLP;
        }

        public String getORIG_CARD_ID_OLP() {
            return ORIG_CARD_ID_OLP;
        }

        public void setORIG_CARD_ID_OLP(String ORIG_CARD_ID_OLP) {
            this.ORIG_CARD_ID_OLP = ORIG_CARD_ID_OLP;
        }

        public String getDEST_NE_ID_OLP() {
            return DEST_NE_ID_OLP;
        }

        public void setDEST_NE_ID_OLP(String DEST_NE_ID_OLP) {
            this.DEST_NE_ID_OLP = DEST_NE_ID_OLP;
        }

        public String getDEST_CARD_ID_OLP() {
            return DEST_CARD_ID_OLP;
        }

        public void setDEST_CARD_ID_OLP(String DEST_CARD_ID_OLP) {
            this.DEST_CARD_ID_OLP = DEST_CARD_ID_OLP;
        }

        public int getCOUNTER() {
            return COUNTER;
        }

        public void setCOUNTER(int COUNTER) {
            this.COUNTER = COUNTER;
        }
    }
}
