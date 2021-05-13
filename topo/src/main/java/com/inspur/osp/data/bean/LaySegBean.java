package com.inspur.osp.data.bean;

import com.amap.api.maps.model.LatLng;

import java.util.List;

/**
 * Created by liuchen01 on 2019/10/21.
 */

public class LaySegBean {


    /**
     * uuid : 1371084894
     * name : 张家口张家口市圆通快递0002号电杆-张家口市市府东大街0008号电杆
     * label : null
     * c_length : 22
     * a_point : 685304691
     * z_point : 466447220
     * shape : LINESTRING  ( 114.90300212 40.76564061, 114.90312000 40.76582000)
     */

    private String uuid;
    private String name;
    private Object label;
    private String c_length;
    private String a_point;
    private String z_point;
    private String shape;

    List<LatLng> latLngs;

    public void setLatLngs(List<LatLng> latLngs) {
        this.latLngs = latLngs;
    }

    public List<LatLng> getLatLngs() {
        return latLngs;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getLabel() {
        return label;
    }

    public void setLabel(Object label) {
        this.label = label;
    }

    public String getC_length() {
        return c_length;
    }

    public void setC_length(String c_length) {
        this.c_length = c_length;
    }

    public String getA_point() {
        return a_point;
    }

    public void setA_point(String a_point) {
        this.a_point = a_point;
    }

    public String getZ_point() {
        return z_point;
    }

    public void setZ_point(String z_point) {
        this.z_point = z_point;
    }

    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }
}
