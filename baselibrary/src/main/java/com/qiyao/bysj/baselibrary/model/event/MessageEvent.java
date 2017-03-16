package com.qiyao.bysj.baselibrary.model.event;

/**
 * Created by lvqiyao (amorfatilay@163.com).
 * 2017/3/16 22:20.
 * 类描述：
 */

public class MessageEvent {
    private String name;
    private boolean success;
    private String msg;

    public MessageEvent(String name, String value) {
        this.name = name;
        this.msg = value;
    }

    public MessageEvent(String name, boolean success) {
        this.name = name;
        this.success = success;
    }

    public MessageEvent(String name, boolean success, String msg) {
        this.name = name;
        this.success = success;
        this.msg = msg;
    }

    public MessageEvent(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return msg;
    }

    public boolean isSuccess() {
        return success;
    }
}
