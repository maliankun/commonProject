package com.inspur.osp.data.bean;

/**
 * author:liyakun
 * date:2019/9/5 9:17
 * descripte:网元详情信息
 */
public class CellDetailInfo {

    private String cnName;
    private String enName;
    private Boolean show;
    private String value;


    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public Boolean getShow() {
        return show;
    }

    public void setShow(Boolean show) {
        this.show = show;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
