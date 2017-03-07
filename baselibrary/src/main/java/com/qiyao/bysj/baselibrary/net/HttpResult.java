package com.qiyao.bysj.baselibrary.net;

/**
 * Created by lvqiyao (amorfatilay@163.com).
 * 2017/2/22 20:38.
 * 类描述：
 */

public class HttpResult<T> {
    private int code;
    private String callbackMsg;
    private T responseContext;

    public static final String ERROR = "Error";
    public static final String SUCCESS = "Success";

    public boolean isSuccess() {
        return callbackMsg.equals(SUCCESS);
    }

    public T getResponseContext() {
        return responseContext;
    }

    public void setResponseContext(T responseContext) {
        this.responseContext = responseContext;
    }

    public int getCode() {
        return code;
    }

    public String getCallbackMsg() {
        return callbackMsg;
    }
}
