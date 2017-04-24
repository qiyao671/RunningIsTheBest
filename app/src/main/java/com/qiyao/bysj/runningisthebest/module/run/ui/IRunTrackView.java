package com.qiyao.bysj.runningisthebest.module.run.ui;

import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;

import java.util.List;

/**
 * Created by lvqiyao (amorfatilay@163.com).
 * 2017/4/23 20:20.
 * 类描述：
 */

public interface IRunTrackView {
    void addTrackToMap(List<LatLng> track);

    void setMapScale(LatLngBounds bounds);
}
