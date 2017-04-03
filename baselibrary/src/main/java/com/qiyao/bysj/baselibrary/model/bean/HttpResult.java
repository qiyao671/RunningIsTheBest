package com.qiyao.bysj.baselibrary.model.bean;

/**
 * Created by lvqiyao (amorfatilay@163.com).
 * 2017/2/22 20:38.
 * 类描述：
 */

public class HttpResult<T> {
    /* 请求返回 状态的信息 */
    private Boolean result;
    /* 请求返回 具体内容*/
    private T responseContext;
    /*返回请求的msg*/
    private String msg;

    public boolean isSuccess() {
        return result;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public T getResponseContext() {
        return responseContext;
    }

    public void setResponseContext(T responseContext) {
        this.responseContext = responseContext;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
