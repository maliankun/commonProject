package com.inspur.osp.data.bean;

import java.util.List;

/**
 * Created by liuchen01 on 2019/10/18.
 */

public class BaseBean<T> {
    public int code = 0;
    public String msg;
    public List<T> data;

}
