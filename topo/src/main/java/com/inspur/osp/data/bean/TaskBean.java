package com.inspur.osp.data.bean;

import com.cocoahero.android.geojson.GeoJSONObject;
import com.google.gson.JsonObject;

import java.util.List;

/**
 * Created by liuchen_ on 2017/12/16.
 */

public class TaskBean {

    private String coordtype;
    private List<ResultsBean> results;

    public String getCoordtype() {
        return coordtype;
    }

    public void setCoordtype(String coordtype) {
        this.coordtype = coordtype;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean  {
        /**
         * layerId : zhandian
         * layerName : wx2_query
         * attributes : {"ATTR01":"null","ATTR02":"null","ATTR05":"null","LNG":"117.01335668","ATTR06":"null","ROUTE_UUID":"64d35935-0dd2-4df8-9f35-075e3f741d7a","ATTR03":"null","ATTR04":"null","ATTR09":"null","ATTR07":"null","ATTR08":"null","TIME_STAMP":"1512575736000","AMAP_DISTRICT":"历下区","DML_STATUS":"1","OBJECTID":"1280","STATEFLAG":"0","TASK_UUID":"PL_WX_TASK:588f6511-2c74-401a-9a23-fa4e117ffc1b","CREATOR":"admin","AMAP_STREETNUMBER":"null","LABEL":"20171206测试管道-10-关键点","ID":"835fed9e-6529-42d0-a363-dc540046a76a","UUID":"835fed9e-6529-42d0-a363-dc540046a76a","AMAP_CITY":"济南市","MODIFIER":"null","PROJECT_UUID":"null","RMW_UUID":"null","STYLE":"null","LC_STATUS":"8","RES_TYPE":"9503","AMAP_ADDRESS":"山东省济南市历下区泉城路街道泺源大街95号山东邮政大厦","NAME":"20171206测试管道-10-关键点","AMAP_CITYCODE":"0531","COUNTY_UUID":"150","CREATE_TYPE":"null","ADDRESS":"1","MODIFY_TIME":"0","AMAP_TOWNSHIP":"泉城路街道","BUSINESS_UUID":"null","X":"117.01925602716572","AMAP_STREET":"泺源大街","Y":"36.66254245977305","ATTR20":"null","ATTR12":"null","CREAT_TIME":"1512575736000","ATTR13":"null","ATTR10":"null","ATTR11":"null","CITY_UUID":"32","ATTR16":"null","ATTR17":"null","ATTR14":"null","ATTR15":"null","ATTR18":"null","AMAP_ADCODE":"370102","ATTR19":"null","SYSTEM_LEVEL":"1","AMAP_PROVINCE":"山东省","LAT":"36.6622428"}
         * geometryType : esriGeometryPoint
         * geometry : {"x":117.01925602716572,"y":36.66254245977305,"paths":null}
         * geoJson : {"type":"Point","coordinates":[117.01925602716572,36.66254245977305],"crs":{"type":"name","properties":{"name":"EPSG:4326"}}}
         */

        private String layerId;
        private String layerName;
        private AttributesBean attributes;
        private String geometryType;

        private GeoJSONObject mGeoJsonObject;
        private JsonObject geoJson;


        public JsonObject getGeoJson() {
            return geoJson;
        }
        public void setGeoJson(JsonObject geoJson) {
            this.geoJson = geoJson;
        }


        public GeoJSONObject getmGeoJsonObject() {
            return mGeoJsonObject;
        }

        public void setmGeoJsonObject(GeoJSONObject mGeoJsonObject) {
            this.mGeoJsonObject = mGeoJsonObject;
        }

        public String getLayerId() {
            return layerId;
        }

        public void setLayerId(String layerId) {
            this.layerId = layerId;
        }

        public String getLayerName() {
            return layerName;
        }

        public void setLayerName(String layerName) {
            this.layerName = layerName;
        }

        public AttributesBean getAttributes() {
            return attributes;
        }

        public void setAttributes(AttributesBean attributes) {
            this.attributes = attributes;
        }

        public String getGeometryType() {
            return geometryType;
        }

        public void setGeometryType(String geometryType) {
            this.geometryType = geometryType;
        }



        public static class AttributesBean {
            /**
             * ATTR01 : null
             * ATTR02 : null
             * ATTR05 : null
             * LNG : 117.01335668
             * ATTR06 : null
             * ROUTE_UUID : 64d35935-0dd2-4df8-9f35-075e3f741d7a
             * ATTR03 : null
             * ATTR04 : null
             * ATTR09 : null
             * ATTR07 : null
             * ATTR08 : null
             * TIME_STAMP : 1512575736000
             * AMAP_DISTRICT : 历下区
             * DML_STATUS : 1
             * OBJECTID : 1280
             * STATEFLAG : 0
             * TASK_UUID : PL_WX_TASK:588f6511-2c74-401a-9a23-fa4e117ffc1b
             * CREATOR : admin
             * AMAP_STREETNUMBER : null
             * LABEL : 20171206测试管道-10-关键点
             * ID : 835fed9e-6529-42d0-a363-dc540046a76a
             * UUID : 835fed9e-6529-42d0-a363-dc540046a76a
             * AMAP_CITY : 济南市
             * MODIFIER : null
             * PROJECT_UUID : null
             * RMW_UUID : null
             * STYLE : null
             * LC_STATUS : 8
             * RES_TYPE : 9503
             * AMAP_ADDRESS : 山东省济南市历下区泉城路街道泺源大街95号山东邮政大厦
             * NAME : 20171206测试管道-10-关键点
             * AMAP_CITYCODE : 0531
             * COUNTY_UUID : 150
             * CREATE_TYPE : null
             * ADDRESS : 1
             * MODIFY_TIME : 0
             * AMAP_TOWNSHIP : 泉城路街道
             * BUSINESS_UUID : null
             * X : 117.01925602716572
             * AMAP_STREET : 泺源大街
             * Y : 36.66254245977305
             * ATTR20 : null
             * ATTR12 : null
             * CREAT_TIME : 1512575736000
             * ATTR13 : null
             * ATTR10 : null
             * ATTR11 : null
             * CITY_UUID : 32
             * ATTR16 : null
             * ATTR17 : null
             * ATTR14 : null
             * ATTR15 : null
             * ATTR18 : null
             * AMAP_ADCODE : 370102
             * ATTR19 : null
             * SYSTEM_LEVEL : 1
             * AMAP_PROVINCE : 山东省
             * LAT : 36.6622428
             */

            private String ATTR01;
            private String ATTR02;
            private String ATTR05;
            private String LNG;
            private String ATTR06;
            private String ROUTE_UUID;
            private String ATTR03;
            private String ATTR04;
            private String ATTR09;
            private String ATTR07;
            private String ATTR08;
            private String TIME_STAMP;
            private String AMAP_DISTRICT;
            private String DML_STATUS;
            private String OBJECTID;
            private String STATEFLAG;
            private String TASK_UUID;
            private String CREATOR;
            private String AMAP_STREETNUMBER;
            private String LABEL;
            private String ID;
            private String UUID;
            private String AMAP_CITY;
            private String MODIFIER;
            private String PROJECT_UUID;
            private String RMW_UUID;
            private String STYLE;
            private String LC_STATUS;
            private String RES_TYPE;
            private String DISTANCE;
            private double METER;

            public String getROUTE_TYPE() {
                return ROUTE_TYPE;
            }

            public void setROUTE_TYPE(String ROUTE_TYPE) {
                this.ROUTE_TYPE = ROUTE_TYPE;
            }

            private String ROUTE_TYPE;
            private String AMAP_ADDRESS;
            private String NAME;
            private String AMAP_CITYCODE;
            private String COUNTY_UUID;
            private String CREATE_TYPE;
            private String ADDRESS;
            private String MODIFY_TIME;
            private String AMAP_TOWNSHIP;
            private String BUSINESS_UUID;
            private String X;
            private String AMAP_STREET;
            private String Y;
            private String ATTR20;
            private String ATTR12;
            private String CREAT_TIME;
            private String ATTR13;
            private String ATTR10;
            private String ATTR11;
            private String CITY_UUID;
            private String ATTR16;
            private String ATTR17;
            private String ATTR14;
            private String ATTR15;
            private String ATTR18;
            private String AMAP_ADCODE;
            private String ATTR19;
            private String SYSTEM_LEVEL;
            private String AMAP_PROVINCE;
            private String LAT;
            private String BREAK_REASON;
            private String BREAK_DESC;


            public String getATTR01() {
                return ATTR01;
            }

            public void setATTR01(String ATTR01) {
                this.ATTR01 = ATTR01;
            }

            public String getATTR02() {
                return ATTR02;
            }

            public void setATTR02(String ATTR02) {
                this.ATTR02 = ATTR02;
            }

            public String getATTR05() {
                return ATTR05;
            }

            public void setATTR05(String ATTR05) {
                this.ATTR05 = ATTR05;
            }

            public String getLNG() {
                return LNG;
            }

            public void setLNG(String LNG) {
                this.LNG = LNG;
            }

            public String getATTR06() {
                return ATTR06;
            }

            public void setATTR06(String ATTR06) {
                this.ATTR06 = ATTR06;
            }

            public String getROUTE_UUID() {
                return ROUTE_UUID;
            }

            public void setROUTE_UUID(String ROUTE_UUID) {
                this.ROUTE_UUID = ROUTE_UUID;
            }

            public String getATTR03() {
                return ATTR03;
            }

            public void setATTR03(String ATTR03) {
                this.ATTR03 = ATTR03;
            }

            public String getATTR04() {
                return ATTR04;
            }

            public void setATTR04(String ATTR04) {
                this.ATTR04 = ATTR04;
            }

            public String getATTR09() {
                return ATTR09;
            }

            public void setATTR09(String ATTR09) {
                this.ATTR09 = ATTR09;
            }

            public String getATTR07() {
                return ATTR07;
            }

            public void setATTR07(String ATTR07) {
                this.ATTR07 = ATTR07;
            }

            public String getATTR08() {
                return ATTR08;
            }

            public void setATTR08(String ATTR08) {
                this.ATTR08 = ATTR08;
            }

            public String getTIME_STAMP() {
                return TIME_STAMP;
            }

            public void setTIME_STAMP(String TIME_STAMP) {
                this.TIME_STAMP = TIME_STAMP;
            }

            public String getAMAP_DISTRICT() {
                return AMAP_DISTRICT;
            }

            public void setAMAP_DISTRICT(String AMAP_DISTRICT) {
                this.AMAP_DISTRICT = AMAP_DISTRICT;
            }

            public String getDML_STATUS() {
                return DML_STATUS;
            }

            public void setDML_STATUS(String DML_STATUS) {
                this.DML_STATUS = DML_STATUS;
            }

            public String getOBJECTID() {
                return OBJECTID;
            }

            public void setOBJECTID(String OBJECTID) {
                this.OBJECTID = OBJECTID;
            }

            public String getSTATEFLAG() {
                return STATEFLAG;
            }

            public void setSTATEFLAG(String STATEFLAG) {
                this.STATEFLAG = STATEFLAG;
            }

            public String getTASK_UUID() {
                return TASK_UUID;
            }

            public void setTASK_UUID(String TASK_UUID) {
                this.TASK_UUID = TASK_UUID;
            }

            public String getCREATOR() {
                return CREATOR;
            }

            public void setCREATOR(String CREATOR) {
                this.CREATOR = CREATOR;
            }

            public String getAMAP_STREETNUMBER() {
                return AMAP_STREETNUMBER;
            }

            public void setAMAP_STREETNUMBER(String AMAP_STREETNUMBER) {
                this.AMAP_STREETNUMBER = AMAP_STREETNUMBER;
            }

            public String getLABEL() {
                return LABEL;
            }

            public void setLABEL(String LABEL) {
                this.LABEL = LABEL;
            }

            public String getID() {
                return ID;
            }

            public void setID(String ID) {
                this.ID = ID;
            }

            public String getUUID() {
                return UUID;
            }

            public void setUUID(String UUID) {
                this.UUID = UUID;
            }

            public String getAMAP_CITY() {
                return AMAP_CITY;
            }

            public void setAMAP_CITY(String AMAP_CITY) {
                this.AMAP_CITY = AMAP_CITY;
            }

            public String getMODIFIER() {
                return MODIFIER;
            }

            public void setMODIFIER(String MODIFIER) {
                this.MODIFIER = MODIFIER;
            }

            public String getPROJECT_UUID() {
                return PROJECT_UUID;
            }

            public void setPROJECT_UUID(String PROJECT_UUID) {
                this.PROJECT_UUID = PROJECT_UUID;
            }

            public String getRMW_UUID() {
                return RMW_UUID;
            }

            public void setRMW_UUID(String RMW_UUID) {
                this.RMW_UUID = RMW_UUID;
            }

            public String getSTYLE() {
                return STYLE;
            }

            public void setSTYLE(String STYLE) {
                this.STYLE = STYLE;
            }

            public String getLC_STATUS() {
                return LC_STATUS;
            }

            public void setLC_STATUS(String LC_STATUS) {
                this.LC_STATUS = LC_STATUS;
            }

            public String getRES_TYPE() {
                return RES_TYPE;
            }

            public void setRES_TYPE(String RES_TYPE) {
                this.RES_TYPE = RES_TYPE;
            }

            public String getAMAP_ADDRESS() {
                return AMAP_ADDRESS;
            }

            public void setAMAP_ADDRESS(String AMAP_ADDRESS) {
                this.AMAP_ADDRESS = AMAP_ADDRESS;
            }

            public String getNAME() {
                return NAME;
            }

            public void setNAME(String NAME) {
                this.NAME = NAME;
            }

            public String getAMAP_CITYCODE() {
                return AMAP_CITYCODE;
            }

            public void setAMAP_CITYCODE(String AMAP_CITYCODE) {
                this.AMAP_CITYCODE = AMAP_CITYCODE;
            }

            public String getCOUNTY_UUID() {
                return COUNTY_UUID;
            }

            public void setCOUNTY_UUID(String COUNTY_UUID) {
                this.COUNTY_UUID = COUNTY_UUID;
            }

            public String getCREATE_TYPE() {
                return CREATE_TYPE;
            }

            public void setCREATE_TYPE(String CREATE_TYPE) {
                this.CREATE_TYPE = CREATE_TYPE;
            }

            public String getADDRESS() {
                return ADDRESS;
            }

            public void setADDRESS(String ADDRESS) {
                this.ADDRESS = ADDRESS;
            }

            public String getMODIFY_TIME() {
                return MODIFY_TIME;
            }

            public void setMODIFY_TIME(String MODIFY_TIME) {
                this.MODIFY_TIME = MODIFY_TIME;
            }

            public String getAMAP_TOWNSHIP() {
                return AMAP_TOWNSHIP;
            }

            public void setAMAP_TOWNSHIP(String AMAP_TOWNSHIP) {
                this.AMAP_TOWNSHIP = AMAP_TOWNSHIP;
            }

            public String getBUSINESS_UUID() {
                return BUSINESS_UUID;
            }

            public void setBUSINESS_UUID(String BUSINESS_UUID) {
                this.BUSINESS_UUID = BUSINESS_UUID;
            }

            public String getX() {
                return X;
            }

            public void setX(String X) {
                this.X = X;
            }

            public String getAMAP_STREET() {
                return AMAP_STREET;
            }

            public void setAMAP_STREET(String AMAP_STREET) {
                this.AMAP_STREET = AMAP_STREET;
            }

            public String getY() {
                return Y;
            }

            public void setY(String Y) {
                this.Y = Y;
            }

            public String getATTR20() {
                return ATTR20;
            }

            public void setATTR20(String ATTR20) {
                this.ATTR20 = ATTR20;
            }

            public String getATTR12() {
                return ATTR12;
            }

            public void setATTR12(String ATTR12) {
                this.ATTR12 = ATTR12;
            }

            public String getCREAT_TIME() {
                return CREAT_TIME;
            }

            public void setCREAT_TIME(String CREAT_TIME) {
                this.CREAT_TIME = CREAT_TIME;
            }

            public String getATTR13() {
                return ATTR13;
            }

            public void setATTR13(String ATTR13) {
                this.ATTR13 = ATTR13;
            }

            public String getATTR10() {
                return ATTR10;
            }

            public void setATTR10(String ATTR10) {
                this.ATTR10 = ATTR10;
            }

            public String getATTR11() {
                return ATTR11;
            }

            public void setATTR11(String ATTR11) {
                this.ATTR11 = ATTR11;
            }

            public String getCITY_UUID() {
                return CITY_UUID;
            }

            public void setCITY_UUID(String CITY_UUID) {
                this.CITY_UUID = CITY_UUID;
            }

            public String getATTR16() {
                return ATTR16;
            }

            public void setATTR16(String ATTR16) {
                this.ATTR16 = ATTR16;
            }

            public String getATTR17() {
                return ATTR17;
            }

            public void setATTR17(String ATTR17) {
                this.ATTR17 = ATTR17;
            }

            public String getATTR14() {
                return ATTR14;
            }

            public void setATTR14(String ATTR14) {
                this.ATTR14 = ATTR14;
            }

            public String getATTR15() {
                return ATTR15;
            }

            public void setATTR15(String ATTR15) {
                this.ATTR15 = ATTR15;
            }

            public String getATTR18() {
                return ATTR18;
            }

            public void setATTR18(String ATTR18) {
                this.ATTR18 = ATTR18;
            }

            public String getAMAP_ADCODE() {
                return AMAP_ADCODE;
            }

            public void setAMAP_ADCODE(String AMAP_ADCODE) {
                this.AMAP_ADCODE = AMAP_ADCODE;
            }

            public String getATTR19() {
                return ATTR19;
            }

            public void setATTR19(String ATTR19) {
                this.ATTR19 = ATTR19;
            }

            public String getSYSTEM_LEVEL() {
                return SYSTEM_LEVEL;
            }

            public void setSYSTEM_LEVEL(String SYSTEM_LEVEL) {
                this.SYSTEM_LEVEL = SYSTEM_LEVEL;
            }

            public String getAMAP_PROVINCE() {
                return AMAP_PROVINCE;
            }

            public String getDISTANCE() {
                return DISTANCE;
            }

            public void setDISTANCE(String DISTANCE) {
                this.DISTANCE = DISTANCE;
            }

            public void setAMAP_PROVINCE(String AMAP_PROVINCE) {
                this.AMAP_PROVINCE = AMAP_PROVINCE;
            }

            public String getLAT() {
                return LAT;
            }

            public void setLAT(String LAT) {
                this.LAT = LAT;
            }

            public double getMETER() {
                return METER;
            }

            public void setMETER(double METER) {
                this.METER = METER;
            }

            public String getBREAK_REASON() {
                return BREAK_REASON;
            }

            public void setBREAK_REASON(String BREAK_REASON) {
                this.BREAK_REASON = BREAK_REASON;
            }

            public String getBREAK_DESC() {
                return BREAK_DESC;
            }

            public void setBREAK_DESC(String BREAK_DESC) {
                this.BREAK_DESC = BREAK_DESC;
            }
        }

    }
}
