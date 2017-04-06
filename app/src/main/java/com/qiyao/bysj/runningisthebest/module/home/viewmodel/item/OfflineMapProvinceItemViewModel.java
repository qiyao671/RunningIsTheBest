package com.qiyao.bysj.runningisthebest.module.home.viewmodel.item;

import android.content.Context;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.view.View;

import com.amap.api.maps.offlinemap.OfflineMapCity;
import com.amap.api.maps.offlinemap.OfflineMapProvince;
import com.qiyao.bysj.baselibrary.component.OnItemClickListener;
import com.qiyao.bysj.baselibrary.viewmodel.itemviewmodel.IItemViewModel;

import java.util.List;

/**
 * Created by qiyao on 2017/4/5.
 */

public class OfflineMapProvinceItemViewModel implements IItemViewModel, View.OnClickListener {
    private Context context;
    public ObservableField<String> name = new ObservableField<>();
    public ObservableBoolean isFold = new ObservableBoolean(true);
    private OfflineMapProvince province;
    public static final String TYPE_PROVINCE = "PROVINCE";

    private OnItemClickListener onProvinceClickListener;

    public OfflineMapProvinceItemViewModel(Context context, OfflineMapProvince province) {
        this.context = context;
        this.name.set(province.getProvinceName());
        this.province = province;
    }

    @Override
    public String getItemViewType() {
        return TYPE_PROVINCE;
    }

    @Override
    public void onClick(View v) {
        isFold.set(!isFold.get());
        if (onProvinceClickListener != null) {
            onProvinceClickListener.onItemClick(this);
        }
    }

    public void setOnProvinceClickListener(OnItemClickListener onProvinceClickListener) {
        this.onProvinceClickListener = onProvinceClickListener;
    }

    public List<OfflineMapCity> getCities() {
        return province.getCityList();
    }

    public String getProvinceName() {
        return name.get();
    }
}
