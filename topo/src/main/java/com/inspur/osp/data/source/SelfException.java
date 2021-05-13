package com.inspur.osp.data.source;

import java.util.Map;

/**
 * Created by liuchen_ on 2017/12/23.
 */

public class SelfException extends RuntimeException {

    private int code;  //异常对应的返回码
    private String message;  //异常对应的描述信息

    private Map<String,String> extraMap ;

    public static final int TIME_OUT = 4444;
    public static final int CODE_DEAULT = 4444;
    public static final int DATA_EMPTY = 4445;


    public SelfException() {
        super();
    }

    public SelfException(String message) {
        super(message);
        this.message = message;
    }

    public Map<String, String> getExtraMap() {
        return extraMap;
    }

    public void setExtraMap(Map<String, String> extraMap) {
        this.extraMap = extraMap;
    }

    public SelfException(int retCd, String msgDes) {
        this(msgDes);
        this.code = retCd;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "SelfException{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", extraMap=" + extraMap +
                '}';
    }
}
