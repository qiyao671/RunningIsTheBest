package com.qiyao.bysj.runningisthebest.module.run.viewmodel;

import android.app.Fragment;
import android.databinding.ObservableField;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.model.LatLng;
import com.amap.api.services.weather.LocalWeatherForecastResult;
import com.amap.api.services.weather.LocalWeatherLive;
import com.amap.api.services.weather.LocalWeatherLiveResult;
import com.amap.api.services.weather.WeatherSearch;
import com.amap.api.services.weather.WeatherSearchQuery;
import com.qiyao.bysj.baselibrary.common.utils.ConstUtils;
import com.qiyao.bysj.baselibrary.common.utils.StringUtils;
import com.qiyao.bysj.baselibrary.common.utils.TimeUtils;
import com.qiyao.bysj.baselibrary.common.utils.ToastUtils;
import com.qiyao.bysj.baselibrary.viewmodel.IViewModel;
import com.qiyao.bysj.runningisthebest.model.bean.RunBean;
import com.qiyao.bysj.runningisthebest.model.net.HttpMethods;
import com.trello.rxlifecycle.components.RxFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by qiyao on 2017/3/7.
 */

public class RunViewModel implements IViewModel, AMapLocationListener, WeatherSearch.OnWeatherSearchListener {
    private Fragment fragment;

    public ObservableField<Long> spendTime = new ObservableField<>(0L);
    public ObservableField<Double> distance = new ObservableField<>(0d);
    public ObservableField<Boolean> isRunning = new ObservableField<>(false);
    public ObservableField<String> city = new ObservableField<>();
    public ObservableField<String> temperature = new ObservableField<>("-");
    public ObservableField<String> weather = new ObservableField<>("-");
    public ObservableField<String> windDirection = new ObservableField<>("-");
    public ObservableField<String> windPower = new ObservableField<>("-");

    private List<List<LatLng>> tracks = new ArrayList<>();
    private List<LatLng> currentTrack = new ArrayList<>();
    private List<List<Double>> altitudeLists = new ArrayList<>();
    private List<Double> currentAltitudeList = new ArrayList<>();
    private RunBean run;

    private Subscription timeCounter;

    public RunViewModel(Fragment fragment) {
        this.fragment = fragment;
    }

    public List<LatLng> getCurrentTrack() {
        return currentTrack;
    }

    private void addPoint(AMapLocation aMapLocation) {
        LatLng latLng = new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude());
        currentTrack.add(latLng);
    }

    private void calculateDistance() {
        int size = currentTrack.size();
        if (size > 1) {
            double delta = AMapUtils.calculateLineDistance(currentTrack.get(size - 1), currentTrack.get(size - 2)) / 1000;
            Log.d("delta", delta + "");
            distance.set(distance.get() + delta);
        }
    }

    public void startRun() {
        run = new RunBean();
        run.setStartRunTime(TimeUtils.getNowTimeMills());
        onRunResume();
    }

    public void onRunResume() {
        isRunning.set(true);
        startTimeCounter();
    }

    public void onRunPause() {
        isRunning.set(false);
        timeCounter.unsubscribe();
        //添加这一段轨迹到轨迹列表中
        tracks.add(currentTrack);
        //开始一段新的轨迹
        currentTrack = new ArrayList<>();

        altitudeLists.add(currentAltitudeList);
        currentAltitudeList = new ArrayList<>();
    }

    private void startTimeCounter() {
        timeCounter = Observable.interval(1, 1, TimeUnit.SECONDS, Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> spendTime.set(spendTime.get() + ConstUtils.SEC));
    }

    public void stopRun() {
        run.setDistance(distance.get());
        run.setSpendTime(spendTime.get());
        uploadRunRecord(run);
    }

    private void uploadRunRecord(RunBean run) {
        // TODO: 2017/4/4 上传失败的时候
        HttpMethods.getInstance()
                .uploadRunRecord(run)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(ToastUtils::showShortToast, e -> ToastUtils.showLongToast(e.getMessage()));
    }

    private void getWeather() {
        Observable.interval(0, 5, TimeUnit.MINUTES)
                .compose(((RxFragment)fragment).bindToLifecycle())
                .subscribe(aLong -> {
                    //检索参数为城市和天气类型，实况天气为WEATHER_TYPE_LIVE、天气预报为WEATHER_TYPE_FORECAST
                    WeatherSearchQuery query = new WeatherSearchQuery(city.get(), WeatherSearchQuery.WEATHER_TYPE_LIVE);
                    WeatherSearch weathersearch = new WeatherSearch(fragment.getActivity());
                    weathersearch.setOnWeatherSearchListener(RunViewModel.this);
                    weathersearch.setQuery(query);
                    weathersearch.searchWeatherAsyn(); //异步搜索
                });
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (StringUtils.isEmpty(city.get()) && !StringUtils.isEmpty(aMapLocation.getCity())) {
            city.set(aMapLocation.getCity());
            getWeather();
        }
        if (aMapLocation.getAltitude() > 0) {
            currentAltitudeList.add(aMapLocation.getAltitude());
        }
        addPoint(aMapLocation);
        calculateDistance();
    }

    @Override
    public void onWeatherLiveSearched(LocalWeatherLiveResult localWeatherLiveResult, int i) {
        LocalWeatherLive liveResult = localWeatherLiveResult.getLiveResult();
        temperature.set(liveResult.getTemperature());
        weather.set(liveResult.getWeather());
        windPower.set(liveResult.getWindPower());
        windDirection.set(liveResult.getWindDirection());
    }

    @Override
    public void onWeatherForecastSearched(LocalWeatherForecastResult localWeatherForecastResult, int i) {
        //ignore
    }
}
