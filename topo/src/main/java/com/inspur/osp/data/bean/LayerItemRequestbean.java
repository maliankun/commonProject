package com.inspur.osp.data.bean;

/**
 * Created by liuchen_ on 2017/12/19.
 */

public class LayerItemRequestbean {


    public  String filter;
    public final String layerId;

    public LayerItemRequestbean(String layerId, String filter) {
        this.filter = filter;
        this.layerId = layerId;
    }
}
