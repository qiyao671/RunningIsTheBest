package com.qiyao.bysj.baselibrary.model.bean;

import java.util.List;

/**
 * Created by lvqiyao (amorfatilay@163.com).
 * 2017/2/22 21:04.
 * 类描述：
 */

public class ListResult<T> {
    private List<T> ts;
    private boolean isEndPaging;

    public List<T> getList() {
        return ts;
    }

    public boolean isEndPaging() {
        return isEndPaging;
    }
}
