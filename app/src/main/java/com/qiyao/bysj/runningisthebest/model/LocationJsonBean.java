package com.qiyao.bysj.runningisthebest.model;

import java.util.List;

/**
 * Created by qiyao on 2017/3/16.
 */

public class LocationJsonBean {
    private String name;
    private List<LocationJsonBean> sub;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<LocationJsonBean> getSub() {
        return sub;
    }

    public void setSub(List<LocationJsonBean> sub) {
        this.sub = sub;
    }
}
