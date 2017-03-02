package com.qiyao.bysj.baselibrary.net;

/**
 * Created by lvqiyao (amorfatilay@163.com).
 * 2017/2/22 20:38.
 * 类描述：
 */

public class HttpResult<T> {
    private int resultCode;
    private String resultMessage;

    private T data;

    public boolean isSuccess() {
        // TODO: 2017/2/23
        return true;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getResultCode() {
        return resultCode;
    }

    public String getResultMessage() {
        return resultMessage;
    }
}
