package com.inspur.osp.data.bean;


import com.amap.api.maps.model.Polyline;

/**
 * Created by liuchen_ on 2017/12/21.
 */

public class PolylineObj {

    private Object dataObj;
    private Polyline mPolyline;

    public PolylineObj(Object dataObj, Polyline mPolyline) {
        this.dataObj = dataObj;
        this.mPolyline = mPolyline;
    }

    public Object getDataObj() {
        return dataObj;
    }

    public void setDataObj(Object dataObj) {
        this.dataObj = dataObj;
    }

    public Polyline getmPolyline() {
        return mPolyline;
    }

    public void setmPolyline(Polyline mPolyline) {
        this.mPolyline = mPolyline;
    }


    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
