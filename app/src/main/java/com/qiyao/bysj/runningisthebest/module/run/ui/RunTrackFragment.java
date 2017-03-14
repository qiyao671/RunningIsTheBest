package com.qiyao.bysj.runningisthebest.module.run.ui;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.qiyao.bysj.baselibrary.common.CommonConstants;
import com.qiyao.bysj.baselibrary.ui.fragment.ADataBindingFragment;
import com.qiyao.bysj.baselibrary.viewmodel.IViewModel;
import com.qiyao.bysj.runningisthebest.R;
import com.qiyao.bysj.runningisthebest.databinding.FragmentRunTrackBinding;
import com.qiyao.bysj.runningisthebest.databinding.FragmentTotalRunBinding;
import com.qiyao.bysj.runningisthebest.model.bean.RunBean;
import com.qiyao.bysj.runningisthebest.module.run.viewmodel.RunTrackViewModel;

/**
 * Created by lvqiyao (amorfatilay@163.com).
 * 2017/3/11 22:14.
 * 类描述：
 */

public class RunTrackFragment extends ADataBindingFragment
        implements LocationSource, AMapLocationListener {
    private AMap map;
    private MapView mapView;
    private OnLocationChangedListener mListener;
    private AMapLocationClient locationClient;
    private AMapLocationClientOption locationOption;
    @NonNull
    @Override
    protected IViewModel createViewModel(Bundle arguments) {
        RunBean runBean = getArguments().getParcelable(CommonConstants.KEY_FRAGMENT_IN_PAGER_ARGS);
        return new RunTrackViewModel(this, runBean);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mapView = getBinding().map;
        mapView.onCreate(savedInstanceState);
        init();
    }


    /**
     * 初始化
     */
    private void init() {
        if (map == null) {
            map = mapView.getMap();
            setUpMap();
        }
    }

    /**
     * 设置一些amap的属性
     */
    private void setUpMap() {
        map.setLocationSource(this);// 设置定位监听
        map.getUiSettings().setMyLocationButtonEnabled(true);// 设置默认定位按钮是否显示
        map.setMyLocationEnabled(true);// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
        // 设置定位的类型为定位模式 ，可以由定位、跟随或地图根据面向方向旋转几种
        map.setMyLocationType(AMap.LOCATION_TYPE_LOCATE);
        //滑动手势
        map.getUiSettings().setScrollGesturesEnabled(true);
        //缩放手势
        map.getUiSettings().setZoomGesturesEnabled(true);
        //倾斜手势
        map.getUiSettings().setTiltGesturesEnabled(false);
        //旋转手势
        map.getUiSettings().setRotateGesturesEnabled(false);
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
    public void activate(OnLocationChangedListener onLocationChangedListener) {
        mListener = onLocationChangedListener;
        if (locationClient == null) {
            //初始化定位
            locationClient = new AMapLocationClient(getActivity());
            //初始化定位参数
            locationOption = new AMapLocationClientOption();
            //设置定位回调监听
            locationClient.setLocationListener(this);
            //设置为高精度定位模式
            locationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            //设置定位参数
            locationClient.setLocationOption(locationOption);
            // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
            // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
            // 在定位结束后，在合适的生命周期调用onDestroy()方法
            // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
        }
    }

    @Override
    public void deactivate() {
        mListener = null;
        if (locationClient != null) {
            locationClient.stopLocation();
            locationClient.onDestroy();
        }
        locationClient = null;
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (mListener != null && aMapLocation != null) {
            if (aMapLocation.getErrorCode() == 0) {
                mListener.onLocationChanged(aMapLocation);// 显示系统小蓝点
                map.moveCamera(CameraUpdateFactory.zoomTo(18));
            } else {
                String errText = "定位失败," + aMapLocation.getErrorCode()+ ": " + aMapLocation.getErrorInfo();
                Log.e("AMapErr",errText);
            }
        }
    }
}
