package com.qiyao.bysj.runningisthebest.module.run.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Polyline;
import com.amap.api.maps.model.PolylineOptions;
import com.qiyao.bysj.baselibrary.common.CommonConstants;
import com.qiyao.bysj.baselibrary.ui.fragment.ADataBindingFragment;
import com.qiyao.bysj.baselibrary.viewmodel.IViewModel;
import com.qiyao.bysj.runningisthebest.R;
import com.qiyao.bysj.runningisthebest.databinding.FragmentRunTrackBinding;
import com.qiyao.bysj.runningisthebest.model.bean.RunBean;
import com.qiyao.bysj.runningisthebest.module.run.viewmodel.RunTrackViewModel;

import java.util.List;

/**
 * Created by lvqiyao (amorfatilay@163.com).
 * 2017/3/11 22:14.
 * 类描述：
 */

public class RunTrackFragment extends ADataBindingFragment
        implements IRunTrackView {
    private AMap map;
    private MapView mapView;
    @NonNull
    @Override
    protected IViewModel createViewModel(Bundle arguments) {
        RunBean runBean = getArguments().getParcelable(CommonConstants.KEY_FRAGMENT_IN_PAGER_ARGS);
        return new RunTrackViewModel(this, runBean, this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mapView = getBinding().map;
        mapView.onCreate(savedInstanceState);
        init();
        setUpMap();
    }


    /**
     * 初始化
     */
    private void init() {
        if (map == null) {
            map = mapView.getMap();
        }
    }


    private void setUpMap() {
        map.moveCamera(CameraUpdateFactory.zoomTo(6));
        map.setMapTextZIndex(2);
    }


    @Override
    protected int layoutRes() {
        return R.layout.fragment_run_track;
    }

    @Override
    public FragmentRunTrackBinding getBinding() {
        return (FragmentRunTrackBinding) super.getBinding();
    }

    @Override
    public void addTrackToMap(List<LatLng> track) {
        Polyline trackPolyline = map.addPolyline(new PolylineOptions().color(Color.BLUE));
        trackPolyline.setPoints(track);
    }
}
