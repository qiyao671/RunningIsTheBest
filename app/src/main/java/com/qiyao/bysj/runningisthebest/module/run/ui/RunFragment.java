package com.qiyao.bysj.runningisthebest.module.run.ui;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.maps.model.Polyline;
import com.amap.api.maps.model.PolylineOptions;
import com.qiyao.bysj.baselibrary.ui.activity.FragmentContainerActivity;
import com.qiyao.bysj.baselibrary.ui.fragment.ADataBindingFragment;
import com.qiyao.bysj.baselibrary.viewmodel.IViewModel;
import com.qiyao.bysj.runningisthebest.R;
import com.qiyao.bysj.runningisthebest.databinding.FragmentRunBinding;
import com.qiyao.bysj.runningisthebest.module.run.viewmodel.RunViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qiyao on 2017/3/7.
 */

public class RunFragment extends ADataBindingFragment
        implements LocationSource, AMapLocationListener {
    private AMap map;
    private MapView mapView;
    private OnLocationChangedListener mListener;
    private AMapLocationClient locationClient;
    private AMapLocationClientOption locationOption;
    private List<LatLng> trackPoints = new ArrayList<>();
    private Polyline trackPolyline;

    private boolean isRunning = true;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getActivity().getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
        //隐藏toolbar
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity.getSupportActionBar() != null) {
            activity.getSupportActionBar().hide();
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mapView = getBinding().map;
        mapView.onCreate(savedInstanceState);
        init();
    }

    public static void newInstance(Activity activity) {
        FragmentContainerActivity.launch(activity, RunFragment.class);
    }

    @NonNull
    @Override
    protected IViewModel createViewModel(Bundle arguments) {
        return new RunViewModel();
    }

    @Override
    protected int layoutRes() {
        return R.layout.fragment_run;
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
        map.setMyLocationType(AMap.LOCATION_TYPE_MAP_FOLLOW);
//        map.getUiSettings().setCompassEnabled(true);    //指南针用于向 App 端用户展示地图方向，默认不显示
        setupLocationStyle();
        //滑动手势
        map.getUiSettings().setScrollGesturesEnabled(true);
        //缩放手势
        map.getUiSettings().setZoomGesturesEnabled(true);
        //倾斜手势
        map.getUiSettings().setTiltGesturesEnabled(false);
        //旋转手势
        map.getUiSettings().setRotateGesturesEnabled(true);
    }


    @Override
    public FragmentRunBinding getBinding() {
        return (FragmentRunBinding) super.getBinding();
    }


    /**
     * 激活定位
     */
    @Override
    public void activate(OnLocationChangedListener listener) {
        mListener = listener;
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
            locationClient.startLocation();//启动定位
        }
    }

    /**
     * 停止定位
     */
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
                if (isRunning) {
                    setTrack(aMapLocation);
                }
            } else {
                String errText = "定位失败," + aMapLocation.getErrorCode()+ ": " + aMapLocation.getErrorInfo();
                Log.e("AMapErr",errText);
            }
        }
    }

    /**
     * 设置自定义定位蓝点
     */
    private void setupLocationStyle(){
        // 自定义系统定位蓝点
        MyLocationStyle myLocationStyle = new MyLocationStyle();
        // 自定义定位蓝点图标
        myLocationStyle.myLocationIcon(BitmapDescriptorFactory.
                fromResource(R.drawable.gps_point));
        // 自定义精度范围的圆形边框颜色
        myLocationStyle.strokeColor(ContextCompat.getColor(getActivity(), R.color.md_blue_600));
        //自定义精度范围的圆形边框宽度
        myLocationStyle.strokeWidth(5);
        // 设置圆形的填充颜色
        myLocationStyle.radiusFillColor(Color.argb(10, 0, 0, 180));
        // 将自定义的 myLocationStyle 对象添加到地图上
        map.setMyLocationStyle(myLocationStyle);
    }

    /**
     * 记录轨迹
     * @param aMapLocation 位置
     */
    private void setTrack(AMapLocation aMapLocation) {
        //记录当前位置
        LatLng latLng = new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude());
        trackPoints.add(0, latLng);
        //画出运动轨迹
        trackPolyline = map.addPolyline(new PolylineOptions().color(Color.BLUE));
        trackPolyline.setPoints(trackPoints);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mapView.onDestroy();
        if(null != locationClient){
            locationClient.onDestroy();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
        if(null != locationClient){
            locationClient.onDestroy();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }
}
